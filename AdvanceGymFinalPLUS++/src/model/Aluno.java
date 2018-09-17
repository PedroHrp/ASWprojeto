package model;

import java.util.List;
import model.sqlite.AlunoSQLiteDAO;

/**
 *
 * @author Pedro
 */

public class Aluno {
    
    private Integer matricula;
    
    private String nome;
    private String endereco;
    private int modalidade;
    private int status;
    private int instrutorA;
    private int rg;
    private int cpf;
    private String dataNasc;
    private String dataInsc;

    public Aluno(String nome, String endereco, int modalidade, int status, int instrutorA, int rg, int cpf, String dataNasc, String dataInsc) {
        this.nome = nome;
        this.endereco = endereco;
        this.modalidade = modalidade;
        this.status = status;
        this.instrutorA = instrutorA;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.dataInsc = dataInsc;
    }

    public Aluno(Integer matricula, String nome, String endereco, int modalidade, int status, int instrutorA, int rg, int cpf, String dataNasc, String dataInsc) {
        this.matricula = matricula;
        this.nome = nome;
        this.endereco = endereco;
        this.modalidade = modalidade;
        this.status = status;
        this.instrutorA = instrutorA;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.dataInsc = dataInsc;
    }
    
    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
        this.modalidade = modalidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getDataInsc() {
        return dataInsc;
    }

    public void setDataInsc(String dataInsc) {
        this.dataInsc = dataInsc;
    }

    public int getInstrutorA() {
        return instrutorA;
    }

    public void setInstrutorA(int instrutorA) {
        this.instrutorA = instrutorA;
    }

    @Override
    public String toString() {
        return "Aluno " + "[" + matricula + "]  "+ nome + " \t" + endereco + " \t" + modalidade + " \t" + status + " \t" + instrutorA + " \t" + rg + " \t" + cpf + " \t" + dataNasc + " \t" + dataInsc ;
    }
    
    
        // ---------- ActiveRecord
    
    private static AlunoSQLiteDAO dao = new AlunoSQLiteDAO();
    
    public void save(){
        if(matricula != null && dao.find(matricula) != null)
            dao.update(this);
        else
            dao.create(this);
    }
    
    public void delete(){
        if(dao.find(matricula) != null)
            dao.delete(this);
    }
    
    public static List<Aluno> all(){
        return dao.all();
    }
    
    public static Aluno find(int matricula){
        return dao.find(matricula);
    }
    
    
}
