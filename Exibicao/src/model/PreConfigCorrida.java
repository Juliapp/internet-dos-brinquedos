package model;

import java.io.Serializable;

public class PreConfigCorrida implements Serializable {
    private static final long serialVersionUID = 8L;
    public int quantidadeVoltas;
    private int[] IdDosJogadores;
    
    public PreConfigCorrida(int voltas, int[] jogadores){
        this.quantidadeVoltas = voltas;
        this.IdDosJogadores = jogadores;
    }

    public int getQuantidadeVoltas() {
        return quantidadeVoltas;
    }

    public void setQuantidadeVoltas(int quantidadeVoltas) {
        this.quantidadeVoltas = quantidadeVoltas;
    }

    public int[] getIdDosJogadores() {
        return IdDosJogadores;
    }

}
