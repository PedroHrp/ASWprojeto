package model.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

public class AlunoSQLiteDAO extends SQLiteBase {
    
    public AlunoSQLiteDAO(){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Alunos ("
                    + "matricula INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT,"
                    + "endereço TEXT,"
                    + "codModalidade INTEGER,"
                    + "codStatu INTEGER,"
                    + "codInstrutor INTEGER,"
                    + "rg INTEGER,"
                    + "cpf INTEGER,"
                    + "dataNasc TEXT,"
                    + "dataInsc TEXT,"
                    + "FOREIGN KEY(codInstrutor) REFERENCES Instrutores(codInstrutor),"
                    + "FOREIGN KEY(codModalidade) REFERENCES Modalidades(codModalidade),"
                    + "FOREIGN KEY(codStatu) REFERENCES Status(codStatu)"
                    + ");");
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    public void create(Aluno a){
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO Alunos VALUES(?,?,?,?,?,?,?,?,?,?);");
            
            stm.setString(2, a.getNome());
            stm.setString(3, a.getEndereco());
            stm.setInt(4, a.getModalidade());
            stm.setInt(5, a.getStatus());
            stm.setInt(6, a.getInstrutorA());
            stm.setInt(7, a.getRg());
            stm.setInt(8, a.getCpf());
            stm.setString(9, a.getDataNasc());
            stm.setString(10, a.getDataInsc());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public void update(Aluno a){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("UPDATE Alunos SET "
                            + "nome = ?, "
                            + "endereço = ?, "
                            + "codModalidade = ?, "
                            + "codStatu = ?,"
                            + "rg = ?,"
                            + "cpf = ?,"
                            + "dataNasc = ?,"
                            + "dataInsc = ?"
                            + "WHERE matricula = ? ;");
            
            stm.setString(1, a.getNome());
            stm.setString(2, a.getEndereco());
            stm.setInt(3, a.getModalidade());
            stm.setInt(4, a.getStatus());
            stm.setInt(5, a.getRg());
            stm.setInt(6, a.getCpf());
            stm.setString(7, a.getDataNasc());
            stm.setString(8, a.getDataInsc());
            stm.setInt(9, a.getMatricula());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public void delete(Aluno a){
        
        conn = open();
        
        try {
            PreparedStatement stm = conn.
                    prepareStatement("DELETE FROM Alunos WHERE matricula = ?;");
            
            stm.setInt(1, a.getMatricula());
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
    }
    
    
    public Aluno find(int matricula){
        
        Aluno result = null;
        conn = open();
                    
        try {
            PreparedStatement stm = conn.
                    prepareStatement("SELECT * FROM Alunos WHERE matricula = ? ;");
            
            stm.setInt(1,matricula);
            
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                    Aluno a = new Aluno(
                                    rs.getInt(1),        // matricula
                                    rs.getString(2),     // nome
                                    rs.getString(3),     // endereco
                                    rs.getInt(4),        // codModalidade
                                    rs.getInt(5),        // codStatus
                                    rs.getInt(6),        // codInstrutor
                                    rs.getInt(7),        // rg
                                    rs.getInt(8),        // cpf
                                    rs.getString(9),     // data nasc
                                    rs.getString(10)     // data isnc
                                    );
                    result = a;
                    
            }
          
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        
        return result;
    }
    
    
    public List<Aluno> all(){
        
        ArrayList<Aluno> result = new ArrayList<>();
        
        open();
        
        try {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Alunos ORDER BY matricula ASC;");
            
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                    Aluno a = new Aluno(
                                    rs.getInt(1),        // matricula
                                    rs.getString(2),     // nome
                                    rs.getString(3),     // endereco
                                    rs.getInt(4),        // codModalidade
                                    rs.getInt(5),        // codStatus
                                    rs.getInt(6),        // codInstrutor
                                    rs.getInt(7),        // rg
                                    rs.getInt(8),        // cpf
                                    rs.getString(9),     // data nasc
                                    rs.getString(10)     // data isnc
                                    );   
                result.add(a);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            close();
        }
        
        return result;
    }

    
}
