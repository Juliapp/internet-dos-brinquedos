package comunicacao;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Time;

public class Cronometro extends Thread{
    private Time cronometro;
    private boolean rodando;

    /**
     *Classe com as funcionalidades de um cronometro 
     */
    public Cronometro() {
        cronometro = new Time();
        rodando = false;
    }

    /**
     * Pega a referência de um cronometro 
     * @return Referência do cronometro 
     */
    public Time getCronometro() {
        return cronometro;
    }

    /**
     * 
     * @return Verdadeiro se o cronometro estiver em funcionameto
     */
    public boolean isRodando() {
        return rodando;
    }
    
    
    @Override
    public void run(){
        while(rodando){
            try {
                Thread.sleep(1);
                conta();
                System.out.println(cronometro.getMinutos() + " min " + cronometro.getSegundos() + " seg " + cronometro.getMilisegundos());
            } catch (InterruptedException ex) {
                Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /**
     *
     */
    public void comecar(){
        rodando = true;
        this.run();
    }
    
    /**
     *
     */
    public void parar(){
        rodando = false;
    }
    
    /**
     * Pega um Objeto Time com tempo que foi requerido 
     * @return
     */
    public Time getCurrentTime(){
        return cronometro.getCurrentTime();
    }
    
    /**
     * Incremento do cronometro em precisão de milisegundos
     */
    public void conta(){
        if(cronometro.getMilisegundos() < 999){
            cronometro.incrementaMilisegundos();
        }else if(cronometro.getSegundos() < 59){ 
            cronometro.incrementaSegundos();
            cronometro.setMilisegundos(0);
        }else if(cronometro.getMinutos() < 59){ 
            cronometro.incrementaMinutos();
            cronometro.setSegundos(0);
            cronometro.setMilisegundos(0);
        }else{ 
            cronometro.incrementaHoras();
            cronometro.setMinutos(0);
            cronometro.setSegundos(0);
            cronometro.setMilisegundos(0);
        }
        
    }
    
    
}
