package me.katie.curium.impl.api;

import me.katie.curium.input.FakeKeyboard;
import me.katie.curium.input.FakeMouse;
import me.katie.curium.TPSLimiter;
import me.katie.curium.impl.duck.CuriumStateHolder;
import me.katie.curium.Curium;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class CuriumImpl implements Curium {
    public final TPSLimiterImpl tpsLimiter = new TPSLimiterImpl();
    public final FakeMouseImpl fakeMouse = new FakeMouseImpl();
    public final FakeKeyboardImpl fakeKeyboard = new FakeKeyboardImpl();
    public final EventBus eventBus = new EventBus();
    public boolean shouldClose = false;

    public CuriumImpl() {

    }

    public @NotNull static CuriumImpl get() {
        return ((CuriumStateHolder) Minecraft.getInstance()).curium_getState();
    }

    @Override
    public @NotNull TPSLimiter getTpsLimiter() {
        return this.tpsLimiter;
    }

    @Override
    public @NotNull FakeMouse getFakeMouse() {
        return this.fakeMouse;
    }

    @Override
    public @NotNull FakeKeyboard getFakeKeyboard() {
        return this.fakeKeyboard;
    }

    @Override
    public @NotNull IEventBus getEventBus() {
        return this.eventBus;
    }

    @Override
    public void close() {
        this.shouldClose = true;
    }
}
