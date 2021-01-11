package user11681.numerusromani.mixin;

import java.util.List;
import java.util.Set;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class NumerusRomaniPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        if (mixinClassName.endsWith("LanguageDummyMixin")) {
            for (MethodNode method : targetClass.methods) {
                if (method.name.equals("method_4679") || method.name.equals("get")) {
                    InsnList instructions = new InsnList();
                    LabelNode end = new LabelNode();

                    instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
                    instructions.add(new LdcInsnNode("enchantment\\.level\\.\\d+"));
                    instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String", "matches", "(Ljava/lang/String;)Z", false));
                    instructions.add(new JumpInsnNode(Opcodes.IFEQ, end));
                    instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
                    instructions.add(new LdcInsnNode("\\D"));
                    instructions.add(new LdcInsnNode(""));
                    instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String", "replaceAll", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false));
                    instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Integer", "parseInt", "(Ljava/lang/String;)I"));
                    instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "user11681/numerusromani/RomanNumerals", "fromDecimal", "(I)Ljava/lang/String;"));
                    instructions.add(new InsnNode(Opcodes.ARETURN));
                    instructions.add(end);

                    method.instructions.add(instructions);
                }
            }
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
