package me.katie.curium.input;

/**
 * Interface to fake inputs from a keyboard.
 */
public interface FakeKeyboard {
    /**
     * Simulates pressing, holding or releasing a key.
     *
     * @param key the keyboard key to press, hold or release
     * @param action the action to perform
     */
    void update(KeyboardKey key, InputAction action);

    /**
     * Simulates typing a unicode codepoint (e.g. into a text box).
     *
     * @param codepoint the codepoint that was typed
     * @param modifiers the modifier keys to simulate holding
     */
    void type(int codepoint, InputModifiers modifiers);
}
