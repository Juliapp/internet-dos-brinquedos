package comunicacao;
   
import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import execoes.PilotoNaoExisteException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ConectionIO implements Runnable{
    //tratamento de mensagem e checagem de mensagem
    private ControllerDeTratamento tratamento;
    private ControladorDeMensagens mensagens;
    private Socket socket; 
    private Solicitante id;
    
    public ConectionIO(Socket socket, Solicitante id, ControllerDeTratamento tratamento, ControladorDeMensagens mensagens){
        this.tratamento = tratamento;
        this.mensagens = mensagens;
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("O cliente está rodando na porta: " + socket.getLocalPort());
        OutputStream output = null;
        InputStream input = null;
        
        try {
            output = socket.getOutputStream();
            input = socket.getInputStream();  
        } catch (IOException ex) {
            Logger.getLogger(ConectionIO.class.getName()).log(Level.SEVERE, null, ex);
        }    

        
        
        while(!Thread.currentThread().isInterrupted()){
            try {
                tratarOutput(output);
                tratarInput(input);
                wait(10);
            } catch (IOException | PilotoNaoExisteException | InterruptedException ex) {
                Logger.getLogger(ConectionIO.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
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
