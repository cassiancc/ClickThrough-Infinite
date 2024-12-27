package cc.cassian.clickthrough.helpers;

import cc.cassian.clickthrough.config.ModConfig;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Unique;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import static cc.cassian.clickthrough.ClickThrough.MOD_ID;


public class ModHelpers {
    //Shorthand for config.
    public static ModConfig config = ModConfig.get();



    //Check if Cloth Config is installed and its configuration can be used.
    @ExpectPlatform
    public static boolean clothConfigInstalled() {
        throw new AssertionError();
    }

    public static String getSignRowText(SignBlockEntity sign, int row) {
        StringBuilder builder =  new StringBuilder();
        return sign.getFrontText().getMessage(row, true).getString();
    }


    //Automatically generate translation keys for config options.
    public static Text fieldName(Field field) {
        return Text.translatable("clickthrough.config." + field.getName());
    }


    //Get the current value of a config field.
    @SuppressWarnings("unchecked")
    public static <T> T fieldGet(Object instance, Field field) {
        try {
            return (T) field.get(instance);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    //Set a config field.
    public static <T> Consumer<T> fieldSetter(Object instance, Field field) {
        return t -> {
            try {
                field.set(instance, t);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @ExpectPlatform
    public static boolean isTaggedAsContainer(BlockState state) {
        throw new AssertionError();
    }

    @Unique
    public static boolean isClickableBlockAt(BlockPos pos, ClientWorld world) {
        if (!config.onlycontainers) {
            return true;
        }
        BlockEntity entity = world.getBlockEntity(pos);
        var state = world.getBlockState(pos);
        if (entity instanceof LockableContainerBlockEntity)
            return true;
        return (ModHelpers.isTaggedAsContainer(state));
    }
}
