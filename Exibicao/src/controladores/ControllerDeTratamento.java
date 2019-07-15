package controladores;

import facade.FacadeExibicao;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
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
        print(convertToString(bytes));
        
    }
    
    
    public void print(String info){
        JSONObject dados = new JSONObject(info);
        JSONArray array = dados.getJSONArray("arrayDeJogadores");
        for(int i=0; i< array.length(); i++){
            System.out.println(array.getString(i).toString());
        }
    }
    
    
}
