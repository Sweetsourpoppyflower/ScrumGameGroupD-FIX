package game.assistent;

import game.kamers.Kamer;
import game.speler.Speler;

import java.util.ArrayList;


public class Assistent {
    private ArrayList<AssistentActie> acties;
    public Assistent() {
        this.acties = new ArrayList<>();

        this.acties.add(new aHint());
        this.acties.add(new aEducatief());
        this.acties.add(new aMotivatief());
    }
    public void activate(Kamer kamer, Speler speler) {
        for (AssistentActie actie : acties) {
            actie.voerUit(kamer, speler);
        }
    }

    public void voegActie(AssistentActie action) {
        this.acties.add(action);
    }
    public void verwijderActie(int index) {
        if (index >= 0 && index < acties.size()) {
            this.acties.remove(index);
        }
    }

}
