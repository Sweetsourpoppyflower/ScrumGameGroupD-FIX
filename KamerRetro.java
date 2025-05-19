import java.util.Scanner;

public class KamerRetro extends Kamer {
    private String retroDetails;
    private VraagStrategie vraag;
    private Monster monster;

    public KamerRetro(String beschrijving, String retroDetails) {
        super(beschrijving);
        this.retroDetails = retroDetails;
        this.vraag = maakRetroVraag();
        this.monster = new Draak();
    }
    
    private VraagStrategie maakRetroVraag() {
        String vraag = "Wat is het primaire doel van een Sprint Retrospective?";
        String[] opties = {
            "Het beoordelen van de kwaliteit van het opgeleverde product",
            "Het plannen van de volgende sprint",
            "Het verbeteren van het werkproces van het team",
            "Het demonstreren van de voltooide functionaliteiten aan stakeholders"
        };
        String correctAntwoord = "Het verbeteren van het werkproces van het team";
        
        return new MeerkeuzeStrategie(
            vraag, 
            opties, 
            correctAntwoord,
            "Correct! De Sprint Retrospective is inderdaad bedoeld om het werkproces van het team te verbeteren.",
            "Helaas, dat is niet correct. De Sprint Retrospective is bedoeld om het werkproces van het team te verbeteren."
        );
    }

    @Override
    public void betreed() {
        System.out.println(KamerAsciiLayouts.getRetroLayout());
        System.out.println("Welkom in de Retrospective Kamer!");
        System.out.println(beschrijving);
        System.out.println("Retro Details: " + retroDetails);
        
        System.out.println("\nEr verschijnt een " + monster.getNaam() + "!");
        System.out.println(monster.beschrijving());
        monster.aanval();
        
        stelVraag();
    }

    public void toonRetro() {
        System.out.println(retroDetails);
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
