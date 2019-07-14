package comunicacao;

import execoes.PilotoNaoExisteException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadConections extends Thread{
    ConectionIO adm;

    public ThreadConections(ConectionIO adm) {
        this.adm = adm;
    }
    
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                adm.tratar();

                //exib.tratar();
                //sensor.tratar();
            } catch (IOException | PilotoNaoExisteException | InterruptedException ex) {
                Logger.getLogger(ThreadConections.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
