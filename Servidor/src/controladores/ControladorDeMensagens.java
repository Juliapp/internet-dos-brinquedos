package controladores;

import comunicacao.Mensagem;
import comunicacao.Solicitante;

public class ControladorDeMensagens {
    private Mensagem mensagemADM;
    private Mensagem mensagemSensor;
    private Mensagem mensagemExibixao;
   
    public ControladorDeMensagens(){
        iniciarObjMensagens();
    }
    public void iniciarObjMensagens(){
        mensagemADM = new Mensagem(Solicitante.ClienteADM);
        mensagemSensor = new Mensagem(Solicitante.Sensor);
        mensagemExibixao = new Mensagem(Solicitante.ClienteExib);
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
   
}
