/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controladores;

import facade.FacadeExibicao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author nana-
 */
public class FXMLInicioController implements Initializable {

    @FXML   private TableView<?> table;
    @FXML   private TableColumn<?, ?> posCol;
    @FXML   private TableColumn<?, ?> pilotoCol;
    @FXML   private TableColumn<?, ?> timeCol;
    @FXML   private TableColumn<?, ?> tempoCol;
    @FXML   private TableColumn<?, ?> VoltaMRCol;
    @FXML   private TableColumn<?, ?> VoltasCol;
    
    FacadeExibicao facade;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FacadeExibicao.getInstance();
        table.getItems().setAll(facade.getList());
    }    
    
}
