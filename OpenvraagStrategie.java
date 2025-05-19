import java.util.Objects;

public class OpenvraagStrategie implements VraagStrategie {
    private String vraag;
    private String antwoord;
    private String[] sleutelwoorden;
    private String positieveFeedback;
    private String negatieveFeedback;
    
    public OpenvraagStrategie(String vraag, String antwoord, String[] sleutelwoorden,
                             String positieveFeedback, String negatieveFeedback) {
        this.vraag = vraag;
        this.antwoord = antwoord;
        this.sleutelwoorden = sleutelwoorden;
        this.positieveFeedback = positieveFeedback;
        this.negatieveFeedback = negatieveFeedback;
    }
    
    @Override
    public void toonVraag() {
        System.out.println(vraag);
    }

    @Override
    public boolean controleerAntwoord(String gegevenAntwoord) {
        if (gegevenAntwoord.equalsIgnoreCase(antwoord)) {
            return true;
        }

        for (String sleutelwoord : sleutelwoorden) {
            if (gegevenAntwoord.toLowerCase().contains(sleutelwoord.toLowerCase())) {
                return true;
            }
        }
        return false;
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
