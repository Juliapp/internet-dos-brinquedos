package comunicacao;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private boolean has;
    private byte[] bytes;

    public Mensagem() {
        has = false;
        has = false;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    
    public void setHasMensagemToTrue(){
        has = true;
    }

    public boolean hasMensagem(){
        return has;
    }
    
    public void enviouMensagem(){
        has = false;
    }
    
    
}
