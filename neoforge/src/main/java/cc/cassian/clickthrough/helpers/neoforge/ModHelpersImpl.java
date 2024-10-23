package cc.cassian.clickthrough.helpers.neoforge;

import net.neoforged.fml.ModList;

public class ModHelpersImpl {
    public static boolean clothConfigInstalled() {
        return ModList.get().isLoaded("cloth_config");
    }
    public static boolean architecturyInstalled() {
        return ModList.get().isLoaded("architectury");
    }

}
