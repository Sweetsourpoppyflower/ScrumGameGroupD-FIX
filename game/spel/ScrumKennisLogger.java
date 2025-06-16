package game.spel;

import game.speler.Speler;
import game.speler.VoortgangsMonitor;

public class ScrumKennisLogger implements VoortgangsMonitor {
    private int laatsteScrumKennis;
    private int vorigeScrumKennis;
    private boolean heeftPuntenVerloren;
    private int verliesPunten;
    
    public ScrumKennisLogger() {
        this.laatsteScrumKennis = 0;
        this.vorigeScrumKennis = 0;
        this.heeftPuntenVerloren = false;
        this.verliesPunten = 0;
    }
    
    @Override
    public void update(Speler speler) {
        int nieuweScrumKennis = speler.getScrumKennis();

        if (nieuweScrumKennis < vorigeScrumKennis) {
            heeftPuntenVerloren = true;
            verliesPunten = vorigeScrumKennis - nieuweScrumKennis;
        } else {
            heeftPuntenVerloren = false;
        }
        
        vorigeScrumKennis = laatsteScrumKennis;
        laatsteScrumKennis = nieuweScrumKennis;
    }
    
    @Override
    public String getVoortgangsInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Scrum Kennis: ").append(laatsteScrumKennis).append(" punten");
        
        if (heeftPuntenVerloren) {
            info.append("\nJe hebt ").append(verliesPunten).append(" punten verloren door een fout antwoord!");
        }
        
        info.append("\nLet op: Bij een fout antwoord worden de helft van de monster levenspunten van je Scrum kennis afgetrokken!");
        return info.toString();
    }
}
