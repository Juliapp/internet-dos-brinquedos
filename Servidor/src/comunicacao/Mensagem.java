package comunicacao;

import java.io.Serializable;

public class Mensagem implements Serializable {
    private Solicitante solicitante;
    private boolean has;
    private byte[] bytes;

    public Mensagem(Solicitante sol) {
        this.solicitante = sol;
        has = false;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }
    
    public boolean hasMensagem(){
        return has;
    }
    
    public void enviouMensagem(){
        has = false;
    }
    
}
