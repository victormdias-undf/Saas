package cursos.DAO;

import connection.integracaoBanco;
import cursos.model.Matricula;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public void inserirMatricula(Matricula matricula) {
        String validarEstudanteSql = "SELECT 1 FROM estudante WHERE matricula = ?";
        String validarCursoSql = "SELECT 1 FROM curso WHERE codigo = ?";
        String sql = "INSERT INTO matricula (estudante_matricula, curso_codigo, data_matricula) VALUES (?, ?, ?)";

        try (Connection conn = integracaoBanco.conectar()) {
            try (PreparedStatement validarEstudante = conn.prepareStatement(validarEstudanteSql)) {
                validarEstudante.setString(1, matricula.getEstudante_matricula());
                ResultSet rsEstudante = validarEstudante.executeQuery();
                if (!rsEstudante.next()) {
                    System.err.println("Estudante não encontrado para matrícula: " + matricula.getEstudante_matricula());
                    return;
                }
            }

            try (PreparedStatement validarCurso = conn.prepareStatement(validarCursoSql)) {
                validarCurso.setString(1, matricula.getCurso_codigo());
                ResultSet rsCurso = validarCurso.executeQuery();
                if (!rsCurso.next()) {
                    System.err.println("Curso não encontrado para código: " + matricula.getCurso_codigo());
                    return;
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, matricula.getEstudante_matricula());
                stmt.setString(2, matricula.getCurso_codigo());
                stmt.setDate(3, new Date(matricula.getData_matricula().getTime()));

                stmt.executeUpdate();
                System.out.println("Matrícula realizada com sucesso!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao realizar matrícula: " + e.getMessage());
        }
    }

    public List<Matricula> listarPorEstudante(String matriculaEstudante) {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula WHERE estudante_matricula = ?";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matriculaEstudante);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Matricula(
                        rs.getInt("id"),
                        rs.getString("estudante_matricula"),
                        rs.getString("curso_codigo"),
                        rs.getDate("data_matricula")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar matrículas do estudante: " + e.getMessage());
        }

        return lista;
    }

    public List<Matricula> listarPorCurso(String codigoCurso) {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula WHERE curso_codigo = ?";

        try (Connection conn = integracaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoCurso);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Matricula(
                        rs.getInt("id"),
                        rs.getString("estudante_matricula"),
                        rs.getString("curso_codigo"),
                        rs.getDate("data_matricula")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar matrículas do curso: " + e.getMessage());
        }

        return lista;
    }
}