package view.controladores;

import facade.FacadeExibicao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Item;

public class FXMLHome implements Initializable {

    @FXML   private TableColumn<?, ?> posCol;
    @FXML   private TableColumn<?, ?> pilotoCol;
    @FXML   private TableColumn<?, ?> timeCol;
    @FXML   private TableColumn<?, ?> tempoCol;
    @FXML   private TableColumn<?, ?> VoltaMRCol;
    @FXML   private TableColumn<?, ?> VoltasCol;
    
    @FXML   private TableView<Item> table;
    
    FacadeExibicao facade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = FacadeExibicao.getInstance();
        try{
            table.getItems().setAll(facade.getList());
        }catch(NullPointerException e){
        }
    }    
    
}
