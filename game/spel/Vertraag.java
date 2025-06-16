package game.spel;

public class Vertraag {

    public static void inMilliseconden(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("De vertraging werd onderbroken.");
        }
    }

    public static void inSeconden(long sec) {
        inMilliseconden(sec * 1000);
    }
}