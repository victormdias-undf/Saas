package relatorios;

import aluno.Estudante;
import aluno.EstudanteDAO;
import connection.integracaoBanco;
import cursos.DAO.CursoDAO;
import cursos.model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import professor.dao.ProfessorDAO;
import professor.model.Professor;

public class RelatorioService {

    private final EstudanteDAO estudanteDAO = new EstudanteDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    public List<Estudante> listarEstudantes() {
        return estudanteDAO.listarTodos();
    }

    public List<Professor> listarProfessores() {
        return professorDAO.listarTodos();
    }

    public List<Curso> listarCursos() {
        return cursoDAO.listarTodos();
    }

    public List<String> listarCursosPorEstudante(String matriculaEstudante) {
        List<String> cursos = new ArrayList<>();
        String sql = "SELECT c.nome_curso FROM matricula m JOIN curso c ON c.codigo = m.curso_codigo WHERE m.estudante_matricula = ?";

        try (Connection conexao = integracaoBanco.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, matriculaEstudante);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                cursos.add(resultado.getString("nome_curso"));
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao listar cursos do estudante: " + erro.getMessage());
        }

        return cursos;
    }

    public List<String> listarCursosPorProfessor(String matriculaProfessor) {
        List<String> cursos = new ArrayList<>();
        String sql = "SELECT nome_curso FROM curso WHERE professor_matricula = ?";

        try (Connection conexao = integracaoBanco.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, matriculaProfessor);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                cursos.add(resultado.getString("nome_curso"));
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao listar cursos do professor: " + erro.getMessage());
        }

        return cursos;
    }

    public String buscarNomeProfessor(String matriculaProfessor) {
        String sql = "SELECT nome_completo FROM professor WHERE matricula = ?";

        try (Connection conexao = integracaoBanco.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, matriculaProfessor);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                return resultado.getString("nome_completo");
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao buscar professor: " + erro.getMessage());
        }

        return "Não informado";
    }

    public int contarAlunosMatriculados(String codigoCurso) {
        String sql = "SELECT COUNT(*) AS total FROM matricula WHERE curso_codigo = ?";

        try (Connection conexao = integracaoBanco.conectar(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, codigoCurso);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                return resultado.getInt("total");
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao contar alunos matriculados: " + erro.getMessage());
        }

        return 0;
    }
}
