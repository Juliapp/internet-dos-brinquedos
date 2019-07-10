/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import comunicacao.Mensagem;
import facade.ServidorFacade;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import execoes.PilotoNaoExisteException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import model.Carro;
import model.Jogador;
import model.PreConfigCorrida;
import model.TagColetada;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Teeu Guima
 */
public class ControllerDeTratamento {

    private ServidorFacade facade;
    
    public ControllerDeTratamento(ServidorFacade facade){
        this.facade = facade;
    }

    public byte[] convertToByte(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    public String convertToString(byte[] dados) {
        return new String(dados, StandardCharsets.UTF_8);
    }

    public void respostaCliente(String id, String resposta) {
        byte[] bytes = convertToByte(resposta);
        facade.novaMensagem(id, bytes);
    }

    public void tratamentoMensagem(byte[] bytes) throws PilotoNaoExisteException {

        String info = new String(bytes, StandardCharsets.UTF_8);
        JSONObject dados = new JSONObject(info);
        switch (dados.getString("solicitante")) {
            case "ClienteADM":
                switch (dados.getString("command")) {
                    case "CadCarro":
                        if (facade.cadastrarCarro(dados.getString("tag"), dados.getString("cor"), dados.getString("equipe"))) {
                            //responde a solicitação do cliente!
                            respostaCliente(dados.getString("solicitante"), "Carro Cadastrado!");
                            

                        }
                        break;
                    case "CadPiloto":
                        if (facade.cadastrarPiloto(dados.getString("nomePiloto"), null)) {
                            //responde a solicitação do cliente!
                            respostaCliente(dados.getString("solicitante"), "Piloto Cadastrado!");

                        }
                        break;
                    case "CadJogador":
                        if (facade.CadastrarJogador(dados.getInt("idCarro"), dados.getString("nomePiloto"))) {
                            //responde a solicitação do cliente!
                            respostaCliente(dados.getString("solicitante"), "Jogador Cadastrado!");
                        }
                        break;
                    case "IterarCarros":
                        Iterator<Carro> carros = facade.getListaDeCarros().iterator();
                        JSONArray arrayCarros = new JSONArray();
                        while (carros.hasNext()) {
                            Carro carro = (Carro) carros.next();
                            arrayCarros.put(carro);
                        }

                        JSONObject dadosCarros = new JSONObject();
                        dadosCarros.put("arrayDeCarros", arrayCarros);
                        //responde a solicitação do cliente, enviando o json com o array de Carros

                        break;
                    case "IterarJogadores":
                        Iterator<Jogador> jogadores = facade.getListaDeJogadores().iterator();
                        JSONArray arrayJogadores = new JSONArray();
                        while (jogadores.hasNext()) {
                            Jogador jogador = (Jogador) jogadores.next();
                            arrayJogadores.put(jogador);
                        }

                        JSONObject dadosJogadores = new JSONObject();
                        dadosJogadores.put("arrayDeCarros", arrayJogadores);

                        break;
                    case "PreConfigCorrida":
                        JSONArray arrayIds = dados.getJSONArray("ids_jogadores"); 
                        int[] ids = new int[arrayIds.length()];
                        
                        for(int i = 0; i < arrayIds.length(); i++){
                            ids[i] = arrayIds.optInt(i);
                        }
                        if(facade.novaCorrida(ids, dados.getInt("num_voltas"))){
                            
                        }
                        break;
                    case "ComecarCorrida":
                        if(facade.comecarCorrida()){
                            respostaCliente(dados.getString("solicitante"), "Corrida Iniciada, Tudo pronto!!!");
                        }
                        break;
                    
                }
            case "ClienteExib":
                break;
            case "Sensor":
                break;
        }

    }

    /*
    @Override
    public void run() {

        try {

            // Ask user what he wants 
            Mensagem msg = (Mensagem) is.readObject();

            switch (msg.getSolicitante()) {
                case ClienteCad:
                    switch (msg.getCommand()) {
                        case CadCarro:
                            String[] dadosCarro = (String[]) msg.getObject();

                            //nessa parte ele tem que pegar do coletor
                            if (servidorFacade.cadastrarCarro(dadosCarro[0], dadosCarro[1], dadosCarro[2])) {
                                this.os.writeUTF("Carro Cadastrado");
                                this.os.flush();
                            }
                            break;

                        case IterarCarros:
                            Object arrayCarros = (Object) servidorFacade.getListaDeCarros();
                            if (arrayCarros != null) {
                                this.os.writeObject(arrayCarros);
                                this.os.flush();
                            }
                            break;
                        case CadPiloto:
                            String dadosPiloto = (String) msg.getObject();
                            if (servidorFacade.cadastrarPiloto(dadosPiloto, null)) {
                                this.os.writeUTF("Piloto Cadastrado");
                                this.os.flush();
                            }
                            break;
                        case CadJogador:
                            String[] dadosJogador = (String[]) msg.getObject();
                            int id = Integer.parseInt(dadosJogador[0]);
                            if (servidorFacade.CadastrarJogador(id, dadosJogador[1])) {
                                this.os.writeUTF("Jogador Cadastrado");
                                this.os.flush();
                            }
                            break;
                        case ComecarCorrida:
                            if (servidorFacade.comecarCorrida()) {
                                this.os.writeUTF("Tudo pronto...Foi dada a largada...Que vença o melhor!!!");
                                this.os.flush();
                            }

                            break;
                        case IterarJogadores:
                            Object arrayJogadores = (Object) servidorFacade.getListaDeJogadores();
                            if (arrayJogadores != null) {
                                this.os.writeObject(arrayJogadores);
                                this.os.flush();
                            } else {
                                String erroinfo = "Erro no array";
                                this.os.writeObject(erroinfo);
                                this.os.flush();
                            }
                            break;
                        case PreConfiguracaoCorrida:
                            if (msg.getObject() instanceof PreConfigCorrida) {
                                PreConfigCorrida preCor = (PreConfigCorrida) msg.getObject();
                                servidorFacade.novaCorrida(preCor.getIdDosJogadores(), preCor.getQuantidadeVoltas());
                                this.os.writeUTF("Partida configurada!");
                                this.os.flush();
                            }
                            break;
                    }

                    break;
                case ClienteExib:
                    break;
                case Sensor:
                    switch (msg.getCommand()) {
                        case StatusCorrida:
                            this.os.writeObject((Object) servidorFacade.statusCorrida());
                            this.os.flush();
                            //servidorFacade.coletorDeTags(tag, tempoColetado);
                            break;

                        case EnviarTags:
                            boolean statusEnvio = true;
                            TagColetada tag = (TagColetada) msg.getObject(); //Tag coletada com o tempo inserido!
                            this.os.writeObject((Object) statusEnvio);
                            this.os.flush();

                            break;
                    }

                    break;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (PilotoNaoExisteException ex) {
            Logger.getLogger(ControllerDeTratamento.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            this.is.close();
            this.os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
     */
}
