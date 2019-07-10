package facade;

import comunicacao.Mensagem;
import controladores.ControladorConexao;
import controladores.ControllerDeTratamento;
import execoes.PilotoNaoExisteException;
import org.json.JSONObject;

public class ClienteFacade {

    private ControladorConexao transmissao;
    private ControllerDeTratamento tratamento;
    private static ClienteFacade facade;
    private JSONObject respostaJSON;
    //A gente pode instanciar a comunicação com o servidor e controlar por aqui

    public ClienteFacade() {
        transmissao = new ControladorConexao(this);
        tratamento = new ControllerDeTratamento(this);
    }

    public static synchronized ClienteFacade getInstance() {
        return (facade == null) ? facade = new ClienteFacade() : facade;
    }

    public void novaMensagem(byte[] bytes) {
        transmissao.novaMensagem(bytes);
    }

    public Mensagem getMensagem() {
        return transmissao.getMensagem();
    }

    public void trataMensagem(byte[] bytes) throws PilotoNaoExisteException {
        
        this.respostaJSON =  tratamento.tratamentoMensagem(bytes);
    }
    
    public JSONObject getRespostaString(){
        return this.respostaJSON;
    }

}
