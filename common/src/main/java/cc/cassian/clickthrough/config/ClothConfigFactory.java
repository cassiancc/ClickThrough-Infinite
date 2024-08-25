package cc.cassian.clickthrough.config;


import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import static cc.cassian.clickthrough.helpers.ModHelpers.*;


public class ClothConfigFactory {

    private static final ModConfig DEFAULT_VALUES = new ModConfig();

    public static Screen create(Screen parent) {
        final var builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("key.categories.clickthrough"));

        final var entryBuilder = builder.entryBuilder();
        final var configInstance = ModConfig.get();
        final var generalCategory = builder.getOrCreateCategory(Text.translatable("key.categories.clickthrough"));



        for (var field : ModConfig.class.getFields()) {
            if (field.getType() == boolean.class) {
                generalCategory.addEntry(entryBuilder.startBooleanToggle(fieldName(field), fieldGet(configInstance, field))
                        .setSaveConsumer(fieldSetter(configInstance, field))
                        .setDefaultValue((boolean) fieldGet(DEFAULT_VALUES, field)).build());

            }
            else if (field.getType() == String.class) {
                generalCategory.addEntry(entryBuilder.startStrField(fieldName(field), fieldGet(configInstance, field))
                        .setSaveConsumer(fieldSetter(configInstance, field))
                        .setDefaultValue((String) fieldGet(DEFAULT_VALUES, field)).build());
            }
            else if (field.getType() == int.class) {
                generalCategory.addEntry(entryBuilder.startIntField(fieldName(field), fieldGet(configInstance, field))
                        .setSaveConsumer(fieldSetter(configInstance, field))
                        .setDefaultValue((int) fieldGet(DEFAULT_VALUES, field)).build());
            }
        }
        builder.setSavingRunnable(ModConfig::save);
        return builder.build();
    }
}