package teste;

import comunicacao.Mensagem;
import execoes.PilotoNaoExisteException;
import facade.ClienteFacade;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConectionIO implements Runnable {

    private Socket socket;
    private ClienteFacade facade;
    private Mensagem mensagem;

    public ConectionIO(Socket socket, ClienteFacade facade) {
        this.socket = socket;
        this.facade = facade;

    }

    @Override
    public void run() {
        System.out.println("O cliente está rodando na porta: " + socket.getLocalPort());
        while (true) {
            System.out.println("Entrei no while");
            try {

                OutputStream output = socket.getOutputStream();
                InputStream input = socket.getInputStream();
                tratarOutput(output);
                tratarInput(input);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (PilotoNaoExisteException ex) {
                Logger.getLogger(ConectionIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void tratarOutput(OutputStream output) throws IOException {

        
        if(facade.getMensagem().hasMensagem()){
            Mensagem mensagem = facade.getMensagem();
            byte[] bytes = mensagem.getBytes();
            output.write(bytes, 0, bytes.length);
            facade.getMensagem().enviouMensagem();
        }    
         
    }

    private void tratarInput(InputStream input) throws IOException, PilotoNaoExisteException {
        byte[] bytes = toByteArray(input);
        if (bytes.length > 0) {
            facade.trataMensagem(bytes);
        }
    }

    private byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buf = new byte[4096];

        while (true) {
            int n = input.read(buf);
            if (n < 0) {
                break;
            }
            baos.write(buf, 0, n);
        }
        return baos.toByteArray();
    }

    public void fecharSocket(Socket socket) throws IOException {
        socket.close();
    }

}
