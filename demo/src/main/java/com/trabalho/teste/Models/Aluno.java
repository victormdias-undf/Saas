package com.trabalho.teste.Models;

public class Aluno extends Pessoa {

    public Aluno(String matricula, String nome, String data) {
        super(matricula, nome, data);
    }

    @Override
    public String exibirDados() {
        return "==========Aluno==========\nMatricula: "+this.matricula+"\nNome: "+this.nome+"\nData: "+this.data;
    }
    
}
