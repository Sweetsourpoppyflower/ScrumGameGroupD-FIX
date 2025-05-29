import java.util.List;

public class Speler {
    private String naam;
    private SpelerStatus status;
    private RepoManager persistence;
    private SpelerObservable observable;
    private JokerManager jokerManager;
    private KamerTracker kamerTracker;

    public Speler(String naam) {
        this.naam = naam;
        this.status = new SpelerStatus();
        this.status.setNaam(naam);
        this.status.setScrumKennis(0);
        this.status.setHuidigeKamer(0);
        this.status.setAantalSleutels(0);
        
        this.persistence = new RepoManager();
        this.observable = new SpelerObservable();
        this.jokerManager = new JokerManager();
        this.kamerTracker = new KamerTracker();
        this.kamerTracker.voegKamerToe(naam, 0);
        

        this.persistence.saveSpelerStatus(this.status);
    }

    public void verplaats(int nieuwePositie) {
        this.status.setHuidigeKamer(nieuwePositie);
        
        this.kamerTracker.voegKamerToe(this.naam, nieuwePositie);
        
        this.persistence.updatePositie(this.naam, nieuwePositie);
        
        this.observable.notifyObservers(this);
    }
    
    public boolean isKamerBezocht(int kamerNr) {
        return this.kamerTracker.isKamerBezocht(this.naam, kamerNr);
    }

    public int getHuidigeKamer() {
        return this.status.getHuidigeKamer();
    }

    public String getStatus() {
        return this.status.getStatus();
    }

    public String getNaam() {
        return this.status.getNaam();
    }

    public void verhoogScrumKennis(int aantal) {
        int newKennis = this.status.getScrumKennis() + aantal;
        this.status.setScrumKennis(newKennis);
        
        System.out.println("Scrum kennis van " + this.naam + " verhoogd met " + aantal);
        
        this.persistence.updateScrumKennis(this.naam, newKennis);
        
        this.observable.notifyObservers(this);
    }
    
    public void verlaagScrumKennis(int monsterLevenspunten) {
        int aftrek = monsterLevenspunten / 2;
        int newKennis = Math.max(0, this.status.getScrumKennis() - aftrek);
        this.status.setScrumKennis(newKennis);
        
        System.out.println("Fout antwoord! Scrum kennis van " + this.naam + " verlaagd met " + aftrek);
        
        this.persistence.updateScrumKennis(this.naam, newKennis);
        
        this.observable.notifyObservers(this);
    }

    public int getScrumKennis() {
        return this.status.getScrumKennis();
    }

    public void loadFromDatabase() {
        this.status = this.persistence.getSpelerStatus(this.naam);
        
        this.kamerTracker.voegKamerToe(this.naam, this.status.getHuidigeKamer());
        
        this.observable.notifyObservers(this);
    }

    public void registerObserver(VoortgangsMonitor observer) {
        this.observable.registerObserver(observer);
        observer.update(this);
    }
    
    public void removeObserver(VoortgangsMonitor observer) {
        this.observable.removeObserver(observer);
    }
    
    public void notifyObservers() {
        this.observable.notifyObservers(this);
    }
    
    public List<VoortgangsMonitor> getObservers() {
        return this.observable.getObservers();
    }

    public void setJoker(Joker joker) {
        this.jokerManager.setJoker(joker);
    }

    public Joker getJoker() {
        return this.jokerManager.getJoker();
    }
    
    public boolean hasJoker() {
        return this.jokerManager.hasJoker();
    }

    public void setAantalSleutels(int aantal) {
        this.status.setAantalSleutels(aantal);
    }

    public int getAantalSleutels() {
        return this.status.getAantalSleutels();
    }
    
    public void clearBezochteKamers() {
        this.persistence.clearBezochteKamers(this.naam);
    }
}
