package game.objects;

public interface Readable {
    default void showMessage(String message) {
        System.out.println("Dit is een interactief object.");
    }

}
