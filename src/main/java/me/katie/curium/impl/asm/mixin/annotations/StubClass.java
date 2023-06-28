package me.katie.curium.impl.asm.mixin.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that can be applied to a mixin to generate automatic method stubs. This occurs before the contents of the
 * mixin are applied, so mixin overwrites will still apply properly.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface StubClass {
    String[] skip() default {};

    /**
     * If set to {@literal true}, every method will be stubbed with an exception throw. Otherwise, only
     * {@literal void}-returning methods will be stubbed (to no-ops).
     */
    boolean isThrowing() default false;
}
