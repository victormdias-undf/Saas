package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class integracaoBanco {

    private static final String URL =  "jdbc:mysql://localhost:3306/gerenciamentoestudantil"; 
    private static final String USUARIO = "app_estudantil"; // usuário do banco de dados
    private static final String SENHA = System.getenv("estudantesenha"); // senha do banco de dados

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA); // estabelece a conexão com o banco de dados
    }
}
