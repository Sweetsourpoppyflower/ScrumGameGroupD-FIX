public class JokerSelector {
    private static Joker createJoker(String type) {
        switch (type.toLowerCase()) {
            case "hint":
                return new HintJoker();
            case "sleutel":
                return new SleutelJoker();
            default:
                return null;
        }
    }
    // Deze methode maakt een Joker object aan op basis van de opgegeven type string.
    // Het accepteert "hint" of "sleutel" als geldige types en retourneert het overeenkomstige Joker object.

    public static void verwerkJokerKeuze(Speler speler) throws InterruptedException {
        GebruikersInvoerVerwerker invoerVerwerker = new GebruikersInvoerVerwerker();

        System.out.println("\n\nWil je een HintJoker of een SleutelJoker? (hint/sleutel)");
        String jokerKeuze = invoerVerwerker.getJokerChoice().toLowerCase();

        if (speler.getJoker() != null) {
            System.out.println("Je hebt al een joker toegewezen gekregen.");
            return;
        }

        Joker joker = createJoker(jokerKeuze);
        if (joker != null) {
            speler.setJoker(joker);
            System.out.println(speler.getJoker().beschrijving());
            System.out.println("Je kunt de joker later gebruiken met het commando 'gebruik joker'.");
        } else {
            System.out.println("Ongeldige keuze, geen joker toegewezen.");
        }
    }
    // Deze methode verwerkt de keuze van de speler voor een joker.
    // Het vraagt de speler om een keuze te maken tussen een HintJoker of een SleutelJoker.
    // Als de speler al een joker heeft, wordt er een melding gegeven en wordt er geen nieuwe joker toegewezen.
    // Als de keuze geldig is, wordt de joker toegewezen aan de speler en wordt er een beschrijving van de joker weergegeven.


}
