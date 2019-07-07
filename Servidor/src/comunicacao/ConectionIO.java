package comunicacao;
   
import facade.ServidorFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;


public class ConectionIO implements Runnable{
    private Socket socket;
    private ServidorFacade facade;
    private Solicitante id;
    
    public ConectionIO(Socket socket, Solicitante id){
        this.socket = socket;
        this.id = id;
        facade = ServidorFacade.getInstance();
    }

    @Override
    public void run() {
        while(true){
            try{
                
                OutputStream output = socket.getOutputStream();
                InputStream input = socket.getInputStream();
                tratarOutput(output);
                tratarInput(input);
                
            }catch(IOException e){}

        }

    }
    
    public void tratarOutput(OutputStream output) throws IOException{
        Mensagem mensagem = facade.getMensagem(id);
        if(mensagem.hasMensagem()){
            byte[] bytes = mensagem.getBytes();
            output.write(bytes, 0, bytes.length);
            facade.getMensagem(id).enviouMensagem();
        }    
    }
    
    public void tratarInput(InputStream input) throws IOException{
        
        byte[] bytes = null;
        input.read(bytes);
    }
    
    public void fecharSocket(Socket socket) throws IOException{
        socket.close();
    }
    
}
