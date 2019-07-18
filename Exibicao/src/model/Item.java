package model;

public class Item {
    private int item;
    private String piloto;
    private String tempo;
    private String tempovolta;
    private String voltarapida;
    private int voltas;

    public Item(int item, String piloto, String tempo, String tempovolta, String voltarapida, int voltas) {
        this.item = item;
        this.piloto = piloto;
        this.tempo = tempo;
        this.tempovolta = tempovolta;
        this.voltarapida = voltarapida;
        this.voltas = voltas;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getTempovolta() {
        return tempovolta;
    }

    public void setTempovolta(String tempovolta) {
        this.tempovolta = tempovolta;
    }

    public String getVoltarapida() {
        return voltarapida;
    }

    public void setVoltarapida(String voltarapida) {
        this.voltarapida = voltarapida;
    }

    public int getVoltas() {
        return voltas;
    }

    public void setVoltas(int voltas) {
        this.voltas = voltas;
    }
    
    
  
}
