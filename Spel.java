import java.sql.SQLException;
import java.util.*;

public class Spel {
    private String spelNaam;
    private List<Kamer> kamers;
    private List<Speler> spelers;
    private Scanner scanner;
    private static Speler huidigeSpeler;

    public Spel(String spelNaam) {
        this.spelNaam = spelNaam;
        this.kamers = new ArrayList<>();
        this.spelers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
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
        System.out.print("Voer je naam in: ");
        String naam = scanner.nextLine();
        Speler speler = new Speler(naam);
        spelers.add(speler);
        speler.verplaats(0);
        huidigeSpeler = speler;
        
        
        if (scrumKennisLogger != null) {
            speler.registerObserver(scrumKennisLogger);
        }
        
        if (kamerVoortgangsMonitor != null) {
            speler.registerObserver(kamerVoortgangsMonitor);
        }

        while (true) {
            Kamer huidigeKamer = kamers.get(speler.getHuidigeKamer());
            huidigeKamer.betreed();
            System.out.print("> ");
            String input = scanner.nextLine();
            verwerkCommando(input, speler);
        }
    }

    private void verwerkCommando(String commando, Speler speler) throws SQLException {
        if (commando.startsWith("ga naar kamer")) {
            String[] delen = commando.split(" ");
            int kamerNr = Integer.parseInt(delen[delen.length - 1]);
            if (kamerNr >= 0 && kamerNr < kamers.size()) {
                if (speler.isKamerBezocht(kamerNr)) {
                    System.out.println("Je kunt niet terug naar een kamer die je al hebt bezocht.");
                } else {
                    speler.verplaats(kamerNr);
                }
            } else {
                System.out.println("Ongeldige kamer.");
            }
        } else if (commando.equals("status")) {
            toonStatus(speler);
        } else if (commando.equals("stop")) {
            eindig();
        } else {
            System.out.println("Onbekend commando. Typ 'status' of 'ga naar kamer X'.");
        }
    }
    
    private void toonStatus(Speler speler) {
        System.out.println("Je bent nu in kamer: " + speler.getHuidigeKamer());
        System.out.println("Status: " + speler.getStatus());
        
        for (VoortgangsMonitor monitor : speler.getObservers()) {
            System.out.println(monitor.getVoortgangsInfo());
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
