package model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Modalidade;

public class ModalidadeSQLiteDAO extends SQLiteBase{
    
    public ModalidadeSQLiteDAO(){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Modalidades ("
                    + "codModalidade INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT);");
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public void create(Modalidade m){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Modalidades VALUES(?,?);");
            
            stm.setString(2, m.getNome());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public void update(Modalidade m){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("UPDATE Modalidades SET "
                            + "nome = ?, "
                            + "WHERE codModalidade = ?;");
            
            stm.setString(1, m.getNome());
            stm.setInt(2, m.getId());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public void delete(Modalidade m){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("DELETE FROM Modalidades WHERE codModalidade = ?;");
            
            stm.setInt(1, m.getId());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public Modalidade find(int id){
        
        Modalidade result = null;
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("SELECT * FROM Modalidades WHERE codModalidade = ?");
            
            stm.setInt(1, id);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                    Modalidade m = new Modalidade(
                                    rs.getInt(1),        // codModalidade
                                    rs.getString(2)      // nome
                                    );
                    result = m;
            }
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        
        return result;
    }
    
    public List<Modalidade> all(){
        
        ArrayList<Modalidade> result = new ArrayList<>();
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Modalidades ORDER BY codModalidade ASC;");
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Modalidade m = new Modalidade(
                                    rs.getInt(1),        
                                    rs.getString(2)
                                    );    
                
                result.add(m);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        
        return result;
    }
}
