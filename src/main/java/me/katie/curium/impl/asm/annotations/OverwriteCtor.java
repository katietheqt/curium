package me.katie.curium.impl.asm.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation applied to a method within a mixin to indicate that the method should overwrite the body of a constructor
 * within the target.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface OverwriteCtor {
    /**
     * The target descriptor of the superclass constructor to invoke.
     */
    String superDesc() default  "()V";
}
