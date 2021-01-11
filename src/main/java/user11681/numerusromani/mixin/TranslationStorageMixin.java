package user11681.numerusromani.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.numerusromani.RomanNumerals;

@Environment(EnvType.CLIENT)
@Mixin(TranslationStorage.class)
abstract class TranslationStorageMixin {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    public void get(String key, CallbackInfoReturnable<String> info) {
        if (key.matches("enchantment\\.level\\.\\d+")) {
            info.setReturnValue(RomanNumerals.fromDecimal(Integer.parseInt(key.replaceAll("\\D", ""))));
        }
    }
}
