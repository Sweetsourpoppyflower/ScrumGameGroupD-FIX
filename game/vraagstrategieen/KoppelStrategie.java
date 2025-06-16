package game.vraagstrategieen;

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
        if (antwoord == null || antwoord.trim().isEmpty()) {
            return false;
        }
        
        String[] pairs = antwoord.split(",");
        
        if (pairs.length != correcteAntwoorden.size()) {
            return false;
        }
        
        for (String pair : pairs) {
            String[] parts = pair.trim().split("=", 2);
            if (parts.length != 2) {
                return false;
            }
            
            String key = parts[0].trim();
            String value = parts[1].trim();
            
            boolean isCorrect = false;
            
            // Check if key->value is correct (term=definition)
            String correctValue = correcteAntwoorden.get(key);
            if (correctValue != null && correctValue.equalsIgnoreCase(value)) {
                isCorrect = true;
            }
            
            // Check if value->key is correct (definition=term)
            if (!isCorrect) {
                for (Map.Entry<String, String> entry : correcteAntwoorden.entrySet()) {
                    if (entry.getValue().equalsIgnoreCase(key) && entry.getKey().equalsIgnoreCase(value)) {
                        isCorrect = true;
                        break;
                    }
                }
            }
            
            if (!isCorrect) {
                return false;
            }
        }
        
        return true;
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
