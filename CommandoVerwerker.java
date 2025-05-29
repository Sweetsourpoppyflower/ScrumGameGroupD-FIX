import java.sql.SQLException;
import java.util.List;

public class CommandoVerwerker {

    private List<Kamer> kamers;


    public CommandoVerwerker(List<Kamer> kamers) {
        this.kamers = kamers;
    }

    public void verwerkCommand(String command, Speler speler) throws SQLException {
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
        else if (command.equals("stop")) {
            System.out.println("Bedankt voor het spelen!");
            System.exit(0);
        }
        else {
            System.out.println("Onbekend commando. Typ 'status' of 'ga naar kamer X'.");
        }
    }

    private void verwerkBeweging(String[] delen, Speler speler) {
        int kamerNr = Integer.parseInt(delen[delen.length - 1]);
        if (kamerNr >= 0 && kamerNr < kamers.size()) {
            speler.verplaats(kamerNr);
            System.out.println("Je bent verplaatst naar kamer " + kamerNr);
        } else {
            System.out.println("Ongeldige kamer.");
        }
    }

    private void verwerkJokerGebruik(Speler speler) {
        Joker joker = speler.getJoker();
        if (joker != null) {
            joker.gebruikIn(kamers.get(speler.getHuidigeKamer()));
        } else {
            System.out.println("Je hebt geen joker.");
        }
    }

    private void verwerkStatus(Speler speler) {
        System.out.println("Je bent nu in kamer: " + speler.getHuidigeKamer());
        System.out.println("Status: " + speler.getStatus());

        for (VoortgangsMonitor monitor : speler.getObservers()) {
            System.out.println(monitor.getVoortgangsInfo());
        }
    }


}
