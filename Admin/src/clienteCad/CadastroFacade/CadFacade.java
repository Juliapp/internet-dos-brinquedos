package clienteCad.CadastroFacade;

import java.util.ArrayList;
import controladores.ControladorFactory;
import controladores.ControladorCorrida;
import controladores.ControladorDeDados;
import model.Carro;
import model.Equipe;
import model.Piloto;
import java.util.LinkedList;

public class CadFacade {


    
    //A gente pode instanciar a comunicação com o servidor e controlar por aqui
    
    public CadFacade() {
        
    }
    
     public void novaMensagem(String id, byte[] bytes){
        clientes.novaMensagem(id, bytes);
    }

   
}
