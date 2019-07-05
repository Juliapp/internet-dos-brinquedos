package comunicacao;

import facade.ServidorFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.function.Consumer;


public class Conexao{
    private ServerSocket serverSocket;
    private Solicitante id;
    private ServidorFacade servidorFacade;
    private Mensagem mensagem;

    
    public Conexao(Solicitante id){
        this.id = id;
        this.servidorFacade = ServidorFacade.getInstance();
    }
    
    public void rodar() throws IOException {
//        Conexao server = new Conexao();

        this.conectar();
        Socket socket = this.esperandoConexao();

        this.tratarConexao(socket);
    }
    

    public ServerSocket getSocket(){
        return serverSocket;

    }
    
    private void criarServerSocket(int porta) throws IOException {
        //cria um serverSocket se tiver em só uma placa de rede
        serverSocket = new ServerSocket(porta);
    }
    
    private void criarServerSocket(int endereco, int porta) throws IOException {
        //cria um serverSocket se tiver mais de uma placa de rede
        serverSocket = new ServerSocket(endereco, porta);

    }
    
        private Socket esperandoConexao() throws IOException {
        //Faz o serverSocket esperar uma conexão, só da o retorno quando a conexão não é estabelecida
        System.out.println("Esperando a resposta do cliente .....");
        Socket socket = serverSocket.accept();
        System.out.println("conexão estabelecida com o cliente");
        return socket;
    }
    
    private void conectar() throws IOException{
        boolean entrou = false;

        do{
            System.out.println("O cliente vai rodar na mesma placa de rede? S/N");
            System.out.println("Você pode trocar a opção digitando a resposta errada.");
            Scanner s = new Scanner(System.in);
            String resposta = s.nextLine();

            if(resposta.equals("s") || resposta.equals("S")){
                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int porta = Integer.parseInt(sporta);

                criarServerSocket(porta);

                entrou = true;

            }else if(resposta.equals("n") || resposta.equals("N")){
                System.out.println("Qual o endereço?");
                s = new Scanner(System.in);
                String sendereco = s.nextLine(); 
                
                int endereco = Integer.parseInt(sendereco);	 

                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int porta = Integer.parseInt(sporta);	

                criarServerSocket(endereco, porta);

                entrou = true;
            }else{
                System.out.println("Resposta incorreta");
            }
            
        }while(!entrou);
    }
     
    //criar streams de entrada e saída
    private void tratarConexao(Socket socket){
       
            
        try{
            BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream(), "UTF8"));
            System.out.println(in.readLine());
            //socket.getInputStream();

            socket.getOutputStream();
        }catch(IOException e){
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


