package exibtcom;

import facade.FacadeExibicao;
import javafx.application.Application;
import javafx.stage.Stage;

public class Exibicao extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FacadeExibicao facade = FacadeExibicao.getInstance(); 
        facade.putStage(stage);
        facade.telaConect();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
