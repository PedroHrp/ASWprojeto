package model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Instrutor;

/**
 *
 * @author Pedro
 */
public class InstrutorSQLiteDAO extends SQLiteBase{
    
    public InstrutorSQLiteDAO(){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Instrutores ("
                    + "codInstrutor INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT,"
                    + "senha TEXT,"
                    + "telefone TEXT,"
                    + "email TEXT);");
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public void create(Instrutor i){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Instrutores VALUES(?,?,?,?,?);");
            
            stm.setString(2, i.getNome());
            stm.setString(3, i.getSenha());
            stm.setString(4, i.getTelefone());
            stm.setString(5, i.getEmail());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public void update(Instrutor i){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("UPDATE Instrutores SET "
                            + "nome = ?, "
                            + "senha = ?, "
                            + "telefone = ?, "
                            + "email = ?"
                            + "WHERE codInstrutor = ?;");
            
            stm.setString(1, i.getNome());
            stm.setString(2, i.getSenha());
            stm.setString(3, i.getTelefone());
            stm.setString(4, i.getEmail());
            stm.setInt(5, i.getCodInstrutor());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public void delete(Instrutor i){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("DELETE FROM Instrutores WHERE codInstrutor = ?;");
            
            stm.setInt(1, i.getCodInstrutor());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public Instrutor find(String emailLogin){
        
        Instrutor result = null;
        conn = open();
                    
        try {
            PreparedStatement stm = conn.
                    prepareStatement("SELECT * FROM Instrutores WHERE email LIKE ? ;");
            
            stm.setString(1,emailLogin);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                    Instrutor i = new Instrutor(
                                    rs.getInt(1),        // codInstrutor
                                    rs.getString(2),     // nome
                                    rs.getString(3),     // senha
                                    rs.getString(4),     // telefone
                                    rs.getString(5)      // email
                                    );
                    result = i;       
            }   
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }  
        return result;
    }

    
    public List<Instrutor> all(){
        
        ArrayList<Instrutor> result = new ArrayList<>();
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Instrutores ORDER BY codInstrutor ASC;");
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Instrutor i = new Instrutor(
                                    rs.getInt(1),        // codInstrutor
                                    rs.getString(2),     // nome
                                    rs.getString(3),     // senha
                                    rs.getString(4),     // telefone
                                    rs.getString(5)      // email
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
