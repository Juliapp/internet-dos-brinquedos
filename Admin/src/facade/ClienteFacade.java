package facade;

import comunicacao.ConectionIO;
import comunicacao.Mensagem;
import comunicacao.Solicitante;
import controladores.ControladorDeClientes;
import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import execoes.PilotoNaoExisteException;
import java.io.IOException;
import org.json.JSONObject;

public class ClienteFacade {

    private ControllerDeTratamento tratamento;
    private ControladorDeMensagens mensagens;
    private static ClienteFacade facade;
    private JSONObject respostaJSON;
    private final ControladorDeClientes clientes;
    //A gente pode instanciar a comunicação com o servidor e controlar por aqui

    public ClienteFacade() {
        tratamento = new ControllerDeTratamento(this);
        mensagens = new ControladorDeMensagens();
        clientes = new ControladorDeClientes(this.tratamento, this.mensagens);
    }

    public static synchronized ClienteFacade getInstance() {
        return (facade == null) ? facade = new ClienteFacade() : facade;
    }

    public void novaMensagem(String id, byte[] bytes) {
        mensagens.novaMensagem(id, bytes);
    }

    public Mensagem getMensagem() {
        return mensagens.getMensagem(Solicitante.ClienteADM);
    }
    
    public JSONObject getRespostaJSON() throws InterruptedException{
        Thread.sleep(3000);
        return this.respostaJSON;
    }
    
    public void setRespostaJSON(JSONObject json){
        this.respostaJSON = json;
    }
    
    public void iniciarClienteADM() throws IOException{                                       
        clientes.iniciarClienteADM();
    }
    
     public ConectionIO getConectionIOADM(){
        return clientes.getConectionIOADM();
    }

}
