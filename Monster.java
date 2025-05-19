public interface Monster {
    void aanval();
    String getNaam();
    int getLevenspunten();
    void versla(Speler speler);
    String beschrijving();
}
