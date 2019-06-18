package model;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cronometro extends Thread implements Serializable{
    private int horas;
    private int minutos;
    private int segundos;
    private boolean rodando;

    public Cronometro() {
        horas = 0;
        minutos = 0;
        segundos = 0;
        rodando = false;
    }
    
    
    @Override
    public void run(){
        while(rodando){
            try {
                Thread.sleep(1000);
                conta();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    
    public void comecar(){
        rodando = true;
        this.run();
    }
    
    public void parar(){
        rodando = false;
    }
    
    
    public void conta(){
        if(segundos != 59){ 
            segundos++; 
        }else if(minutos != 59){ 
            minutos++; 
            segundos = 0;
        }else{ 
            horas++;
            segundos = 0;
            minutos = 0;
        }
        
    }
    
    
}
