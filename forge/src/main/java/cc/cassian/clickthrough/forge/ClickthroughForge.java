package cc.cassian.clickthrough.forge;

import cc.cassian.clickthrough.ClickThrough;
import cc.cassian.clickthrough.config.forge.ModConfigFactory;
import net.minecraftforge.client.ConfigScreenHandler;
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
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory(ModConfigFactory::createScreen));
    }


}
