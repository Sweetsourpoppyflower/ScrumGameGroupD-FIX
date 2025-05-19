public class MeerkeuzeStrategie implements VraagStrategie{
    private String positieveFeedback;
    private String negatieveFeedback;
    private String vraag;
    private String[] opties;
    private String correctAntwoord;
    
    public MeerkeuzeStrategie(String vraag, String[] opties, String correctAntwoord, 
                             String positieveFeedback, String negatieveFeedback) {
        this.vraag = vraag;
        this.opties = opties;
        this.correctAntwoord = correctAntwoord;
        this.positieveFeedback = positieveFeedback;
        this.negatieveFeedback = negatieveFeedback;
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
        return antwoord.equalsIgnoreCase(correctAntwoord);
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
