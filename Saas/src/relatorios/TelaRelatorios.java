package relatorios;

import aluno.Estudante;
import cursos.model.Curso;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import professor.model.Professor;

public class TelaRelatorios extends JFrame {

    private final RelatorioService service = new RelatorioService();
    private final JTextArea areaTexto = new JTextArea();

    public TelaRelatorios() {
        setTitle("Relatórios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelSuperior = new JPanel(new GridLayout(1, 4, 10, 10));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnEstudantes = new JButton("Estudantes");
        JButton btnProfessores = new JButton("Professores");
        JButton btnCursos = new JButton("Cursos");
        JButton btnSair = new JButton("Sair");

        btnEstudantes.addActionListener(e -> exibirRelatorioEstudantes());
        btnProfessores.addActionListener(e -> exibirRelatorioProfessores());
        btnCursos.addActionListener(e -> exibirRelatorioCursos());
        btnSair.addActionListener(e -> dispose());

        painelSuperior.add(btnEstudantes);
        painelSuperior.add(btnProfessores);
        painelSuperior.add(btnCursos);
        painelSuperior.add(btnSair);

        areaTexto.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaTexto);

        JPanel painelInferior = new JPanel();
        JButton btnPdf = new JButton("Gerar PDF");
        btnPdf.addActionListener(e -> gerarPdf());
        painelInferior.add(btnPdf);

        add(painelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    public void abrirTelaRelatorios() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    private void exibirRelatorioEstudantes() {
        List<Estudante> estudantes = service.listarEstudantes();
        StringBuilder texto = new StringBuilder();
        texto.append("=== Relatório de Estudantes ===\n\n");

        if (estudantes.isEmpty()) {
            texto.append("Nenhum estudante cadastrado.\n");
        } else {
            for (Estudante estudante : estudantes) {
                texto.append("Matrícula: ").append(estudante.getMatricula()).append("\n");
                texto.append("Nome: ").append(estudante.getNomeCompleto()).append("\n");
                texto.append("Data de nascimento: ").append(estudante.getDataNascimento()).append("\n");
                texto.append("Cursos matriculados: ");
                List<String> cursos = service.listarCursosPorEstudante(estudante.getMatricula());
                if (cursos.isEmpty()) {
                    texto.append("Nenhum");
                } else {
                    texto.append(String.join(", ", cursos));
                }
                texto.append("\n\n");
            }
        }

        areaTexto.setText(texto.toString());
    }

    private void exibirRelatorioProfessores() {
        List<Professor> professores = service.listarProfessores();
        StringBuilder texto = new StringBuilder();
        texto.append("=== Relatório de Professores ===\n\n");

        if (professores.isEmpty()) {
            texto.append("Nenhum professor cadastrado.\n");
        } else {
            for (Professor professor : professores) {
                texto.append("Matrícula: ").append(professor.getMatricula()).append("\n");
                texto.append("Nome: ").append(professor.getNomeCompleto()).append("\n");
                texto.append("Especialidade: ").append(professor.getEspecialidade()).append("\n");
                texto.append("Cursos associados: ");
                List<String> cursos = service.listarCursosPorProfessor(professor.getMatricula());
                if (cursos.isEmpty()) {
                    texto.append("Nenhum");
                } else {
                    texto.append(String.join(", ", cursos));
                }
                texto.append("\n\n");
            }
        }

        areaTexto.setText(texto.toString());
    }

    private void exibirRelatorioCursos() {
        List<Curso> cursos = service.listarCursos();
        StringBuilder texto = new StringBuilder();
        texto.append("=== Relatório de Cursos ===\n\n");

        if (cursos.isEmpty()) {
            texto.append("Nenhum curso cadastrado.\n");
        } else {
            for (Curso curso : cursos) {
                texto.append("Código: ").append(curso.getCodigo()).append("\n");
                texto.append("Nome: ").append(curso.getNome_curso()).append("\n");
                texto.append("Carga horária: ").append(curso.getCarga_horaria()).append("h\n");
                texto.append("Professor responsável: ").append(service.buscarNomeProfessor(curso.getProfessor_matricula())).append("\n");
                texto.append("Alunos matriculados: ").append(service.contarAlunosMatriculados(curso.getCodigo())).append("\n\n");
            }
        }

        areaTexto.setText(texto.toString());
    }

    private void gerarPdf() {
        String conteudo = areaTexto.getText();
        if (conteudo == null || conteudo.isBlank()) {
            JOptionPane.showMessageDialog(this, "Gere um relatório antes de exportar para PDF.");
            return;
        }

        try {
            Path pasta = Files.createTempDirectory("relatorios-estudantil");
            Path arquivo = pasta.resolve("relatorio.pdf");
            escreverPdf(arquivo, conteudo);
            Desktop.getDesktop().open(arquivo.toFile());
            JOptionPane.showMessageDialog(this, "PDF gerado com sucesso em:\n" + arquivo.toAbsolutePath());
        } catch (IOException erro) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar o arquivo: " + erro.getMessage());
        }
    }

    public static void escreverPdf(Path arquivo, String conteudo) throws IOException {
        List<String> linhas = new ArrayList<>();
        for (String linha : conteudo.split("\\R")) {
            linhas.add(linha);
        }

        StringBuilder stream = new StringBuilder();
        stream.append("BT\n/F1 12 Tf\n72 760 Td\n");

        for (int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            stream.append("(").append(escaparTextoPdf(linha)).append(") Tj\n");
            if (i < linhas.size() - 1) {
                stream.append("0 -14 Td\n");
            }
        }

        stream.append("ET\n");

        List<byte[]> objetos = new ArrayList<>();
        objetos.add(("1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n").getBytes(StandardCharsets.ISO_8859_1));
        objetos.add(("2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n").getBytes(StandardCharsets.ISO_8859_1));
        objetos.add(("3 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] /Contents 4 0 R /Resources << /Font << /F1 5 0 R >> >> >>\nendobj\n").getBytes(StandardCharsets.ISO_8859_1));
        objetos.add(("4 0 obj\n<< /Length 0 >>\nstream\n" + stream + "endstream\nendobj\n").getBytes(StandardCharsets.ISO_8859_1));
        objetos.add(("5 0 obj\n<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\nendobj\n").getBytes(StandardCharsets.ISO_8859_1));

        StringBuilder pdf = new StringBuilder();
        pdf.append("%PDF-1.4\n");
        List<Long> offsets = new ArrayList<>();
        offsets.add(0L);
        for (byte[] objeto : objetos) {
            offsets.add((long) pdf.length());
            pdf.append(new String(objeto, StandardCharsets.ISO_8859_1));
        }

        long xrefOffset = pdf.length();
        pdf.append("xref\n0 ").append(objetos.size() + 1).append("\n");
        pdf.append("0000000000 65535 f \n");
        for (int i = 1; i <= objetos.size(); i++) {
            pdf.append(String.format("%010d 00000 n \n", offsets.get(i)));
        }

        pdf.append("trailer\n<< /Size ").append(objetos.size() + 1).append(" /Root 1 0 R >>\n");
        pdf.append("startxref\n").append(xrefOffset).append("\n%%EOF\n");

        Files.write(arquivo, pdf.toString().getBytes(StandardCharsets.ISO_8859_1));
    }

    private static String escaparTextoPdf(String texto) {
        return texto.replace("\\", "\\\\")
                .replace("(", "\\(")
                .replace(")", "\\)");
    }
}
