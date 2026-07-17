package professor.controller;

import java.util.ArrayList;
import professor.dao.ProfessorDAO;
import professor.model.Professor;

public class ProfessorController {

    private ProfessorDAO dao;

    public ProfessorController() {
        dao = new ProfessorDAO();
    }

    public void salvarProfessor(Professor professor) {
        dao.inserirProfessor(professor);
    }

    public Professor buscarPorMatricula(String matricula) {
        return dao.buscarPorMatricula(matricula);
    }

    public ArrayList<Professor> buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }

    public ArrayList<Professor> listarTodos() {
        return dao.listarTodos();
    }

    public void atualizarProfessor(Professor professor) {
        dao.atualizarProfessor(professor);
    }

    public void excluirProfessor(String matricula) {
        dao.excluirProfessor(matricula);
    }

}