package comunicacao;

public class Mensagem {
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
    
    public void setHasMensagemToTrue(){
        has = true;
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
