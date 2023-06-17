package me.katie.curium.impl.asm.handlers;

import me.katie.curium.impl.asm.CuriumMixinPlugin;
import me.katie.curium.impl.asm.helper.CtorOverwriteHelper;
import me.katie.curium.impl.asm.Transformer;
import me.katie.curium.impl.asm.annotations.OverwriteCtor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.util.Annotations;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Handles the {@link OverwriteCtor} annotation.
 */
public class OverwriteCtorHandler implements Transformer {
    private static final String HELPER_INTERNAL = Type.getInternalName(CtorOverwriteHelper.class);
    private static final String HELPER_SUPER_METHOD = "super_";

    @Override
    public void preMixinTransform(ClassNode target, ClassNode mixin) {

    }

    @Override
    public void postMixinTransform(ClassNode target, ClassNode mixin) {
        // Relocate overwritten constructors from target class.
        for (MethodNode method: new ArrayList<>(target.methods)) {
            AnnotationNode annotation = Annotations.getInvisible(method, OverwriteCtor.class);

            if (annotation == null) {
                continue;
            }


            // Find the super call we're targeting.
            String targetDescString = Annotations.getValue(annotation, "superDesc", "()V");
            Type targetDesc = Type.getMethodType(targetDescString);
            assert targetDesc.getReturnType() == Type.VOID_TYPE;

            // Overwrite super call.
            int foundCtorCount = 0;

            for (Iterator<AbstractInsnNode> it = method.instructions.iterator(); it.hasNext(); ) {
                AbstractInsnNode insn = it.next();

                if (insn instanceof MethodInsnNode methodInsn) {
                    if (!methodInsn.owner.equals(HELPER_INTERNAL) || !methodInsn.name.equals(HELPER_SUPER_METHOD)) {
                        continue;
                    }

                    // Inject bytecode to generate correct arguments.
                    InsnList insns = new InsnList();

                    // Load uninitialized `this`.
                    insns.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    insns.add(new InsnNode(Opcodes.SWAP));

                    // Unwrap varargs array.
                    Type[] argTypes = targetDesc.getArgumentTypes();

                    for (int i = 0; i < argTypes.length; i++) {
                        Type argType = argTypes[i];
                        int opcode = argType.getOpcode(Opcodes.IALOAD);

                        // - Duplicate varargs array on stack.
                        // - Load constant index onto stack.
                        // - Load from array.
                        // - Cast to target class.
                        // - Then swap array and item for next loop.
                        insns.add(new InsnNode(Opcodes.DUP));
                        insns.add(new LdcInsnNode(i));
                        insns.add(new InsnNode(opcode));

                        if (argType.getSort() == Type.OBJECT || argType.getSort() == Type.ARRAY) {
                            insns.add(new TypeInsnNode(Opcodes.CHECKCAST, argType.getInternalName()));
                        }

                        insns.add(new InsnNode(Opcodes.SWAP));
                    }

                    // Pop the array.
                    insns.add(new InsnNode(Opcodes.POP));

                    // Replace INVOKESTATIC with INVOKESPECIAL call.
                    insns.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, target.superName, "<init>", targetDescString));
                    method.instructions.insertBefore(insn, insns);
                    it.remove();

                    foundCtorCount++;
                }
            }

            if (foundCtorCount == 0) {
                if (targetDescString.equals("()V")) {
                    // Can omit call in mixin if target descriptor is blank.
                    InsnList insns = new InsnList();
                    insns.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    insns.add(new MethodInsnNode(
                            Opcodes.INVOKESPECIAL,
                            target.superName,
                            "<init>",
                            "()V"
                    ));

                    method.instructions.insert(insns);
                } else {
                    throw new IllegalStateException(String.format(
                            "missing super call in ctor overwrite for %s (from %s;%s%s)",
                            target.name, mixin.name, method.name, method.desc
                    ));
                }
            } else if (foundCtorCount == 1) {
                CuriumMixinPlugin.LOGGER.debug("Overwrote constructor in {} with {};{}{}", target.name, mixin.name, method.name, method.desc);
            } else {
                throw new IllegalStateException(String.format(
                        "found multiple super constructor calls in ctor overwrite for %s (from %s;%s%s)",
                        target.name, mixin.name, method.name, method.desc
                ));
            }

            // Remove matching constructor from the target class.
            boolean foundTarget = false;

            for (Iterator<MethodNode> it = target.methods.iterator(); it.hasNext();) {
                MethodNode targetCtor = it.next();

                if (targetCtor.name.equals("<init>") && targetCtor.desc.equals(method.desc)) {
                    // Found the overwritten ctor, match the access.
                    foundTarget = true;
                    it.remove();

                    method.access = targetCtor.access;
                }
            }

            if (!foundTarget) {
                throw new IllegalStateException("tried to overwrite non-existent constructor in " + target.name +
                        " (mixin " + mixin.name + ")");
            }

            method.name = "<init>";
        }
    }
}
