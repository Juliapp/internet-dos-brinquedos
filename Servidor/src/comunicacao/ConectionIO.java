package comunicacao;
   
import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import execoes.PilotoNaoExisteException;
import facade.ServidorFacade;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
        
        InputStreamReader inr;  
        BufferedReader bfr;
        
        Writer ouw;
        BufferedWriter bfw;

        
        
        while(true){
            System.out.println("buzina");
            try{
                OutputStream output = socket.getOutputStream();
                InputStream input = socket.getInputStream();
                tratarOutput(output);
                tratarInput(input);
                
            }catch(IOException e){
                e.printStackTrace();
            } catch (PilotoNaoExisteException ex) {
                Logger.getLogger(ConectionIO.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    private void tratarOutput(OutputStream output) throws IOException{
        Writer ouw;
        BufferedWriter bfw;
        
        if(mensagens.getMensagem(id).hasMensagem()){
            Mensagem mensagem = mensagens.getMensagem(id);
            byte[] bytes = mensagem.getBytes();
            output.write(bytes, 0, bytes.length);
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
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        byte[] dyn_data = new byte[64];
        dataInputStream.readFully(dyn_data);

        DataInputStream speedData = new DataInputStream(new ByteArrayInputStream(dyn_data));
        System.out.println("speedData.available:" + speedData.available());

        for ( int i = 0; i < speedData.available(); i++ ) { 
        // exception deals catches EOF
               byte ch = speedData.readByte();
               System.out.print((char) ch + "(" + ch + ")");
        }
        return null;
    }
    
    public void fecharSocket(Socket socket) throws IOException{
        socket.close();
    }
    
    
    
}
