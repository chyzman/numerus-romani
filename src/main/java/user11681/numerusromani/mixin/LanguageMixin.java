package user11681.numerusromani.mixin;

import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.numerusromani.RomanNumerals;

/**
 * @see Language
 */
@Mixin(targets = "net.minecraft.util.Language$1")
abstract class LanguageMixin {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    public void get(String key, CallbackInfoReturnable<String> info) {
        if (key.matches("enchantment\\.level\\.\\d+")) {
            info.setReturnValue(RomanNumerals.fromDecimal(Integer.parseInt(key.replaceAll("\\D", ""))));
        }
    }
}
