package me.katie.curium.impl.asm.mixin.transformers;

import me.katie.curium.impl.asm.util.ASMUtil;
import me.katie.curium.impl.asm.CuriumASM;
import me.katie.curium.impl.asm.mixin.ClassTransformer;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;
import me.katie.curium.impl.asm.mixin.helper.StubInternalHelper;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.util.Annotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Handles the {@link StubClass} annotation.
 */
public class StubClassHandler implements ClassTransformer {
    @Override
    public void preMixinTransform(ClassNode target, ClassNode mixin) {
        // Check annotation.
        AnnotationNode annotation = Annotations.getInvisible(mixin, StubClass.class);

        if (annotation == null) {
            return;
        }

        boolean isThrowing = Annotations.getValue(annotation, "isThrowing", Boolean.FALSE);
        List<String> skippedMethods = Annotations.getValue(annotation, "skip", new ArrayList<>());

        // Overwrite any void methods in the target with empty bodies.
        List<MethodNode> newMethods = new ArrayList<>();

        for (Iterator<MethodNode> it = target.methods.iterator(); it.hasNext();) {
            MethodNode method = it.next();

            Type desc = Type.getMethodType(method.desc);
            boolean doesApply = (!method.name.equals("<init>") || isThrowing) &&
                    (isThrowing || desc.getReturnType() == Type.VOID_TYPE) &&
                    !skippedMethods.contains(method.name + method.desc) &&
                    !skippedMethods.contains(method.name) &&
                    (method.access & (Opcodes.ACC_NATIVE | Opcodes.ACC_ABSTRACT)) == 0;

            if (doesApply) {
                it.remove();

                MethodNode fakeMethod = new MethodNode();
                fakeMethod.name = method.name;
                fakeMethod.desc = method.desc;
                fakeMethod.access = method.access;
                fakeMethod.invisibleAnnotations = method.invisibleAnnotations;
                fakeMethod.visibleAnnotations = method.visibleAnnotations;
                fakeMethod.instructions = new InsnList();

                if (isThrowing && !method.name.equals("<clinit>")) {
                    fakeMethod.instructions.add(new MethodInsnNode(
                            Opcodes.INVOKESTATIC,
                            Type.getInternalName(StubInternalHelper.class),
                            "stubbed",
                            "()V"
                    ));

                    fakeMethod.instructions.add(ASMUtil.generateFailedAssert("StubInternalHelper#stubbed didn't throw"));
                } else {
                    fakeMethod.instructions.add(new InsnNode(Opcodes.RETURN));
                }

                newMethods.add(fakeMethod);
            }
        }

        target.methods.addAll(newMethods);
        CuriumASM.LOGGER.debug("Stripped {} void method bodies from {}", newMethods.size(), target.name);
    }

    @Override
    public void postMixinTransform(ClassNode target, ClassNode mixin) {

    }
}
