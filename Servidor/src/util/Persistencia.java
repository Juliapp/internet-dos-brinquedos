package util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Carro;
import model.Jogador;

public class Persistencia {
    File saves;
    File carro;
    File equipe;
    File pilotos;
    File jogadores;
    File corrida;
    
    public Persistencia(){
        try {
            initialize();
        } catch (IOException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void initialize() throws IOException{
        File saves = new File(".//src//saves");
        carro = new File(".//src//saves//carros.json");
        equipe = new File(".//src//saves//equipes.json");
        pilotos = new File(".//src/saves//pilotos.json");
        jogadores = new File(".//src//saves//pilotos.json");
        corrida = new File(".//src//saves//corridas.json");
        
        if(!saves.exists()){
            saves.mkdir();
        }
        
        if(!carro.exists()){
            carro.createNewFile();
        }
        if(!equipe.exists()){
            equipe.createNewFile();
        }
        if(!pilotos.exists()){
            pilotos.createNewFile();
        }
        if(!jogadores.exists()){
            jogadores.createNewFile();
        }        
        if(!corrida.exists()){
            corrida.createNewFile();
        }        
    }
    
    
    public void saveCarro(Carro c){

    }
    
    public void saveEquipe(Carro c){
        
    }

    public void savePiloto(Carro c){
        
    }    
    
    public void saveJogador(Jogador j){
        
    }
    
    public void saveCorrida(){
        
    }
    
    public void getSavedCarros(){
        
    }
    
    public void getSavedEquipes(){
        
    }

    public void getSavedPilotos(){
        
    }    
    
    public void getSavedJogadores(){
        
    }
    
    public void getSavedCorridas(){
        
    }    
    

    public BufferedReader fileToBufferedReader(File file){
        try {
            FileReader reader = new FileReader(carro);
            return new BufferedReader(reader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public BufferedWriter fileToBufferedWriter(File file){
        try {
            FileWriter reader = new FileWriter(carro);
            return new BufferedWriter(reader);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
}
