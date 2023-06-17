package me.katie.curium;

import me.katie.curium.input.FakeKeyboard;
import me.katie.curium.input.FakeMouse;
import me.katie.curium.impl.api.CuriumImpl;
import meteordevelopment.orbit.IEventBus;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

/**
 * Curium-owned state of a {@link Minecraft} instance.
 */
public interface Curium {
    /**
     * Gets the current {@link CuriumImpl} instance.
     */
    static @NotNull Curium get() {
        return CuriumImpl.get();
    }

    /**
     * @return the client TPS limiter
     */
    @NotNull TPSLimiter getTpsLimiter();

    /**
     * @return a fake mouse for the client
     */
    @NotNull FakeMouse getFakeMouse();

    /**
     * @return a fake keyboard for the client
     */
    @NotNull FakeKeyboard getFakeKeyboard();

    /**
     * @return the Curium event bus
     */
    @NotNull IEventBus getEventBus();

    /**
     * Sets a flag to indicate that the client should exit on the next tick loop.
     */
    void close();
}
