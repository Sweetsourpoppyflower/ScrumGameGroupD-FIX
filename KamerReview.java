class KamerReview extends Kamer {
    private int reviewScore;

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
    void geefHint() {
        System.out.println("Hint: De Sprint Review is bedoeld om de voltooide functionaliteiten te demonstreren en feedback te verzamelen van stakeholders.");
    }

    @Override
    protected String getKamerNaam() {
        return "Review Kamer";
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
}
