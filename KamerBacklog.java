import java.util.Scanner;

public class KamerBacklog extends Kamer {
    private String backlogDetails;
    private Zwaard zwaard = new Zwaard(50);

    public KamerBacklog(String beschrijving, String backlogDetails) {
        super(beschrijving);
        this.backlogDetails = backlogDetails;
        this.vraag = maakBacklogVraag();
        this.monster = new Draak();
    }

    public void gebruikZwaard(Zwaard zwaard) {
        System.out.println("Je gebruikt een zwaard met " + zwaard.getSchade() + " schade.");
        System.out.println("wil je de " + monster.getNaam() + " aanvallen? ja/nee");
        Scanner scanner = new Scanner(System.in);
        String antwoord = scanner.nextLine().toLowerCase();
        if (antwoord.equals("ja") || antwoord.equals("j")) {
            monster.ontvangSchade(zwaard.getSchade());
            System.out.println("Je hebt de " + monster.getNaam() + " aangevallen met " + zwaard.getSchade() + " schade.");
        } else {
            System.out.println("Je hebt ervoor gekozen om niet aan te vallen.");
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
        System.out.println("Hint: De product backlog is een geprioriteerde lijst van alle gewenste functionaliteiten en verbeteringen.");
    }

    @Override
    protected String getKamerNaam() {
        return "Backlog Kamer";
    }

    @Override
    public void betreed() {
        System.out.println(KamerAsciiLayouts.getBacklogLayout());
        System.out.println("Welkom in de Backlog Kamer!");
        System.out.println(beschrijving);
        System.out.println("Backlog Details: " + backlogDetails);
        
        System.out.println("\nEr verschijnt een " + monster.getNaam() + "!");
        System.out.println(monster.beschrijving());
        monster.aanval();
        gebruikZwaard(new Zwaard(50));
        stelVraag();
    }

    public void toonBacklog() {
        System.out.println(backlogDetails);
    }

    @Override
    public void accepteer(Joker joker) {
        if (joker.kanGebruiktWordenIn(this)) {
            joker.gebruikIn(this);
            System.out.println("Joker is gebruikt in de Backlog Kamer.");
        } else {
            System.out.println("Deze joker kan niet in deze kamer worden gebruikt.");
        }
    }
}
