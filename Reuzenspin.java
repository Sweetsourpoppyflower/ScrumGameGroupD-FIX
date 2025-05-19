public class Reuzenspin implements Monster {
    private int levenspunten;

    public Reuzenspin() {
        this.levenspunten = 80;
    }

    @Override
    public void aanval() {
        System.out.println("🕷️🕷️🕷️🕷️🕷️🕷️ De reuzenspin spuit gif! 🕷️🕷️🕷️🕷️🕷️🕷️");
    }

    @Override
    public String getNaam() {
        return "Reuzenspin";
    }

    @Override
    public int getLevenspunten() {
        return levenspunten;
    }

    @Override
    public void versla(Speler speler) {
        System.out.println("Je hebt de reuzenspin verslagen!");
        speler.verhoogScrumKennis(levenspunten);
    }

    @Override
    public String beschrijving() {
        return "Een enorme spin met acht harige poten en giftige kaken.";
    }
}
