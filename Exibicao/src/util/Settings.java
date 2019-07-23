package util;

public class Settings {
        
    public enum Scenes{    
        TELA_CONECTAR_SERVIDOR("/view/telas/FXMLConexao.fxml"),
        HOME("/view/telas/FXMLHome.fxml");
        
        private final String value;
        
        Scenes(String value){
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
    }
}
