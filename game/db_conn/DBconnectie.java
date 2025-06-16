package game.db_conn;

import java.sql.*;

public class DBconnectie {
    private static final String URL = "jdbc:mysql://localhost:3306/SCRUM?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Syria2004";

    public static Connection maakConnectie() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
