package controladores;

import facade.FacadeExibicao;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class ControllerDeTratamento{

    private FacadeExibicao facade;
    
    public ControllerDeTratamento(FacadeExibicao facade) {
        this.facade = facade;
    }

    public byte[] convertToByte(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    public String convertToString(byte[] dados) {
        return new String(dados, StandardCharsets.UTF_8);
    }

    public void tratarMensagem(byte[] bytes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
