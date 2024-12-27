package cc.cassian.clickthrough.helpers.fabric;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;

public class ModHelpersImpl {
    public static boolean clothConfigInstalled() {
        return FabricLoader.getInstance().isModLoaded("cloth-config");
    }

    public static boolean isTaggedAsContainer(BlockState state) {
        var stack = state.getBlock().asItem().getDefaultStack();
        return state.isIn(ConventionalBlockTags.CHESTS) || state.isIn(ConventionalBlockTags.WOODEN_BARRELS)
                || stack.isIn(ConventionalItemTags.CHESTS)  || stack.isIn(ConventionalItemTags.WOODEN_BARRELS)
                || state.isIn(BlockTags.GUARDED_BY_PIGLINS);
    }


    public static boolean architecturyInstalled() {
        return FabricLoader.getInstance().isModLoaded("architectury");
    }
}
