package me.katie.curium.impl.asm.handlers;

import me.katie.curium.impl.asm.Transformer;
import me.katie.curium.impl.asm.annotations.OverwriteClass;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.service.MixinService;
import org.spongepowered.asm.util.Annotations;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles the {@link OverwriteClass} annotation.
 */
public class OverwriteClassHandler implements Transformer {
    @Override
    public void preMixinTransform(ClassNode target, ClassNode mixin) {
        // Check for annotation.
        AnnotationNode annotation = Annotations.getInvisible(mixin, OverwriteClass.class);

        if (annotation == null) {
            return;
        }

        // Resolve replacement class node.
        Type replacementType = Annotations.getValue(annotation);
        assert replacementType.getSort() == Type.OBJECT;

        ClassNode replacement;

        try {
            replacement = MixinService.getService().getBytecodeProvider().getClassNode(replacementType.getInternalName(), true);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException("failed to read @OverwriteClass target node", e);
        }

        // Remap class name.
        ClassNode remapped = new ClassNode();
        ClassRemapper remapper = new ClassRemapper(remapped, new Remapper() {
            @Override
            public String mapType(String internalName) {
                if (internalName.equals(replacement.name)) {
                    return target.name;
                }

                return internalName;
            }

            @Override
            public String map(String internalName) {
                return this.mapType(internalName);
            }
        });

        replacement.accept(remapper);

        // Sanity check that this class has the same non-private fields and methods.
        boolean verify = Annotations.getValue(annotation, "verify", Boolean.TRUE);

        if (verify) {
            Set<String> replacerMethods = remapped.methods.stream()
                    .map(m -> m.name + m.desc)
                    .collect(Collectors.toSet());

            for (MethodNode method : target.methods) {
                if (!isSafeToRemove(method.access, method.name) && !replacerMethods.contains(method.name + method.desc)) {
                    throw new IllegalStateException("replacer class for " + target.name + " (" + replacement.name + ") missing method " + method.name + method.desc);
                }
            }

            Set<String> replacerFields = remapped.fields.stream()
                    .map(m -> m.name + ";" + m.desc)
                    .collect(Collectors.toSet());

            for (FieldNode field : target.fields) {
                if (!isSafeToRemove(field.access, field.name) && !replacerFields.contains(field.name + ";" + field.desc)) {
                    throw new IllegalStateException("replacer class for " + target.name + " (" + replacement.name + ") missing field " + field.name + field.desc);
                }
            }
        }

        // Overwrite target.
        target.version = remapped.version;
        target.access = remapped.access;
        target.signature = remapped.signature;
        target.superName = remapped.superName;
        target.interfaces = remapped.interfaces;
        target.sourceFile = remapped.sourceFile;
        target.sourceDebug = remapped.sourceDebug;
        target.visibleAnnotations = remapped.visibleAnnotations;
        target.invisibleAnnotations = remapped.invisibleAnnotations;
        target.visibleTypeAnnotations = remapped.visibleTypeAnnotations;
        target.invisibleTypeAnnotations = remapped.invisibleTypeAnnotations;
        target.attrs = remapped.attrs;
        target.recordComponents = remapped.recordComponents;
        target.fields = remapped.fields;
        target.methods = remapped.methods;
    }

    @Override
    public void postMixinTransform(ClassNode target, ClassNode mixin) {

    }

    private static boolean isSafeToRemove(int access, String name) {
        return (access & Opcodes.ACC_PRIVATE) != 0 || // private
                (access & Opcodes.ACC_PROTECTED) != 0 && (access & Opcodes.ACC_STATIC) != 0 || // protected and static
                name.equals("<clinit>");
    }
}
