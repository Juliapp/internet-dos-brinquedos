package controladores;

import java.util.ArrayList;
import java.util.Iterator;
import model.Carro;
import model.Equipe;
import model.Jogador;
import model.Piloto;
import java.util.LinkedList;

public class ControladorDeDados {

    private LinkedList<Piloto> pilotos;
    private LinkedList<Equipe> equipes;
    private LinkedList<Carro> carros;
    private LinkedList<Jogador> participantes;
    //hash map de partidas

    public ControladorDeDados() {
        this.pilotos = new LinkedList<>();
        this.equipes = new LinkedList<>();
        this.carros = new LinkedList<>();
        this.participantes = new LinkedList<>();
    }

    public Piloto addPiloto(Piloto p) {
        if (this.pilotos.isEmpty()) {
            this.pilotos.add(p);
            return p;
        } else if (!hasPiloto(p)) {
            pilotos.add(p);
            return p;
        } else {
            return null;
        }
    }

    public boolean hasPiloto(Piloto p) {
        return pilotos.contains(p); //Exceção não contem piloto
    }

    public Iterator<Piloto> itPiloto() {
        return pilotos.iterator();
    }

    public Piloto getPiloto(String n) {
        Piloto p;
        while (itPiloto().hasNext()) {
            p =(Piloto) itPiloto().next();
            if (p.getNome().equals(n)) {
                return p;
            }
        }
        return null; //jogar exceção piloto não existe
    }

    public Equipe addEquipe(Equipe e) {
        if (this.equipes.isEmpty()) {
            this.equipes.add(e);
            return e;
        } else if (!hasEquipe(e)) {
            equipes.add(e);
            return e;
        } else {
            return null;
        }
    }

    public boolean hasEquipe(Equipe e) {
        return equipes.contains(e); //Exceção não contem piloto
    }

    public Iterator<Equipe> itEquipe() {
        return equipes.iterator();
    }

    public Equipe getEquipe(String n) {
        Equipe e;
        while (itEquipe().hasNext()) {
            e = (Equipe) itEquipe().next();
            if (e.getNome().equals(n)) {
                return e;
            }
        }
        return null; //jogar exceção piloto não existe
    }

    public Carro addCarros(Carro c) {
        if (this.carros.isEmpty()) {
            System.out.println("Entrou");
            this.carros.add(c);
            return c;
        } else if (!hasCarro(c)) {
            carros.add(c);
            return c;
        } else {
            return null;
        }

    }

    public boolean hasCarro(Carro c) {
        //Exceção não contem piloto
        return this.carros.contains(c);

    }

    public Iterator<Carro> itCarro() {
        return carros.iterator();
    }

    public Carro getCarro(String tag) {
        Carro c;
        while (itCarro().hasNext()) {
            c = (Carro) itCarro().next();
            if (c.getTag().equals(tag)) {
                return c;
            }
        }
        return null; //jogar exceção piloto não existe
    }

    public Carro getCarroPorCor(String cor) {
        Carro c;
        while (itCarro().hasNext()) {
            c = (Carro) itCarro().next();
            if (c.getCor().equals(cor)) {
                return c;
            }
        }
        return null;
    }

    public void addParticipantes(Carro c, Piloto p) {
        Jogador part = new Jogador(c, p);
        if (!hasParticipantes(part)) {
            this.participantes.add(part);
        }
    }

    public boolean hasParticipantes(Jogador part) {
        return this.participantes.contains(part);
    }

    public Iterator itParticipantes() {
        return this.participantes.iterator();
    }

    public Jogador getParticipantes(String nome) {
        while (itPiloto().hasNext()) {
            Piloto p = (Piloto) itPiloto().next();
            if (p.getNome().equals(nome)) {
                while (itParticipantes().hasNext()) {
                    Jogador part = (Jogador) itParticipantes().next();
                    if (part.getPiloto().equals(p)) {
                        return part;
                    }

                }
                return null;
            }

        }
        return null;
    }

    public LinkedList<Jogador> getParticipantes() {
        return participantes;
    }

    public LinkedList<Piloto> getPilotos() {
        return pilotos;
    }

    public LinkedList<Equipe> getEquipes() {
        return equipes;
    }

    public LinkedList<Carro> getCarros() {
        return carros;
    }

}
