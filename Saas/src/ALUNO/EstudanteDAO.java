package aluno;
import connection.integracaoBanco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO {


    public void cadastrar(Estudante estudante) {
        String sql = "INSERT INTO estudantes (matricula, nome_completo, data_nascimento) VALUES (?, ?, ?)";


        try {
            Connection conexao = integracaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);


            stmt.setString(1, estudante.getMatricula());
            stmt.setString(2, estudante.getNomeCompleto());
            stmt.setString(3, estudante.getDataNascimento());


            stmt.executeUpdate();


            stmt.close();
            conexao.close();


        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar estudante: " + erro.getMessage());
        }
    }


    public Estudante consultarPorMatricula(String matricula) {
        String sql = "SELECT * FROM estudantes WHERE matricula = ?";


        try {
            Connection conexao = integracaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);


            stmt.setString(1, matricula);


            ResultSet resultado = stmt.executeQuery();


            if (resultado.next()) {
                Estudante estudante = new Estudante(
                        resultado.getString("matricula"),
                        resultado.getString("nome_completo"),
                        resultado.getString("data_nascimento")
                );


                stmt.close();
                conexao.close();


                return estudante;
            }


            stmt.close();
            conexao.close();


        } catch (SQLException erro) {
            System.out.println("Erro ao consultar estudante: " + erro.getMessage());
        }


        return null;
    }


    public List<Estudante> consultarPorNome(String nome) {
        List<Estudante> lista = new ArrayList<>();


        String sql = "SELECT * FROM estudantes WHERE nome_completo LIKE ?";


        try {
            Connection conexao = integracaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);


            stmt.setString(1, "%" + nome + "%");


            ResultSet resultado = stmt.executeQuery();


            while (resultado.next()) {
                Estudante estudante = new Estudante(
                        resultado.getString("matricula"),
                        resultado.getString("nome_completo"),
                        resultado.getString("data_nascimento")
                );


                lista.add(estudante);
            }


            stmt.close();
            conexao.close();


        } catch (SQLException erro) {
            System.out.println("Erro ao consultar por nome: " + erro.getMessage());
        }


        return lista;
    }


    public void editar(Estudante estudante) {
        String sql = "UPDATE estudantes SET nome_completo = ?, data_nascimento = ? WHERE matricula = ?";


        try {
            Connection conexao = integracaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);


            stmt.setString(1, estudante.getNomeCompleto());
            stmt.setString(2, estudante.getDataNascimento());
            stmt.setString(3, estudante.getMatricula());


            stmt.executeUpdate();


            stmt.close();
            conexao.close();


        } catch (SQLException erro) {
            System.out.println("Erro ao editar estudante: " + erro.getMessage());
        }
    }


    public void excluir(String matricula) {
        String sql = "DELETE FROM estudantes WHERE matricula = ?";


        try {
            Connection conexao = integracaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);


            stmt.setString(1, matricula);


            stmt.executeUpdate();


            stmt.close();
            conexao.close();


        } catch (SQLException erro) {
            System.out.println("Erro ao excluir estudante: " + erro.getMessage());
        }
    }


    public List<Estudante> listarTodos() {
        List<Estudante> lista = new ArrayList<>();


        String sql = "SELECT * FROM estudantes";


        try {
            Connection conexao = integracaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);


            ResultSet resultado = stmt.executeQuery();


            while (resultado.next()) {
                Estudante estudante = new Estudante(
                        resultado.getString("matricula"),
                        resultado.getString("nome_completo"),
                        resultado.getString("data_nascimento")
                );


                lista.add(estudante);
            }


            stmt.close();
            conexao.close();


        } catch (SQLException erro) {
            System.out.println("Erro ao listar estudantes: " + erro.getMessage());
        }


        return lista;
    }
}

