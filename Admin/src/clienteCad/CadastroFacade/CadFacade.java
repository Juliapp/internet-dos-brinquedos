package clienteCad.CadastroFacade;

import java.util.ArrayList;
import controladores.ControladorFactory;
import controladores.ControladorCorrida;
import controladores.ControladorDeDados;
import model.Carro;
import model.Equipe;
import model.Piloto;
import java.util.LinkedList;

public class CadFacade {

    ControladorCorrida controladorCorrida;
    ControladorDeDados controladorDados;
    ControladorFactory cf;
    
    //A gente pode instanciar a comunicação com o servidor e controlar por aqui
    
    public CadFacade() {
        this.controladorCorrida = new ControladorCorrida();
        this.controladorDados = new ControladorDeDados();
        this.cf = new ControladorFactory();
    }

    // Primeira parte do Facade dedicada as funções de cadastro no jogo 
    public Equipe cadastrarEquipe(String nome) {
        Equipe e = cf.factoryE(nome);
        controladorDados.addEquipe(e);
        return e;
    }

    public Equipe getEquipe(String nome) {
        return controladorDados.getEquipe(nome);
    }

    public Carro cadastrarCarro(String tag, String cor, Equipe e) {
        Carro c = cf.factoryC(tag, cor, e);
        controladorDados.addCarros(c);
        e.addCarro(c);
        return c;
    }

     public ControladorDeDados getController(){
        return this.controladorDados;
    }
    
    public Carro getCarro(String tag) {
        return controladorDados.getCarro(tag);
    }
  

    public Piloto cadastrarPiloto(String nome, String foto) {
        Piloto p = cf.factoryP(nome, foto);
        controladorDados.addPiloto(p);
        return p;
    }

    public Equipe getPiloto(String nome) {
        return controladorDados.getEquipe(nome);
    }

    ////Pegando as listas
    public LinkedList<Piloto> getPilotos() {
        return controladorDados.getPilotos();
    }

    public LinkedList<Equipe> getEquipes() {
        return controladorDados.getEquipes();
    }

    public LinkedList<Carro> getCarros() {
        return controladorDados.getCarros();
    }

    //ainda falta decidir como vai icar as partidas
    //Segunda parte do Facade dedicado ao recebimento de dados do sensor
    //Terceira parte do Facade dedicado a partida
}
