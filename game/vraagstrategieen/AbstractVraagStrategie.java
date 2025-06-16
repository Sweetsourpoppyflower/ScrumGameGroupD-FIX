package game.vraagstrategieen;

public abstract class AbstractVraagStrategie implements VraagStrategie {
    protected String vraag;
    protected String positieveFeedback;
    protected String negatieveFeedback;
    
    public AbstractVraagStrategie(String vraag, String positieveFeedback, String negatieveFeedback) {
        this.vraag = vraag;
        this.positieveFeedback = positieveFeedback;
        this.negatieveFeedback = negatieveFeedback;
    }
    
    @Override
    public void toonVraag() {
        System.out.println(vraag);
    }
    
    @Override
    public String positieveFeedback() {
        return positieveFeedback;
    }
    
    @Override
    public String negatieveFeedback() {
        return negatieveFeedback;
    }
    
    @Override
    public abstract boolean controleerAntwoord(String antwoord);
}
