public class Reuzenspin extends Monster {

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
    public String beschrijving() {
        return "Een enorme spin met acht harige poten en giftige kaken.";
    }
}
