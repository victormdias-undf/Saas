package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Matricula;

public class MatriculaDAO {

    public void inserirMatricula(Matricula matricula) {
        String sql = "INSERT INTO matricula (estudante_matricula, curso_codigo, data_matricula) VALUES (?, ?, ?)";

        // Agora usamos Conexao.getConnection() em vez de DriverManager...
        try (Connection conn = Conexao.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, matricula.getEstudante_matricula());
            stmt.setString(2, matricula.getCurso_codigo());
            stmt.setDate(3, new Date(matricula.getData_matricula().getTime()));

            stmt.executeUpdate();
            System.out.println("Matrícula realizada com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao realizar matrícula: " + e.getMessage());
        }
    }
}