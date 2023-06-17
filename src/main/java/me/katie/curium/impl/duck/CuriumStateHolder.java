package me.katie.curium.impl.duck;

import me.katie.curium.impl.api.CuriumImpl;
import org.jetbrains.annotations.NotNull;

public interface CuriumStateHolder {
    @NotNull CuriumImpl curium_getState();
}
