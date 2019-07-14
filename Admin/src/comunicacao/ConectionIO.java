package comunicacao;
   
import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import execoes.PilotoNaoExisteException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConectionIO {
    //tratamento de mensagem e checagem de mensagem
    private ControllerDeTratamento tratamento;
    private ControladorDeMensagens mensagens;
    private Socket socket; 
    private Solicitante id;
    private OutputStream output;
    private InputStream input = null; 
    
    public ConectionIO(Socket socket, Solicitante id, ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) throws IOException{
        this.tratamento = tratamento;
        this.mensagens = mensagens;
        this.socket = socket;
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
        //Thread.sleep(2000);
    }
    
    private void tratarOutput(OutputStream output) throws IOException{
        
        if(mensagens.getMensagem(id).hasMensagem()){
            System.out.println("Entrou no IF");
            Mensagem mensagem = mensagens.getMensagem(id);
            byte[] bytes = mensagem.getBytes();
            System.out.println(bytes.length);
            output.write(bytes, 0, bytes.length);
            output.flush();
            mensagens.getMensagem(id).enviouMensagem();
        }    
    }
    
    private void tratarInput(InputStream input) throws IOException, PilotoNaoExisteException{
        byte[] bytes = toByteArray(input);
        if(bytes.length > 0){
            System.out.println("Chegouu pacote");
            tratamento.tratamentoMensagem(bytes);
            
            
        }
      //  System.out.print(new String(bytes, StandardCharsets.UTF_8));
      //  System.out.print(bytes.length + "       ");
      //  System.out.println(bytes.toString() + "     ");
        
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
