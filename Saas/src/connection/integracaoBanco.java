package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class integracaoBanco {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/gerenciamentoEstudantil?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USUARIO = "app_estudantil";
    private static final String SENHA = obterSenha();

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException erro) {
            System.err.println("Driver JDBC do MySQL não encontrado no classpath. Certifique-se de rodar com: java -cp \"bin;library\\mysql-connector-j-9.7.0.jar\" App");
        }
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    private static String obterSenha() {
        String senha = System.getenv("estudantesenha");
        if (senha == null || senha.isBlank()) {
            return "estud@nte";
        }
        return senha;
    }
}
