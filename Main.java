
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        SpelInitiatie spelInitiatie = new SpelInitiatie();
        Spel spel = spelInitiatie.initializeGame("Scrum Avonturen Spel");
        
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

        spel.initializeCommandoVerwerker();
        spel.start();
    }
    // De main methode start het spel en voegt de verschillende kamers toe aan het spel.
    // Het spel wordt geïnitialiseerd met de naam "Scrum Avonturen Spel" en de kamers worden toegevoegd aan het spel.
    // De kamers bevatten specifieke informatie en vragen die relevant zijn voor het Scrum proces.
    // De spel.start() methode start het spel en laat de speler door de verschillende kamers navigeren.
}
