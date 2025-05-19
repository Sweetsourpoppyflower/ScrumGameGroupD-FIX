public interface VraagStrategie {

    void toonVraag();
    boolean controleerAntwoord(String antwoord);
    String positieveFeedback();
    String negatieveFeedback();
}
