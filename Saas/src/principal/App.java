package principal;

import DAO.CursoDAO;
import model.Curso;

public class App {
    public static void main(String[] args) {
        Curso novoCurso = new Curso("C01", "Programação Java", 80, "P001");
        CursoDAO dao = new CursoDAO();
        
        System.out.println("Tentando inserir...");
        dao.inserirCurso(novoCurso);
    }
}