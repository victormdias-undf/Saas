package com.trabalho.teste.ConnDB;
import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {
    public static void Criar(){
        String sql = """
                 CREATE TABLE IF NOT EXISTS alunos (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 nome VARCHAR(100) NOT NULL,
                                 matricula VARCHAR(150) NOT NULL,
                                 dataDeNascimento DATE NOT NULL);""";
        String sql2 = """
                 CREATE TABLE IF NOT EXISTS professores (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 nome VARCHAR(100) NOT NULL,
                                 matricula VARCHAR(150) NOT NULL,
                                 dataDeNascimento DATE NOT NULL,
                                 especializacao VARCHAR(150) NOT NULL);""";
        try (
            Connection conn = ConnectDb.conectar();
            Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
            stmt.execute(sql2);
            System.out.println("Tabela criada!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

                            
}
