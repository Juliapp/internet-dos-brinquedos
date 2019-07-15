package comunicacao;
   
import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import execoes.PilotoNaoExisteException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class ConectionIO {
    //tratamento de mensagem e checagem de mensagem
    private final ControllerDeTratamento tratamento;
    private final ControladorDeMensagens mensagens;
    private final Solicitante id;
    private final OutputStream output;
    private InputStream input = null; 
    
    public ConectionIO(Socket socket, Solicitante id, ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) throws IOException{
        this.tratamento = tratamento;
        this.mensagens = mensagens;
        this.id = id;
        
        output = socket.getOutputStream();
        input = socket.getInputStream();       
    }
    
    public Solicitante getSolicitante(){
        return id;
    }

    public void tratar() throws IOException, PilotoNaoExisteException, InterruptedException {
        tratarOutput(output);
        tratarInput(input);
    }
    
    private void tratarOutput(OutputStream output) throws IOException{
        
        if(mensagens.getMensagem(id).hasMensagem()){
            Mensagem mensagem = mensagens.getMensagem(id);
            byte[] bytes = mensagem.getBytes();
            output.write(bytes, 0, bytes.length);
            output.flush();
            mensagens.getMensagem(id).enviouMensagem();
        }    
    }
    
    private void tratarInput(InputStream input) throws IOException, PilotoNaoExisteException{
        byte[] bytes = toByteArray(input);
        if(bytes.length > 0){
            tratamento.tratarMensagem(bytes);
        }
        if(id != Solicitante.ClienteADM){
            System.out.print(bytes.length + "       " + id + "\n");     
        }
    }
    
    private byte[] toByteArray(InputStream input) throws IOException{
        DataInputStream dataInputStream = new DataInputStream(input);
        
        byte buffer[] = new byte[dataInputStream.available()]; 
        dataInputStream.readFully(buffer);

        return buffer;
    }
    
    public void fecharSocket(Socket socket) throws IOException{
        socket.close();
    }
    
    
    
}
