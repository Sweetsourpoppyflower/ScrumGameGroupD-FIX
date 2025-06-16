package game.assistent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import game.assistent.AssistentActie;
import game.speler.SpelerStatus;
import game.kamers.Kamer;
import game.speler.Speler;
public class aEducatief implements AssistentActie {
    List<String> educatieveInformatie;
    SpelerStatus status = new SpelerStatus();

    private static Random random = new Random();
    public aEducatief () {
        this.educatieveInformatie = Arrays.asList(
                "Scrum Stappenplan: 1. Backlog maken 2. Sprint plannen 3. Daily scrums houden 4. Sprint review 5. Retrospective",
                "Definitie van Done checklist: 1. Code geschreven 2. Tests geschreven 3. Code review gedaan 4. Documentatie bijgewerkt",
                "User Story template: Als [rol] wil ik [functionaliteit] zodat [doel]",
                "Planning Poker kaarten: 0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89",
                "Burndown chart voorbeeld voor sprint voortgang visualisatie"
        );

    }


    @Override
    public void voerUit(Kamer kamer, Speler speler) {
        int randomIndex = random.nextInt(educatieveInformatie.size());
        String educatieveInfo = educatieveInformatie.get(randomIndex);
        status.setEducatiefHulpMiddel(educatieveInfo);
        System.out.println("📚 Je hebt een educatief hulp middel ontvangen!");
    }
}
