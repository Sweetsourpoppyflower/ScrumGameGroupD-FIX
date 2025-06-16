package game.kamers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import game.monsters.Reuzenspin;
import game.vraagstrategieen.VraagStrategie;
import game.vraagstrategieen.KoppelStrategie;
import game.layouts.KamerAsciiLayouts;
import game.spel.Vertraag;
import game.assistent.Assistent;
import game.objects.KamerInfo;

public class KamerPlanning extends Kamer {
    private Boolean heeftAssistent = true;
    private Assistent assistent;
    private String planningDetails;

    public KamerPlanning(String beschrijving, String planningDetails) {
        super(beschrijving);
        this.planningDetails = planningDetails;
        this.vraag = maakPlanningVraag();
        this.monster = new Reuzenspin();
        this.assistent = new Assistent();
    }

    private VraagStrategie maakPlanningVraag() {
        String vraag = "Koppel de juiste termen aan elkaar door het antwoord in het formaat 'term=definitie' of 'definitie=term' te geven.\n" +
                       "Geef alle antwoorden op één regel, gescheiden door komma's (bijv. '1=D, 2=A, 3=B, 4=E, 5=C' of 'A=2, B=3, C=5, D=1, E=4'):\n" +
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

    public void gebruikKamerInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("❓ Wil je meer informatie over deze kamer? (ja/nee)");
        String antwoord = scanner.nextLine().toLowerCase();
        if (antwoord.equals("ja") || antwoord.equals("j")) {
            KamerInfo kamerInfo = new KamerInfo();
            kamerInfo.showMessage("Dit is de planning kamer. Hier maak je de sprint planning." +
                    " Zorg ervoor dat je de juiste taken selecteert voor de komende sprint.");
        } else {
            System.out.println("👍 Oké, laten we verder gaan.");
        }
    }

    @Override
    public void geefHint() {
        System.out.println("💡 Hint: De Sprint Planning is een meeting waarin het team bepaalt wat er in de komende sprint gedaan zal worden.");
    }

    @Override
    protected String getKamerNaam() {
        return "Planning Kamer";
    }

    @Override
    public void betreed() throws SQLException, InterruptedException {
        System.out.println(KamerAsciiLayouts.getPlanningLayout());
        System.out.println("👋 Welkom in de Planning Kamer!");
        Vertraag.inMilliseconden(400);
        System.out.println(beschrijving);
        Vertraag.inMilliseconden(400);
        System.out.println("📋 Planning Details: " + planningDetails);
        Vertraag.inMilliseconden(400);
        System.out.println("\n🕷️ Er verschijnt een " + monster.getNaam() + "!");
        Vertraag.inMilliseconden(400);
        System.out.println(monster.beschrijving());
        Vertraag.inMilliseconden(400);
        monster.aanval();
        Vertraag.inMilliseconden(300);
        gebruikKamerInfo();
        stelVraag();
    }

    public void toonPlanning() {
        System.out.println("📋 " + planningDetails);
    }

    public Boolean heeftAssistent() {
        return heeftAssistent;
    }
}
