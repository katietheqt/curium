package me.katie.curium.impl.asm.handlers;

import me.katie.curium.impl.asm.CuriumMixinPlugin;
import me.katie.curium.impl.asm.Transformer;
import me.katie.curium.impl.asm.annotations.CustomTransformer;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.util.Annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the {@link CustomTransformer} annotation.
 */
public class CustomTransformerHandler implements Transformer {
    private static List<Transformer> getTransformers(ClassNode mixin) {
        // Check for annotation.
        AnnotationNode annotation = Annotations.getInvisible(mixin, CustomTransformer.class);

        if (annotation == null) {
            return null;
        }

        // Fetch transformers.
        List<Transformer> transformers = new ArrayList<>();
        List<Type> transformerTypes = Annotations.getValue(annotation);

        for (Type transformerType: transformerTypes) {
            assert transformerType.getSort() == Type.OBJECT;
            String className = transformerType.getInternalName().replace('/', '.');

            // Load transformer class.
            Class<? extends Transformer> transformerClass;

            try {
                Class<?> maybeTransformerClass = Class.forName(className, true, CuriumMixinPlugin.class.getClassLoader());
                transformerClass = maybeTransformerClass.asSubclass(Transformer.class);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("@ClassTransformers transformer " + className + " could not be loaded", e);
            } catch (ClassCastException e) {
                throw new IllegalStateException("@ClassTransformers transformer " + className + " does not extend Transformer", e);
            }

            // Locate constructor.
            Constructor<? extends Transformer> ctor;

            try {
                ctor = transformerClass.getConstructor();
                ctor.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("@ClassTransformers transformer " + className + " is missing no-args constructor", e);
            }

            // Construct instance.
            try {
                Transformer transformer = ctor.newInstance();
                transformers.add(transformer);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("@ClassTransformers transformer constructor threw", e);
            } catch (InstantiationException e) {
                throw new IllegalStateException("@ClassTransformers transformer could not be constructed", e);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("illegal access to @ClassTransformers transformer constructor", e);
            }
        }

        return transformers;
    }

    @Override
    public void preMixinTransform(ClassNode target, ClassNode mixin) {
        List<Transformer> transformers = getTransformers(mixin);

        if (transformers == null) {
            return;
        }

        // Apply all located transformers.
        for (Transformer transformer: transformers) {
            transformer.preMixinTransform(target, mixin);
        }
    }

    @Override
    public void postMixinTransform(ClassNode target, ClassNode mixin) {
        List<Transformer> transformers = getTransformers(mixin);

        if (transformers == null) {
            return;
        }

        // Apply all located transformers.
        for (Transformer transformer: transformers) {
            transformer.postMixinTransform(target, mixin);
        }
    }
}
