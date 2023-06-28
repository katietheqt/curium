package me.katie.curium.impl.asm.mixin.annotations;

import me.katie.curium.impl.asm.mixin.ClassTransformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation applied to a mixin class that allows arbitrary class transformers to be applied to the target class.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface CustomTransformer {
    /**
     * List of transformers to apply.
     */
    Class<? extends ClassTransformer>[] value();
}
