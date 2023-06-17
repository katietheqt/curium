package me.katie.curium.input;

/**
 * Ergonomic wrapper for setting input modifier bit flags.
 */
public class InputModifiers {
    /** If this flag is set one or more Shift keys are held down. */
    public static final int SHIFT = 0x1;

    /** If this flag is set one or more Control keys are held down. */
    public static final int CONTROL = 0x2;

    /** If this flag is set one or more Alt keys are held down. */
    public static final int ALT = 0x4;

    /** If this flag is set one or more Super keys are held down. */
    public static final int SUPER = 0x8;

    /** If this flag is set Caps Lock is enabled. */
    public static final int CAPS_LOCK = 0x10;

    /** If this flag is set Num Lock is enabled. */
    public static final int NUM_LOCK = 0x20;

    private final int flags;

    private InputModifiers(int flags) {
        this.flags = flags;
    }

    /**
     * @return the raw flags of this {@link InputModifiers} instance (as a bit field).
     */
    public int toRaw() {
        return this.flags;
    }

    /**
     * @return an instance of {@link InputModifiers} with the given flags as the bits.
     */
    public static InputModifiers fromRawFlags(int flags) {
        return new InputModifiers(flags);
    }

    /**
     * @return an instance of {@link InputModifiers} with no flags set.
     */
    public static InputModifiers none() {
        return new InputModifiers(0);
    }

    /**
     * @return an instance of {@link InputModifiers} with the {@link InputModifiers#SHIFT SHIFT} flag set.
     */
    public static InputModifiers shift() {
        return new InputModifiers(SHIFT);
    }

    /**
     * @return {@literal this} but with the {@link InputModifiers#SHIFT SHIFT} flag set.
     */
    public InputModifiers withShift() {
        return new InputModifiers(this.flags | SHIFT);
    }

    /**
     * @return an instance of {@link InputModifiers} with the {@link InputModifiers#CONTROL CONTROL} flag set.
     */
    public static InputModifiers control() {
        return new InputModifiers(CONTROL);
    }

    /**
     * @return {@literal this} but with the {@link InputModifiers#CONTROL CONTROL} flag set.
     */
    public InputModifiers withControl() {
        return new InputModifiers(this.flags | CONTROL);
    }

    /**
     * @return an instance of {@link InputModifiers} with the {@link InputModifiers#ALT ALT} flag set.
     */
    public static InputModifiers alt() {
        return new InputModifiers(ALT);
    }

    /**
     * @return {@literal this} but with the {@link InputModifiers#ALT ALT} flag set.
     */
    public InputModifiers withAlt() {
        return new InputModifiers(this.flags | ALT);
    }

    /**
     * @return an instance of {@link InputModifiers} with the {@link InputModifiers#SUPER SUPER} flag set.
     */
    public static InputModifiers super_() {
        return new InputModifiers(SUPER);
    }

    /**
     * @return {@literal this} but with the {@link InputModifiers#SUPER SUPER} flag set.
     */
    public InputModifiers withSuper() {
        return new InputModifiers(this.flags | SUPER);
    }

    /**
     * @return an instance of {@link InputModifiers} with the {@link InputModifiers#CAPS_LOCK CAPS_LOCK} flag set.
     */
    public static InputModifiers capsLock() {
        return new InputModifiers(CAPS_LOCK);
    }

    /**
     * @return {@literal this} but with the {@link InputModifiers#CAPS_LOCK CAPS_LOCK} flag set.
     */
    public InputModifiers withCapsLock() {
        return new InputModifiers(this.flags | CAPS_LOCK);
    }

    /**
     * @return an instance of {@link InputModifiers} with the {@link InputModifiers#NUM_LOCK NUM_LOCK} flag set.
     */
    public static InputModifiers numLock() {
        return new InputModifiers(NUM_LOCK);
    }

    /**
     * @return {@literal this} but with the {@link InputModifiers#NUM_LOCK NUM_LOCK} flag set.
     */
    public InputModifiers withNumLock() {
        return new InputModifiers(this.flags | NUM_LOCK);
    }
}
