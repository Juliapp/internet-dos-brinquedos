package facade;

import controladores.ControladorDeConexao;
import controladores.ControladorDeTelas;
import controladores.ControllerDeTratamento;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
public class FacadeExibicao {

    private final ControladorDeConexao conexao;
    private final ControllerDeTratamento tratamento;
    private ControladorDeTelas telas;
    private static FacadeExibicao facade;
    //A gente pode instanciar a comunicação com o servidor e controlar por aqui

    
    private FacadeExibicao() {
        tratamento = new ControllerDeTratamento();
        conexao = new ControladorDeConexao(tratamento);
    }
    
    public static synchronized FacadeExibicao getInstance() {
        return (facade == null) ? facade = new FacadeExibicao() : facade;
    }
    
    public void putStage(Stage stage){
        telas = new ControladorDeTelas(stage);
    }
    
    public void initialize(){
        String ip = "127.0.0.1";
        int host = 1234;
        conexao.conectarServidor(ip, host);
    }

    public void initialize(String ip, int host){
        conexao.conectarServidor(ip, host);
    }
          
    public void initialize(int host){
        conexao.conectarServidor("127.0.0.1", 1234);
    }
    
    public void telaConect(){
        telas.telaConectarServer();
    }
    
    public void telaHome(){
        telas.telaHome();
    }
    
    public ObservableList getList(){
        return tratamento.getList();
    }
}
