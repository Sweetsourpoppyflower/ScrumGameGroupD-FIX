public class MeerkeuzeStrategie implements VraagStrategie {
    private String positieveFeedback;
    private String negatieveFeedback;
    private String vraag;
    private String[] opties;
    private String correctAntwoord;
    private int correcteNrAntwoord;

    public MeerkeuzeStrategie(String vraag, String[] opties, String correctAntwoord,
                              String positieveFeedback, String negatieveFeedback) {
        this.vraag = vraag;
        this.opties = opties;
        this.correctAntwoord = correctAntwoord;
        this.positieveFeedback = positieveFeedback;
        this.negatieveFeedback = negatieveFeedback;
        this.correcteNrAntwoord = bepaalCorrecteIndex();
    }

    private int bepaalCorrecteIndex() {
        for (int i = 0; i < opties.length; i++) {
            if (opties[i].equalsIgnoreCase(correctAntwoord)) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public void toonVraag() {
        System.out.println(vraag);
        for (int i = 0; i < opties.length; i++) {
            System.out.println((i + 1) + ". " + opties[i]);
        }
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        try {
            int keuze = Integer.parseInt(antwoord);
            return keuze == correcteNrAntwoord;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String positieveFeedback() {
        return positieveFeedback;
    }

    @Override
    public String negatieveFeedback() {
        return negatieveFeedback;
    }
}
