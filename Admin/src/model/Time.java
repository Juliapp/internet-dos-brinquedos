package model;

import java.io.Serializable;

public class Time implements Serializable{
    private static final long serialVersionUID = 6L;
    private double horas;
    private double minutos;
    private double segundos;
    private double milisegundos;

    /**
     *
     */
    public Time() {
        horas = 0;
        minutos = 0;
        segundos = 0;
        milisegundos = 0;
    }

    /**
     *
     * @param horas
     * @param minutos
     * @param segundos
     * @param milesimos
     */
    public Time(double horas, double minutos, double segundos, double milesimos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
        this.milisegundos = milesimos;
    }

    /**
     *
     * @return
     */
    public double getHoras() {
        return horas;
    }

    /**
     *
     * @param horas
     */
    public void setHoras(double horas) {
        this.horas = horas;
    }

    /**
     *
     * @return
     */
    public double getMinutos() {
        return minutos;
    }

    /**
     *
     * @param minutos
     */
    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    /**
     *
     * @return
     */
    public double getSegundos() {
        return segundos;
    }

    /**
     *
     * @param segundos
     */
    public void setSegundos(double segundos) {
        this.segundos = segundos;
    }

    /**
     *
     * @return
     */
    public double getMilisegundos() {
        return milisegundos;
    }

    /**
     *
     * @param milisegundos
     */
    public void setMilisegundos(double milisegundos) {
        this.milisegundos = milisegundos;
    }

    /**
     *
     */
    public void incrementaHoras() {
        horas++;
    }
        
    /**
     *
     */
    public void incrementaMinutos() {
        minutos++;
    }

    /**
     *
     */
    public void incrementaSegundos() {
        segundos++;
    }    
    
    /**
     *
     */
    public void incrementaMilisegundos(){
        milisegundos++;
    }
    
    /**
     *
     * @return
     */
    public Time getCurrentTime() {
        return new Time(this.getHoras(), this.getMinutos(), this.getSegundos(), this.getMilisegundos());
    }    
    
    /**
     *
     * @return
     */
    public double transformarEmMilisegundos(){
        return (3600000 * this.horas) + (60000 * this.minutos) + (1000 * this.segundos) + (this.milisegundos);
    }

    /**
     *Mede a variação do tempo de acordo a dois tempos passados por parâmetro
     * @param time1 tempo 1
     * @param time2 tempo 2
     * @return novo tempo com a variação
     */    
    public Time deltaTime(Time time1, Time time2){
        Time newTime = new Time();
        if(time1.compareTo(time2) > 0){
            divide(newTime, time1, time2);
        } else if(time1.compareTo(time2) < 0){
            divide(newTime, time2, time1);
        }
        
         return newTime;
    }
    
    private void divide(Time novo, Time maior, Time menor){
        
        double auxMaior = maior.transformarEmMilisegundos();
        double auxMenor = menor.transformarEmMilisegundos();        
        double delta = auxMaior - auxMenor;
        
        novo.setHoras(delta/3600000);
        novo.setMinutos((delta%3600000) / 60000);
        novo.setSegundos((delta%3600000) - (delta%60000) / 1000);
        novo.setMilisegundos((delta%3600000) - (delta%60000) - (delta%1000));
    }    
    
    /**
     *
     * @param time
     * @return
     */
    public int compareTo(Time time) {
        double contAtual = this.transformarEmMilisegundos();
        double contOutro = time.transformarEmMilisegundos();
        
        if(contAtual ==  contOutro){
            return 0;
        }else if(contAtual >  contOutro){
            return 1;
        }else{
            return -1;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%2.0f", horas) + ":" + String.format("%2.0f", minutos) + ":" + String.format("%2.0f", segundos) + ":"+ String.format("%3.0f", milisegundos);
    }
    
}
