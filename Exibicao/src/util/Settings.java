package util;

/**
 *
 * @author Uellington Damasceno
 */
public class Settings {
        
    public enum Scenes{       
        TELA_CONECTAR_SERVIDOR("conectarservidor.fxml", false),
        HOME("Home.fxml", false);
        
        private final String value;
        private final boolean cache;
        
        Scenes(String value, boolean inCache){
            this.value = value;
            this.cache = inCache;
        }
        
        public String getValue(){
            return this.value;
        }
        
        public boolean isCache(){
            return this.cache;
        }
    }
}
