package comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket serverSocket;
    
    public static void main(String[] args) throws ClassNotFoundException{
        preSet();
        ServerSocket serverSock;
        try {
            serverSock = new ServerSocket(5555);
            while(true){
                System.out.println("O servidor está rodando na porta"+serverSock.getLocalPort());
                
                Socket recebido = serverSock.accept();
                
                ObjectOutputStream os = new ObjectOutputStream(recebido.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(recebido.getInputStream());
                
                
                Thread t = new ControllerDeTratamento(recebido, os, is);
                t.start();
            }
        } catch (IOException e) {
            
        }
       

        
        /*
        
        try {
            
            Servidor0001 server = new Servidor0001();
            server.criarServerSocket(12345);
                
            while(true){
                //Esse loop faz a coneão não se "quebrar" em apenas uma conexão
                Socket socket = server.esperandoConexao();
                server.tratarConexao(socket);
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor0001.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        */
        
    }

    
    
    private void criarServerSocket(int porta) throws IOException{
        //cria um socket
        serverSocket = new ServerSocket(porta);
    }
    
    
    
    private Socket esperandoConexao() throws IOException{     
        //Faz o socket esperar uma conexão, só da o retorno quando a conexão não é estabelecida
        Socket socket = serverSocket.accept();        
        return socket;
    }
    

    
    //Vou mexer nisso aqui!
    private void tratarConexao(Socket socket) throws IOException{
        //Ponto entre o cliente e o servidor
        try{
            
             ObjectInputStream input = new  ObjectInputStream( socket.getInputStream() );
             ObjectOutputStream output = new ObjectOutputStream( socket.getOutputStream() );
             
             // pode usar o input.readObject pra pegar um obj do cliente
             
             String msg = input.readUTF();
             System.out.println("Mensagem Recebida: " + msg);
             output.writeUTF(msg);
             
             input.close();
             output.close();
             
        }catch(IOException e) {
            
        }finally{
            //idependente de erro ele fecha o socket
            fecharSocket(socket);
        }
        
    }
    
    private void fecharSocket(Socket s) throws IOException{
        s.close();
    }
    
    private static void preSet(){
        ServidorFacade facade = ServidorFacade.getInstance();
        facade.cadastrarEquipe("MUSTANG");
        facade.cadastrarEquipe("REDBUL");
        facade.cadastrarEquipe("TESLA");
        facade.cadastrarEquipe("REDBONE");
        facade.cadastrarEquipe("RIHANNA");
        
        facade.cadastrarCarro("E29JJA", "VERMELHO", "MUSTANG");
        facade.cadastrarCarro("YUSH21", "LARANJA", "REDBUL");
        facade.cadastrarCarro("2901S", "AMARELO", "TESLA");
        facade.cadastrarCarro("8394SDF", "VERDE", "REDBONE");
        facade.cadastrarCarro("SD252", "AZUL", "RIHANNA");
    }
    
    
}
    

