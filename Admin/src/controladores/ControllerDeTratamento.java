/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import comunicacao.Mensagem;
import comunicacao.Solicitante;
import facade.ClienteFacade;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Teeu Guima
 */
public class ControllerDeTratamento{

    private ClienteFacade facade;
    private Mensagem mensagem;
    

    public ControllerDeTratamento(ClienteFacade facade) {
        this.facade = facade;
        mensagem = new Mensagem(Solicitante.ClienteADM);
    }

    public byte[] convertToByte(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    public String convertToString(byte[] dados) {
        return new String(dados, StandardCharsets.UTF_8);
    }
    
    public void respostaServidor(JSONObject json){
        mensagem.setHasMensagemToTrue();
        mensagem.setJson(json);
    }

    public void tratamentoMensagem(byte[] bytes) throws PilotoNaoExisteException {
         JSONObject dados_retorno = new JSONObject(convertToString(bytes));
         
         facade.setRespostaJSON(dados_retorno);
        /* 
         switch(dados_retorno.getString("command")){
             case "CadCarro":
                 return dados_retorno.getString("status");
             case "CadPiloto":
                 return dados_retorno.getString("status");
             case "CadJogadores":
                 return dados_retorno.getString("status");
             case "IterarCarros":
                 break;
             case "IterarJogadores":
                 break;
             case "PreConfigCorrida":
                 break;
         }
        return null;
        */
    }
}
