package cc.cassian.clickthrough.helpers.neoforge;

import net.minecraft.block.BlockState;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.common.Tags;

public class ModHelpersImpl {
    public static boolean clothConfigInstalled() {
        return ModList.get().isLoaded("cloth_config");
    }

    public static boolean isTaggedAsContainer(BlockState state) {
        return state.isIn(Tags.Blocks.CHESTS) || state.isIn(Tags.Blocks.BARRELS);
    }
}
