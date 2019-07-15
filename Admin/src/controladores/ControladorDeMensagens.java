package controladores;

import comunicacao.Mensagem;
import comunicacao.Solicitante;

public class ControladorDeMensagens {
    private Mensagem mensagemServidor;
   
    public ControladorDeMensagens(){
        iniciarObjMensagens();
    }
    public void iniciarObjMensagens(){
        mensagemServidor = new Mensagem(Solicitante.ClienteADM);
    }
   
    public void novaMensagem(String id, byte[] bytes) {
        if(null != id)switch (id) {
           case "ClienteADM":
               mensagemServidor.setBytes(bytes);
               mensagemServidor.setHasMensagemToTrue();
               break;
           default:
               break;
       }    
    }

    public Mensagem getMensagem(Solicitante sol) {
        
        if(sol == Solicitante.ClienteADM){
            return mensagemServidor;
        }
        return null;
    }
   
}
