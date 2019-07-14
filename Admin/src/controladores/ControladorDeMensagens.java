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
    }
   
    public void novaMensagem(String id, byte[] bytes) {
        if(null != id)switch (id) {
           case "ClienteADM":
               mensagemADM.setBytes(bytes);
               mensagemADM.setHasMensagemToTrue();
               break;
           default:
               break;
       }    
    }

    public Mensagem getMensagem(Solicitante sol) {
        
        if(sol == Solicitante.ClienteADM){
            return mensagemADM;
        }
        return null;
    }
   
}
