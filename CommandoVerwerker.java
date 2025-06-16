import java.sql.SQLException;
import java.util.List;

public class CommandoVerwerker {
    Assistent assistent = new Assistent();
    private List<Kamer> kamers;


    public CommandoVerwerker(List<Kamer> kamers) {
        this.kamers = kamers;
    }

    public void verwerkCommand(String command, Speler speler) throws SQLException, InterruptedException {
        if (command.startsWith("ga naar kamer")) {
            verwerkBeweging(command.split(" "), speler);
        }
        else if (command.equals("gebruik joker")) {
            verwerkJokerGebruik(speler);
        }
        else if (command.equals("status")) {
            verwerkStatus(speler);
        }
        else if (command.equals("joker")) {
            JokerSelector.verwerkJokerKeuze(speler);
        }
        else if (command.equals("assistent")) {
            verwerkAssistentGebruik(speler);
        }
        else if (command.equals("stop")) {
            System.out.println("👋 Bedankt voor het spelen!");
            System.exit(0);
        }
        else {
            System.out.println("⚠️ Onbekend commando. Typ 'status' of 'ga naar kamer X'.");
        }
    }
    // De verwerkCommand methode verwerkt de ingevoerde commando's van de speler.
    // Afhankelijk van het commando wordt de juiste methode aangeroepen om de actie uit te voeren.
    // Het ondersteunt commando's zoals 'ga naar kamer X', 'gebruik joker', 'status', 'joker' en 'stop'.
    // Als het commando niet herkend wordt, geeft het een foutmelding weer.

    private void verwerkBeweging(String[] delen, Speler speler) {
        int kamerNr = Integer.parseInt(delen[delen.length - 1]);
        if (kamerNr >= 0 && kamerNr < kamers.size()) {
            speler.verplaats(kamerNr);
            System.out.println("🚶 Je bent verplaatst naar kamer " + kamerNr);
        } else {
            System.out.println("⚠️ Ongeldige kamer.");
        }
    }
    // De verwerkBeweging methode verwerkt de beweging van de speler naar een specifieke kamer.
    // Het controleert of het opgegeven kamer nummer geldig is en verplaatst de speler naar die kamer.

    private void verwerkJokerGebruik(Speler speler) {
        Joker joker = speler.getJoker();
        if (joker != null) {
            joker.gebruikIn(kamers.get(speler.getHuidigeKamer()));
        } else {
            System.out.println("⚠️ Je hebt geen joker.");
        }
    }
    // De verwerkJokerGebruik methode verwerkt het gebruik van een joker door de speler.


    private void verwerkStatus(Speler speler) {
        System.out.println("📍 Je bent nu in kamer: " + speler.getHuidigeKamer());
        System.out.println("ℹ️ Status: " + speler.getStatus());

        for (VoortgangsMonitor monitor : speler.getObservers()) {
            System.out.println(monitor.getVoortgangsInfo());
        }
    }
    // De verwerkStatus methode toont de huidige status van de speler, inclusief de huidige kamer en andere relevante informatie.
    // Het geeft ook informatie van alle voortgangsmonitors die aan de speler zijn gekoppeld.

    private void verwerkAssistentGebruik(Speler speler) {
        Kamer huidigeKamer = kamers.get(speler.getHuidigeKamer());
        if (huidigeKamer.heeftAssistent()) {
            assistent.activate(huidigeKamer, speler);
        } else {
            System.out.println("⚠️ De assistent is niet beschikbaar in deze kamer.");
        }
    }



}
