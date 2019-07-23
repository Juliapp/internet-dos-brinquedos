package controladores;
import comunicacao.Conexao;
import comunicacao.ThreadConections;

public class ControladorDeConexao {
    private Conexao servidor;
    private ThreadConections tcIO;
   
    private final ControllerDeTratamento tratamento;

    public ControladorDeConexao(ControllerDeTratamento tratamento) {
        this.tratamento = tratamento;
    }
    
    public void conectarServidor(String ip, int host) {
        servidor = new Conexao(tratamento, ip, host);
        tcIO = new ThreadConections(servidor.getConectionIO());
        new Thread(tcIO).start();
    }

    
}