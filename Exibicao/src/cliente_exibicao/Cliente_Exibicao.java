package cliente_exibicao;
 
import facades.FacadeExibicao;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Cliente_Exibicao extends Application{
    public static FacadeExibicao facade = FacadeExibicao.getInstance();
    
    
        @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        facade.initialize(primaryStage);
        Parent root = null;
        try {
             root = FXMLLoader.load(getClass().getResource("/telas/conectarservidor.fxml"));
         } catch (IOException ex) {
         }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();         
        
    } 
    
    public static void main(String[] args) {
        launch(args);

    }
    
}
