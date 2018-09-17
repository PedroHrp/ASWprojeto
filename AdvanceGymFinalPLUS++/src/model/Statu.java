/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.sqlite.StatuSQLiteDAO;


public class Statu {
    
    private Integer id;
    private String nome;

    public Statu(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Statu(String nome) {
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
    
    private static StatuSQLiteDAO dao = new StatuSQLiteDAO();
    
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
    
    public static List<Statu> all(){
        return dao.all();
    }
    
    public static Statu find(int id){
        return dao.find(id);
    }
}
