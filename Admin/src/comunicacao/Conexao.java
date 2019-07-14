package comunicacao;

import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Conexao{
    private Socket socket;
    private final Solicitante id;
    private int porta;
    private ConectionIO io;
    private final int PORTA = 5555;

    private ControllerDeTratamento tratamento;
    private ControladorDeMensagens mensagens;
    
    public Conexao(Solicitante id, ControllerDeTratamento tratamento, ControladorDeMensagens mensagens){
        this.tratamento = tratamento;
        this.mensagens = mensagens;
        this.id = id;
    }
    
    public void iniciar() throws IOException {
        conectar();
        io = new ConectionIO(socket, id, tratamento, mensagens);
    }
    
    public ConectionIO getConectionIO(){
        return io;
    }

    public Socket getSocket(){
        return socket;

    }
    
    private Socket criarSocket(int porta) throws IOException {
        this.porta = porta;
        return socket = new Socket("127.0.0.1",porta);
    }
   
    public int getPorta(){
        return porta;
    }
    
    
    private void conectar() throws IOException{
        
        /*
        System.out.println("Qual a porta?");
        Scanner s = new Scanner(System.in);
        String sporta = s.nextLine();   
        int porta = Integer.parseInt(sporta);
        */
        
        criarSocket(PORTA);
 
    }
    
}


