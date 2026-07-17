package cursos.tela;

import cursos.DAO.CursoDAO;
import cursos.model.Curso;
import java.util.List;
import javax.swing.*;

public class TelaCurso {

    private final CursoDAO cursoDAO = new CursoDAO();

    public void abrirMenuCurso() {
        int opcao;

        do {
            String menu = """
                    MENU CURSOS


                    1 - Cadastrar curso
                    2 - Consultar por código
                    3 - Consultar por nome
                    4 - Listar todos
                    0 - Voltar
                    """;

            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) {
                case 1:
                    cadastrarCurso();
                    break;
                case 2:
                    consultarPorCodigo();
                    break;
                case 3:
                    consultarPorNome();
                    break;
                case 4:
                    listarTodos();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Voltando ao menu principal...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarCurso() {
        String codigo = JOptionPane.showInputDialog("Digite o código do curso:");
        String nomeCurso = JOptionPane.showInputDialog("Digite o nome do curso:");
        String cargaHorariaTexto = JOptionPane.showInputDialog("Digite a carga horária:");
        String professorMatricula = JOptionPane.showInputDialog("Digite a matrícula do professor:");

        int cargaHoraria = Integer.parseInt(cargaHorariaTexto);

        Curso curso = new Curso(codigo, nomeCurso, cargaHoraria, professorMatricula);

        cursoDAO.cadastrar(curso);

        JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!");
    }

    private void consultarPorCodigo() {
        String codigo = JOptionPane.showInputDialog("Digite o código do curso:");

        Curso curso = cursoDAO.consultarPorCodigo(codigo);

        if (curso == null) {
            JOptionPane.showMessageDialog(null, "Curso não encontrado.");
        } else {
            mostrarCursoComOpcoes(curso);
        }
    }

    private void consultarPorNome() {
        String nome = JOptionPane.showInputDialog("Digite o nome do curso:");

        List<Curso> cursos = cursoDAO.consultarPorNome(nome);

        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso encontrado.");
        } else {
            String texto = "";

            for (Curso curso : cursos) {
                texto += exibirDados(curso) + "\n\n";
            }

            JOptionPane.showMessageDialog(null, texto);
        }
    }

    private void listarTodos() {
        List<Curso> cursos = cursoDAO.listarTodos();

        if (cursos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum curso cadastrado.");
        } else {
            String texto = "";

            for (Curso curso : cursos) {
                texto += exibirDados(curso) + "\n\n";
            }

            JOptionPane.showMessageDialog(null, texto);
        }
    }

    private void mostrarCursoComOpcoes(Curso curso) {
        String mensagem = exibirDados(curso)
                + "\n\n1 - Editar"
                + "\n2 - Excluir"
                + "\n0 - Voltar";

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(mensagem));

        if (opcao == 1) {
            editarCurso(curso);
        } else if (opcao == 2) {
            excluirCurso(curso);
        }
    }

    private void editarCurso(Curso curso) {
        String novoNome = JOptionPane.showInputDialog("Digite o novo nome do curso:", curso.getNome_curso());
        String novaCargaTexto = JOptionPane.showInputDialog("Digite a nova carga horária:", curso.getCarga_horaria());
        String novoProfessor = JOptionPane.showInputDialog("Digite a nova matrícula do professor:", curso.getProfessor_matricula());

        int novaCarga = Integer.parseInt(novaCargaTexto);

        Curso cursoAtualizado = new Curso(curso.getCodigo(), novoNome, novaCarga, novoProfessor);

        cursoDAO.editar(cursoAtualizado);

        JOptionPane.showMessageDialog(null, "Curso editado com sucesso!");
    }

    private void excluirCurso(Curso curso) {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Deseja excluir este curso?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            cursoDAO.excluir(curso.getCodigo());
            JOptionPane.showMessageDialog(null, "Curso excluído com sucesso!");
        }
    }

    private String exibirDados(Curso curso) {
        return "Código: " + curso.getCodigo()
                + "\nNome: " + curso.getNome_curso()
                + "\nCarga horária: " + curso.getCarga_horaria()
                + "\nProfessor: " + curso.getProfessor_matricula();
    }
}
