package me.katie.curium.impl.asm;

import org.objectweb.asm.tree.ClassNode;

/**
 * Interface implemented by class transformers as part of the ASM pipeline.
 */
public interface Transformer {
    /**
     * Transformation executed before mixins are applied to the target class.
     *
     * @param target the target class (changes are applied)
     * @param mixin a <b>copy of</b> the mixin (changes to this class are <i>not</i> applied)
     */
    void preMixinTransform(ClassNode target, ClassNode mixin);

    /**
     * Transformation executed after mixins are applied to the target class.
     *
     * @param target the target class (changes are applied)
     * @param mixin a <b>copy of</b> the mixin (changes to this class are <i>not</i> applied)
     */
    void postMixinTransform(ClassNode target, ClassNode mixin);
}
