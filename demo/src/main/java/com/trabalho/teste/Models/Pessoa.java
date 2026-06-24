package com.trabalho.teste.Models;

public abstract class Pessoa {
    protected String matricula, nome, data;

    public Pessoa(String matricula, String nome, String data) {
        this.matricula=matricula;
        this.nome = nome;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public abstract String exibirDados();
}
