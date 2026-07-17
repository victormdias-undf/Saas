package aluno;
import integrations.Pessoa;
public class Estudante extends Pessoa {
    private String matricula;
    public Estudante(String matricula, String nomeCompleto, String dataNascimento) {
        super(nomeCompleto, dataNascimento);
        this.matricula = matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getMatricula() {
        return matricula;
    }
    @Override
    public String exibirDados() {
        return "Matrícula: " + getMatricula()
                + "\nNome: " + getNomeCompleto()
                + "\nData de nascimento: " + getDataNascimento();
    }
}

