import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KamerPlanning extends Kamer {
    private String planningDetails;
    private VraagStrategie vraag;
    private Monster monster;

    public KamerPlanning(String beschrijving, String planningDetails) {
        super(beschrijving);
        this.planningDetails = planningDetails;
        this.vraag = maakPlanningVraag();
        this.monster = new Reuzenspin();
    }
    
    private VraagStrategie maakPlanningVraag() {
        String vraag = "Koppel de juiste termen aan elkaar door het antwoord in het formaat 'term=definitie' te geven:\n" +
                       "1. Sprint Planning\n" +
                       "2. Story Points\n" +
                       "3. Definition of Done\n" +
                       "4. Velocity\n" +
                       "5. Product Owner\n\n" +
                       "A. Relatieve maat voor de complexiteit van een taak\n" +
                       "B. Criteria waaraan een taak moet voldoen om als voltooid te worden beschouwd\n" +
                       "C. Persoon die verantwoordelijk is voor het maximaliseren van de waarde van het product\n" +
                       "D. Meeting om te bepalen wat er in de komende sprint gedaan zal worden\n" +
                       "E. De hoeveelheid werk die een team in een sprint kan voltooien";
        
        Map<String, String> correcteAntwoorden = new HashMap<>();
        correcteAntwoorden.put("1", "D");
        correcteAntwoorden.put("2", "A");
        correcteAntwoorden.put("3", "B");
        correcteAntwoorden.put("4", "E");
        correcteAntwoorden.put("5", "C");
        
        return new KoppelStrategie(
            vraag, 
            correcteAntwoorden,
            "Correct! Je hebt de juiste koppeling gemaakt.",
            "Dat is niet de juiste koppeling. Probeer het nog eens."
        );
    }

    @Override
    public void betreed() {
        System.out.println(KamerAsciiLayouts.getPlanningLayout());
        System.out.println("Welkom in de Planning Kamer!");
        System.out.println(beschrijving);
        System.out.println("Planning Details: " + planningDetails);
        
        System.out.println("\nEr verschijnt een " + monster.getNaam() + "!");
        System.out.println(monster.beschrijving());
        monster.aanval();
        
        stelVraag();
    }

    public void toonPlanning() {
        System.out.println(planningDetails);
    }
    
    private void stelVraag() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nOm de " + monster.getNaam() + " te verslaan, moet je deze vraag beantwoorden:");
        
        vraag.toonVraag();
        
        System.out.print("\nJouw antwoord (bijv. '1=D'): ");
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
