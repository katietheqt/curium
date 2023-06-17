package me.katie.curium.impl.api;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import me.katie.curium.input.FakeKeyboard;
import me.katie.curium.input.InputAction;
import me.katie.curium.input.InputModifiers;
import me.katie.curium.input.KeyboardKey;
import me.katie.curium.impl.mixin.core.minecraft.KeyboardHandlerAccessor;
import net.minecraft.client.Minecraft;

public class FakeKeyboardImpl implements FakeKeyboard {
    public final Int2ObjectMap<InputAction> keyStates = new Int2ObjectOpenHashMap<>();
    private boolean capsLock = false;
    private boolean numLock = false;

    public boolean isPressed(KeyboardKey... keys) {
        for (KeyboardKey key: keys) {
            if (this.isPressed(key.lwjglIndex)) {
                return true;
            }
        }

        return false;
    }

    public boolean isPressed(int key) {
        return this.keyStates.getOrDefault(key, InputAction.Release) == InputAction.Press;
    }

    private int generateModifiers() {
        int flags = 0;

        if (this.isPressed(KeyboardKey.LEFT_SHIFT, KeyboardKey.RIGHT_SHIFT)) {
            flags |= InputModifiers.SHIFT;
        }

        if (this.isPressed(KeyboardKey.LEFT_CONTROL, KeyboardKey.RIGHT_CONTROL)) {
            flags |= InputModifiers.CONTROL;
        }

        if (this.isPressed(KeyboardKey.LEFT_ALT, KeyboardKey.RIGHT_ALT)) {
            flags |= InputModifiers.ALT;
        }

        if (this.isPressed(KeyboardKey.LEFT_SUPER, KeyboardKey.RIGHT_SUPER)) {
            flags |= InputModifiers.SUPER;
        }

        if (this.capsLock) {
            flags |= InputModifiers.CAPS_LOCK;
        }

        if (this.numLock) {
            flags |= InputModifiers.NUM_LOCK;
        }

        return flags;
    }

    public void update(KeyboardKey key, InputAction action) {
        if (action == InputAction.Press || action == InputAction.Release) {
            keyStates.put(key.lwjglIndex, action);
        }

        if (action == InputAction.Press || action == InputAction.Repeat) {
            if (key == KeyboardKey.CAPS_LOCK) {
                this.capsLock = !this.capsLock;
            }

            if (key == KeyboardKey.NUM_LOCK) {
                this.numLock = !this.numLock;
            }
        }

        int mods = this.generateModifiers();
        Minecraft.getInstance().keyboardHandler.keyPress(0, key.lwjglIndex, -1, action.ordinal(), mods);
    }

    public void type(int codepoint, InputModifiers modifiers) {
        KeyboardHandlerAccessor accessor = (KeyboardHandlerAccessor) Minecraft.getInstance().keyboardHandler;
        accessor.callCharTyped(0, codepoint, modifiers.toRaw());
    }
}
