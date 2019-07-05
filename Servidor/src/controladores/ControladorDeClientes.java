package controladores;

import comunicacao.Mensagem;
import comunicacao.Solicitante;
import comunicacao.ThreadConexao;
import java.util.ArrayList;

public class ControladorDeClientes {
   private ThreadConexao adm;
   private ThreadConexao sensor;
   private ThreadConexao exibicao;
   
   private Mensagem mensagemADM;
   private Mensagem mensagemSensor;
   private Mensagem mensagemExibixao;
   private ArrayList<Mensagem> mensagens;

    public ControladorDeClientes() {
        adm = new ThreadConexao();
        sensor = new ThreadConexao();
        exibicao = new ThreadConexao();
        mensagens = new ArrayList();
        
        iniciarObjMensagens();
    }
    
    public void iniciarObjMensagens(){
        mensagemADM = new Mensagem(Solicitante.ClienteADM);
        mensagemSensor = new Mensagem(Solicitante.Sensor);
        mensagemExibixao = new Mensagem(Solicitante.ClienteExib);
        
        mensagens.add(mensagemADM);
        mensagens.add(mensagemSensor);
        mensagens.add(mensagemExibixao);
    }

    public ThreadConexao getAdm() {
        return adm;
    }

    public ThreadConexao getSensor() {
        return sensor;
    }

    public ThreadConexao getExibicao() {
        return exibicao;
    }
   
    public void iniciarAdm(Solicitante id){
        adm.iniciarThread(Solicitante.ClienteADM);
    }
    
    public void iniciarSensor(Solicitante id){
       sensor.iniciarThread(Solicitante.Sensor);
   }
       
    public void iniciarExibicao(Solicitante id){
       exibicao.iniciarThread(Solicitante.ClienteExib);
   }

    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }
    
}
