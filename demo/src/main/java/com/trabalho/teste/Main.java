package com.trabalho.teste;

import org.h2.tools.Server;

import com.trabalho.teste.ConnDB.CreateTables;
import com.trabalho.teste.Router.AlunoController;
import com.trabalho.teste.Service.AlunoService;

public class Main {
    public static void main(String[] args) throws Exception {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
              .start();

        System.out.println("H2 Console: http://localhost:8082");
        AlunoController aluno = new AlunoController();
        CreateTables.Criar();
        aluno.Cadastrar("Victor", "12/05/2006");
        AlunoService.SearchAll();

    }
}