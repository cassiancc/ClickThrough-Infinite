package cc.cassian.clickthrough.config.fabric;


import cc.cassian.clickthrough.config.ClothConfigFactory;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.minecraft.client.gui.screen.Screen;

public class ModConfigFactory implements ConfigScreenFactory<Screen> {

    @Override
    public Screen create(Screen parent) {
        return ClothConfigFactory.create(parent);
    }
}