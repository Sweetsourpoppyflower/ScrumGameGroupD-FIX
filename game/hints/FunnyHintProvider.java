package game.hints;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FunnyHintProvider implements HintProvider {
    private List<String> funnyHints = Arrays.asList(
        "Voortaan niet hele avonden gamen, studeren is beter voor je!",
        "Misschien moet je minder koffie drinken en meer slapen?",
        "Heb je geprobeerd het aan te zetten en weer uit te zetten?",
        "Als programmeren makkelijk was, zou iedereen het doen!",
        "Zelfs een Scrum Master maakt wel eens fouten, maar jij wat vaker...",
        "Misschien moet je de Product Owner om hulp vragen? Oh wacht, die wil alleen maar meer features!",
        "Agile betekent niet dat je snel foute antwoorden mag geven!",
        "Heb je al geprobeerd het juiste antwoord te geven? Dat werkt meestal.",
        "Je bent bijna zo goed in Scrum als een waterval-manager!",
        "Volgens de planning zou je dit al moeten weten..."
    );
    
    private Random random = new Random();
    
    @Override
    public String getHint() {
        return funnyHints.get(random.nextInt(funnyHints.size()));
    }
}
