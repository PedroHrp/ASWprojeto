package controller;

import advancegym.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import model.Instrutor;


public class FXMLLoginController{
    
    @FXML
    protected Label label;
    
    @FXML
    protected JFXButton btEntrar;

    @FXML
    protected Hyperlink hlLembrar;

    @FXML
    protected JFXButton btCadastro;
    
     @FXML
    private JFXTextField tfLogin;

    @FXML
    private JFXPasswordField tfSenha;
    
    @FXML
    protected void initialize(){
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void OnScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("login"))
                System.out.println("tela: "+ newScreen+" ,"+userData);
            }
        });
    }
    
    @FXML
    protected void esqueceSenha(ActionEvent event) {
        String login = null;
        String telefone = null;
        String novaSenha = null;
        
        while(login == null || login.equals("")){
            login = JOptionPane.showInputDialog("Qual o email perdido?");
            if( login == null || login.equals("") ){
                JOptionPane.showMessageDialog(null, " voce nao respondeu a pergunta");
            }else{
                telefone = JOptionPane.showInputDialog("Qual o telefone do email?");
            }        
            try{
                if(Instrutor.find(login).getTelefone().equals(telefone)){
                    novaSenha = JOptionPane.showInputDialog("Digite a nova senha");
                    atualizarSenha(novaSenha,login);
                }else{
                    throw new RuntimeException(" O telefone digitado nao pertence ao email");
                }
           
            }catch(RuntimeException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Telefone invalido ");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }         
        }
    }
    
    protected void atualizarSenha(String novaSenha, String login){
         
        try{
            if(novaSenha == null || novaSenha.equals(""))
                throw new RuntimeException(" O campo senha não pode ser vazio ");

            Instrutor i = Instrutor.find(login);
            i.setSenha(novaSenha);
            i.save();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso !");
           
        }catch(RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao entrar ");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    protected void closeLogin(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    protected void entrarCadastro(ActionEvent event){
        System.out.println("Cadastro Admin");
        
        Main.trocarTela("cadastro");
    }
    
    @FXML
    protected void fazerLogin(ActionEvent event) {
        System.out.println("Entrar");
        //Main.trocarTela("menu");       
     
        boolean entrar = false;
        
        entrar = verificarLogin();
        
        if(entrar == true){
             Main.trocarTela("menu",Instrutor.find(tfLogin.getText()));
        }

    }
    
    protected boolean verificarLogin(){            
        
        try{
            if(tfLogin.getText().isEmpty())
                throw new RuntimeException(" O campo email não pode ser vazio ");
            if(tfSenha.getText().isEmpty())
                throw new RuntimeException(" O campo senha não pode ser vazio ");
            
            if(Instrutor.find(tfLogin.getText()).getSenha().equals(tfSenha.getText())){
               return true;
            }
           
        }catch(RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao entrar ");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }*/    
    
}
