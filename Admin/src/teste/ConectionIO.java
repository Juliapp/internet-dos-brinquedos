package teste;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConectionIO implements Runnable {

    private Socket socket;

    public ConectionIO(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        System.out.println("O cliente está rodando na porta: " + socket.getLocalPort());
        while (true) {
            try {

                OutputStream output = socket.getOutputStream();
                InputStream input = socket.getInputStream();
                tratarOutput(output);
                tratarInput(input);

            } catch (IOException e) {
            }
        }
    }

    private void tratarOutput(OutputStream output) throws IOException {

        /*
        if(facade.getMensagem(id).hasMensagem()){
            Mensagem mensagem = facade.getMensagem(id);
            byte[] bytes = mensagem.getBytes();
            output.write(bytes, 0, bytes.length);
            facade.getMensagem(id).enviouMensagem();
        }    
         */
    }

    private void tratarInput(InputStream input) throws IOException {
        byte[] bytes = toByteArray(input);
        if (bytes.length > 0) {
            // facade.tratarMensagem(bytes);
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
