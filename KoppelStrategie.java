import java.util.Map;

public class KoppelStrategie implements VraagStrategie {
    private String positieveFeedback;
    private String negatieveFeedback;
    private String vraag;
    private Map<String, String> correcteAntwoorden;
    
    public KoppelStrategie(String vraag, Map<String, String> correcteAntwoorden,
                          String positieveFeedback, String negatieveFeedback) {
        this.vraag = vraag;
        this.correcteAntwoorden = correcteAntwoorden;
        this.positieveFeedback = positieveFeedback;
        this.negatieveFeedback = negatieveFeedback;
    }
    

    @Override
    public void toonVraag() {
        System.out.println(vraag);
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        // Format expected: "key=value"
        String[] parts = antwoord.split("=", 2);
        if (parts.length != 2) {
            return false;
        }
        
        String key = parts[0].trim();
        String value = parts[1].trim();
        
        String correctValue = correcteAntwoorden.get(key);
        return correctValue != null && correctValue.equalsIgnoreCase(value);
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
