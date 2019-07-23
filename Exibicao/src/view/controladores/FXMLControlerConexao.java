/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controladores;

import facade.FacadeExibicao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FXMLControlerConexao implements Initializable {

    @FXML   private Button conectarServer;
    @FXML   private TextField entradaIP;
    @FXML   private TextField entradaPorta;

    FacadeExibicao facade;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FacadeExibicao.getInstance();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(entradaPorta.getText() != null){
            if(entradaIP.getText() == null){
                facade.initialize(Integer.parseInt(entradaPorta.getText()));
            }else if(entradaIP.getText() != null){
                facade.initialize(entradaIP.getText(),Integer.parseInt(entradaPorta.getText()));
            }
            conectarServer.setDisable(true);
        }
        
        facade.telaHome();
    }
    
}
