package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Curso;

public class CursoDAO {
    public void inserirCurso(Curso curso) {
        String sql = "INSERT INTO curso (codigo, nome_curso, carga_horaria, professor_matricula) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexao.conectar();
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
}