package game.assistent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import game.assistent.AssistentActie;
import game.kamers.Kamer;
import game.speler.Speler;

public class aMotivatief implements AssistentActie {
    private List<String> motivationalMessages = Arrays.asList(
            "Je denkt als een echte product owner!",
            "Wat een geweldige Scrum Master zou jij zijn!",
            "Je agile mindset is indrukwekkend!",
            "Je bent een natuurtalent in het prioriteren van taken!",
            "Je zou een uitstekende developer in een Scrum team zijn!",
            "Je inzicht in iteratieve ontwikkeling is bewonderenswaardig!",
            "Je bent een ster in het identificeren van waarde voor de klant!"
    );

    private Random random = new Random();

    @Override
    public void voerUit(Kamer kamer, Speler speler) {
        String booschap = motivationalMessages.get(random.nextInt(motivationalMessages.size()));
        System.out.println("\n🌟 " + booschap);
    }
}
