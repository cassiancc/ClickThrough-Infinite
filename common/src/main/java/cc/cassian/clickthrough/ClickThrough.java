package cc.cassian.clickthrough;



import cc.cassian.clickthrough.config.ModConfig;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraft.text.Text;

public class ClickThrough
{
    static public final String MOD_ID = "clickthrough";
    static public final String MOD_NAME = "ClickThrough";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);



    public static void init() {
        ModConfig.load();
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
        LOGGER.info("Successfully initialized ClickThrough Plus. Signs are now out of the way!");
    }

    static public boolean isDyeOnSign = false;



    // A key mapping with keyboard as the default
    public static final KeyBinding onoff = new KeyBinding(
            "key.clickthrough.toggle", // The translation key of the name shown in the Controls screen
            InputUtil.Type.KEYSYM, // This key mapping is for Keyboards by default
            InputUtil.GLFW_KEY_F9, // The default keycode
            "key.categories.clickthrough" // The category translation key used to categorize in the Controls screen
    );



    public static void setActive() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            player.sendMessage(new TranslatableText("clickthrough.msg.active"), false);
        }
        ModConfig.get().isActive = true;
    }

    public static void setInActive() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            player.sendMessage(new TranslatableText("clickthrough.msg.inactive"), false);
        }
        ModConfig.get().isActive = false;
    }



}
