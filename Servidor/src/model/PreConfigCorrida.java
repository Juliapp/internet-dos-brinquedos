package model;


public class PreConfigCorrida  {
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
