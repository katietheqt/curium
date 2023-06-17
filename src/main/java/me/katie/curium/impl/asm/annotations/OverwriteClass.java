package me.katie.curium.impl.asm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Overwrites the target class of this mixin entirely with a different class. Be careful - if this mixin contains any
 * injectors they will apply against the replaced target.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface OverwriteClass {
    /**
     * The class to overwrite with.
     */
    Class<?> value();

    /**
     * Whether to verify methods are matching.
     */
    boolean verify() default true;
}
