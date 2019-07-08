package teste;

import teste.Conexao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    
    public Servidor(){
        
    }

    public static void main(String[] args) {
        try {
            Servidor server = new Servidor();
            server.conectarClientes();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void conectarClientes() throws IOException{
        System.out.println("----- Internet dos brinquedos -----");
        System.out.println("Vamos fazer a conexão com os clientes");
        Conexao c = new Conexao(Solicitante.ClienteADM);
        c.rodar();
    }


}
