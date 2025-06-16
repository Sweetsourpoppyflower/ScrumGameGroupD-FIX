package game.speler;

import java.util.List;

import game.jokers.JokerManager;
import game.jokers.Joker;

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

    // De Speler klasse vertegenwoordigt een speler in het spel.
    // De constructor initialiseert de speler met een naam en stelt de initiële status in.
    // De status bevat informatie zoals de huidige kamer, scrum kennis en aantal sleutels.
    // De RepoManager wordt gebruikt om de status van de speler op te slaan en op te halen.
    // De SpelerObservable wordt gebruikt om observables te beheren die de voortgang van de speler volgen.


    public void verplaats(int nieuwePositie) {
        this.status.setHuidigeKamer(nieuwePositie);
        
        this.kamerTracker.voegKamerToe(this.naam, nieuwePositie);
        
        this.persistence.updatePositie(this.naam, nieuwePositie);
        
        this.observable.notifyObservers(this);
    }
    // De verplaats methode wijzigt de huidige kamer van de speler naar een nieuwe positie.
    // Het voegt de nieuwe kamer toe aan de kamer tracker en slaat de nieuwe positie op in de database.
    // Het notifyt ook alle geregistreerde observables over de voortgang van de speler.
    // Deze methode wordt gebruikt om de voortgang van de speler bij te houden en te synchroniseren met de database.
    
    public boolean isKamerBezocht(int kamerNr) {
        return this.kamerTracker.isKamerBezocht(this.naam, kamerNr);
    }
    // De isKamerBezocht methode controleert of de speler een bepaalde kamer al heeft bezocht.

    public int getHuidigeKamer() {
        return this.status.getHuidigeKamer();
    }
    // De getHuidigeKamer methode retourneert het huidige kamer nummer van de speler.

    public String getStatus() {
        return this.status.getStatus();
    }
    // De getStatus methode retourneert de huidige status van de speler, zoals opgeslagen in de SpelerStatus klasse.

    public String getNaam() {
        return this.status.getNaam();
    }
    // De getNaam methode retourneert de naam van de speler, zoals opgeslagen in de SpelerStatus klasse.

    public void verhoogScrumKennis(int aantal) {
        int newKennis = this.status.getScrumKennis() + aantal;
        this.status.setScrumKennis(newKennis);
        
        System.out.println("⬆️ Scrum kennis van " + this.naam + " verhoogd met " + aantal);
        
        this.persistence.updateScrumKennis(this.naam, newKennis);
        
        this.observable.notifyObservers(this);
    }
    // De verhoogScrumKennis methode verhoogt de scrum kennis van de speler met een bepaald aantal.
    // Het slaat de nieuwe kennis op in de SpelerStatus en in de database via de RepoManager.
    // Het notifyt ook alle geregistreerde observables over de voortgang van de speler.
    // Deze methode wordt gebruikt om de voortgang van de speler bij te houden en te synchroniseren met de database.
    
    public void verlaagScrumKennis(int monsterLevenspunten) {
        int aftrek = monsterLevenspunten / 2;
        int newKennis = Math.max(0, this.status.getScrumKennis() - aftrek);
        this.status.setScrumKennis(newKennis);
        
        System.out.println("⬇️ Fout antwoord! Scrum kennis van " + this.naam + " verlaagd met " + aftrek);
        
        this.persistence.updateScrumKennis(this.naam, newKennis);
        
        this.observable.notifyObservers(this);
    }
    // De verlaagScrumKennis methode verlaagt de scrum kennis van de speler op basis van de levenspunten van een monster.
    // Het berekent de aftrek als de helft van de monster levenspunten, zorgt ervoor dat de kennis niet negatief wordt,
    // en slaat de nieuwe kennis op in de SpelerStatus en in de database via de RepoManager.
    // Het notifyt ook alle geregistreerde observables over de voortgang van de speler.

    public int getScrumKennis() {
        return this.status.getScrumKennis();
    }
    // De getScrumKennis methode retourneert de huidige scrum kennis van de speler, zoals opgeslagen in de SpelerStatus klasse.

    public void loadFromDatabase() {
        this.status = this.persistence.getSpelerStatus(this.naam);
        
        this.kamerTracker.voegKamerToe(this.naam, this.status.getHuidigeKamer());
        
        this.observable.notifyObservers(this);
    }
    // De loadFromDatabase methode laadt de status van de speler uit de database.
// Het haalt de SpelerStatus op via de RepoManager en voegt de huidige kamer toe aan de kamer tracker.
// Vervolgens notifyt het alle geregistreerde observables over de voortgang van de speler.

    public void registerObserver(VoortgangsMonitor observer) {
        this.observable.registerObserver(observer);
        observer.update(this);
    }
// De registerObserver methode voegt een nieuwe observer toe aan de lijst van observables.
    // Het registreert de observer en roept direct de update methode aan om de huidige status van de speler te verzenden.
    
    public void removeObserver(VoortgangsMonitor observer) {
        this.observable.removeObserver(observer);
    }
    // De removeObserver methode verwijdert een bestaande observer uit de lijst van observables.
    
    public void notifyObservers() {
        this.observable.notifyObservers(this);
    }
    // De notifyObservers methode notifyt alle geregistreerde observables over de voortgang van de speler.
    
    public List<VoortgangsMonitor> getObservers() {
        return this.observable.getObservers();
    }
    // De getObservers methode retourneert de lijst van geregistreerde observables die de voortgang van de speler volgen.

    public void setJoker(Joker joker) {
        this.jokerManager.setJoker(joker);
    }
    // De setJoker methode stelt een joker in voor de speler.

    public Joker getJoker() {
        return this.jokerManager.getJoker();
    }
    // De getJoker methode retourneert de huidige joker van de speler.
    
    public boolean hasJoker() {
        return this.jokerManager.hasJoker();
    }
    // De hasJoker methode controleert of de speler een joker heeft.

    public void setAantalSleutels(int aantal) {
        this.status.setAantalSleutels(aantal);
    }
    // De setAantalSleutels methode stelt het aantal sleutels van de speler in.

    public int getAantalSleutels() {
        return this.status.getAantalSleutels();
    }
    // De getAantalSleutels methode retourneert het huidige aantal sleutels van de speler.
    
    public void clearBezochteKamers() {
        this.persistence.clearBezochteKamers(this.naam);
    }

}
