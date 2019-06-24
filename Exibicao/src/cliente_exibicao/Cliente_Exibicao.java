/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_exibicao;

import comunicacao.Command;
import comunicacao.Mensagem;
import comunicacao.Solicitante;
import comunicacao.Transmissao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Jogador;

/**
 *
 * @author Teeu Guima
 */
public class Cliente_Exibicao extends Application {

    private Transmissao transm;

    public Cliente_Exibicao() {
        transm = new Transmissao();
    }

    public void SolicitarDadosDaCorrida() throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            Thread.sleep(4000);
            Mensagem msg = new Mensagem(Command.ListaDeJogadores, null, Solicitante.ClienteExib);
            transm.solicitaMensagem(msg);
        }

    }

    public boolean verificaStatusDaCorrida() throws IOException, ClassNotFoundException {
        transm.enviaMensagem(new Mensagem(Command.StatusCorrida, null, Solicitante.ClienteExib));
        if ((boolean) transm.getDadoRecebido() == false) {
            System.out.println("Deu Erro!");
        } else {
            System.out.println("Passou Carai!!!");
        }
        return (boolean) transm.getDadoRecebido();
    }

    public boolean observador() throws ClassNotFoundException, IOException, InterruptedException {
        boolean status;

        do {
            Thread.sleep(2000);
            status = verificaStatusDaCorrida();
        } while (status == false);
        return status;

    }

    public void ExibicaoCorrida() {
        ArrayList<Object> info = (ArrayList<Object>) transm.getDadoRecebido();

        ArrayList<Jogador> jogadores = (ArrayList<Jogador>) info.get(0);
        Iterator infoJogadores = jogadores.iterator();
        int voltas = (int) info.get(1);

        System.out.println("Sessão de Corrida:");
        System.out.println(voltas);
        System.out.println("Piloto" + "Time" + "Tempo de Corrida" + "Tempo de Volta" + "Volta Mais Rápida" + "Voltas" + "Pits");

        while (infoJogadores.hasNext()) {
            Jogador jogador = (Jogador) infoJogadores.next();
            System.out.println(jogador.getPiloto().getNome() + jogador.getPiloto().getEquipe() + jogador.getTempoDeCorrida()
                    + jogador.getUltimaVoltaComputada() + jogador.getVoltaMaisRapida() + jogador.getVoltas()
                    + jogador.getPitStops());
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //launch(args);
        Cliente_Exibicao exib = new Cliente_Exibicao();
        try {
            if (exib.verificaStatusDaCorrida() == true) {

            }
        } catch (IOException | ClassNotFoundException e) {
        }

    }

}
