import java.sql.SQLException;

public class Eindscherm {
    public String Abdul = "Abdulkadir Atik";
    public String Andio = "Andio Garia";
    public String Faouzane = "Faouzane Djamgbedja";
    public String GroepsNaam = "GROEP D";

    public void rolCredits() throws SQLException, InterruptedException {
        System.out.println(KamerAsciiLayouts.getEindschermLayout());
        System.out.println("Mogelijk gemaakt door:");
        Vertraag.inMilliseconden(500);
        System.out.print(Abdul);
        Vertraag.inMilliseconden(500);
        System.out.print(", ");
        System.out.print(Andio);
        Vertraag.inMilliseconden(500);
        System.out.print(" en ");
        System.out.print(Faouzane);


        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("1 - Start het spel opnieuw");
        System.out.println("2 - Sluit het spel af");
        System.out.print("Kies een optie: ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int keuze = scanner.nextInt();
        if (keuze == 1) {
            System.out.println("Het spel wordt opnieuw gestart...");
            Vertraag.inMilliseconden(1000);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
            Main.main(new String[0]);
        } else if (keuze == 2) {
            System.out.println("Bedankt voor het spelen! Tot de volgende keer!");
            System.exit(0);
        } else {
            System.out.println("Ongeldige keuze. Het spel wordt afgesloten.");
            System.exit(0);
        }
    }



}
