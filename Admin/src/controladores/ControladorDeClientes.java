package controladores;

import comunicacao.ConectionIO;
import comunicacao.Conexao;
import comunicacao.Mensagem;
import comunicacao.Solicitante;
import java.io.IOException;

public class ControladorDeClientes {
   private Conexao servidor;
   
    private final ControllerDeTratamento tratamento;
    private final ControladorDeMensagens mensagens;

    public ControladorDeClientes(ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) {
        this.tratamento = tratamento;
        this.mensagens = mensagens;
    }
    
    
    public void iniciarServidor() throws IOException{                                       
        servidor = new Conexao(Solicitante.ClienteADM, tratamento, mensagens);
        servidor.iniciar();
    }
    
    public ConectionIO getConectionIOADM(){
        return servidor.getConectionIO();
    }
    
    
    
}