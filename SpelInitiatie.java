public class SpelInitiatie {
    private Spel spel;

    public Spel initializeGame(String spelNaam) {
        spel = new Spel(spelNaam);
        spel.setScrumKennisLogger(new ScrumKennisLogger());
        spel.setKamerVoortgangsMonitor(new KamerVoortgangsMonitor(5));
        return spel;
    }

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

    public Speler createPlayer(String naam) {
        Speler speler = new Speler(naam);
        speler.registerObserver(new ScrumKennisLogger());
        speler.registerObserver(new KamerVoortgangsMonitor(5));
        return speler;
    }


}