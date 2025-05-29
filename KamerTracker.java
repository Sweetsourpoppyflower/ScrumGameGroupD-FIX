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

    public void voegKamerToe(String naam, int kamerNr) {
        spelerRepository.addBezochteKamer(naam, kamerNr);
    }

    public boolean isKamerBezocht(String naam, int kamerNr) {
        return spelerRepository.getBezochteKamers(naam).contains(kamerNr);
    }

    public Set<Integer> getBezochteKamers(String naam) {
        return spelerRepository.getBezochteKamers(naam);
    }
}
