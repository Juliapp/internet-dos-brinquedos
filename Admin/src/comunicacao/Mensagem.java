package comunicacao;

import org.json.JSONObject;

/**Classe responsável por aplicar
 * empacotamento dos dados que vão
 * ser armazenados no servidor.
 * 
 * @author Mateus Guimarães
 * @author Juliana Aragão
 */
public class Mensagem {
    private Solicitante solicitante;
    private boolean has;
    private byte[] bytes;
    private JSONObject json;

    public Mensagem(Solicitante sol) {
        has = false;
        this.solicitante = sol;
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

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
    
    
}
