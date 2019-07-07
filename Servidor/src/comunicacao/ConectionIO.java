package comunicacao;
   
import facade.ServidorFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;

public class ConectionIO implements Runnable{
    private Socket socket;
    private ServidorFacade servidorFacade;
    private Solicitante id;
    
    public ConectionIO(Socket socket, Solicitante id){
        this.socket = socket;
        this.id = id;
        servidorFacade = ServidorFacade.getInstance();
    }

    @Override
    public void run() {
        while(true){
            try{
                BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF8"));
                System.out.println(in.readLine());
                //socket.getInputStream();

                socket.getOutputStream();
            }catch(IOException e){}
        }
        
    }
    
        public Mensagem buscarMensagem(){
        ArrayList<Mensagem> listM = servidorFacade.getMensagens();
        ListIterator l = listM.listIterator();
        
        while(l.hasNext()){
            Mensagem m =(Mensagem) l.next();
            if(m.getSolicitante() == id){
                return m;
            }
        }    
        return null;
    }
}
