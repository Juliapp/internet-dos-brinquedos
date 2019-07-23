package controladores;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static util.Settings.Scenes.HOME;
import static util.Settings.Scenes.TELA_CONECTAR_SERVIDOR;

public class ControladorDeTelas {
    private Stage stage;
    
    public ControladorDeTelas(Stage primaryStage){
        stage = primaryStage;   
    }
    
    
    public void telaConectarServer(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(TELA_CONECTAR_SERVIDOR.getValue()));
        } catch (IOException ex) {
        }
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();           
    }
    
    public void telaHome(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(HOME.getValue()));
        } catch (IOException ex) {
        }
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();  
    }        
}
