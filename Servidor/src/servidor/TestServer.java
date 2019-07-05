package servidor;

import comunicacao.ThreadConexao;
import java.util.Scanner;

public class TestServer {
    private ThreadConexao adm;
    private ThreadConexao exibicao;
    private ThreadConexao sensor;
    
    public void TestServer(){}
    
    public static void main(String[] args) throws InterruptedException {
        TestServer server = new TestServer();
        server.conectarDispositivos();
                
    }

    public ThreadConexao getAdm() {
        return adm;
    }

    public void setAdm(ThreadConexao adm) {
        this.adm = adm;
    }

    public ThreadConexao getExibicao() {
        return exibicao;
    }

    public void setExibicao(ThreadConexao exibicao) {
        this.exibicao = exibicao;
    }

    public ThreadConexao getSensor() {
        return sensor;
    }

    public void setSensor(ThreadConexao sensor) {
        this.sensor = sensor;
    }
    
    public void conectarADM(){
        adm = new ThreadConexao();
    }
    
    public void conectarExibicao(){
        exibicao = new ThreadConexao();
    }
    
    public void conectarSensor() throws InterruptedException{
        sensor = new ThreadConexao();
        sensor.setTest("Funcionou");
    }    
    
    public void conectarDispositivos() throws InterruptedException{
        do{
            System.out.println("Qual dispositivo você quer conectar?");
            if(adm == null){
                System.out.println("1 - Cliente ADM");
            }
            if(sensor == null){
                System.out.println("2 - Cliente sensor");
            }
            if(exibicao == null){
                System.out.println("3 - Cliente de exibição");
            }
            
            Scanner s = new Scanner(System.in);
            int resposta = s.nextInt();
            
            switch (resposta){
                    case 1: 
                        conectarADM();
                        break;
                    case 2: 
                        conectarSensor();
                        break;
                    case 3: 
                        conectarExibicao();
                        break;
                    default:  System.out.println("Resposta incorreta");
            }
        }while(adm != null && sensor != null && exibicao != null);     
    }
}
