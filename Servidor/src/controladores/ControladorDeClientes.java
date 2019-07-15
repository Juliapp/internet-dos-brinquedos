package controladores;

import comunicacao.ConectionIO;
import comunicacao.Conexao;
import comunicacao.Solicitante;
import java.io.IOException;

public class ControladorDeClientes {
   private Conexao adm;
   private Conexao sensor;
   private Conexao exibicao;
   
    private final ControllerDeTratamento tratamento;
    private final ControladorDeMensagens mensagens;

    public ControladorDeClientes(ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) {
        this.tratamento = tratamento;
        this.mensagens = mensagens;
    }
    
    
    public void iniciarClienteADM() throws IOException{                                       
        adm = new Conexao(Solicitante.ClienteADM, tratamento, mensagens);
        adm.iniciar();
    }
    
    public void iniciarClienteASensor() throws IOException{
        sensor = new Conexao(Solicitante.Sensor, tratamento, mensagens);
        sensor.iniciar();
    }
    
    public void iniciarClienteExibicao() throws IOException{
        exibicao = new Conexao(Solicitante.ClienteExib, tratamento, mensagens);
        exibicao.iniciar();
    }
    
    public ConectionIO getConectionIOADM(){
        return adm.getConectionIO();
    }
    
    public ConectionIO getConectionIOExib(){
        return adm.getConectionIO();
    }   
    
    public ConectionIO getConectionIOSensor(){
        return adm.getConectionIO();
    }    
    
}