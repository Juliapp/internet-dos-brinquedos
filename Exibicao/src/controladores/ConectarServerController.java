package controladores;

import facades.FacadeExibicao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ConectarServerController implements Initializable {
    FacadeExibicao facade = FacadeExibicao.getInstance();
    
    @FXML private Button conectarServer;
    @FXML private TextField entradaIP;
    @FXML private TextField entradaPorta;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //facade.iniciarServidor(entradaIP.getText(), Integer.parseInt(entradaPorta.getText()));
        conectarServer.setVisible(false);
        facade.trocarTelaHome();
    }
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  
    }    
    
}
