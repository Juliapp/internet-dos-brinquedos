
package view.controladores;

import facade.FacadeExibicao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;


public class FXMLTabelaController implements Initializable {

    @FXML
    private TableView<?> table;
    FacadeExibicao facade;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FacadeExibicao.getInstance();
        table.getItems().setAll(facade.getList());
    }    
    
}
