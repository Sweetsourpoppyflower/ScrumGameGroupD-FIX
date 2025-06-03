import java.util.Set;

public class KamerTracker {
    private SpelerRepository spelerRepository;
    private int huidigeKamer;
    
    public KamerTracker() {
        this.spelerRepository = new SpelerRepository();
    }

    public void setHuidigeKamer
        (int huidigeKamer) {
        this.huidigeKamer = huidigeKamer;
    }

    public int getHuidigeKamer(String naam) {
        return spelerRepository.getHuidigeKamer(naam);
    }
    // De getHuidigeKamer methode retourneert de huidige kamer van de speler met de gegeven naam.

    public void voegKamerToe(String naam, int kamerNr) {
        spelerRepository.addBezochteKamer(naam, kamerNr);
    }
    // De voegKamerToe methode voegt een kamer toe aan de lijst van bezochte kamers voor de speler met de gegeven naam.

    public boolean isKamerBezocht(String naam, int kamerNr) {
        return spelerRepository.getBezochteKamers(naam).contains(kamerNr);
    }
    // De isKamerBezocht methode controleert of de speler met de gegeven naam de opgegeven kamer al heeft bezocht.

    public Set<Integer> getBezochteKamers(String naam) {
        return spelerRepository.getBezochteKamers(naam);
    }
    // De getBezochteKamers methode retourneert een set van kamer nummers die de speler met de gegeven naam heeft bezocht.
}
