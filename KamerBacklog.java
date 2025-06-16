import java.sql.SQLException;
import java.util.Scanner;

public class KamerBacklog extends Kamer {
    private Boolean heeftAssistent = false;
    private String backlogDetails;
    private Zwaard zwaard = new Zwaard(50);

    public KamerBacklog(String beschrijving, String backlogDetails) {
        super(beschrijving);
        this.backlogDetails = backlogDetails;
        this.vraag = maakBacklogVraag();
        this.monster = new Draak();
    }

    public void gebruikZwaard(Zwaard zwaard) {
        System.out.println("⚔️ Je gebruikt een zwaard met " + zwaard.getSchade() + " schade.");
        Vertraag.inMilliseconden(300);
        System.out.println("❓ Wil je de " + monster.getNaam() + " aanvallen? ja/nee");
        Vertraag.inMilliseconden(300);
        Scanner scanner = new Scanner(System.in);
        String antwoord = scanner.nextLine().toLowerCase();
        if (antwoord.equals("ja") || antwoord.equals("j")) {
            monster.ontvangSchade(zwaard.getSchade());
            System.out.println("💥 Je hebt de " + monster.getNaam() + " aangevallen met " + zwaard.getSchade() + " schade.");
            Vertraag.inMilliseconden(300);
        } else {
            System.out.println("🛡️ Je hebt ervoor gekozen om niet aan te vallen.");
            Vertraag.inMilliseconden(300);
        }
    }

    private VraagStrategie maakBacklogVraag() {
        String vraag = "Wat is het primaire doel van een product backlog?";
        String[] opties = {
            "Een lijst van alle taken die het team moet uitvoeren",
            "Een geprioriteerde lijst van alle gewenste functionaliteiten en verbeteringen",
            "Een dagelijks overzicht van de voortgang van het team",
            "Een document met alle technische specificaties"
        };
        String correctAntwoord = "Een geprioriteerde lijst van alle gewenste functionaliteiten en verbeteringen";
        
        return new MeerkeuzeStrategie(
            vraag, 
            opties, 
            correctAntwoord,
            "Correct! De product backlog is inderdaad een geprioriteerde lijst van alle gewenste functionaliteiten.",
            "Helaas, dat is niet correct. De product backlog is een geprioriteerde lijst van alle gewenste functionaliteiten."
        );
    }

    @Override
    void geefHint() {
        System.out.println("💡 Hint: De product backlog is een geprioriteerde lijst van alle gewenste functionaliteiten en verbeteringen.");
        Vertraag.inMilliseconden(400);
    }

    @Override
    protected String getKamerNaam() {
        return "Backlog Kamer";
    }

    @Override
    public void betreed() throws SQLException, InterruptedException {
        System.out.println(KamerAsciiLayouts.getBacklogLayout());
        System.out.println("👋 Welkom in de Backlog Kamer!");
        Vertraag.inMilliseconden(400);
        System.out.println(beschrijving);
        Vertraag.inMilliseconden(400);
        System.out.println("📋 Backlog Details: " + backlogDetails);
        Vertraag.inMilliseconden(400);
        
        System.out.println("\n🐉 Er verschijnt een " + monster.getNaam() + "!");
        Vertraag.inMilliseconden(400);
        System.out.println(monster.beschrijving());
        Vertraag.inMilliseconden(400);
        monster.aanval();
        Vertraag.inMilliseconden(300);
        gebruikZwaard(new Zwaard(50));
        stelVraag();
    }

    public void toonBacklog() {
        System.out.println("📋 " + backlogDetails);
    }

    @Override
    public void accepteer(Joker joker) {
        if (joker.kanGebruiktWordenIn(this)) {
            joker.gebruikIn(this);
            System.out.println("🃏 Joker is gebruikt in de Backlog Kamer.");
            Vertraag.inMilliseconden(300);
        } else {
            System.out.println("⚠️ Deze joker kan niet in deze kamer worden gebruikt.");
            Vertraag.inMilliseconden(300);
        }
    }

    public Boolean heeftAssistent() {
        return heeftAssistent;
    }
}
