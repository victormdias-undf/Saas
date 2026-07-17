package cursos.DAO;

import connection.integracaoBanco;
import cursos.model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void cadastrar(Curso curso) {
        String sql = "INSERT INTO curso (codigo, nome_curso, carga_horaria, professor_matricula) VALUES (?, ?, ?, ?)";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getCodigo());
            stmt.setString(2, curso.getNome_curso());
            stmt.setInt(3, curso.getCarga_horaria());
            stmt.setString(4, curso.getProfessor_matricula());

            stmt.executeUpdate();
            System.out.println("Curso inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir curso: " + e.getMessage());
        }
    }

    public Curso consultarPorCodigo(String codigo) {
        String sql = "SELECT * FROM curso WHERE codigo = ?";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                return new Curso(
                        resultado.getString("codigo"),
                        resultado.getString("nome_curso"),
                        resultado.getInt("carga_horaria"),
                        resultado.getString("professor_matricula")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar curso: " + e.getMessage());
        }

        return null;
    }

    public List<Curso> consultarPorNome(String nome) {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso WHERE nome_curso LIKE ?";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(new Curso(
                        resultado.getString("codigo"),
                        resultado.getString("nome_curso"),
                        resultado.getInt("carga_horaria"),
                        resultado.getString("professor_matricula")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar cursos por nome: " + e.getMessage());
        }

        return lista;
    }

    public List<Curso> listarTodos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                lista.add(new Curso(
                        resultado.getString("codigo"),
                        resultado.getString("nome_curso"),
                        resultado.getInt("carga_horaria"),
                        resultado.getString("professor_matricula")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar cursos: " + e.getMessage());
        }

        return lista;
    }

    public void editar(Curso curso) {
        String sql = "UPDATE curso SET nome_curso = ?, carga_horaria = ?, professor_matricula = ? WHERE codigo = ?";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome_curso());
            stmt.setInt(2, curso.getCarga_horaria());
            stmt.setString(3, curso.getProfessor_matricula());
            stmt.setString(4, curso.getCodigo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao editar curso: " + e.getMessage());
        }
    }

    public void excluir(String codigo) {
        String sql = "DELETE FROM curso WHERE codigo = ?";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir curso: " + e.getMessage());
        }
    }
}