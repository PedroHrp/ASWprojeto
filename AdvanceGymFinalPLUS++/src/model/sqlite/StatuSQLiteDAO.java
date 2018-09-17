/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Statu;

/**
 *
 * @author Pedro
 */
public class StatuSQLiteDAO extends SQLiteBase{
    
    public StatuSQLiteDAO(){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Status ("
                    + "codStatu INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT);");
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public void create(Statu s){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Status VALUES(?,?);");
            
            stm.setString(2, s.getNome());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public void update(Statu s){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("UPDATE Status SET "
                            + "nome = ?, "
                            + "WHERE codStatu = ?;");
            
            stm.setString(1, s.getNome());
            stm.setInt(2, s.getId());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public void delete(Statu s){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("DELETE FROM Status WHERE codStatu = ?;");
            
            stm.setInt(1, s.getId());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public Statu find(int id){
        
        Statu result = null;
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("SELECT * FROM Status WHERE codStatu = ?");
            
            stm.setInt(1, id);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                    Statu i = new Statu(
                                    rs.getInt(1),        // codStatu
                                    rs.getString(2)      // nome
                                    );
                    result = i;
            }
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        
        return result;
    }
    
    
    public List<Statu> all(){
        
        ArrayList<Statu> result = new ArrayList<>();
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Status ORDER BY codStatu ASC;");
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Statu i = new Statu(
                                    rs.getInt(1),        
                                    rs.getString(2)
                                    );    
                
                result.add(i);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        
        return result;
    }
    
}
