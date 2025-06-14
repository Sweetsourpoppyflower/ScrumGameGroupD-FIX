import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        SpelInitiatie spelInitiatie = new SpelInitiatie();
        Spel spel = spelInitiatie.initializeGame("Scrum Excape Spel");


        KamerBacklog backlogKamer = new KamerBacklog(
                "Dit is de backlog kamer waar je de product backlog beheert.",
                "Product backlog met geprioriteerde user stories."
        );

        spel.voegKamerToe(backlogKamer);
        KamerPlanning planningKamer = new KamerPlanning(
                "Dit is de planning kamer waar je de sprint planning doet.",
                "Sprint planning voor de komende twee weken."
        );
        spel.voegKamerToe(planningKamer);

        KamerDaily dailyKamer = new KamerDaily(
                "Dit is de daily kamer waar je de dagelijkse stand-up houdt.",
                "Dagelijkse stand-up meeting om 9:00 uur."
        );
        spel.voegKamerToe(dailyKamer);

        KamerReview reviewKamer = new KamerReview(
                "Dit is de review kamer waar je de sprint review houdt.",
                85
        );
        spel.voegKamerToe(reviewKamer);

        KamerRetro retroKamer = new KamerRetro(
                "Dit is de retrospective kamer waar je terugkijkt op de sprint.",
                "Wat ging goed, wat kan beter, en wat gaan we anders doen?"
        );
        spel.voegKamerToe(retroKamer);

        KamerFinale finaleKamer = new KamerFinale(
                "Dit is de finale kamer waar je je Scrum kennis moet bewijzen om het spel te winnen."
        );
        spel.voegKamerToe(finaleKamer);

        spel.initializeCommandoVerwerker();
        spel.start();

    }

}
