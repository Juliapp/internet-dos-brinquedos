/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import comunicacao.Solicitante;
import execoes.CorridaNaoIniciadaException;
import facade.ServidorFacade;
import execoes.PilotoNaoExisteException;
import execoes.TagInvalidaException;
import execoes.VoltaInvalidaException;
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

    private final ServidorFacade facade;
    private final ControladorDeMensagens mensagem;
    private boolean rodandoCorrida;
    private String curTag;
    
    public ControllerDeTratamento(ServidorFacade facade, ControladorDeMensagens mensagem, boolean rodandoCorrida){
        this.rodandoCorrida = rodandoCorrida;
        this.mensagem = mensagem;
        this.facade = facade;
        
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

    public void tratarMensagem(byte[] bytes) throws PilotoNaoExisteException {

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
                            System.out.println("Repetindo...");
                            Carro carro = (Carro) carros.next();
                            arrayCarros.put(carro.toString());
                            System.out.println("Enviou!!!");
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
                        
                    case "EtapaClassificacao":
                        
                        
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
                        
                    case "ComeçarCorrida":
                        if(facade.comecarCorrida()){
                            dados.put("status", "Corrida Iniciada, Tudo pronto!!!");
                            respostaCliente(dados.getString("solicitante"), dados);
                        }
                        break;
                    
                }
            case "ClienteExib":
                break;
            case "Sensor":
                        
                        if(rodandoCorrida){
                            //jogar as tags na corrida
                            try {
                                facade.coletorDeTags(new TagColetada(dados.getString("tag"), converterTempo(dados.getString("tempo"))));
                                //Mandar mensagem pra o cliente de exibição
                                tabelaExibicao();
                            } catch (TagInvalidaException | CorridaNaoIniciadaException | VoltaInvalidaException | ParseException ex) {
                                Logger.getLogger(ControllerDeTratamento.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            //pega a tag atual pra ser cadastrada
                            curTag = dados.getString("tag");
                        }
                break;
                
            default: 
                    System.out.println("Argumento inválido");
                
        }

    }

    
    public Time converterTempo(String tempo) throws ParseException{

        SimpleDateFormat parseData = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSSSSS"); 
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(parseData.parse(tempo));
        
        return new Time(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                        c.get(Calendar.SECOND), c.get(Calendar.MILLISECOND));
    }
    
    public void tabelaExibicao(){
        ArrayList <Jogador> jogadores = facade.jogadoresDaCorridaAtual();
        JSONArray arrayJogadores = new JSONArray();
        while(jogadores.iterator().hasNext()){
            Jogador j = jogadores.iterator().next();
            arrayJogadores.put(j.toString());
        }
        JSONObject dados = new JSONObject();
        dados.put("arrayDeJogadores", arrayJogadores);
        respostaCliente("ClienteExib", dados);
    }
}
