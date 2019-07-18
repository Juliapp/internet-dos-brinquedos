package controladores;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PassarTela {
    Stage primaryStage;
    Parent root;
    
    public PassarTela(Stage primaryStage){
        this.primaryStage = primaryStage;
        Parent root = null;
    }
    
    
    public void telaConectarServer(){
       try {
            root = FXMLLoader.load(getClass().getResource("/telas/conectarservidor.fxml"));
        } catch (IOException ex) {
        }
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();           
    }
    
    public void telaHome(){
       try {
            root = FXMLLoader.load(getClass().getResource("/telas/Home.fxml"));
        } catch (IOException ex) {
        }
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();           
    }    
    
}
