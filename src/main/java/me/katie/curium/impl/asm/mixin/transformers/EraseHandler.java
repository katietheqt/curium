package me.katie.curium.impl.asm.mixin.transformers;

import me.katie.curium.impl.asm.mixin.ClassTransformer;
import me.katie.curium.impl.asm.mixin.annotations.Erase;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.util.Annotations;

import java.util.List;

/**
 * Handles the {@link Erase} annotation.
 */
public class EraseHandler implements ClassTransformer {
    @Override
    public void preMixinTransform(ClassNode target, ClassNode mixin) {
        // Fetch annotation.
        AnnotationNode annotation = Annotations.getInvisible(mixin, Erase.class);

        if (annotation == null) {
            return;
        }

        // Remove methods.
        List<String> targetMethods = Annotations.getValue(annotation, "methods");

        if (targetMethods != null && !targetMethods.isEmpty()) {
            target.methods.removeIf(m -> targetMethods.contains(m.name + m.desc) || targetMethods.contains(m.name));
        }

        // Remove fields.
        List<String> targetFields = Annotations.getValue(annotation, "fields");

        if (targetFields != null && !targetFields.isEmpty()) {
            target.fields.removeIf(f -> targetFields.contains(f.name));
        }
    }

    @Override
    public void postMixinTransform(ClassNode target, ClassNode mixin) {

    }
}
