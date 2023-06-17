package me.katie.curium.events.client;

public class TickEvent {
    public static class Pre extends TickEvent {
        private static final Pre INSTANCE = new Pre();

        private Pre() {

        }

        public static Pre get() {
            return INSTANCE;
        }
    }

    public static class Post extends TickEvent {
        private static final Post INSTANCE = new Post();

        private Post() {

        }

        public static Post get() {
            return INSTANCE;
        }
    }
}
