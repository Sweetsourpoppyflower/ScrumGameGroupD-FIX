package game.hints;

import game.vraagstrategieen.KoppelStrategie;
import game.vraagstrategieen.MeerkeuzeStrategie;
import game.vraagstrategieen.OpenvraagStrategie;
import game.vraagstrategieen.VraagStrategie;

public class HelpHintProvider implements HintProvider {
    private VraagStrategie vraagStrategie;
    
    public HelpHintProvider(VraagStrategie vraagStrategie) {
        this.vraagStrategie = vraagStrategie;
    }
    
    @Override
    public String getHint() {
        if (vraagStrategie instanceof OpenvraagStrategie) {
            return "Denk aan de belangrijkste elementen van Scrum en probeer sleutelwoorden te gebruiken in je antwoord.";
        } else if (vraagStrategie instanceof MeerkeuzeStrategie) {
            return "Elimineer eerst de opties waarvan je zeker weet dat ze onjuist zijn.";
        } else if (vraagStrategie instanceof KoppelStrategie) {
            return "Begin met de termen die je zeker weet en werk van daaruit verder.";
        } else {
            return "Lees de vraag nog eens goed door en denk aan wat je over Scrum hebt geleerd.";
        }
    }
}
