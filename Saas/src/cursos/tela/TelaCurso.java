package cursos.tela;

import cursos.DAO.CursoDAO;
import cursos.model.Curso;
import java.util.List;
import javax.swing.*;
import professor.dao.ProfessorDAO;

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
                    5 - Vincular professor ao curso
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
                case 5:
                    associarProfessorAoCurso();
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
        String professorMatricula = JOptionPane.showInputDialog("Digite a matrícula do professor (opcional):");

        if (codigo == null || nomeCurso == null || cargaHorariaTexto == null ||
                codigo.isBlank() || nomeCurso.isBlank() || cargaHorariaTexto.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha código, nome e carga horária.");
            return;
        }

        int cargaHoraria;
        try {
            cargaHoraria = Integer.parseInt(cargaHorariaTexto);
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(null, "Carga horária inválida.");
            return;
        }

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

    private void associarProfessorAoCurso() {
        String codigoCurso = JOptionPane.showInputDialog("Digite o código do curso:");
        String matriculaProfessor = JOptionPane.showInputDialog("Digite a matrícula do professor:");

        if (codigoCurso == null || matriculaProfessor == null || codigoCurso.isBlank() || matriculaProfessor.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha código do curso e matrícula do professor.");
            return;
        }

        Curso cursoExistente = cursoDAO.consultarPorCodigo(codigoCurso);
        if (cursoExistente == null) {
            JOptionPane.showMessageDialog(null, "Curso não encontrado.");
            return;
        }

        ProfessorDAO professorDAO = new ProfessorDAO();
        if (professorDAO.buscarPorMatricula(matriculaProfessor) == null) {
            JOptionPane.showMessageDialog(null, "Professor não encontrado para a matrícula informada.");
            return;
        }

        Curso cursoAtualizado = new Curso(cursoExistente.getCodigo(), cursoExistente.getNome_curso(), cursoExistente.getCarga_horaria(), matriculaProfessor);
        cursoDAO.editar(cursoAtualizado);

        JOptionPane.showMessageDialog(null, "Professor vinculado ao curso com sucesso!");
    }

    private void mostrarCursoComOpcoes(Curso curso) {
        String mensagem = exibirDados(curso)
                + "\n\n1 - Editar"
                + "\n2 - Excluir"
                + "\n0 - Voltar";

        int opcao = lerOpcao(mensagem);

        if (opcao == 1) {
            editarCurso(curso);
        } else if (opcao == 2) {
            excluirCurso(curso);
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

    private void editarCurso(Curso curso) {
        String novoNome = JOptionPane.showInputDialog("Digite o novo nome do curso:", curso.getNome_curso());
        String novaCargaTexto = JOptionPane.showInputDialog("Digite a nova carga horária:", curso.getCarga_horaria());
        String novoProfessor = JOptionPane.showInputDialog("Digite a nova matrícula do professor:", curso.getProfessor_matricula());

        if (novoNome == null || novaCargaTexto == null ||
                novoNome.isBlank() || novaCargaTexto.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha nome e carga horária.");
            return;
        }

        int novaCarga;
        try {
            novaCarga = Integer.parseInt(novaCargaTexto);
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(null, "Carga horária inválida.");
            return;
        }

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
