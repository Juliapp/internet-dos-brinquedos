package controladores;

import java.util.List;
import model.Carro;
import model.Equipe;
import model.Jogador;
import model.Piloto;
import java.io.IOException;
import java.net.Socket;

public class ControladorFactory {

    public Carro factoryC(String tag, String cor, Equipe e){
        return new Carro(tag, cor, e);
    }    
    
    public Equipe factoryE(String nome){
        return new Equipe(nome);
    }
    
    public Piloto factoryP(String nome, String foto){
        return new Piloto(nome, foto);
    }
    
    public Jogador factoryPar(Carro c, Piloto p) {
        return new Jogador(c, p);
    }
    
    public Socket factorySocket() throws IOException{
        return new Socket("localhost", 5555);
    }
    
    
}
