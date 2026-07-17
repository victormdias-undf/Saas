package integrations;

public class Pessoa {
    private String nomeCompleto, dataNascimento;

    public Pessoa(String nomeCompleto, String dataNascimento) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
    public String exibirDados(){
        
        return "Nome: "+ nomeCompleto+"\nData de nascimento: " + dataNascimento;
    }
}
