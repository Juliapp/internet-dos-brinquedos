package controladores;



import comunicacao.ConectionIO;
import comunicacao.Conexao;
import controladores.ControllerDeTratamento;
import java.io.IOException;

public class ControladorDeConexaoServidor {
     private Conexao servidor;
   
    private final ControllerDeTratamento tratamento;

    public ControladorDeConexaoServidor(ControllerDeTratamento tratamento) {
        this.tratamento = tratamento;
    }
    
    public void conectarServidor() throws IOException{                                       
        servidor = new Conexao(tratamento);
        servidor.iniciar();
    }
    
    public ConectionIO getConectionIO(){
        return servidor.getConectionIO();
    }

    public void conectarServidor(String ip, int host) {
        servidor = new Conexao(tratamento, ip, host);
    }

    
}