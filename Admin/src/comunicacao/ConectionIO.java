package comunicacao;
   
import controladores.ControladorDeMensagens;
import controladores.ControllerDeTratamento;
import execoes.PilotoNaoExisteException;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**Classe responsável para tratamento da conexão
 * entre cliente e servidor.
 * 
 * @author Mateus Guimarães
 * @author Juliana Aragão
 */

public class ConectionIO {
   
    private final ControllerDeTratamento tratamento;
    private final ControladorDeMensagens mensagens;
    private final Solicitante id;
    private final OutputStream output;
    private InputStream input = null; 
    
    public ConectionIO(Socket socket, Solicitante id, ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) throws IOException{
        this.tratamento = tratamento;
        this.mensagens = mensagens;
        this.id = id;
        
        output = socket.getOutputStream();
        input = socket.getInputStream();       
    }
    
    /**Getter do solicitante, no caso
     * Admin
     * 
     * @return Objeto do tipo Solicitante
     */
    public Solicitante getSolicitante(){
        return id;
    }
    
    /**Método que chama os métodos de 
     * tratamento de saída e entrada
     * para e do servidor.
     * 
     * @throws IOException
     * @throws PilotoNaoExisteException
     * @throws InterruptedException 
     */
    public void tratar() throws IOException, PilotoNaoExisteException, InterruptedException {
        tratarOutput(output);
        tratarInput(input);
    }
    
    /**Método responsável pelo tratamento do que
     * será enviado para o servidor. Confere
     * a existência de mensagens, se houver
     * há a conversão em byte e a transferência.
     * 
     * @param output
     * @throws IOException 
     */
    private void tratarOutput(OutputStream output) throws IOException{
        
        if(mensagens.getMensagem(id).hasMensagem()){
            Mensagem mensagem = mensagens.getMensagem(id);
            byte[] bytes = mensagem.getBytes();
            //System.out.println(bytes.length);
            output.write(bytes, 0, bytes.length);
            output.flush();
            mensagens.getMensagem(id).enviouMensagem();
        }    
    }
    
    /**Método responsável pelo tratamento do que
     * é recebido do servidor.
     * 
     * @param input
     * @throws IOException
     * @throws PilotoNaoExisteException 
     */
    private void tratarInput(InputStream input) throws IOException, PilotoNaoExisteException{
        byte[] bytes = toByteArray(input);
        if(bytes.length > 0){
            tratamento.tratamentoMensagem(bytes);
        }
    }
    
    /**Método para conversão de um objeto do tipo
     * InputStream em DataInputStream, onde o mesmo
     * é novamente convertido em um array de byte.
     * 
     * @param input
     * @return Array de Byte
     * @throws IOException 
     */
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
