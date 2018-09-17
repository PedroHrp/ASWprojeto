/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import advancegym.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import model.Aluno;
import model.Instrutor;
import model.Modalidade;
import model.Statu;
import model.sqlite.ModalidadeSQLiteDAO;

/**
 * FXML Controller class
 *
 * @author Pedro
 */
public class FXMLMenuController {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXComboBox<Modalidade> cbModalidades;

    @FXML
    private JFXComboBox<Statu> cbStatus;
    
    @FXML
    private JFXCheckBox chbEditar;
    
    @FXML
    private JFXTextField tfMatricula;
    
    @FXML
    private JFXTextField tfNome;

    @FXML
    private JFXTextField tfEndereco;

    @FXML
    private JFXTextField tfRG;

    @FXML
    private JFXTextField tfCPF;

    @FXML
    private JFXTextField tfDataNasc;

    @FXML
    private JFXTextField tfDataInsc;
    
    @FXML
    private JFXButton btCadastroAluno;
    
    @FXML
    private JFXButton btCarregar;
   
    @FXML
    protected JFXListView<Aluno> lvAlunos;
    
    @FXML
    private Label tfApresentacao;
    
    private int modalidade;
    private int statu;
    private Instrutor instrutor;
    private Aluno aluno;

    public FXMLMenuController() {
    }
    @FXML
    protected void initialize(){
        
       tfMatricula.setEditable(false);
       btCarregar.setVisible(false);
       
       
       Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void OnScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("menu")){
                    
                    if(userData != null){
                        Instrutor i = (Instrutor)userData; //atencao erro de cast que pode ser tratado com class cast exeception
                        instrutor = i;
                        tfApresentacao.setText(instrutor.getNome());
                        
                    }
                    atualizarLista();
                }            
            }
        });
       
        ModalidadeSQLiteDAO dao = new ModalidadeSQLiteDAO();
        
        for(Modalidade m : dao.all()){
            cbModalidades.getItems().add(m);     
        }           
        
        cbModalidades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Modalidade>() {
           @Override
           public void changed(ObservableValue<? extends Modalidade> observable, Modalidade oldValue, Modalidade newValue) {
               modalidade = newValue.getId();
           }
        });
        
        for(Statu s : Statu.all()){
            cbStatus.getItems().add(s);
        }
        
        cbStatus.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Statu>() {
           @Override
           public void changed(ObservableValue<? extends Statu> observable, Statu oldValue, Statu newValue) {
               statu = newValue.getId();
           }
       });
        
       atualizarLista();

    }
    
    @FXML
    protected void cadastrarAluno(ActionEvent event) {
        
        try{
            if(tfNome.getText().isEmpty())
                throw new RuntimeException(" O campo nome não pode ser vazio ");
            if(tfEndereco.getText().isEmpty())
                throw new RuntimeException(" O campo endereço não pode ser vazio ");
            if(tfRG.getText().isEmpty())
                throw new RuntimeException(" O campo rg não pode ser vazio ");
            if(tfCPF.getText().isEmpty())
                throw new RuntimeException(" O campo cpf não pode ser vazio ");
            if(tfDataNasc.getText().isEmpty())
                throw new RuntimeException(" O campo data de nasc. não pode ser vazio ");
            if(tfDataInsc.getText().isEmpty())
                throw new RuntimeException(" O campo data de insc. não pode ser vazio ");
            
            if(chbEditar.isSelected()){
                aluno = new Aluno(Integer.parseInt(tfMatricula.getText()),tfNome.getText(), tfEndereco.getText(), modalidade, statu, instrutor.getCodInstrutor(), Integer.parseInt(tfRG.getText()),Integer.parseInt(tfCPF.getText()),tfDataNasc.getText(),tfDataInsc.getText());

            }else{
                aluno = new Aluno(tfNome.getText(), tfEndereco.getText(), modalidade, statu, instrutor.getCodInstrutor(), Integer.parseInt(tfRG.getText()),Integer.parseInt(tfCPF.getText()),tfDataNasc.getText(),tfDataInsc.getText());

            }
            aluno.save();
            System.out.println(aluno);
            
            JOptionPane.showMessageDialog(null," Enviado com sucesso! ");
              
        }catch(RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao Enviar Admin");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }finally{
            atualizarLista();
        }   
    }
    
    public void cbEditar(){
       
        if(chbEditar.isSelected()){
            tfMatricula.setEditable(true);
            btCarregar.setVisible(true);
            btCadastroAluno.setText("ATUALIZAR");
            
        }else{
            tfMatricula.setEditable(false);
            tfMatricula.setText("");
            btCarregar.setVisible(false);
            btCadastroAluno.setText("CADASTRAR");
            limparCampos();
        }
    }
    
    protected void limparCampos(){
        
        tfNome.setText("");
        tfEndereco.setText("");
        tfCPF.setText("");
        tfRG.setText("");
        tfDataNasc.setText("");
        tfDataInsc.setText("");
    }
    
    private void atualizarLista(){
        
        lvAlunos.getItems().clear();
        for(Aluno a :Aluno.all()){
           lvAlunos.getItems().add(a);
        }
        
        lvModalidade.getItems().clear();
        for(Modalidade m :Modalidade.all()){
            lvModalidade.getItems().add(m);
        }
        lvStatus.getItems().clear();
        for(Statu s :Statu.all()){
            lvStatus.getItems().add(s);
        }
    }
    
    
    @FXML
    protected void carregarAluno(ActionEvent event) {
        
        try{
            Aluno a = Aluno.find(Integer.parseInt(tfMatricula.getText()));
        
            tfNome.setText(a.getNome());
            tfEndereco.setText(a.getEndereco());
            tfCPF.setText(String.valueOf(a.getCpf()));
            tfRG.setText(String.valueOf(a.getRg()));
            tfDataNasc.setText(a.getDataNasc());
            tfDataInsc.setText(a.getDataInsc());           
            
        }catch(Exception e){
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao buscar Aluno");
            alert.setContentText("O numero de matricula nao foi encontrado ");
            alert.showAndWait();
        }  
        
    }
    
    @FXML
    protected void deletaAluno(ActionEvent event) {
        ObservableList<Aluno> ol = lvAlunos.getSelectionModel().getSelectedItems();
        
        if(!ol.isEmpty()){
            Aluno a = ol.get(0);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Desenha realmente excluir o Aluno ? ");
            alert.setContentText(a.toString());
            
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                a.delete();
                atualizarLista();
            }   
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhum aluno foi selecionado ");
            alert.setContentText("selecione algum aluno da lista");
            alert.showAndWait();
            
        }
    }
    
    
    @FXML
    protected void sairMenu(ActionEvent event) {
        System.out.println("Saiu");
        
        Main.trocarTela("login");
    }
    
    @FXML
    protected void closeMenu(ActionEvent event) {
        
        System.exit(0);
    }
    
    //----------- Modalidade 
    @FXML
    private JFXTextField tfNomeModalidade;
    
    @FXML
    private JFXListView<Modalidade> lvModalidade;
    
    @FXML
    protected void adicionarModalidade(ActionEvent event) {
        try{
            if(tfNomeModalidade.getText().isEmpty())
                throw new RuntimeException(" O campo nome não pode ser vazio ");
            
            Modalidade m = new Modalidade(tfNomeModalidade.getText());
            m.save();
            System.out.println(m);
            
            JOptionPane.showMessageDialog(null," Enviado com sucesso! ");
              
        }catch(RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao Enviar");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }finally{
            atualizarLista();
        } 
        
    }
    
    @FXML
    protected void removerModalidade(ActionEvent event) {
        ObservableList<Modalidade> ol = lvModalidade.getSelectionModel().getSelectedItems();
        
        if(!ol.isEmpty()){
            Modalidade m = ol.get(0);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Desenha realmente excluir essa Modalidade ? ");
            alert.setContentText(m.toString());
            
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                m.delete();
                atualizarLista();
            }   
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhuma modalidade foi selecionado ");
            alert.setContentText("selecione algum aluno da lista");
            alert.showAndWait();
            
        }
    }
    
    //----------- Status 
    @FXML
    private JFXTextField tfNomeStatus;
    
    @FXML
    private JFXListView<Statu> lvStatus;
    
    @FXML
    protected void adicionarStatus(ActionEvent event) {
        try{
            if(tfNomeStatus.getText().isEmpty())
                throw new RuntimeException(" O campo nome não pode ser vazio ");
            
            Statu s = new Statu(tfNomeStatus.getText());
            s.save();
            System.out.println(s);
            
            JOptionPane.showMessageDialog(null," Enviado com sucesso! ");
              
        }catch(RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao Enviar");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }finally{
            atualizarLista();
        } 
        
    }
    
    @FXML
    protected void removerStatus(ActionEvent event) {
        ObservableList<Statu> ol = lvStatus.getSelectionModel().getSelectedItems();
        
        if(!ol.isEmpty()){
            Statu s = ol.get(0);
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Desenha realmente excluir essa Modalidade ? ");
            alert.setContentText(s.toString());
            
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.get() == ButtonType.OK){
                s.delete();
                atualizarLista();
            }   
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informação");
            alert.setHeaderText("Nenhuma modalidade foi selecionado ");
            alert.setContentText("selecione algum aluno da lista");
            alert.showAndWait();
            
        }
    }
    
}
