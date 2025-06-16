import java.sql.SQLException;
import java.util.*;

public class Spel {
    private String spelNaam;
    public List<Kamer> kamers;
    private List<Speler> spelers;
    private GebruikersInvoerVerwerker invoerVerwerker;
    private CommandoVerwerker commandoVerwerker;
    private static Speler huidigeSpeler;
    private ScrumKennisLogger scrumKennisLogger;
    private KamerVoortgangsMonitor kamerVoortgangsMonitor;

    public Spel(String spelNaam) {
        this.spelNaam = spelNaam;
        this.kamers = new ArrayList<>();
        this.spelers = new ArrayList<>();
        this.invoerVerwerker = new GebruikersInvoerVerwerker();
        this.commandoVerwerker = new CommandoVerwerker(kamers);
    }

    public List<Kamer> getKamers() {
        return kamers;
    }


    public void initializeCommandoVerwerker() {
        this.commandoVerwerker = new CommandoVerwerker(kamers);

        // Pass the CommandoVerwerker to each Kamer
        for (Kamer kamer : kamers) {
            kamer.setCommandoVerwerker(commandoVerwerker);
        }
    }
    // De initializeCommandoVerwerker methode initialiseert de CommandoVerwerker met de huidige kamers van het spel.

    
    public void setScrumKennisLogger(ScrumKennisLogger logger) {
        this.scrumKennisLogger = logger;
    }
    // De setScrumKennisLogger methode stelt de ScrumKennisLogger in die gebruikt wordt om de voortgang van de speler te loggen.
    
    public void setKamerVoortgangsMonitor(KamerVoortgangsMonitor monitor) {
        this.kamerVoortgangsMonitor = monitor;
    }
    // De setKamerVoortgangsMonitor methode stelt de KamerVoortgangsMonitor in die gebruikt wordt om de voortgang van de speler in kamers te monitoren.
    
    public void start() throws SQLException, InterruptedException {
        CLIFormatter.header("Welkom bij " + spelNaam + "!");
        String naam = invoerVerwerker.getPlayerName();

        SpelInitiatie spelInitiatie = new SpelInitiatie();
        Speler speler = spelInitiatie.createPlayer(naam);

        speler.clearBezochteKamers();
        
        spelers.add(speler);
        JokerSelector.verwerkJokerKeuze(speler);

        speler.verplaats(0);
        huidigeSpeler = speler;

        spelInitiatie.setupObservers(speler, scrumKennisLogger, kamerVoortgangsMonitor);

        initializeCommandoVerwerker();

        while (true) {
            Kamer huidigeKamer = kamers.get(speler.getHuidigeKamer());
            huidigeKamer.betreed();
            String input = invoerVerwerker.getNextCommand();
            commandoVerwerker.verwerkCommand(input, speler);
        }
    }
    // De start methode initialiseert het spel, vraagt de speler om een naam, en start de spel loop.
    // Het voegt de speler toe aan de lijst van spelers, stelt de huidige speler in, en configureert de observables.


    public void eindig() {
        CLIFormatter.header("👋 Bedankt voor het spelen!");
        System.exit(0);
    }
    // De eindig methode sluit het spel af en geeft een bedankbericht weer.


    public void voegKamerToe(Kamer kamer) {
        kamers.add(kamer);
    }
    // De voegKamerToe methode voegt een nieuwe kamer toe aan de lijst van kamers in het spel.
    
    public static Speler getHuidigeSpeler() {
        return huidigeSpeler;
    }
    // De getHuidigeSpeler methode retourneert de huidige speler van het spel.

}
