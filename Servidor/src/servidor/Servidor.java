package servidor;

import comunicacao.ConectionIO;
import comunicacao.ThreadConections;
import facade.ServidorFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static ServidorFacade facade;
    private static ThreadConections tcIO;

    public Servidor() throws IOException, FileNotFoundException, ClassNotFoundException {
        facade = ServidorFacade.getInstance();
    }

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        try {
            Servidor server = new Servidor();
            server.conectarClientes();

            tcIO = new ThreadConections(facade.getConectionIOADM(), facade.getConectionIOExib(), facade.getConectionIOSensor());
          //tcIO = new ThreadConections(facade.getConectionIOADM(), facade.getConectionIOSensor());

            new Thread(tcIO).start();

        } catch (IOException | ClassNotFoundException | NullPointerException ex) {
            ex.printStackTrace();
            // System.out.println("Erro" + ex);
        }

    }

    private static void conectarClientes() throws IOException, FileNotFoundException, ClassNotFoundException {
        System.out.println("----- Internet dos brinquedos -----");
        System.out.println("Vamos fazer a conexão com os clientes");
        boolean adm = false, sensor = false, exibicao = false;

        do {
            System.out.println("Qual cliente você quer conectar?");
            if (!adm) {
                System.out.println("1 - Cliente ADM");
            }
            /*    if (!exibicao) {
                System.out.println("2 - Cliente de exibição");
            }
             */
            if (!sensor) {
                System.out.println("3 - Cliente sensor");
            }
            Scanner s = new Scanner(System.in);
            int resposta = s.nextInt();

            switch (resposta) {
                
                case 1:
                    facade.iniciarClienteADM();
                    adm = true;
                    break;
                
                         
                case 2:
                    facade.iniciarClienteExibicao();
                    exibicao = true;
                    break;
                 
                case 3:
                    facade.iniciarClienteASensor();
                    sensor = true;
                    break;
                default:
                    System.out.println("Resposta incorreta");

            }

       } while (!adm || !sensor || !exibicao);
       
      
        facade.criandoArquivos();
        facade.lerDados();

    }

}
