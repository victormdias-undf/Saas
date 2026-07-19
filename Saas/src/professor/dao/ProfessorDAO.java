package professor.dao;

import connection.integracaoBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import professor.model.Professor;

public class ProfessorDAO {

    public void inserirProfessor(Professor professor) {

        String sql = "INSERT INTO professor(nome_completo, data_nascimento, especialidade, matricula) VALUES (?,?,?,?)";

        try {

            Connection conexao = integracaoBanco.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, professor.getNomeCompleto());
            ps.setDate(2, Date.valueOf(professor.getDataNascimento()));
            ps.setString(3, professor.getEspecialidade());
            ps.setString(4, professor.getMatricula());

            ps.executeUpdate();

            ps.close();
            conexao.close();

            System.out.println("Professor cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Professor buscarPorMatricula(String matricula) {

        String sql = "SELECT * FROM professor WHERE matricula = ?";

        Professor professor = null;

        try {

            Connection conexao = integracaoBanco.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, matricula);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                professor = new Professor();

                professor.setNomeCompleto(rs.getString("nome_completo"));
                professor.setDataNascimento(rs.getDate("data_nascimento").toString());
                professor.setEspecialidade(rs.getString("especialidade"));
                professor.setMatricula(rs.getString("matricula"));
            }

            rs.close();
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professor;
    }

    public ArrayList<Professor> buscarPorNome(String nome) {

        String sql = "SELECT * FROM professor WHERE nome_completo LIKE ?";

        ArrayList<Professor> lista = new ArrayList<>();

        try {

            Connection conexao = integracaoBanco.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, "%" + nome + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Professor professor = new Professor();

                professor.setNomeCompleto(rs.getString("nome_completo"));
                professor.setDataNascimento(rs.getDate("data_nascimento").toString());
                professor.setEspecialidade(rs.getString("especialidade"));
                professor.setMatricula(rs.getString("matricula"));

                lista.add(professor);
            }

            rs.close();
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Professor> listarTodos() {

        String sql = "SELECT * FROM professor ORDER BY nome_completo";

        ArrayList<Professor> lista = new ArrayList<>();

        try {

            Connection conexao = integracaoBanco.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Professor professor = new Professor();

                professor.setNomeCompleto(rs.getString("nome_completo"));
                professor.setDataNascimento(
                        rs.getDate("data_nascimento").toString());
                professor.setEspecialidade(
                        rs.getString("especialidade"));
                professor.setMatricula(
                        rs.getString("matricula"));

                lista.add(professor);
            }

            rs.close();
            ps.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizarProfessor(Professor professor) {

        String sql = "UPDATE professor SET nome_completo=?, data_nascimento=?, especialidade=? WHERE matricula=?";

        try {

            Connection conexao = integracaoBanco.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, professor.getNomeCompleto());
            ps.setDate(2, Date.valueOf(professor.getDataNascimento()));
            ps.setString(3, professor.getEspecialidade());
            ps.setString(4, professor.getMatricula());

            ps.executeUpdate();

            ps.close();
            conexao.close();

            System.out.println("Professor atualizado!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProfessor(String matricula) {

        String sql = "DELETE FROM professor WHERE matricula=?";

        try {

            Connection conexao = integracaoBanco.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, matricula);

            ps.executeUpdate();

            ps.close();
            conexao.close();

            System.out.println("Professor excluído!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}