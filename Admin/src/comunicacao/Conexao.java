package comunicacao;

import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**Classe que estabelece a comunicação
 * do cliente Admin com o Servidor.
 * 
 * @author Mateus Guimarães
 * @author Juliana Aragão
 */
public class Conexao{
    private Socket socket;
    private final Solicitante id;
    private int porta;
    private ConectionIO io;
    

    private final ControllerDeTratamento tratamento;
    private final ControladorDeMensagens mensagens;
    
    public Conexao(Solicitante id, ControllerDeTratamento tratamento, ControladorDeMensagens mensagens){
        this.tratamento = tratamento;
        this.mensagens = mensagens;
        this.id = id;
    }
    
    /**Método responsável por dar início ao processo
     * de conexão
     * 
     * @throws IOException 
     */
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
    
    /**Método que inicializa o socket do cliente
     * Admin no caso onde o Servidor é
     * executado na mesma máquina.
     * 
     * @param porta
     * @return Socket
     * @throws IOException 
     */
    private Socket criarSocket(int porta) throws IOException {
        this.porta = porta;
        return socket = new Socket("127.0.0.1" , porta);
    }
    
    /**Método que inicializa o socket do cliente
     * Admin no caso onde o Servidor é
     * executado em outra máquina, solicitando
     * o endereço(ip).
     * 
     * @param host
     * @param porta
     * @return
     * @throws IOException 
     */
     private Socket criarSocket(String host, int porta) throws IOException {
        this.porta = porta;
        return socket = new Socket(host , porta);
    }   
   
    /**Getter da porta 
     * 
     * @return 
     */
    public int getPorta(){
        return porta;
    }
    
    /**Método que coleta informações necessárias 
     * sobre a inicialização da conexão 
     * 
     * @throws IOException 
     */
    private void conectar() throws IOException{
        boolean entrou = false;

        do{
            System.out.println("O cliente vai rodar na mesma placa de rede que o servidor? S/N");
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


