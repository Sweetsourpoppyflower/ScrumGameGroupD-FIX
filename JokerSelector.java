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

    public static void verwerkJokerKeuze(Speler speler) {
        GebruikersInvoerVerwerker invoerVerwerker = new GebruikersInvoerVerwerker();

        System.out.println("Wil je een HintJoker of een SleutelJoker? (hint/sleutel)");
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

}
