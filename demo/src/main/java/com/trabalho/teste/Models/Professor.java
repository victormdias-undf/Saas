package com.trabalho.teste.Models;

public class Professor extends Pessoa{
    protected String especialidade;
    public Professor(String matricula, String nome, String data, String especialidade) {
        super(matricula, nome, data);
        this.especialidade=especialidade;
    }

    @Override
    public String exibirDados() {
        return "==========Professor==========\nMatricula: "+this.matricula+"\nNome: "+this.nome+"\nData: "+this.data+"\nEspecialidade: "+this.especialidade;
    }

    
}
