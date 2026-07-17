package model;

public class Curso {
    private String codigo;
    private String nome_curso;
    private int carga_horaria;
    private String professor_matricula;

    public Curso(String codigo, String nome_curso, int carga_horaria, String professor_matricula) {
        this.codigo = codigo;
        this.nome_curso = nome_curso;
        this.carga_horaria = carga_horaria;
        this.professor_matricula = professor_matricula;
    }

    public String getCodigo() { return codigo; }
    public String getNome_curso() { return nome_curso; }
    public int getCarga_horaria() { return carga_horaria; }
    public String getProfessor_matricula() { return professor_matricula; }
}