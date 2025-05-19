import java.util.Scanner;

public class KamerBacklog extends Kamer {
    private String backlogDetails;
    private VraagStrategie vraag;
    private Monster monster;

    public KamerBacklog(String beschrijving, String backlogDetails) {
        super(beschrijving);
        this.backlogDetails = backlogDetails;
        this.vraag = maakBacklogVraag();
        this.monster = new Draak();
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
    public void betreed() {
        System.out.println(KamerAsciiLayouts.getBacklogLayout());
        System.out.println("Welkom in de Backlog Kamer!");
        System.out.println(beschrijving);
        System.out.println("Backlog Details: " + backlogDetails);
        
        System.out.println("\nEr verschijnt een " + monster.getNaam() + "!");
        System.out.println(monster.beschrijving());
        monster.aanval();
        
        stelVraag();
    }

    public void toonBacklog() {
        System.out.println(backlogDetails);
    }
    
    private void stelVraag() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nOm de " + monster.getNaam() + " te verslaan, moet je deze vraag beantwoorden:");
        
        vraag.toonVraag();
        
        System.out.print("\nJouw antwoord: ");
        String antwoord = scanner.nextLine();
        
        boolean isCorrect = vraag.controleerAntwoord(antwoord);
        if (isCorrect) {
            System.out.println(vraag.positieveFeedback());
            monster.versla(Spel.getHuidigeSpeler());
        } else {
            System.out.println(vraag.negatieveFeedback());
            System.out.println("De " + monster.getNaam() + " valt opnieuw aan!");
            
            vraagOmHint();
            
            stelVraag();
        }
    }
    
    private void vraagOmHint() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wil je een hint? (ja/nee): ");
        String antwoord = scanner.nextLine().toLowerCase();
        
        if (antwoord.equals("ja") || antwoord.equals("j")) {
            HintProvider hintProvider = HintFactory.createHintProvider(vraag);
            
            System.out.println("\nHINT: " + hintProvider.getHint() + "\n");
        }
    }
}
