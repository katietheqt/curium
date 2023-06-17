package me.katie.curium.impl.api;

import me.katie.curium.input.FakeMouse;
import me.katie.curium.input.InputAction;
import me.katie.curium.input.InputModifiers;
import me.katie.curium.impl.mixin.core.minecraft.MouseHandlerAccessor;
import net.minecraft.client.Minecraft;

public class FakeMouseImpl implements FakeMouse {
    @Override
    public void move(long x, long y) {
        MouseHandlerAccessor accessor = (MouseHandlerAccessor) Minecraft.getInstance().mouseHandler;
        accessor.callOnMove(0, x, y);
    }

    @Override
    public void click(Button button, InputAction action, InputModifiers modifiers) {
        if (action != InputAction.Press && action != InputAction.Release) {
            throw new IllegalArgumentException("FakeMouse#click action must be press or release");
        }

        MouseHandlerAccessor accessor = (MouseHandlerAccessor) Minecraft.getInstance().mouseHandler;
        accessor.callOnPress(0, button.ordinal(), action.ordinal(), modifiers.toRaw());
    }

    @Override
    public void scroll(double xOffset, double yOffset) {
        MouseHandlerAccessor accessor = (MouseHandlerAccessor) Minecraft.getInstance().mouseHandler;
        accessor.callOnScroll(0, xOffset, yOffset);
    }
}
