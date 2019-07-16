package comunicacao;

import execoes.PilotoNaoExisteException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadConections extends Thread{
    ConectionIO adm;
    ConectionIO exib;
    ConectionIO sensor;

    public ThreadConections(ConectionIO adm, ConectionIO exib, ConectionIO sensor) {
        this.adm = adm;
        this.exib = exib;
        this.sensor = sensor;
    }
    
    public ThreadConections(ConectionIO adm, ConectionIO exib) {
        this.adm = adm;
        this.exib = exib;
    }
    
    public ThreadConections(ConectionIO adm){
        this.adm = adm;
    }
    
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                adm.tratar();
                exib.tratar();
                sensor.tratar();
            } catch (IOException | PilotoNaoExisteException | InterruptedException ex) {
                Logger.getLogger(ThreadConections.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
