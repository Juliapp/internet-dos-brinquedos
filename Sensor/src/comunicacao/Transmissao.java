/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Teeu Guima
 */
public class Transmissao {

    private Object dado;
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    /*public Socket solicitaSocket() throws IOException {
        return new Socket("localhost", 5555);
    }
     */
    public void enviaMensagem(Mensagem obj) throws IOException, ClassNotFoundException {
        socket = new Socket("127.0.0.1", 5555);
        Object objeto = (Object) obj;

        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

        os.writeObject(objeto);
        os.flush();

        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        this.dado = is.readObject();

        os.reset();
        os.close();
        is.close();
        socket.close();

    }

    public void esperaMensagem() throws IOException, ClassNotFoundException {
        this.socket = new Socket("127.0.0.1", 5555);
        this.is = new ObjectInputStream(socket.getInputStream());
        
        
        

    }

    public void cortaConexão() throws IOException {
        if (this.dado.equals(false)) {
            this.os.close();
            this.is.close();
            this.socket.close();
        }

    }

    public boolean dadoRecebido() {
        return (boolean) this.dado;
    }

}
