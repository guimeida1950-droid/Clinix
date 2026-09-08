import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/clinix";

    private static final String USUARIO = "root";

    private static final String SENHA = "root";

    public static Connection conectar() throws SQLException {

        try {

            return DriverManager.getConnection(
                    URL,
                    USUARIO,
                    SENHA);

        } catch (SQLException e) {

            System.out.println("ERRO DO MYSQL:");
            System.out.println(e.getMessage());

            throw e;
        }
    }
}