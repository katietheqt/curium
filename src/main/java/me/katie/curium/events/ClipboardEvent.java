package me.katie.curium.events;

import net.minecraft.client.KeyboardHandler;

/**
 * Events fired when the game attempts to access the clipboard.
 */
public class ClipboardEvent {
    /**
     * Fired when a {@link KeyboardHandler#setClipboard(String)} call occurs.
     */
    public static class Set extends ClipboardEvent {
        private static final Set INSTANCE = new Set();
        public String contents;

        private Set() {

        }

        public static Set get(String contents) {
            INSTANCE.contents = contents;
            return INSTANCE;
        }
    }

    /**
     * Fired when a {@link KeyboardHandler#getClipboard()} call occurs.
     */
    public static class Get extends ClipboardEvent {
        private static final Get INSTANCE = new Get();
        public String contents;

        private Get() {

        }

        public static Get get() {
            INSTANCE.contents = null;
            return INSTANCE;
        }
    }
}
