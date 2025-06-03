public class KamerDaily extends Kamer {
    private String dailyDetails;

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
    void geefHint() {
        System.out.println("Hint: De Daily Scrum is een korte dagelijkse meeting waarin teamleden elkaar op de hoogte houden van hun voortgang.");
    }

    @Override
    protected String getKamerNaam() {
        return "Daily Kamer";
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
}
