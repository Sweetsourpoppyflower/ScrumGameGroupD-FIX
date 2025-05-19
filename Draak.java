public class Draak implements Monster {
    private int levenspunten;

    public Draak() {
        this.levenspunten = 200;
    }

    @Override
    public void aanval() {
        System.out.println("🐉🐉🐉🐉🐉🐉 De draak spuugt vuur! 🐉🐉🐉🐉🐉🐉");
    }

    @Override
    public String getNaam() {
        return "Draak";
    }

    @Override
    public int getLevenspunten() {
        return levenspunten;
    }

    @Override
    public void versla(Speler speler) {
        System.out.println("Je hebt de draak verslagen!");
        speler.verhoogScrumKennis(levenspunten);
    }

    @Override
    public String beschrijving() {
        return "De draak is een machtig wezen met schubben en vurige adem.";
    }
}
