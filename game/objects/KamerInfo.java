package game.objects;

import java.io.IOException;
import java.nio.CharBuffer;

public class KamerInfo implements Readable {
    public void showMessage(String message) {
        System.out.println("Dit is een interactief object dat informatie geeft over de kamer.");
        System.out.println("Informatie: " + message);
    }

}
