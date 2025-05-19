import java.util.Set;
import java.util.HashSet;

public class KamerVoortgangsMonitor implements VoortgangsMonitor {
    private int huidigeKamer;
    private Set<Integer> bezochteKamers;
    private int totaalAantalKamers;
    
    public KamerVoortgangsMonitor(int totaalAantalKamers) {
        this.huidigeKamer = 0;
        this.bezochteKamers = new HashSet<>();
        this.totaalAantalKamers = totaalAantalKamers;
    }
    
    @Override
    public void update(Speler speler) {
        this.huidigeKamer = speler.getHuidigeKamer();
        this.bezochteKamers.add(this.huidigeKamer);
    }
    
    @Override
    public String getVoortgangsInfo() {
        return "Kamer voortgang: " + bezochteKamers.size() + "/" + totaalAantalKamers + 
               " kamers bezocht (" + (int)((bezochteKamers.size() * 100.0) / totaalAantalKamers) + "%)";
    }
}
