import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHelper {

    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }

    @FunctionalInterface
    public interface SQLFunction<T, R> {
        R apply(T t) throws SQLException;
    }

    public static void executeUpdate(String sql, SQLConsumer<PreparedStatement> parameterSetter) {
        try (Connection connection = DBconnectie.maakConnectie();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            parameterSetter.accept(statement);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL-fout bij update: " + e.getMessage());
        }
    }

    public static <T> T executeSelect(String sql, SQLConsumer<PreparedStatement> parameterSetter, SQLFunction<ResultSet, T> resultMapper) {
        try (Connection connection = DBconnectie.maakConnectie();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            parameterSetter.accept(statement);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultMapper.apply(resultSet);
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL-fout bij select: " + e.getMessage());
        }

        return null;
    }
}
