package me.katie.curium.impl.asm.mixin.transformers;

import me.katie.curium.impl.asm.mixin.ClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.Iterator;

/**
 * Transformer that replaces {@code LDC} instructions referencing LWJGL classes with {@code ACONST_NULL} instructions.
 */
public class StripLwjglLdcTransformer implements ClassTransformer {
    @Override
    public void preMixinTransform(ClassNode target, ClassNode mixin) {

    }

    @Override
    public void postMixinTransform(ClassNode target, ClassNode mixin) {
        for (MethodNode method: target.methods) {
            for (Iterator<AbstractInsnNode> it = method.instructions.iterator(); it.hasNext();) {
                AbstractInsnNode insn = it.next();

                if (insn instanceof LdcInsnNode ldcInsn) {
                    if (ldcInsn.cst instanceof Type type) {
                        if (type.getInternalName().contains("lwjgl")) {
                            method.instructions.insertBefore(insn, new InsnNode(Opcodes.ACONST_NULL));
                            it.remove();
                        }
                    }
                }
            }
        }
    }
}
