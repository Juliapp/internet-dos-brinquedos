package cliente_exibicao;
 
import comunicacao.ThreadConections;
import facade.FacadeExibicao;
import java.io.IOException;

public class Cliente_Exibicao{
    public static FacadeExibicao facade;
    private static ThreadConections tcIO;
    
    public Cliente_Exibicao(){
        System.out.println("b");
        facade = FacadeExibicao.getInstance();
        System.out.println("c");
    }
    
    public static void main(String[] args) throws IOException {
        Cliente_Exibicao exibicao = new Cliente_Exibicao();
        exibicao.conectarServidor();
        tcIO = new ThreadConections(facade.getConectionIO());
        new Thread(tcIO).start();        
        
    }

    private void conectarServidor() throws IOException {
        facade.iniciarServidor();
    }


}
