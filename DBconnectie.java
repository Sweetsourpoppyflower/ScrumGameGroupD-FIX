import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnectie {
    private static final String URL = "jdbc:mysql://localhost:3306/SCRUM?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Syria2004";

    public static Connection maakConnectie() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Verbonden met de database.");
        } catch (SQLException e) {
            System.out.println("Fout bij het verbinden met de database: " + e.getMessage());
        }
        return connection;
    }
}
