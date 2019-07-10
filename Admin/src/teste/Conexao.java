package teste;

import facade.ClienteFacade;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Conexao{
    private Socket socket;
    private int porta;
    private ConectionIO io;
    private ClienteFacade facade;

    
    public Conexao(ClienteFacade facade){
        this.facade = facade;
    }
    
    public void rodar() throws IOException {
        conectar();
        
        io = new ConectionIO(socket, facade);
        Thread threadIO = new Thread(io);
        threadIO.run();
    }
    

    public Socket getSocket(){
        return socket;

    }
    
    private void criarSocket(String host, int porta) throws IOException {
        this.porta = porta;
        socket = new Socket(host, porta);
    }

    private void criarSocket(int porta) throws IOException {
        String host = "127.0.0.1";
        this.porta = porta;
        socket = new Socket(host, porta);
    }
    public int getPorta(){
        return porta;
    }
    
    
private void conectar() throws IOException{
        boolean entrou = false;

            System.out.println("O cliente vai rodar na mesma placa de rede? S/N");
            System.out.println("Você pode trocar a opção digitando a resposta errada.");
            Scanner s = new Scanner(System.in);
            String resposta = s.nextLine();

            if(resposta.equals("s") || resposta.equals("S")){
                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int port = Integer.parseInt(sporta);

                criarSocket(port);
            }else if(resposta.equals("n") || resposta.equals("N")){
                System.out.println("Qual o endereço?");
                s = new Scanner(System.in);
                String endereco = s.nextLine(); 

                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int port = Integer.parseInt(sporta);	

                criarSocket(endereco, port);
            }else{
                System.out.println("Resposta incorreta");
            }
            
    }
    
}


