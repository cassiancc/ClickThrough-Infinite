package cc.cassian.clickthrough.neoforge;

import cc.cassian.clickthrough.ClickThrough;
import cc.cassian.clickthrough.config.neoforge.ModConfigFactory;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import static cc.cassian.clickthrough.helpers.ModHelpers.clothConfigInstalled;


@Mod(ClickThrough.MOD_ID)
public final class ClickthroughNeoForge {
    public ClickthroughNeoForge() {
        // Run our common setup.
        ClickThrough.init();
        registerModsPage();


    }

    //Integrate Cloth Config screen (if mod present) with NeoForge mod menu.
    public void registerModsPage() {
        if (clothConfigInstalled()) ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, ModConfigFactory::new);
    }

}
