package cc.cassian.clickthrough;

import cc.cassian.clickthrough.config.ModConfig;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;

import static cc.cassian.clickthrough.ClickThrough.*;

public class ArchitecturyImpl {
    public static void load() {
        KeyMappingRegistry.register(onoff);
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            while (onoff.wasPressed()) {
                if (ModConfig.get().isActive) {
                    setInActive();
                } else {
                    setActive();
                }

            }
        });
    }
}
