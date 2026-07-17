package professor.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import professor.controller.ProfessorController;
import professor.model.Professor;

public class TelaProfessor extends JFrame {

    private JTextField txtNome;
    private JTextField txtNascimento;
    private JTextField txtEspecialidade;
    private JTextField txtMatricula;

    private JTable tabela;
    private DefaultTableModel modelo;

    private ProfessorController controller;

    public TelaProfessor() {

        controller = new ProfessorController();

        setTitle("Cadastro de Professores");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

        painelCampos.add(new JLabel("Nome"));
        txtNome = new JTextField();
        painelCampos.add(txtNome);

        painelCampos.add(new JLabel("Data Nascimento (AAAA-MM-DD)"));
        txtNascimento = new JTextField();
        painelCampos.add(txtNascimento);

        painelCampos.add(new JLabel("Especialidade"));
        txtEspecialidade = new JTextField();
        painelCampos.add(txtEspecialidade);

        painelCampos.add(new JLabel("Matrícula"));
        txtMatricula = new JTextField();
        painelCampos.add(txtMatricula);

        add(painelCampos, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout());

        JButton btCadastrar = new JButton("Cadastrar");
        JButton btBuscar = new JButton("Buscar");
        JButton btAtualizar = new JButton("Atualizar");
        JButton btExcluir = new JButton("Excluir");
        JButton btListar = new JButton("Listar");
        JButton btLimpar = new JButton("Limpar");

        painelBotoes.add(btCadastrar);
        painelBotoes.add(btBuscar);
        painelBotoes.add(btAtualizar);
        painelBotoes.add(btExcluir);
        painelBotoes.add(btListar);
        painelBotoes.add(btLimpar);

        add(painelBotoes, BorderLayout.CENTER);

        modelo = new DefaultTableModel();

        modelo.addColumn("Nome");
        modelo.addColumn("Nascimento");
        modelo.addColumn("Especialidade");
        modelo.addColumn("Matrícula");

        tabela = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.SOUTH);

        btCadastrar.addActionListener(e -> cadastrar());

        btBuscar.addActionListener(e -> buscar());

        btAtualizar.addActionListener(e -> atualizar());

        btExcluir.addActionListener(e -> excluir());

        btListar.addActionListener(e -> listar());

        btLimpar.addActionListener(e -> limpar());

    }

    private Professor preencherProfessor() {

        Professor professor = new Professor();

        professor.setNomeCompleto(txtNome.getText());

        professor.setDataNascimento(txtNascimento.getText());

        professor.setEspecialidade(txtEspecialidade.getText());

        professor.setMatricula(txtMatricula.getText());

        return professor;

    }

    private void cadastrar() {

        try {

            controller.salvarProfessor(preencherProfessor());

            JOptionPane.showMessageDialog(this,
                    "Professor cadastrado com sucesso!");

            limpar();

            listar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage());

        }

    }

    private void buscar() {

        Professor professor =
                controller.buscarPorMatricula(txtMatricula.getText());

        if (professor == null) {

            JOptionPane.showMessageDialog(this,
                    "Professor não encontrado.");

            return;

        }

        txtNome.setText(professor.getNomeCompleto());

        txtNascimento.setText(
                professor.getDataNascimento().toString());

        txtEspecialidade.setText(
                professor.getEspecialidade());

    }

    private void atualizar() {

        try {

            controller.atualizarProfessor(preencherProfessor());

            JOptionPane.showMessageDialog(this,
                    "Professor atualizado com sucesso!");

            listar();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Erro: " + e.getMessage());

        }

    }

    private void excluir() {

        controller.excluirProfessor(txtMatricula.getText());

        JOptionPane.showMessageDialog(this,
                "Professor excluído com sucesso!");

        limpar();

        listar();

    }

    private void listar() {

        modelo.setRowCount(0);

        ArrayList<Professor> lista =
                controller.listarTodos();

        for (Professor professor : lista) {

            modelo.addRow(new Object[]{

                    professor.getNomeCompleto(),

                    professor.getDataNascimento(),

                    professor.getEspecialidade(),

                    professor.getMatricula()

            });

        }

    }

    private void limpar() {

        txtNome.setText("");

        txtNascimento.setText("");

        txtEspecialidade.setText("");

        txtMatricula.setText("");

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new TelaProfessor().setVisible(true);

        });

    }

}