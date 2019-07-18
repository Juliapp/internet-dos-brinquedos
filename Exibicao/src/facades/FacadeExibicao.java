package facades;

import controladores.ControladorDeConexaoServidor;
import comunicacao.ConectionIO;
import comunicacao.ThreadConections;
import controladores.ControllerDeTratamento;
import controladores.ConectarServerController;
import controladores.PassarTela;
import javafx.stage.Stage;
public class FacadeExibicao {

    private final ControllerDeTratamento tratamento;
    private static FacadeExibicao facade;
    private final ControladorDeConexaoServidor conexaoServidor;
    private PassarTela telas;
    private ThreadConections tcIO;
    //A gente pode instanciar a comunicação com o servidor e controlar por aqui

    private FacadeExibicao() {
        tratamento = new ControllerDeTratamento(this);
        conexaoServidor = new ControladorDeConexaoServidor(this.tratamento);
        telas = null;
    }
    
    public void initialize(Stage primaryStage){
        telas = new PassarTela(primaryStage);
    }

    public static synchronized FacadeExibicao getInstance() {
        return (facade == null) ? facade = new FacadeExibicao() : facade;
    }
    
    public void trocarTelaHome(){
        telas.telaHome();
    }

    public void iniciarServidor(String ip, int host){                                       
        conexaoServidor.conectarServidor(ip, host);
        tcIO = new ThreadConections(conexaoServidor.getConectionIO());
        new Thread(tcIO).start();             
    }
    
     public ConectionIO getConectionIO(){
        return conexaoServidor.getConectionIO();
    }

}
