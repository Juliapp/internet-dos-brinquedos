/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controladores;

import facade.FacadeExibicao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Item;

public class FXMLControlerConexao implements Initializable {

    @FXML   private Button conectarServer;
    @FXML   private TextField entradaIP;

    FacadeExibicao facade;
    
    @FXML   private Label label1;
    @FXML   private Label label2;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FacadeExibicao.getInstance();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
/*
        if(entradaIP.getText() == null){
            facade.initialize();
        }else if(entradaIP.getText() != null){
            facade.initialize(entradaIP.getText());
        }
*/
        conectarServer.setDisable(true);
        facade.telaHome();
    }
    
}
