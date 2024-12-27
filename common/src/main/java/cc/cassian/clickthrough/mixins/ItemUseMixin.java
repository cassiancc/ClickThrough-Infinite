package cc.cassian.clickthrough.mixins;

import static cc.cassian.clickthrough.helpers.ModHelpers.config;
import static cc.cassian.clickthrough.helpers.ModHelpers.isClickableBlockAt;

import cc.cassian.clickthrough.ClickThrough;
import cc.cassian.clickthrough.helpers.ModHelpers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBannerBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(MinecraftClient.class)
public class ItemUseMixin {

    @Shadow public HitResult crosshairTarget;
    @Shadow public ClientPlayerEntity player;
    @Shadow public ClientWorld world;

    @Inject(method="doItemUse", at=@At(value="INVOKE",
            target="Lnet/minecraft/client/network/ClientPlayerEntity;getStackInHand(Lnet/minecraft/util/Hand;)Lnet/minecraft/item/ItemStack;"))
    public void switchCrosshairTargetItemUse(CallbackInfo ci) {
        this.clickthrough$switchCrosshairTarget();
    }

    @Unique
    private void clickthrough$switchCrosshairTarget() {
        if (!config.isActive) {
            return;
        }
        ClickThrough.isDyeOnSign = false;
        if (crosshairTarget != null) {
            if (crosshairTarget.getType() == HitResult.Type.ENTITY && ((EntityHitResult) crosshairTarget).getEntity() instanceof ItemFrameEntity itemFrame) {
                // copied from AbstractDecorationEntity#canStayAttached
                BlockPos attachedPos = itemFrame.getDecorationBlockPos().offset(itemFrame.getHorizontalFacing().getOpposite());
                // System.out.println("Item frame attached to "+state.getBlock().getTranslationKey()+" at "+blockPos.toShortString());
                if (!player.isSneaking() && isClickableBlockAt(attachedPos, world)) {
                    this.crosshairTarget = new BlockHitResult(crosshairTarget.getPos(), itemFrame.getHorizontalFacing(), attachedPos, false);
                }
            }
            else if (crosshairTarget.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = ((BlockHitResult)crosshairTarget).getBlockPos();
                BlockState state = world.getBlockState(blockPos);
                Block block = state.getBlock();
                if (block instanceof WallSignBlock) {
                    BlockPos attachedPos = blockPos.offset(state.get(WallSignBlock.FACING).getOpposite());
                    if (!isClickableBlockAt(attachedPos, world)) {
                        return;
                    }
                    BlockEntity entity = world.getBlockEntity(blockPos);
                    if (!(entity instanceof SignBlockEntity)) {
                        return;
                    }

                    Item item = player.getStackInHand(Hand.MAIN_HAND).getItem();
                    if (item instanceof DyeItem || item == Items.GLOW_INK_SAC) {
                        if (config.sneaktodye) {
                            ClickThrough.isDyeOnSign = true;                // prevent sneaking from cancelling the interaction
                            if (!player.isSneaking()) {
                                this.crosshairTarget = new BlockHitResult(crosshairTarget.getPos(), ((BlockHitResult) crosshairTarget).getSide(), attachedPos, false);
                            }
                        }
                    } else {
                        if (!player.isSneaking()) {
                            this.crosshairTarget = new BlockHitResult(crosshairTarget.getPos(), ((BlockHitResult)crosshairTarget).getSide(), attachedPos, false);
                        }
                    }
                } else if (block instanceof WallBannerBlock) {
                    BlockPos attachedPos = blockPos.offset(state.get(WallBannerBlock.FACING).getOpposite());
                    if (ModHelpers.isClickableBlockAt(attachedPos, world)) {
                        this.crosshairTarget = new BlockHitResult(crosshairTarget.getPos(), ((BlockHitResult)crosshairTarget).getSide(), attachedPos, false);
                    }
                }
            }
        }
    }


}