package me.katie.curium.events.client;

/**
 * Event fired when the game starts (tick loop).
 */
public class ClientStartedEvent {
    private static final ClientStartedEvent INSTANCE = new ClientStartedEvent();

    private ClientStartedEvent() {

    }

    public static ClientStartedEvent get() {
        return INSTANCE;
    }
}
