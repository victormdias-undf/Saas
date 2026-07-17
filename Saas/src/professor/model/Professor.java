package professor.model;



import integrations.Pessoa;

public class Professor extends Pessoa{
    private String especialidade;
    private String matricula;

    public Professor() {
        super(null, null);
    }
    
    public Professor(String nome, String dataNascimento, 
        String especialidade, String matricula) {
        super(nome, dataNascimento);
        this.especialidade = especialidade;
        this.matricula = matricula;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}