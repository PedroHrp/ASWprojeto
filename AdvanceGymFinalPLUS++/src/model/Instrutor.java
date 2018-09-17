package model;

import java.util.List;
import model.sqlite.InstrutorSQLiteDAO;

public class Instrutor {
    
    private Integer codInstrutor;
    
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    
    public Instrutor(int codInstrutor, String nome, String senha, String telefone, String email) {
       
        this.codInstrutor = codInstrutor;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;

    }
    
    public Instrutor(String nome, String senha, String telefone, String email) {
       
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
        this.email = email;

    }

    public Integer getCodInstrutor() {
        return codInstrutor;
    }

    public void setCodInstrutor(Integer codInstrutor) {
        this.codInstrutor = codInstrutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return "Instrutor("+ nome + "\t"+ email+ "\t"+telefone+ ") [" + codInstrutor + "]";
        //return "["+ codInstrutor + "] "+ nome + "\t\t email : "+ email +"\t\t"+ telefone;
    }
    
    // ---------- ActiveRecord
    
    private static InstrutorSQLiteDAO dao = new InstrutorSQLiteDAO();
    
    public void save(){
        if(codInstrutor != null && dao.find(email) != null)
            dao.update(this);
        else
            dao.create(this);
    }
    
    public void delete(){
        if(dao.find(email) != null)
            dao.delete(this);
    }
    
    public static List<Instrutor> all(){
        return dao.all();
    }
    
    public static Instrutor find(String email){
        return dao.find(email);
    }
    
}
