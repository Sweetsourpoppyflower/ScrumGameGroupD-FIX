import java.util.Scanner;

public class KamerDaily extends Kamer {
    private String dailyDetails;
    private VraagStrategie vraag;
    private Monster monster;

    public KamerDaily(String beschrijving, String dailyDetails) {
        super(beschrijving);
        this.dailyDetails = dailyDetails;
        this.vraag = maakDailyVraag();
        this.monster = new Zombie();
    }
    
    private VraagStrategie maakDailyVraag() {
        String vraag = "Wat zijn de drie vragen die tijdens een Daily Scrum worden beantwoord?";
        String antwoord = "Wat heb ik gisteren gedaan, wat ga ik vandaag doen, en welke obstakels heb ik?";
        String[] sleutelwoorden = {
            "gisteren", "vandaag", "obstakels", "impediments", "blokkades"
        };
        
        return new OpenvraagStrategie(
            vraag, 
            antwoord, 
            sleutelwoorden,
            "Correct! De drie vragen zijn inderdaad: wat heb ik gisteren gedaan, wat ga ik vandaag doen, en welke obstakels heb ik?",
            "Dat is niet helemaal juist. De drie vragen zijn: wat heb ik gisteren gedaan, wat ga ik vandaag doen, en welke obstakels heb ik?"
        );
    }

    @Override
    public void betreed() {
        System.out.println(KamerAsciiLayouts.getDailyLayout());
        System.out.println("Welkom in de Daily Kamer!");
        System.out.println(beschrijving);
        System.out.println("Daily Details: " + dailyDetails);
        
        System.out.println("\nEr verschijnt een " + monster.getNaam() + "!");
        System.out.println(monster.beschrijving());
        monster.aanval();
        
        stelVraag();
    }

    public void toonDaily() {
        System.out.println(dailyDetails);
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
