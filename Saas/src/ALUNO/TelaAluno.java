package aluno;

import cursos.DAO.MatriculaDAO;
import cursos.model.Matricula;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class TelaAluno {

    private final EstudanteDAO estudanteDAO = new EstudanteDAO();


    public void abrirMenuAluno() {
        int opcao;


        do {
            String menu = """
                    MENU ALUNO


                    1 - Cadastrar aluno
                    2 - Consultar por matrícula
                    3 - Consultar por nome
                    4 - Listar todos
                    5 - Vincular aluno a um curso
                    0 - Voltar
                    """;


            String entrada = JOptionPane.showInputDialog(menu);
            if (entrada == null || entrada.isBlank()) {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                return;
            }

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(null, "Digite uma opção válida!");
                opcao = -1;
            }


            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    consultarPorMatricula();
                    break;
                case 3:
                    consultarPorNome();
                    break;
                case 4:
                    listarTodos();
                    break;
                case 5:
                    matricularAlunoEmCurso();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Voltando ao menu principal...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }


        } while (opcao != 0);
    }


    private void cadastrarAluno() {
        String matricula = JOptionPane.showInputDialog("Digite a matrícula:");
        String nome = JOptionPane.showInputDialog("Digite o nome completo:");
        String dataNascimento = JOptionPane.showInputDialog("Digite a data de nascimento: AAAA-MM-DD");


        Estudante estudante = new Estudante(matricula, nome, dataNascimento);


        estudanteDAO.cadastrar(estudante);


        JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
    }


    private void consultarPorMatricula() {
        String matricula = JOptionPane.showInputDialog("Digite a matrícula do aluno:");


        Estudante estudante = estudanteDAO.consultarPorMatricula(matricula);


        if (estudante == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
        } else {
            mostrarAlunoComOpcoes(estudante);
        }
    }


    private void consultarPorNome() {
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");


        List<Estudante> estudantes = estudanteDAO.consultarPorNome(nome);


        if (estudantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado.");
        } else {
            String texto = "";


            for (Estudante estudante : estudantes) {
                texto += estudante.exibirDados() + "\n\n";
            }


            JOptionPane.showMessageDialog(null, texto);
        }
    }


    private void listarTodos() {
        List<Estudante> estudantes = estudanteDAO.listarTodos();


        if (estudantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
        } else {
            String texto = "";


            for (Estudante estudante : estudantes) {
                texto += estudante.exibirDados() + "\n\n";
            }


            JOptionPane.showMessageDialog(null, texto);
        }
    }


    private void matricularAlunoEmCurso() {
        String matriculaEstudante = JOptionPane.showInputDialog("Digite a matrícula do aluno:");
        String codigoCurso = JOptionPane.showInputDialog("Digite o código do curso:");

        if (matriculaEstudante == null || codigoCurso == null || matriculaEstudante.isBlank() || codigoCurso.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha matrícula e código do curso.");
            return;
        }

        MatriculaDAO matriculaDAO = new MatriculaDAO();
        matriculaDAO.inserirMatricula(new Matricula(0, matriculaEstudante, codigoCurso, new Date()));

        JOptionPane.showMessageDialog(null, "Aluno vinculado ao curso com sucesso!");
    }

    private void mostrarAlunoComOpcoes(Estudante estudante) {
        String mensagem = estudante.exibirDados()
                + "\n\n1 - Editar"
                + "\n2 - Excluir"
                + "\n0 - Voltar";

        int opcao = lerOpcao(mensagem);

        if (opcao == 1) {
            editarAluno(estudante);
        } else if (opcao == 2) {
            excluirAluno(estudante);
        }
    }

    private int lerOpcao(String mensagem) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(mensagem);
            if (entrada == null) {
                return 0;
            }
            if (entrada.isBlank()) {
                JOptionPane.showMessageDialog(null, "Digite uma opção válida!");
                continue;
            }
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(null, "Digite uma opção válida!");
            }
        }
    }


    private void editarAluno(Estudante estudante) {
        String novoNome = JOptionPane.showInputDialog("Digite o novo nome:", estudante.getNomeCompleto());
        String novaData = JOptionPane.showInputDialog("Digite a nova data de nascimento:", estudante.getDataNascimento());

        if (novoNome == null || novaData == null || novoNome.isBlank() || novaData.isBlank()) {
            JOptionPane.showMessageDialog(null, "Nome e data de nascimento são obrigatórios.");
            return;
        }

        estudante.setNomeCompleto(novoNome);
        estudante.setDataNascimento(novaData);


        estudanteDAO.editar(estudante);


        JOptionPane.showMessageDialog(null, "Aluno editado com sucesso!");
    }


    private void excluirAluno(Estudante estudante) {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Deseja excluir este aluno?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );


        if (resposta == JOptionPane.YES_OPTION) {
            estudanteDAO.excluir(estudante.getMatricula());


            JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
        }
    }
}

