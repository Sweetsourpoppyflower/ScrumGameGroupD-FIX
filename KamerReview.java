import java.util.Scanner;

class KamerReview extends Kamer {
    private int reviewScore;
    private VraagStrategie vraag;
    private Monster monster;

    public KamerReview(String beschrijving, int reviewScore) {
        super(beschrijving);
        this.reviewScore = reviewScore;
        this.vraag = maakReviewVraag();
        this.monster = new Zombie();
    }
    
    private VraagStrategie maakReviewVraag() {
        String vraag = "Beschrijf het doel van een Sprint Review en wie er normaal gesproken bij aanwezig zijn.";
        String antwoord = "Het doel is om de voltooide functionaliteiten te demonstreren en feedback te verzamelen. Het Scrum team en stakeholders zijn aanwezig.";
        String[] sleutelwoorden = {
            "demonstreren", "tonen", "feedback", "stakeholders", "product owner", "scrum team", "voltooide"
        };
        
        return new OpenvraagStrategie(
            vraag, 
            antwoord, 
            sleutelwoorden,
            "Correct! De Sprint Review is inderdaad bedoeld om voltooide functionaliteiten te demonstreren en feedback te verzamelen van stakeholders.",
            "Dat is niet helemaal juist. De Sprint Review is bedoeld om voltooide functionaliteiten te demonstreren en feedback te verzamelen van stakeholders."
        );
    }

    @Override
    public void betreed() {
        System.out.println(KamerAsciiLayouts.getReviewLayout());
        System.out.println("Welkom in de Review Kamer!");
        System.out.println(beschrijving);
        System.out.println("Reviewscore: " + reviewScore);
        
        System.out.println("\nEr verschijnt een " + monster.getNaam() + "!");
        System.out.println(monster.beschrijving());
        monster.aanval();
        
        stelVraag();
    }

    public void toonReview() {
        System.out.println("Score: " + reviewScore);
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
