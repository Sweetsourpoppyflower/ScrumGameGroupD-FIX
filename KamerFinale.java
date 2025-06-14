import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KamerFinale extends Kamer {
    private int poort = 1250;
    private List<VraagStrategie> vragen;
    private Eindscherm eindscherm = new Eindscherm();

    public KamerFinale(String beschrijving) {
        super(beschrijving);
        this.vragen = maakVragen();
    }

    private List<VraagStrategie> maakVragen() {
        List<VraagStrategie> vragen = new ArrayList<>();

        // Vraag 1
        vragen.add(new MeerkeuzeStrategie(
                "Wat is de typische duur van een Sprint in Scrum?",
                new String[] {
                        "1 week",
                        "2-4 weken",
                        "6 weken",
                        "3 maanden"
                },
                "2-4 weken",
                "Correct! Sprints zijn meestal 2-4 weken lang.",
                "Dat is niet correct. Sprints zijn meestal 2-4 weken lang."
        ));

        // Vraag 2
        vragen.add(new MeerkeuzeStrategie(
                "Wie is verantwoordelijk voor het beheren van de Product Backlog?",
                new String[] {
                        "Scrum Master",
                        "Development Team",
                        "Product Owner",
                        "Project Manager"
                },
                "Product Owner",
                "Correct! De Product Owner is verantwoordelijk voor het beheren van de Product Backlog.",
                "Dat is niet correct. De Product Owner is verantwoordelijk voor het beheren van de Product Backlog."
        ));

        // Vraag 3
        vragen.add(new MeerkeuzeStrategie(
                "Wat is het doel van de Daily Scrum?",
                new String[] {
                        "Om de voortgang aan de Product Owner te rapporteren",
                        "Om het Development Team te synchroniseren en een plan voor de komende 24 uur te maken",
                        "Om problemen op te lossen en technische discussies te voeren",
                        "Om de Sprint Backlog bij te werken"
                },
                "Om het Development Team te synchroniseren en een plan voor de komende 24 uur te maken",
                "Correct! De Daily Scrum is bedoeld om het team te synchroniseren en een plan voor de komende 24 uur te maken.",
                "Dat is niet correct. De Daily Scrum is bedoeld om het team te synchroniseren en een plan voor de komende 24 uur te maken."
        ));

        // Vraag 4
        vragen.add(new MeerkeuzeStrategie(
                "Wat is een 'Definition of Done' in Scrum?",
                new String[] {
                        "Een lijst met alle taken die nog moeten worden voltooid",
                        "Een formele beschrijving van de staat van het increment wanneer het voldoet aan de kwaliteitseisen",
                        "Een document dat de projectdoelen beschrijft",
                        "Een lijst met acceptatiecriteria voor een specifieke user story"
                },
                "Een formele beschrijving van de staat van het increment wanneer het voldoet aan de kwaliteitseisen",
                "Correct! De Definition of Done is een formele beschrijving van de staat van het increment wanneer het voldoet aan de kwaliteitseisen.",
                "Dat is niet correct. De Definition of Done is een formele beschrijving van de staat van het increment wanneer het voldoet aan de kwaliteitseisen."
        ));

        // Vraag 5
        vragen.add(new MeerkeuzeStrategie(
                "Welke van de volgende is GEEN Scrum aspect?",
                new String[] {
                        "Product Backlog",
                        "Sprint Backlog",
                        "Burndown Chart",
                        "Increment"
                },
                "Burndown Chart",
                "Correct! Een Burndown Chart is een hulpmiddel, maar geen officieel Scrum artefact. De officiële artefacten zijn Product Backlog, Sprint Backlog en Increment.",
                "Dat is niet correct. Een Burndown Chart is een hulpmiddel, maar geen officieel Scrum artefact. De officiële artefacten zijn Product Backlog, Sprint Backlog en Increment."
        ));

        return vragen;
    }

    private void stelAlleVragen() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Speler speler = Spel.getHuidigeSpeler();

        for (int i = 0; i < vragen.size(); i++) {
            VraagStrategie vraag = vragen.get(i);
            System.out.println("\nVraag " + (i+1) + " van " + vragen.size() + ":");
            vraag.toonVraag();

            System.out.print("\nJouw antwoord: ");
            String antwoord = scanner.nextLine();

            if (vraag.controleerAntwoord(antwoord)) {
                System.out.println(vraag.positieveFeedback());
                speler.verhoogScrumKennis(200);
                System.out.println("Je krijgt 200 Scrum kennis punten! Totaal: " + speler.getScrumKennis());
            } else {
                System.out.println(vraag.negatieveFeedback());
                speler.verhoogScrumKennis(-50);
                System.out.println("Je verliest 50 Scrum kennis punten. Totaal: " + speler.getScrumKennis());
            }
        }
    }

    private void controleerWinst() throws SQLException, InterruptedException {
        Speler speler = Spel.getHuidigeSpeler();

        if (speler.getScrumKennis() >= poort) {
            System.out.println("\nGefeliciteerd! Je hebt genoeg Scrum kennis (" + speler.getScrumKennis() +
                    " punten) om door de poort te gaan!");
            Vertraag.inMilliseconden(400);
            System.out.println("Je hebt het spel gewonnen!");
            Vertraag.inMilliseconden(1000);

            eindscherm.rolCredits();
        } else {
            System.out.println("\nJe hebt " + speler.getScrumKennis() + " Scrum kennis punten.");
            System.out.println("Je hebt minstens " + poort + " punten nodig om door de poort te gaan en te winnen.");
            System.out.println("Nog " + (poort - speler.getScrumKennis()) + " punten te gaan!");
        }
    }

    @Override
    void geefHint() {
        System.out.println("Hint: Beantwoord de vragen correct om Scrum kennis punten te verdienen.");
    }

    @Override
    public void betreed() throws SQLException, InterruptedException {
        System.out.println(KamerAsciiLayouts.getFinaleLayout());
        System.out.println("Welkom in de Finale Kamer!");
        Vertraag.inMilliseconden(400);
        System.out.println(beschrijving);
        Vertraag.inMilliseconden(400);
        System.out.println("\nDit is je laatste uitdaging. Beantwoord 5 vragen over Scrum.");
        Vertraag.inMilliseconden(400);
        System.out.println("Voor elk correct antwoord krijg je 200 Scrum kennis punten.");
        Vertraag.inMilliseconden(400);
        System.out.println("Voor elk fout antwoord verlies je 50 Scrum kennis punten.");
        Vertraag.inMilliseconden(400);
        System.out.println("Je hebt " + poort + " Scrum kennis punten nodig om te winnen.");
        Vertraag.inMilliseconden(400);

        stelAlleVragen();
        controleerWinst();
    }

    @Override
    protected String getKamerNaam() {
        return "Finale Kamer";
    }
}
