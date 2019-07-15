package facade;

import comunicacao.ConectionIO;
import controladores.ControladorDeConexaoServidor;
import controladores.ControllerDeTratamento;
import java.io.IOException;

public class FacadeExibicao {

    private final ControllerDeTratamento tratamento;
    private static FacadeExibicao facade;
    private final ControladorDeConexaoServidor conexaoServidor;
    //A gente pode instanciar a comunicação com o servidor e controlar por aqui

    public FacadeExibicao() {
        tratamento = new ControllerDeTratamento(this);
        conexaoServidor = new ControladorDeConexaoServidor(this.tratamento);
    }

    public static synchronized FacadeExibicao getInstance() {
        return (facade == null) ? facade = new FacadeExibicao() : facade;
    }

    public void iniciarServidor() throws IOException{                                       
        conexaoServidor.conectarServidor();
    }
    
     public ConectionIO getConectionIO(){
        return conexaoServidor.getConectionIO();
    }

}
