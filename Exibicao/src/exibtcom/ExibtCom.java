package exibtcom;

import facade.FacadeExibicao;
import javafx.application.Application;
import javafx.stage.Stage;

public class ExibtCom extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FacadeExibicao facade = FacadeExibicao.getInstance();
        facade.putStage(stage);
        facade.telaConect();
        
        /*
        Parent root = FXMLLoader.load(getClass().getResource(TELA_CONECTAR_SERVIDOR.getValue()));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
