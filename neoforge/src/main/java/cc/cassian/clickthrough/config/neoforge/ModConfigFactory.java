package cc.cassian.clickthrough.config.neoforge;


import cc.cassian.clickthrough.config.ClothConfigFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;

public class ModConfigFactory {

    public static @NotNull Screen createScreen(@NotNull MinecraftClient arg, @NotNull Screen parent) {
        return ClothConfigFactory.create(parent);
    }
}