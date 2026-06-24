package com.trabalho.teste.Router;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.sql.Date;
import java.util.List;
import java.util.Random;

import com.trabalho.teste.Models.Aluno;
import com.trabalho.teste.Service.AlunoService;
public class AlunoController {
    AlunoService service = new AlunoService();
    Date dataDeNasc;
    public String Cadastrar(String nome, String data){
        if(nome==null||nome.equals("")||nome.matches(".*\\d.*")) return "Nome inválido";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        try{
            dataDeNasc= Date.valueOf(LocalDate.parse(data, formatter));
        }catch(DateTimeException e){
            return "Data informada não é válida";
        }
        Random random = new Random();
        String matricula = "A" + (1000 + random.nextInt(9000));
        return service.Cadastrar(matricula, nome, dataDeNasc);
    }
    // public List<Aluno> Search(){
    //     //return service.
    // }
}
