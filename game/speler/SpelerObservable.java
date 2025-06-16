package game.speler;

import java.util.ArrayList;
import java.util.List;

public class SpelerObservable {
    private List<VoortgangsMonitor> observers;
    
    public SpelerObservable() {
        this.observers = new ArrayList<>();
    }

    public void registerObserver(VoortgangsMonitor observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    public void removeObserver(VoortgangsMonitor observer) {
        observers.remove(observer);
    }
    public void notifyObservers(Speler speler) {
        for (VoortgangsMonitor observer : observers) {
            observer.update(speler);
        }
    }
    
    public List<VoortgangsMonitor> getObservers() {
        return observers;
    }

}
