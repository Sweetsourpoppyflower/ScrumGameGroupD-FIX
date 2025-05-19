public abstract class Kamer {
    protected String beschrijving;

    public Kamer(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public abstract void betreed();
}
