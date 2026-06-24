package com.trabalho.teste.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.sql.Date;

import com.trabalho.teste.ConnDB.ConnectDb;
import com.trabalho.teste.Models.Aluno;

public class AlunoService {

    public AlunoService() {
    }
    public String Cadastrar(String matricula, String nome, Date data){
        String sql = "INSERT INTO alunos (matricula, nome, dataDeNascimento) VALUES (?,?,?)";
        try(
             Connection conn = ConnectDb.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
           stmt.setString(1, matricula);
            stmt.setString(2, nome);
            stmt.setDate(3,data);

            stmt.executeUpdate();
            return "Aluno Salvo!";
        } catch (Exception e) {
            return "Aluno não foi salvo";
        }
    }
    public static void SearchAll(){
        String sql = "SELECT * FROM alunos;";
    
        try(
             Connection conn = ConnectDb.conectar();
             Statement stmt = conn.createStatement();
        ) {
            
            System.out.println(stmt.execute(sql));
        } catch (Exception e) {
        }
    }
}
