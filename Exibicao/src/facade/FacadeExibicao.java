package facade;

import controladores.ControladorDeConexao;
import controladores.ControladorDeTelas;
import controladores.ControllerDeTratamento;
import java.io.IOException;
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
        String ip = "172.16.103.214";
        int host = 1234;
        conexao.conectarServidor(ip, host);
    }

    public void initialize(String ip){
        int host = 1234;
        conexao.conectarServidor(ip, host);
    }
    
    public void telaConect(){
        telas.telaConectarServer();
    }
    
    public void telaHome() throws IOException{
        telas.telaHome();
    }
    
    public ObservableList getList(){
        return tratamento.getList();
    }
}
