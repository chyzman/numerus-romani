package user11681.numerusromani.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.numerusromani.RomanNumerals;

@Environment(EnvType.CLIENT)
@Mixin(I18n.class)
abstract class I18nMixin {
    @Inject(method = "translate", at = @At("HEAD"), cancellable = true)
    private static void translate(String key, Object[] args, CallbackInfoReturnable<String> info) {
        if (key.matches("enchantment\\.level\\.\\d+")) {
            info.setReturnValue(RomanNumerals.fromDecimal(Integer.parseInt(key.replaceAll("\\D", ""))));
        }
    }
}
