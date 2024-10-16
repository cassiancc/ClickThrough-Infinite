package cc.cassian.clickthrough.forge;

import cc.cassian.clickthrough.ClickThrough;
import cc.cassian.clickthrough.config.forge.ModConfigFactory;
import cc.cassian.clickthrough.helpers.ModHelpers;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;


@Mod(ClickThrough.MOD_ID)
public final class ClickthroughForge {
    public ClickthroughForge() {
        // Run our common setup.
        ClickThrough.init();
        registerModsPage();


    }

    //Integrate Cloth Config screen (if mod present) with Forge mod menu.
    public void registerModsPage() {
        if (ModHelpers.clothConfigInstalled()) {
            ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory(ModConfigFactory::createScreen));
        }
    }


}
