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

    public void saveSpeler(Speler speler) {
        SQLHelper.executeUpdate("INSERT INTO speler (naam, huidige_kamer, status, scrum_kennis) VALUES (?, ?, ?, ?)", 
            statement -> {
                statement.setString(1, speler.getNaam());
                statement.setInt(2, speler.getHuidigeKamer());
                statement.setString(3, speler.getStatus());
                statement.setInt(4, speler.getScrumKennis());
            });
    }

    public void updatePositie(String naam, int nieuwePositie) {
        SQLHelper.executeUpdate("UPDATE speler SET huidige_kamer = ? WHERE naam = ?", 
            statement -> {
                statement.setInt(1, nieuwePositie);
                statement.setString(2, naam);
            });
        
        addBezochteKamer(naam, nieuwePositie);
    }
    
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
    

    public void updateScrumKennis(String naam, int scrumKennis) {
        SQLHelper.executeUpdate("UPDATE speler SET scrum_kennis = ? WHERE naam = ?", 
            statement -> {
                statement.setInt(1, scrumKennis);
                statement.setString(2, naam);
            });
    }
    

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
}
