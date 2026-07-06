public class Estudante extends Pessoa {


    public Estudante(String matricula, String nomeCompleto, String dataNascimento) {
        super(matricula, nomeCompleto, dataNascimento);
    }


    @Override
    public String exibirDados() {
        return "Matrícula: " + getMatricula()
                + "\nNome: " + getNomeCompleto()
                + "\nData de nascimento: " + getDataNascimento();
    }
}

