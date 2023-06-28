package me.katie.curium.impl.asm.mixin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Removes methods or fields from a class entirely.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Erase {
    String[] methods() default {};
    String[] fields() default {};
}
