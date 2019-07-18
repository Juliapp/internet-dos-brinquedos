package comunicacao;

import controladores.ControllerDeTratamento;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao{
    private Socket socket;
    private int porta;
    private ConectionIO io;

    private final ControllerDeTratamento tratamento;
    
    public Conexao(ControllerDeTratamento tratamento){
        this.tratamento = tratamento;
    }
 
    public Conexao(ControllerDeTratamento tratamento, String ip, int porta){
        this.tratamento = tratamento;
        try {
            criarSocket(ip, porta);
            io = new ConectionIO(socket, tratamento);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void iniciar() throws IOException {
        conectar();
        io = new ConectionIO(socket, tratamento);
    }
    
    public ConectionIO getConectionIO(){
        return io;
    }

    public Socket getSocket(){
        return socket;

    }
    
    private Socket criarSocket(int porta) throws IOException {
        this.porta = porta;
        return socket = new Socket("127.0.0.1" , porta);
    }
    
     private Socket criarSocket(String host, int porta) throws IOException {
        this.porta = porta;
        return socket = new Socket(host , porta);
    }   
   
    public int getPorta(){
        return porta;
    }
    
    private void conectar() throws IOException{
        boolean entrou = false;

        do{
            System.out.println("O servidor vai rodar na mesma placa de rede? S/N");
            System.out.println("Você pode trocar a opção digitando a resposta errada.");
            Scanner s = new Scanner(System.in);
            String resposta = s.nextLine();

            if(resposta.equals("s") || resposta.equals("S")){
                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int porta = Integer.parseInt(sporta);

                criarSocket(porta);

                entrou = true;

            }else if(resposta.equals("n") || resposta.equals("N")){
                System.out.println("Qual o endereço?");
                s = new Scanner(System.in);
                String endereco = s.nextLine(); 
                	 
                System.out.println("Qual a porta?");
                s = new Scanner(System.in);
                String sporta = s.nextLine();   
                int porta = Integer.parseInt(sporta);	

                criarSocket(endereco, porta);

                entrou = true;
            }else{
                System.out.println("Resposta incorreta");
            }
            
        }while(!entrou);
    }
    
}


