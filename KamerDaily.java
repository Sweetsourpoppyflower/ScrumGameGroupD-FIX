import java.sql.SQLException;

public class KamerDaily extends Kamer {
    private Boolean heeftAssistent = false;
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
        System.out.println("💡 Hint: De Daily Scrum is een korte dagelijkse meeting voor het Development Team om voortgang te bespreken.");
    }

    @Override
    protected String getKamerNaam() {
        return "Daily Kamer";
    }

    @Override
    public void betreed() throws SQLException, InterruptedException {
        System.out.println(KamerAsciiLayouts.getDailyLayout());
        System.out.println("👋 Welkom in de Daily Kamer!");
        Vertraag.inMilliseconden(400);
        System.out.println(beschrijving);
        Vertraag.inMilliseconden(400);
        System.out.println("Daily Details: " + dailyDetails);
        Vertraag.inMilliseconden(400);
        
        System.out.println("\n🧟 Er verschijnt een " + monster.getNaam() + "!");
        Vertraag.inMilliseconden(400);
        System.out.println(monster.beschrijving());
        Vertraag.inMilliseconden(400);
        monster.aanval();
        Vertraag.inMilliseconden(300);
        
        stelVraag();
    }

    public void toonDaily() {
        System.out.println("📅 " + dailyDetails);
    }

    public Boolean heeftAssistent() {
        return heeftAssistent;
    }
}
