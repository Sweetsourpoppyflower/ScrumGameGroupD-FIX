package game.kamers;

import java.sql.SQLException;
import game.monsters.Zombie;
import game.vraagstrategieen.VraagStrategie;
import game.vraagstrategieen.OpenvraagStrategie;
import game.layouts.KamerAsciiLayouts;
import game.spel.Vertraag;
import game.assistent.Assistent;

public class KamerReview extends Kamer {
    private Boolean heeftAssistent = true;
    private Assistent assistent;
    private int reviewScore;

    public KamerReview(String beschrijving, int reviewScore) {
        super(beschrijving);
        this.reviewScore = reviewScore;
        this.vraag = maakReviewVraag();
        this.monster = new Zombie();
        this.assistent = new Assistent();
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
    public void geefHint() {
        System.out.println("💡 Hint: De Sprint Review is bedoeld om de voltooide functionaliteiten te demonstreren en feedback te verzamelen van stakeholders.");
    }

    @Override
    protected String getKamerNaam() {
        return "Review Kamer";
    }

    @Override
    public void betreed() throws SQLException, InterruptedException {
        System.out.println(KamerAsciiLayouts.getReviewLayout());
        System.out.println("👋 Welkom in de Review Kamer!");
        Vertraag.inMilliseconden(400);
        System.out.println(beschrijving);
        Vertraag.inMilliseconden(400);
        System.out.println("⭐ Reviewscore: " + reviewScore);
        Vertraag.inMilliseconden(400);
        
        System.out.println("\n🧟 Er verschijnt een " + monster.getNaam() + "!");
        Vertraag.inMilliseconden(400);
        System.out.println(monster.beschrijving());
        Vertraag.inMilliseconden(400);
        monster.aanval();
        Vertraag.inMilliseconden(300);

        stelVraag();
    }

    public void toonReview() {
        System.out.println("⭐ Score: " + reviewScore);
    }

    public Boolean heeftAssistent() {
        return heeftAssistent;
    }
}
