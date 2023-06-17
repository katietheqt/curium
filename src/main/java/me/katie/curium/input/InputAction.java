package me.katie.curium.input;

/**
 * What action to perform on a button (mouse or keyboard).
 */
public enum InputAction {
    /**
     * The button will be released.
     */
    Release,

    /**
     * The button will be pressed.
     */
    Press,

    /**
     * The button will be repeated (only applicable for keyboards).
     */
    Repeat
}
