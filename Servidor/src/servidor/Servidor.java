package servidor;

import comunicacao.ThreadConections;
import facade.ServidorFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static ServidorFacade facade;
    private static ThreadConections tcIO;

    
    
    
    public Servidor(){
        facade = ServidorFacade.getInstance();
    }

    public static void main(String[] args) {
        try {
            Servidor server = new Servidor();
            server.conectarClientes();
            preSet();
            //iniciarThread();
            tcIO = new ThreadConections(facade.getConectionIOADM());
            new Thread(tcIO).start();
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void conectarClientes() throws IOException{
        System.out.println("----- Internet dos brinquedos -----");
        System.out.println("Vamos fazer a conexão com os clientes");
        boolean adm = false, sensor = false, exibicao = false;
        /*
        
        do{
            System.out.println("Qual cliente você quer conectar?");
            if(!adm){
                System.out.println("1 - Cliente ADM");
            }
            if(!sensor){
                System.out.println("2 - Cliente sensor");
            }
            if(!exibicao){
                System.out.println("3 - Cliente de exibição");
            }
            
            Scanner s = new Scanner(System.in);
            int resposta = s.nextInt();
            
            switch (resposta){
                    case 1: 
                        facade.iniciarClienteADM();
                        adm = true;
                        break;
                    case 2: 
                        facade.iniciarClienteASensor();
                        sensor = true;
                        break;
                    case 3: 
                        facade.iniciarClienteExibicao();
                        exibicao = true;
                        break;
                    default:  System.out.println("Resposta incorreta");       
            }        
        }while(!adm || !sensor || !exibicao);
*/
        facade.iniciarClienteADM();
    }
    
    private static void iniciarThread(){
        tcIO = new ThreadConections(facade.getConectionIOADM(), facade.getConectionIOExib(), facade.getConectionIOSensor());
    }
    private static void preSet() {
        ServidorFacade facade = ServidorFacade.getInstance();
        facade.cadastrarEquipe("MUSTANG");
        facade.cadastrarEquipe("REDBUL");
        facade.cadastrarEquipe("TESLA");
        facade.cadastrarEquipe("REDBONE");
        facade.cadastrarEquipe("RIHANNA");

        facade.cadastrarCarro("E29JJA", "VERMELHO", "MUSTANG");
        facade.cadastrarCarro("YUSH21", "LARANJA", "REDBUL");
        facade.cadastrarCarro("2901S", "AMARELO", "TESLA");
        facade.cadastrarCarro("8394SDF", "VERDE", "REDBONE");
        facade.cadastrarCarro("SD252", "AZUL", "RIHANNA");
    }

}
