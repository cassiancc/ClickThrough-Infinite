package cc.cassian.clickthrough.mixins;

import cc.cassian.clickthrough.ClickThrough;
import cc.cassian.clickthrough.config.ModConfig;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)

public class SneakingCancelsInteractionMixin {
    @Inject(method="shouldCancelInteraction", at=@At("HEAD"), cancellable = true)
    private void noCancelWhenDyeing(CallbackInfoReturnable cir) {
        if (!ModConfig.get().isActive) {
            return;
        }
        if (((Object) this) instanceof ClientPlayerEntity) {
            // System.out.println("on client");
            if (ClickThrough.isDyeOnSign) {
                // System.out.println("  not cancelling!");
                cir.setReturnValue(false);
                cir.cancel();
                ClickThrough.isDyeOnSign = false;
            }
        }
    }
}
