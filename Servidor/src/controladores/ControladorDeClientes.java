package controladores;

import comunicacao.ConectionIO;
import comunicacao.Conexao;
import comunicacao.Solicitante;
import java.io.IOException;

public class ControladorDeClientes {
   private Conexao adm;
   private Conexao sensor;
   private Conexao exibicao;
   

    public ControladorDeClientes() {
    }
    
    
    public void iniciarClienteADM(ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) throws IOException{                                       
        adm = new Conexao(Solicitante.ClienteADM, tratamento, mensagens);
        adm.iniciar();
    }
    
    public void iniciarClienteASensor(ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) throws IOException{
        sensor = new Conexao(Solicitante.Sensor, tratamento, mensagens);
        sensor.iniciar();
    }
    
    public void iniciarClienteExibicao(ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) throws IOException{
        exibicao = new Conexao(Solicitante.ClienteExib, tratamento, mensagens);
        exibicao.iniciar();
    }
    
    public ConectionIO getConectionIOADM(){
        return adm.getConectionIO();
    }
    
    public ConectionIO getConectionIOExib(){
        return exibicao.getConectionIO();
    }   
    
    public ConectionIO getConectionIOSensor(){
        return sensor.getConectionIO();
    }    
    
}