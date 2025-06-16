package game.spel;

import game.speler.Speler;

public class SpelInitiatie {
    private Spel spel;

    public Spel initializeGame(String spelNaam) {
        spel = new Spel(spelNaam);
        spel.setScrumKennisLogger(new ScrumKennisLogger());
        spel.setKamerVoortgangsMonitor(new KamerVoortgangsMonitor(5));
        return spel;
    }
    // De initializeGame methode initialiseert het spel met de gegeven naam en stelt de ScrumKennisLogger en KamerVoortgangsMonitor in.

    public Spel getSpel() {
        return spel;
    }

    public void setupObservers(Speler speler, ScrumKennisLogger logger, KamerVoortgangsMonitor monitor) {
        if (logger != null) {
            speler.registerObserver(logger);
        }
        if (monitor != null) {
            speler.registerObserver(monitor);
        }
    }
// De setupObservers methode registreert de gegeven observers (logger en monitor) bij de speler.


    public Speler createPlayer(String naam) {
        Speler speler = new Speler(naam);
        speler.registerObserver(new ScrumKennisLogger());
        speler.registerObserver(new KamerVoortgangsMonitor(5));
        return speler;
    }
    // De createPlayer methode maakt een nieuwe speler aan met de gegeven naam en registreert de ScrumKennisLogger en KamerVoortgangsMonitor als observers.

}