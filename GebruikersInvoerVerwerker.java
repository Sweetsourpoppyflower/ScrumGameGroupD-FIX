import java.util.Scanner;

public class GebruikersInvoerVerwerker {
    private Scanner scanner;

    public String getPlayerName() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.print("Voer je naam in: ");
        return scanner.nextLine();
    }
    // De getPlayerName methode vraagt de gebruiker om zijn naam in te voeren en retourneert deze als een String.
    // Het gebruikt een Scanner object om de invoer van de gebruiker te lezen vanaf de standaardinvoer (System.in).

    public String getNextCommand() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.print("> ");
        return scanner.nextLine();
    }
    // De getNextCommand methode vraagt de gebruiker om een commando in te voeren en retourneert dit als een String.
    // Het gebruikt een Scanner object om de invoer van de gebruiker te lezen vanaf de standaardinvoer (System.in).

    public String getJokerChoice() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.print("Kies een joker (hint/sleutel): ");
        return scanner.nextLine();
    }
}
