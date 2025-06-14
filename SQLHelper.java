import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHelper {


    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }

    public interface SQLFunction<T, R> {
        R apply(T t) throws SQLException;
    }

    public static void executeUpdate(String sql, SQLConsumer<PreparedStatement> parameterSetter) {
        try (Connection connection = DBconnectie.maakConnectie();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            parameterSetter.accept(statement);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
            System.out.println( e.getMessage());
        }

        return null;
    }
}
