public class SleutelJoker extends Joker {
    @Override
    public boolean kanGebruiktWordenIn(Kamer kamer) {
        return kamer instanceof KamerDaily || kamer instanceof KamerReview;
    }

    @Override
    protected void doeGebruikIn(Kamer kamer) {
        System.out.println("🔑 Sleutel Joker geactiveerd!");
        Speler speler = Spel.getHuidigeSpeler();
        kamer.geefExtraSleutel(speler);
        System.out.println("Je hebt nu " + speler.getAantalSleutels() + " sleutels.");
    }

    @Override
    public String beschrijving() {
        return "Dit is een Sleutel Joker. Je kan deze joker gebruiken om een extra sleutel te krijgen in de Daily Scrum of Review kamer.";
    }
}
