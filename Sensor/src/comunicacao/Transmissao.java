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
    private ObjectOutputStream os;
    private ObjectInputStream is;

    public Socket abreConexao() throws IOException {
        return new Socket("localhost", 5555);
    }

    public void enviaMensagem(Mensagem obj) throws IOException, ClassNotFoundException {
        
        Socket socket = abreConexao();
        
        Object objeto = (Object) obj;
        this.os = new ObjectOutputStream(socket.getOutputStream());

        os.writeObject(objeto);
        os.flush();

        this.is = new ObjectInputStream(socket.getInputStream());
        this.dado = is.readObject();
        
        os.reset();
        os.close();
        is.close();
        socket.close();

    }

    public void solicitaMensagem(Mensagem msg) throws IOException, ClassNotFoundException {
        Socket socket = abreConexao();
        
        Object objeto = (Object) msg;
        
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        

        os.writeObject(objeto);
        os.flush();
        
        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        this.dado = is.readObject();
        
        if(this.dado == null){
            System.out.println("Não chegou nada!");
        }
        os.reset();
        os.close();
        is.close();
        socket.close();

    }

    public Object dadoRecebido() {
        return this.dado;
    }

}
