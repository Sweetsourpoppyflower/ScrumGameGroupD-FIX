package game.assistent;

import game.kamers.Kamer;
import game.speler.Speler;

public class aHint implements AssistentActie{
    @Override
    public void voerUit(Kamer kamer, Speler speler) {
        kamer.geefHint();
    }
}
