/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import execoes.CorridaNaoIniciadaException;
import facade.ServidorFacade;
import execoes.PilotoNaoExisteException;
import execoes.TagInvalidaException;
import execoes.VoltaInvalidaException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Carro;
import model.Jogador;
import model.TagColetada;
import model.Time;
import org.json.JSONArray;
import org.json.JSONObject;

public class ControllerDeTratamento {

    private ServidorFacade facade;
    private ControladorDeMensagens mensagem;
    private boolean rodandoCorrida;
    private String curTag;

    public ControllerDeTratamento(ServidorFacade facade, ControladorDeMensagens mensagem) {
        this.mensagem = mensagem;
        this.facade = facade;
        rodandoCorrida = facade.statusCorrAtual();
    }

    public byte[] convertToByte(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    public String convertToString(byte[] dados) {
        return new String(dados, StandardCharsets.UTF_8);
    }

    public void respostaCliente(String id, JSONObject resposta) {
        String info = resposta.toString();
        byte[] bytes = convertToByte(info);
        System.out.println(bytes.length);
        mensagem.novaMensagem(id, bytes);
    }

    public void tratarMensagem(byte[] bytes) throws PilotoNaoExisteException, IOException, FileNotFoundException, ClassNotFoundException {

        String info = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(info);
        JSONObject dados = new JSONObject(info);
        switch (dados.getString("solicitante")) {
            case "ClienteADM":
                switch (dados.getString("command")) {
                    case "CadCarro":
                        if (facade.cadastrarCarro(curTag, dados.getString("cor"), dados.getString("equipe"))) {
                            //responde a solicitação do cliente!
                            dados.put("status", "Carro Cadastrado!");
                            respostaCliente(dados.getString("solicitante"), dados);
                        }
                        break;

                    case "CadPiloto":
                        if (facade.cadastrarPiloto(dados.getString("nomePiloto"), null)) {
                            //responde a solicitação do cliente!
                            dados.put("status", "Piloto Cadastrado!");
                            respostaCliente(dados.getString("solicitante"), dados);

                        }
                        break;

                    case "CadJogador":
                        if (facade.CadastrarJogador(dados.getInt("idCarro"), dados.getString("nome"))) {
                            //responde a solicitação do cliente!
                            dados.put("status", "Jogador Cadastrado!");
                            respostaCliente(dados.getString("solicitante"), dados);
                        }
                        break;

                    case "IterarCarros":
                        Iterator<Carro> carros = facade.getListaDeCarros().iterator();
                        JSONArray arrayCarros = new JSONArray();
                        while (carros.hasNext()) {
                            Carro carro = (Carro) carros.next();
                            arrayCarros.put(carro.toString());
                        }

                        JSONObject dadosCarros = new JSONObject();
                        dadosCarros.put("arrayDeCarros", arrayCarros);
                        respostaCliente(dados.getString("solicitante"), dadosCarros);
                        //responde a solicitação do cliente, enviando o json com o array de Carros
                        break;

                    case "IterarJogadores":
                        Iterator<Jogador> jogadores = facade.getListaDeJogadores().iterator();
                        JSONArray arrayJogadores = new JSONArray();
                        while (jogadores.hasNext()) {
                            Jogador jogador = (Jogador) jogadores.next();
                            arrayJogadores.put(jogador.toString());
                        }

                        JSONObject dadosJogadores = new JSONObject();
                        dadosJogadores.put("arrayDeJogadores", arrayJogadores);
                        respostaCliente(dados.getString("solicitante"), dadosJogadores);
                        break;

                    case "PreConfigCorrida":
                        JSONArray arrayIds = dados.getJSONArray("ids_jogadores");
                        int[] ids = new int[arrayIds.length()];

                        for (int i = 0; i < arrayIds.length(); i++) {
                            ids[i] = arrayIds.optInt(i);
                        }
                        if (facade.novaCorrida(ids, dados.getInt("num_voltas"))) {
                            dados.put("status", "Corrida Configurada!");
                            respostaCliente(dados.getString("solicitante"), dados);
                        }
                        break;

                    case "StatusCorrida":
                        if (rodandoCorrida == true) {
                            dados.put("status", "Rodando");
                            respostaCliente(dados.getString("solicitante"), dados);
                        } else {
                            dados.put("status", "Finalizada");
                            respostaCliente(dados.getString("solicitante"), dados);
                        }
                        break;

                    case "ComeçarCorrida":
                        if (facade.comecarCorrida()) {
                            rodandoCorrida = facade.statusCorrAtual();
                            System.out.println(rodandoCorrida);
                            dados.put("status", "Corrida Iniciada, Tudo pronto!!!");
                            respostaCliente(dados.getString("solicitante"), dados);
                        }
                        break;

                }
            case "ClienteExib":
                break;
            case "Sensor":
                if (rodandoCorrida) {
                    //jogar as tags na corrida
                    try {
                        facade.coletorDeTags(new TagColetada(dados.getString("tag"), converterTempo(dados.getString("tempo"))));
                        tabelaExibicao();
                    } catch (TagInvalidaException | CorridaNaoIniciadaException | VoltaInvalidaException | ParseException ex) {
                        Logger.getLogger(ControllerDeTratamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //pega a tag atual pra ser cadastrada
                    curTag = dados.getString("tag");
                }
                break;

            default:
                System.out.println("Argumento inválido");

        }
        facade.armazenarDados();

    }

    public Time converterTempo(String data) throws ParseException {

        String hora = data.substring(11, 13);
        String minuto = data.substring(14, 16);
        String segundo = data.substring(17, 19);
        String mil = data.substring(20, 23);

        return new Time(0, Integer.parseInt(minuto),
                Integer.parseInt(segundo), Integer.parseInt(mil));

    }

    public void tabelaExibicao() {
        ArrayList<Jogador> jogadores = facade.getListaDeJogadores();
        JSONArray arrayJogadores = new JSONArray();

        while (jogadores.iterator().hasNext()) {
            Jogador j = jogadores.iterator().next();
            arrayJogadores.put(j.toString());
        }
        JSONObject dados = new JSONObject();
        dados.put("arrayDeJogadores", arrayJogadores);
        respostaCliente("ClienteExib", dados);
    }
}
