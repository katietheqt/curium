package me.katie.curium.impl.stub.lwjgl;

@FunctionalInterface
@SuppressWarnings("unused")
public interface GLFWErrorCallbackI {
    void invoke(int error, long description);
}
