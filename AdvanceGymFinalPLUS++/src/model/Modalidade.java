package model;

import java.util.List;
import model.sqlite.ModalidadeSQLiteDAO;

public class Modalidade {
    
    private Integer id;
    private String nome;

    public Modalidade(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Modalidade(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    // ---------- ActiveRecord
    
    private static ModalidadeSQLiteDAO dao = new ModalidadeSQLiteDAO();
    
    public void save(){
        
        if(id != null && dao.find(id) != null)
            dao.update(this);
        else
            dao.create(this);
    }
    
    public void delete(){
        if(dao.find(id) != null)
            dao.delete(this);
    }
    
    public static List<Modalidade> all(){
        return dao.all();
    }
    
    public static Modalidade find(int id){
        return dao.find(id);
    }
    
}
