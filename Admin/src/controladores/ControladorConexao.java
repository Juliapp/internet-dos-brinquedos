/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import comunicacao.Mensagem;
import facade.ClienteFacade;
import teste.Solicitante;
import java.io.IOException;
import teste.Conexao;

/**
 *
 * @author Teeu Guima
 */
public class ControladorConexao {

    private Conexao servidor;
    private ClienteFacade facade;

    private Mensagem mensagemServidor;

    public ControladorConexao(ClienteFacade facade) {
        this.mensagemServidor = new Mensagem();
        this.facade = facade;
    }

    public void iniciarClienteADM() throws IOException {
        servidor = new Conexao(facade);
        servidor.rodar();
    }

    public void novaMensagem(byte[] bytes) {

        mensagemServidor.setBytes(bytes);
        mensagemServidor.setHasMensagemToTrue();

    }

    public Mensagem getMensagem() {
        return mensagemServidor;
    }
}
