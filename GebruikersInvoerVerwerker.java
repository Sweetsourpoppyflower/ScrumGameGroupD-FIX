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

    public String getNextCommand() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.print("> ");
        return scanner.nextLine();
    }

    public String getJokerChoice() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        System.out.print("Kies een joker (hint/sleutel): ");
        return scanner.nextLine();
    }
}
