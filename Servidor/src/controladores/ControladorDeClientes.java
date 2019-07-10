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
   
    private ControllerDeTratamento tratamento;
    private ControladorDeMensagens mensagens;

    public ControladorDeClientes(ControllerDeTratamento tratamento, ControladorDeMensagens mensagens) {
        this.tratamento = tratamento;
        this.mensagens = mensagens;
    }
    
    
    public void iniciarClienteADM() throws IOException{                                       
        adm = new Conexao(Solicitante.ClienteADM, tratamento, mensagens);
        adm.rodar();
    }
    
    public void iniciarClienteASensor() throws IOException{
        sensor = new Conexao(Solicitante.Sensor, tratamento, mensagens);
        sensor.rodar();
    }
    
    public void iniciarClienteExibicao() throws IOException{
        exibicao = new Conexao(Solicitante.ClienteExib, tratamento, mensagens);
        exibicao.rodar();
    }    
    
}