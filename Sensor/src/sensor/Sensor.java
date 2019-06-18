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
public class Sensor {

    private final int tempo = 10000;
    private String[] tags = {"EPC00000", "EPC00001", "EPC00002", "EPC00003"};
    private int count = 0;
    private Transmissao transm;
    private Cronometro cronometro;
    private Object dado;
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    public Sensor() {
        this.transm = new Transmissao();
        this.cronometro = new Cronometro();
    }

    public void abreConexao() throws IOException, ClassNotFoundException {
        this.socket = new Socket("127.0.0.1", 5555);
        this.is = new ObjectInputStream(socket.getInputStream());
        this.dado = is.readObject();
    }

    public void enviaMensagem(Mensagem obj) throws IOException, ClassNotFoundException {
        socket = new Socket("127.0.0.1", 5555);
        Object objeto = (Object) obj;

        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

        os.writeObject(objeto);
        os.flush();

        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        this.dado = is.readObject();

        os.reset();
        os.close();
        is.close();
        socket.close();

    }

    /*
    public void cortaConexão() throws IOException {
        if (this.dado.equals(false)) {
            this.os.close();
            this.is.close();
            this.socket.close();
        }

    }
    */
    public void lançaTags() throws IOException, ClassNotFoundException {
        Random num = new Random();
        String[] ordem = new String[3]; //Cria um array para colocar uma ordem nas tags
        for (int i = 0; i <= tags.length; i++) {
            ordem[i] = tags[num.nextInt(3)]; //Adiciona a tag pré-cadastrada com uma posição aleatória!
            TagColetada tag = new TagColetada(ordem[i], cronometro.getCurrentTime()); //Cria o objeto TagColetada pra obter o tempo exato do cronometro
            Mensagem msg = new Mensagem(Command.EnviarTags, tag, Solicitante.Sensor); //Cria o objeto Mensagem pra enviar pro servidor as informações coletadas do sensor!
            enviaMensagem(msg);
        }
        if ((boolean) dado == true) {
            System.out.println("Tag Coletada pelo servidor!");
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Sensor sensor = new Sensor();
        try {
            //O laço termina se a permissão for true, ou seja ter dado a largada na corrida
            sensor.abreConexao();

            while (true) {
                boolean status = (boolean) sensor.dado;
                while (status == true) {
                    Thread.sleep(40000);
                    System.out.println("Enviando");
                    sensor.lançaTags();
                }
            }
            /*
            while(sensor.observador() == true){
                sensor.cronometro.comecar(); //Começa a contagem!
                while(true){
                    Thread.sleep(40000); //Espera 10 segs pra enviar as tags
                    sensor.lançaTags(); //Chama o método que enviar para o servidor as tags!
                    System.out.println("Tags enviadas.......");
                }
            }
             */

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
