
package advancegym;

//import com.sun.deploy.util.FXLoader;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Pedro
 */
public class Main extends Application {
    
    private static Stage stage;
    private static Scene loginScene;
    private static Scene menuScene;
    private static Scene cadastroScene;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {              
            
//        AlunoSQLiteDAO dao = new AlunoSQLiteDAO();
//        
//        System.out.println(dao.all());
//        
//        System.exit(0);
        
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);         
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/FXMLLogin.fxml"));
        loginScene = new Scene(fxmlLogin);
               
        Parent fxmlMenu = FXMLLoader.load(getClass().getResource("/view/FXMLMenu.fxml"));
        menuScene = new Scene(fxmlMenu);
        
        Parent fxmlCadastro = FXMLLoader.load(getClass().getResource("/view/FXMLCadastroAdmin.fxml"));
        cadastroScene = new Scene(fxmlCadastro);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    public static void trocarTela(String scr, Object userData){
        switch(scr){
            case "login":
                stage.setScene(loginScene);
                notifyAllListeners("login", userData);
                break;
            case "menu":
                stage.setScene(menuScene);
                notifyAllListeners("menu", userData);
                break;
            case "cadastro":
                stage.setScene(cadastroScene);
                notifyAllListeners("cadastro", userData);
                break;
        }
    }
    
    public static void trocarTela(String scr){
        trocarTela(scr, null);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    // --------- Observable ---------
    
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();
    
    public static interface OnChangeScreen{
        void OnScreenChanged(String newScreen, Object userData);
    }
    
    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }
    
    private static void notifyAllListeners(String newScreen, Object userData){
        for(OnChangeScreen l: listeners)
            l.OnScreenChanged(newScreen, userData);
    }
}
