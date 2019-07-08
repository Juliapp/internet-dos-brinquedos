package controladores;

import comunicacao.Conexao;
import comunicacao.Mensagem;
import comunicacao.Solicitante;
import java.io.IOException;
import java.util.ArrayList;

public class ControladorDeClientes {
   private Conexao adm;
   private Conexao sensor;
   private Conexao exibicao;
   
   private Mensagem mensagemADM;
   private Mensagem mensagemSensor;
   private Mensagem mensagemExibixao;


    public ControladorDeClientes() {
        iniciarObjMensagens();
    }
    
    public void iniciarObjMensagens(){
        mensagemADM = new Mensagem(Solicitante.ClienteADM);
        mensagemSensor = new Mensagem(Solicitante.Sensor);
        mensagemExibixao = new Mensagem(Solicitante.ClienteExib);
    }
    
    public void iniciarClienteADM() throws IOException{                                       
        adm = new Conexao(Solicitante.ClienteADM);
        adm.rodar();
    }
    
    public void iniciarClienteASensor() throws IOException{
        sensor = new Conexao(Solicitante.Sensor);
        sensor.rodar();
    }
    
    public void iniciarClienteExibicao() throws IOException{
        exibicao = new Conexao(Solicitante.ClienteExib);
        exibicao.rodar();
    }    
    
    public Mensagem getMensagem(Solicitante sol) {
        
        if(sol == Solicitante.ClienteADM){
            return mensagemADM;
        }
        else if(sol == Solicitante.ClienteExib){
            return mensagemExibixao;
        }
        else if(sol == Solicitante.Sensor){
            return mensagemSensor;
        }
        
        return null;
    }

    public void novaMensagem(String id, byte[] bytes) {
        if(null != id)switch (id) {
           case "ClienteADM":
               mensagemADM.setBytes(bytes);
               mensagemADM.setHasMensagemToTrue();
               break;
           case "ClienteExib":
               mensagemExibixao.setBytes(bytes);
               mensagemExibixao.setHasMensagemToTrue();
               break;
           case "Sensor":
               mensagemSensor.setBytes(bytes);
               mensagemSensor.setHasMensagemToTrue();    
               break;
           default:
               break;
       }    
    }

    
}