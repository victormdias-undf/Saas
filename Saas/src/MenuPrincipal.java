import aluno.TelaAluno;
import cursos.tela.TelaCurso;
import java.awt.*;
import javax.swing.*;
import professor.view.TelaProfessor;
import relatorios.TelaRelatorios;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Sistema de Gestão Estudantil");
        setSize(480, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton btnAlunos = new JButton("Gerenciar Alunos");
        JButton btnCursos = new JButton("Gerenciar Cursos");
        JButton btnProfessores = new JButton("Gerenciar Professores");
        JButton btnRelatorios = new JButton("Relatórios");
        JButton btnSair = new JButton("Sair");

        btnAlunos.addActionListener(e -> new TelaAluno().abrirMenuAluno());
        btnCursos.addActionListener(e -> new TelaCurso().abrirMenuCurso());
        btnProfessores.addActionListener(e -> {
            TelaProfessor telaProfessor = new TelaProfessor();
            telaProfessor.setVisible(true);
        });
        btnRelatorios.addActionListener(e -> new TelaRelatorios().abrirTelaRelatorios());
        btnSair.addActionListener(e -> dispose());

        painelBotoes.add(btnAlunos);
        painelBotoes.add(btnCursos);
        painelBotoes.add(btnProfessores);
        painelBotoes.add(btnRelatorios);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}
