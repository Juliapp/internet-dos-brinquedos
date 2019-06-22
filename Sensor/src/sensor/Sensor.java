/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor;

import comunicacao.Command;
import comunicacao.Cronometro;
import comunicacao.Mensagem;
import comunicacao.Solicitante;
import comunicacao.Transmissao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import model.TagColetada;

/**
 *
 * @author Teeu Guima
 */
public class Sensor extends Thread {

    private final int tempo = 10000;
    private String[] tags = {"EPC00000", "EPC00001", "EPC00002", "EPC00003"};
    private int count = 0;
    private Transmissao transm;
    private Cronometro cronometro;

    public Sensor() {
        this.transm = new Transmissao();
    }

    public boolean verificaPermissao() throws IOException, ClassNotFoundException {
        transm.enviaMensagem(new Mensagem(Command.StatusCorrida, null, Solicitante.Sensor));
        if ((boolean) transm.dadoRecebido() == false) {
            System.out.println("Deu Erro!");
        } else {
            System.out.println("Passou Carai!!!");
        }
        return (boolean) transm.dadoRecebido();
    }

    public void lançaTags() throws IOException, ClassNotFoundException {
        Random num = new Random();
        String[] ordem = new String[tags.length]; //Cria um array para colocar uma ordem nas tags
        for (int i = 0; i < tags.length; i++) {
            int v = num.nextInt(3);
            ordem[i] = tags[v]; //Adiciona a tag pré-cadastrada com uma posição aleatória!
            TagColetada tag = new TagColetada(ordem[i], cronometro.getCurrentTime()); //Cria o objeto TagColetada pra obter o tempo exato do cronometro
            Mensagem msg = new Mensagem(Command.EnviarTags, tag, Solicitante.Sensor); //Cria o objeto Mensagem pra enviar pro servidor as informações coletadas do sensor!
            transm.enviaMensagem(msg);
        }
        if ((boolean) transm.dadoRecebido() == true) {
            System.out.println("Tag Coletada pelo servidor!");
        }
    }

    public Cronometro getCronometro() {
        return this.cronometro;
    }

    public void setCronometro(Cronometro cron) {
        this.cronometro = cron;
    }

    public boolean observador() throws ClassNotFoundException, IOException, InterruptedException {
        boolean status;

        do {
            Thread.sleep(5000);
            status = verificaPermissao();
            System.out.println("Enviando Tags.......");

        } while (status == false);
        return status;

    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(10000); //Espera 10 segs pra enviar as tags
                lançaTags(); //Chama o método que enviar para o servidor as tags!
                System.out.println("Tags enviadas.......");
            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Sensor sensor = new Sensor();
        Thread p = sensor;

        try {
            //O laço entra se a permissão dada for true, ou seja ter dado a largada na corrida
            Cronometro cron = new Cronometro();

            if(sensor.observador() == true) {
                cron.comecar();
                sensor.setCronometro(cron);
                new Thread(cron).start();
                p.start();

            }

        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
