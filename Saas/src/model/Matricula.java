package model;
import java.util.Date;

public class Matricula {
    private int id;
    private String estudante_matricula;
    private String curso_codigo;
    private Date data_matricula;

    public Matricula(int id, String estudante_matricula, String curso_codigo, Date data_matricula) {
        this.id = id;
        this.estudante_matricula = estudante_matricula;
        this.curso_codigo = curso_codigo;
        this.data_matricula = data_matricula;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEstudante_matricula() { return estudante_matricula; }
    public void setEstudante_matricula(String estudante_matricula) { this.estudante_matricula = estudante_matricula; }

    public String getCurso_codigo() { return curso_codigo; }
    public void setCurso_codigo(String curso_codigo) { this.curso_codigo = curso_codigo; }

    public Date getData_matricula() { return data_matricula; }
    public void setData_matricula(Date data_matricula) { this.data_matricula = data_matricula; }
}