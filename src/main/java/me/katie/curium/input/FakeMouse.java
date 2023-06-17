package me.katie.curium.input;

/**
 * Interface to fake inputs from a mouse.
 */
public interface FakeMouse {
    /**
     * Simulates a mouse movement.
     *
     * @param x the new cursor x-coordinate, relative to the left edge of the content area
     * @param y the new cursor y-coordinate, relative to the top edge of the content area
     */
    void move(long x, long y);

    /**
     * Simulates pressing or releasing a mouse button.
     *
     * @param button the mouse button to press or release
     * @param action the action to perform
     * @param modifiers bitfield describing which modifiers keys were held down
     * @throws IllegalArgumentException if {@code action} is not {@link InputAction#Press Press} or {@link InputAction#Release Release}.
     */
    void click(Button button, InputAction action, InputModifiers modifiers);

    /**
     * Simulates a mouse scroll.
     *
     * @param xOffset the scroll offset along the x-axis
     * @param yOffset the scroll offset along the y-axis
     */
    void scroll(double xOffset, double yOffset);

    /**
     * Enum of possible mouse buttons.
     */
    enum Button {
        Left,
        Middle,
        Right,
        Button4,
        Button5,
        Button6,
        Button7,
        Button8
    }

}
