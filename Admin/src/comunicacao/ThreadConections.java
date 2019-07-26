package comunicacao;

import execoes.PilotoNaoExisteException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Classe que inicializa e controla a
 * thread para a conexão.
 * 
 * @author Mateus Guimarães
 * @author Juliana Aragão
 */
public class ThreadConections extends Thread{
    ConectionIO servidor;

    public ThreadConections(ConectionIO servidor) {
        this.servidor = servidor;
    }
    
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                servidor.tratar();
            } catch (IOException | PilotoNaoExisteException | InterruptedException ex) {
                Logger.getLogger(ThreadConections.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
