package cc.cassian.clickthrough.helpers.forge;


import net.minecraftforge.fml.ModList;

public class ModHelpersImpl {
    public static boolean clothConfigInstalled() {
        return ModList.get().isLoaded("cloth_config");
    }

    public static boolean isTaggedAsContainer(BlockState state) {
        var stack = state.getBlock().asItem().getDefaultStack();
        return state.isIn(Tags.Blocks.CHESTS) || state.isIn(Tags.Blocks.BARRELS)
                || stack.isIn(Tags.Items.CHESTS)  || stack.isIn(Tags.Items.BARRELS)
                || state.isIn(BlockTags.GUARDED_BY_PIGLINS);
    }
    public static boolean architecturyInstalled() {
        return ModList.get().isLoaded("architectury");
    }

}
