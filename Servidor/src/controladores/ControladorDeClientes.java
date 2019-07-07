package controladores;

import comunicacao.Conexao;
import comunicacao.Mensagem;
import comunicacao.Solicitante;
import java.io.IOException;
import java.util.ArrayList;

public class ControladorDeClientes {
   private Conexao adm;
   private Conexao sensor;
   private Conexao exibicao;
   
   private Mensagem mensagemADM;
   private Mensagem mensagemSensor;
   private Mensagem mensagemExibixao;
   private ArrayList<Mensagem> mensagens;

    public ControladorDeClientes() {}
    
    public void iniciarObjMensagens(){
        mensagemADM = new Mensagem(Solicitante.ClienteADM);
        mensagemSensor = new Mensagem(Solicitante.Sensor);
        mensagemExibixao = new Mensagem(Solicitante.ClienteExib);
        
        mensagens.add(mensagemADM);
        mensagens.add(mensagemSensor);
        mensagens.add(mensagemExibixao);
    }
    
    public void iniciarClienteADM() throws IOException{                                       
        adm = new Conexao(Solicitante.ClienteADM);
        adm.rodar();
    }
    
    public void iniciarClienteASensor() throws IOException{
        sensor = new Conexao(Solicitante.Sensor);
        sensor.rodar();
    }
    
    public void iniciarClienteExibicao() throws IOException{
        exibicao = new Conexao(Solicitante.ClienteExib);
        exibicao.rodar();
    }    
}