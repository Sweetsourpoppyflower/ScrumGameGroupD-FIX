package game.speler;

import game.db_conn.SQLHelper;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class SpelerRepository {


    public SpelerRepository() {
        SQLHelper.executeUpdate(
                "CREATE TABLE IF NOT EXISTS bezochte_kamers (" +
                        "speler_naam VARCHAR(255), " +
                        "kamer_id INT, " +
                        "PRIMARY KEY (speler_naam, kamer_id))",
                statement -> {}
        );
    }
    // SpelerRepoistory() is de constructor van de SpelerRepository klasse.
    // Constructor van de SpelerRepository klasse, die een tabel voor bezochte kamers aanmaakt als deze nog niet bestaat.
    // De tabel heeft twee kolommen: speler_naam en kamer_id, die samen de primaire
    // sleutel vormen om duplicaten te voorkomen.
    // De SQLHelper.executeUpdate methode wordt gebruikt om de SQL-query uit te voeren die de tabel aanmaakt.


    public void saveSpeler(Speler speler) {
        SQLHelper.executeUpdate("INSERT INTO speler (naam, huidige_kamer, status, scrum_kennis) VALUES (?, ?, ?, ?)",
                statement -> {
                    statement.setString(1, speler.getNaam());
                    statement.setInt(2, speler.getHuidigeKamer());
                    statement.setString(3, speler.getStatus());
                    statement.setInt(4, speler.getScrumKennis());
                });
    }
    // saveSpeler(Speler speler) is een methode die de gegevens van een speler opslaat in de database.
    // Slaat de status van een speler op in de database.
    // De methode gebruikt een SQL INSERT statement om de gegevens van de speler in de speler tabel op te slaan.
    // De speler wordt geïdentificeerd aan de hand van zijn naam, huidige kamer, status en scrum kennis.


    public void updatePositie(String naam, int nieuwePositie) {
        SQLHelper.executeUpdate("UPDATE speler SET huidige_kamer = ? WHERE naam = ?",
                statement -> {
                    statement.setInt(1, nieuwePositie);
                    statement.setString(2, naam);
                });

        addBezochteKamer(naam, nieuwePositie);
    }
    // updatePositie(String naam, int nieuwePositie) is een methode die de huidige kamer van een speler bijwerkt in de database.
    // De methode gebruikt een SQL UPDATE statement om de huidige kamer van de speler te wijzigen.
    // De speler wordt geïdentificeerd aan de hand van zijn naam.
    // Na het bijwerken van de positie, wordt de methode addBezochteKamer aangeroepen om de nieuwe kamer toe te voegen aan de lijst van bezochte kamers van de speler.
    // Deze methode zorgt ervoor dat de database altijd up-to-date is met de laatste positie van de speler.


    public void addBezochteKamer(String spelerNaam, int kamerId) {
        SQLHelper.executeUpdate(
                "INSERT INTO bezochte_kamers (speler_naam, kamer_id) VALUES (?, ?) " +
                        "ON DUPLICATE KEY UPDATE speler_naam = speler_naam",
                statement -> {
                    statement.setString(1, spelerNaam);
                    statement.setInt(2, kamerId);
                }
        );
    }
    // addBezochteKamer(String spelerNaam, int kamerId) is een methode die een kamer toevoegt aan de lijst van bezochte kamers van een speler.
    // De methode gebruikt een SQL INSERT statement om de kamer toe te voegen aan de bezochte_kamers tabel.
    // Als de kamer al bestaat voor de speler, wordt er geen nieuwe rij toegevoegd, maar blijft de bestaande rij behouden.

    public Set<Integer> getBezochteKamers(String spelerNaam) {
        return SQLHelper.executeSelect(
                "SELECT kamer_id FROM bezochte_kamers WHERE speler_naam = ?",
                statement -> statement.setString(1, spelerNaam),
                resultSet -> {
                    Set<Integer> bezochteKamers = new HashSet<>();
                    try {
                        while (resultSet.next()) {
                            bezochteKamers.add(resultSet.getInt("kamer_id"));
                        }
                        return bezochteKamers;
                    } catch (SQLException e) {
                        System.out.println("Fout bij het ophalen van bezochte kamers: " + e.getMessage());
                        return new HashSet<>();
                    }
                }
        );
    }
    // getBezochteKamers(String spelerNaam) is een methode die de lijst van bezochte kamers van een speler ophaalt uit de database.
    // De methode gebruikt een SQL SELECT statement om de kamer_id's op te halen uit de bezochte_kamers tabel voor de opgegeven speler.
    // De resultaten worden verzameld in een Set om duplicaten te voorkomen en worden teruggegeven als een Set van Integer waarden.
    // Deze methode is handig om te controleren welke kamers een speler al heeft bezocht tijdens het spel.
    // Deze methode haalt de lijst van bezochte kamers op voor een specifieke speler uit de database.
    // Het gebruikt een SQL SELECT statement om de kamer_id's op te halen uit de bezochte_kamers tabel voor de opgegeven speler.


    public int getHuidigeKamer(String naam) {
        return SQLHelper.executeSelect("SELECT huidige_kamer FROM speler WHERE naam = ?",
                statement -> statement.setString(1, naam),
                resultSet -> {
                    try {
                        return resultSet.getInt("huidige_kamer");
                    } catch (SQLException e) {
                        System.out.println("Fout bij het ophalen van de huidige kamer: " + e.getMessage());
                        return 0;
                    }
                });
    }
    // getHuidigeKamer(String naam) is een methode die de huidige kamer van een speler ophaalt uit de database.
    // De methode gebruikt een SQL SELECT statement om de huidige kamer van de speler op te halen uit de speler tabel.
    // De speler wordt geïdentificeerd aan de hand van zijn naam.


    public String getStatus(String naam) {
        return SQLHelper.executeSelect("SELECT status FROM speler WHERE naam = ?",
                statement -> statement.setString(1, naam),
                resultSet -> {
                    try {
                        return resultSet.getString("status");
                    } catch (SQLException e) {
                        System.out.println("Fout bij het ophalen van de status: " + e.getMessage());
                        return "";
                    }
                });
    }
// getStatus(String naam) is een methode die de status van een speler ophaalt uit de database.
    // De methode gebruikt een SQL SELECT statement om de status van de speler op te halen uit de speler tabel.
    // De speler wordt geïdentificeerd aan de hand van zijn naam.
    // De status wordt teruggegeven als een String. Als er een fout optreedt bij het ophalen van de status, wordt een lege String geretourneerd en wordt er een foutmelding weergegeven in de console.

    public void saveSpelerStatus(SpelerStatus status) {
        SQLHelper.executeUpdate("INSERT INTO speler (naam, huidige_kamer, status, scrum_kennis) VALUES (?, ?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE huidige_kamer = ?, status = ?, scrum_kennis = ?",
                statement -> {
                    statement.setString(1, status.getNaam());
                    statement.setInt(2, status.getHuidigeKamer());
                    statement.setString(3, status.getStatus());
                    statement.setInt(4, status.getScrumKennis());
                    statement.setInt(5, status.getHuidigeKamer());
                    statement.setString(6, status.getStatus());
                    statement.setInt(7, status.getScrumKennis());
                });
    }
    // saveSpelerStatus(SpelerStatus status) is een methode die de status van een speler opslaat in de database.
    // De methode gebruikt een SQL INSERT statement om de gegevens van de speler in de speler tabel op te slaan.
    // Als de speler al bestaat, wordt de ON DUPLICATE KEY UPDATE clausule gebruikt om de huidige kamer, status en scrum kennis van de speler bij te werken.
    // De speler wordt geïdentificeerd aan de hand van zijn naam, huidige kamer, status en scrum kennis.


    public void updateScrumKennis(String naam, int scrumKennis) {
        SQLHelper.executeUpdate("UPDATE speler SET scrum_kennis = ? WHERE naam = ?",
                statement -> {
                    statement.setInt(1, scrumKennis);
                    statement.setString(2, naam);
                });
    }
// updateScrumKennis(String naam, int scrumKennis) is een methode die de scrum kennis van een speler bijwerkt in de database.
    // De methode gebruikt een SQL UPDATE statement om de scrum kennis van de speler te wijzigen.
    // De speler wordt geïdentificeerd aan de hand van zijn naam.


    public int getScrumKennis(String naam) {
        return SQLHelper.executeSelect("SELECT scrum_kennis FROM speler WHERE naam = ?",
                statement -> statement.setString(1, naam),
                resultSet -> {
                    try {
                        return resultSet.getInt("scrum_kennis");
                    } catch (SQLException e) {
                        System.out.println("Fout bij het ophalen van de scrum kennis: " + e.getMessage());
                        return 0;
                    }
                });
    }
// getScrumKennis(String naam) is een methode die de scrum kennis van een speler ophaalt uit de database.
    // De methode gebruikt een SQL SELECT statement om de scrum kennis van de speler op te halen uit de speler tabel.
    // De speler wordt geïdentificeerd aan de hand van zijn naam.
    
    public void clearBezochteKamers(String spelerNaam) {
        SQLHelper.executeUpdate(
                "DELETE FROM bezochte_kamers WHERE speler_naam = ?",
                statement -> statement.setString(1, spelerNaam)
        );
        System.out.println("Bezochte kamers voor speler " + spelerNaam + " zijn gewist.");
    }
    // clearBezochteKamers(String spelerNaam) is een methode die de lijst van bezochte kamers voor een specifieke speler leegt in de database.
    // De methode gebruikt een SQL DELETE statement om alle rijen in de bezochte_kamers tabel te verwijderen voor de opgegeven speler.
    // Deze methode is handig om de voortgang van een speler te resetten of om de database op te schonen.

}
