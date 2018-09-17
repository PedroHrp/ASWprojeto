package controller;

import advancegym.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import model.Instrutor;

/**
 * FXML Controller class
 *
 * @author Pedro
 */

public class FXMLCadastroAdminController {

    /**
     * Initializes the controller class.
     */   
    
    @FXML
    private JFXTextField tfLoginCadastro;

    @FXML
    private JFXPasswordField tfSenhaCadastro;

    @FXML
    private JFXTextField tfNomeCadastro;

    @FXML
    private JFXTextField tfTelefoneCadastro;

    
    @FXML
    protected void cadastrarAdmin(ActionEvent event) {

          try{
              if(tfLoginCadastro.getText().isEmpty())
                throw new RuntimeException(" O campo email não pode ser vazio ");
              if(tfNomeCadastro.getText().isEmpty())
                throw new RuntimeException(" O campo nome não pode ser vazio ");
              if(tfTelefoneCadastro.getText().isEmpty())
                throw new RuntimeException(" O campo telefone não pode ser vazio ");
              if(tfSenhaCadastro.getText().isEmpty())
                throw new RuntimeException(" O campo senha não pode ser vazio ");
              
              Instrutor i = new Instrutor(tfNomeCadastro.getText(), tfSenhaCadastro.getText(), tfTelefoneCadastro.getText(), tfLoginCadastro.getText());
              System.out.println(i.getEmail());
                      
              i.save();
              JOptionPane.showMessageDialog(null," Cadastrado com sucesso! ");
              Main.trocarTela("login");
              
          }catch(RuntimeException ex){
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Erro");
              alert.setHeaderText("Erro ao cadastrar Admin");
              alert.setContentText(ex.getMessage());
              alert.showAndWait();
          }
    }
    
    @FXML
    protected void voltarLogin(ActionEvent event) {
        System.out.println("Saiu");
        
        Main.trocarTela("login");
    }
    
    @FXML
    protected void closeCadastro(ActionEvent event) {
        System.exit(0);
    }
}
