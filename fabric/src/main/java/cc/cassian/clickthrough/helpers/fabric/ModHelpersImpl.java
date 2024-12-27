package cc.cassian.clickthrough.helpers.fabric;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;

public class ModHelpersImpl {
    public static boolean clothConfigInstalled() {
        return FabricLoader.getInstance().isModLoaded("cloth-config");
    }

    public static boolean isTaggedAsContainer(BlockState state) {
        return state.isIn(ConventionalBlockTags.CHESTS) || state.isIn(ConventionalBlockTags.BARRELS); 
    }
    
    public static boolean architecturyInstalled() {
        return FabricLoader.getInstance().isModLoaded("architectury");
    }
}
