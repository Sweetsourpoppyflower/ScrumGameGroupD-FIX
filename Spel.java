import java.sql.SQLException;
import java.util.*;

public class Spel {
    private String spelNaam;
    private List<Kamer> kamers;
    private List<Speler> spelers;
    private GebruikersInvoerVerwerker invoerVerwerker;
    private CommandoVerwerker commandoVerwerker;
    private static Speler huidigeSpeler;

    public Spel(String spelNaam) {
        this.spelNaam = spelNaam;
        this.kamers = new ArrayList<>();
        this.spelers = new ArrayList<>();
        this.invoerVerwerker = new GebruikersInvoerVerwerker();
        this.commandoVerwerker = new CommandoVerwerker(kamers);
    }
    

    public void initializeCommandoVerwerker() {
        this.commandoVerwerker = new CommandoVerwerker(kamers);
    }

    private ScrumKennisLogger scrumKennisLogger;
    private KamerVoortgangsMonitor kamerVoortgangsMonitor;
    
    public void setScrumKennisLogger(ScrumKennisLogger logger) {
        this.scrumKennisLogger = logger;
    }
    
    public void setKamerVoortgangsMonitor(KamerVoortgangsMonitor monitor) {
        this.kamerVoortgangsMonitor = monitor;
    }
    
    public void start() throws SQLException {
        System.out.println("Welkom bij " + spelNaam + "!");
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


    public void eindig() {
        System.out.println("Bedankt voor het spelen!");
        System.exit(0);
    }

    public void voegKamerToe(Kamer kamer) {
        kamers.add(kamer);
    }
    
    public static Speler getHuidigeSpeler() {
        return huidigeSpeler;
    }

}
