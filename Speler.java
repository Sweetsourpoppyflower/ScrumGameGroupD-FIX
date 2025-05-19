import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Speler {
    private String naam;
    private int huidigeKamer;
    private String status;
    private int scrumKennis;
    private SpelerRepository repository;
    private Set<Integer> bezochteKamers;
    private List<VoortgangsMonitor> observers;


    public Speler(String naam) {
        this.naam = naam;
        this.huidigeKamer = 0;
        this.status = "Begonnen";
        this.scrumKennis = 0;
        this.repository = new SpelerRepository();
        this.bezochteKamers = new HashSet<>();
        this.bezochteKamers.add(0);
        this.observers = new ArrayList<>();
        
        repository.saveSpeler(this);
    }

    public void verplaats(int nieuwePositie) {
        this.huidigeKamer = nieuwePositie;
        
        this.bezochteKamers.add(nieuwePositie);
        
        repository.updatePositie(this.naam, nieuwePositie);
        
        notifyObservers();
    }
    
    public boolean isKamerBezocht(int kamerNr) {
        return this.bezochteKamers.contains(kamerNr);
    }


    public int getHuidigeKamer() {
        return this.huidigeKamer;
    }


    public String getStatus() {
        return this.status;
    }


    public String getNaam() {
        return this.naam;
    }


    public void verhoogScrumKennis(int aantal) {
        this.scrumKennis += aantal;
        System.out.println("Scrum kennis van " + naam + " verhoogd met " + aantal);
        
        repository.updateScrumKennis(this.naam, this.scrumKennis);
        
        notifyObservers();
    }
    
    public void verlaagScrumKennis(int monsterLevenspunten) {
        int aftrek = monsterLevenspunten / 2;
        this.scrumKennis = Math.max(0, this.scrumKennis - aftrek);
        System.out.println("Fout antwoord! Scrum kennis van " + naam + " verlaagd met " + aftrek);
        
        repository.updateScrumKennis(this.naam, this.scrumKennis);
        
        notifyObservers();
    }


    public int getScrumKennis() {
        return this.scrumKennis;
    }

    public void loadFromDatabase() {
        this.huidigeKamer = repository.getHuidigeKamer(this.naam);
        this.status = repository.getStatus(this.naam);
        this.scrumKennis = repository.getScrumKennis(this.naam);
        
        if (this.bezochteKamers == null) {
            this.bezochteKamers = new HashSet<>();
        }
        
        this.bezochteKamers.add(this.huidigeKamer);
        
        notifyObservers();
    }
    

    public void registerObserver(VoortgangsMonitor observer) {
        observers.add(observer);
        observer.update(this);
    }
    
    public void removeObserver(VoortgangsMonitor observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers() {
        for (VoortgangsMonitor observer : observers) {
            observer.update(this);
        }
    }
    
    public List<VoortgangsMonitor> getObservers() {
        return observers;
    }
}
