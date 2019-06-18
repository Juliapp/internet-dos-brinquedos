package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 3L;
    private Carro carro;
    private Piloto piloto;
    private Time voltaMaisRapida;
    private Time ultimaVoltaComputada;
    private Time tempoDeCorridaFinal;
    private int voltas;
    private int pitStops;
    private int colocacao;
    private static int numero =0;
    private int id;

    public Jogador(Carro carro, Piloto piloto) {
        voltaMaisRapida = new Time(0,0,0,0);
        this.carro = carro;
        this.piloto = piloto;
        pitStops = 0;
        this.id = numero++;
    }

    public Time getTempoDeCorridaFinal() {
        return tempoDeCorridaFinal;
    }

    public int getID() {
        return id;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Time getVoltaMaisRapida() {
        return voltaMaisRapida;
    }

    public void setVoltaMaisRapida(Time voltaMaisRapida) {
        this.voltaMaisRapida = voltaMaisRapida;
    }

    public Time getUltimaVoltaComputada() {
        return ultimaVoltaComputada;
    }

    public void setUltimaVoltaComputada(Time ultimaVoltaComputada) {
        this.ultimaVoltaComputada = ultimaVoltaComputada;
    }

    public Time getTempoDeCorrida() {
        return tempoDeCorridaFinal;
    }

    public void setTempoDeCorrida(Time tempoDeCorrida) {
        this.tempoDeCorridaFinal = tempoDeCorrida;
    }


    public int getVolta() {
        return voltas;
    }

    public int getPitStops() {
        return pitStops;
    }

    public void setPitStops(int pitStops) {
        this.pitStops = pitStops;
    }

    public int getColocacao() {
        return colocacao;
    }

    public void setColocacao(int colocacao) {
        this.colocacao = colocacao;
    }
    
    
    public void pitStop(){
        pitStops++;
    }
    
    public void completouVolta(){
        voltas++;
    }

    public int getVoltas() {
        return voltas;
    }

    public void setVoltas(int voltas) {
        this.voltas = voltas;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        
        if(obj instanceof Jogador){
           Jogador aux  = (Jogador) obj;
           return this.getCarro().getTag().equals(aux.getCarro().getTag()) && this.getPiloto().getNome().equals(aux.getPiloto().getNome()); 
        }
        return false;
        
    }

    @Override
    public String toString() {
        return "Jogador{ID = "+ id + " " + piloto + " " + carro + '}';
    }
    
    
}
