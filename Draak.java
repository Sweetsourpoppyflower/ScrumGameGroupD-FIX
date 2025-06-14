public class Draak extends Monster {

    public Draak() {
        this.levenspunten = 200;
    }

    @Override
    public void aanval() {
        System.out.println("🐉🐉🐉🐉🐉🐉 De draak spuugt vuur! 🐉🐉🐉🐉🐉🐉");
        Vertraag.inMilliseconden(500);
    }

    @Override
    public String getNaam() {
        return "Draak";
    }

    @Override
    public String beschrijving() {
        return "De draak is een machtig wezen met schubben en vurige adem.";
    }
}
