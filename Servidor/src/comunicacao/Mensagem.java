package comunicacao;

import java.io.Serializable;

public class Mensagem implements Serializable {
    private Solicitante solicitante;
    private boolean hasMensagem;
    private Object object;

    public Mensagem(Solicitante sol) {
        this.solicitante = sol;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }
    
    public void hasntMensagem(){
        hasMensagem = false;
    }
    
}
