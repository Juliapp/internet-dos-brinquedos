package teste;

import comunicacao.Command;
import comunicacao.Mensagem;
import controladores.ControladorConexao;
import teste.Conexao;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import model.Carro;
import model.Jogador;
import model.PreConfigCorrida;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Console;

public class Admin {

    String opc = "N";
    private ControladorConexao transm;
    public Admin() {
        this.transm = new ControladorConexao();
    }

    private int voltarMenu(String opc) {
        this.opc = opc;
        if (opc.equals('S')) {
            return 1;
        } else {
            return -1;
        }
    }

    public String getOpc() {
        return opc;
    }

    public int menuPrincipal() throws IOException {
        int opc;
        System.out.println("Menu Internet dos Brinquedos");
        System.out.println("1- Realizar Cadastro de Carros\n"
                + "2- Realizar Cadastro de Jogadores\n"
                + "3- Iniciar Partida");
        opc = Console.readInt();
        return opc;
    }

    public int cadastroCarros() throws IOException, ClassNotFoundException {
        String op;
        System.out.println("Infome a tag do carro(ID)!");
        String tag = Console.readString();

        System.out.println("Informe a cor do carro!");
        String cor = Console.readString();

        System.out.println("Informe a equipe pertencente!");
        String equipe = Console.readString();
        JSONObject carro = new JSONObject();

        carro.put("tag", tag);
        carro.put("cor", cor);
        carro.put("equipe", equipe);
        carro.put("solicitante", "Admin");
        carro.put("command", "CadCarro");
        
        String dados_carro = carro.toString();
        
        byte[] bytes = dados_carro.getBytes(StandardCharsets.UTF_8);
        transm.novaMensagem(bytes);
 
        System.out.println("Deseja cadastrar novamente? S/N");
        op = Console.readString();
        if (op.equals("S")) {
            return 1;
        } else {
            return 0;
        }

    }
    
    public void iteraCarros(){
        //buscar o byte, converter em JSONArray e ler as informações
    }
 

    public int cadastroJogadores() throws IOException, ClassNotFoundException {
        String op;

        System.out.println("Informe o seu nome:");
        String nome = Console.readString();
        JSONObject piloto = new JSONObject();

        piloto.put("nome", nome);
        piloto.put("solicitante", "Admin");
        piloto.put("command", "CadPiloto");

        String dados_piloto = piloto.toString();
        
        byte[] bytes = dados_piloto.getBytes(StandardCharsets.UTF_8);
        transm.novaMensagem(bytes);
        
        System.out.println("Informe o ID do carro desejado!");
        

        String idCarro = Console.readString();

        JSONObject jogador = new JSONObject();

        jogador.put("idCarro", idCarro);
        jogador.put("nome", nome);
        jogador.put("command", Command.CadJogador);
        jogador.put("solicitante", Solicitante.ClienteADM);

        String dados_Jogador = piloto.toString();
        byte[] bytesArray = dados_Jogador.getBytes(StandardCharsets.UTF_8);
        transm.novaMensagem(bytesArray);
         
        System.out.println("Deseja cadastrar novamente? S/N");
        op = Console.readString();
        if (op.equals("S")) {
            return 1;
        } else {
            return 0;
        }
       
    }

    public void iniciaPartida() throws IOException, ClassNotFoundException {

        String op;
        System.out.println("Quantas voltas deseja?");
        int voltas = Console.readInt();

        System.out.println("Quantos jogadores irão jogar?");
        String quant = Console.readString();
        Integer QuantosVaoJogar = new Integer(quant);

        int[] jogadores = new int[QuantosVaoJogar];

       // percorreParticipantes();
        for (int count = 0; count < QuantosVaoJogar; count++) {
            System.out.println("Informe o ID do jogador que deseja cadastrar na corrida!");
            String aux = Console.readString();
            jogadores[count] = Integer.parseInt(aux);
        }

       // PreConfigCorrida preConfig = new PreConfigCorrida(voltas, jogadores);
       // Mensagem mensagem = new Mensagem(Command.PreConfiguracaoCorrida, preConfig, comunicacao.Solicitante.ClienteCad);
       // transm.enviaMensagem(mensagem);

        for (int count = 0; count <= 100; count++) {
            switch (count) {
                case 0:
                    System.out.println("Inicializando a partida...");
                    break;
                case 30:
                    System.out.println("Apertando os parafusos...");
                    break;
                case 60:
                    System.out.println("Colocando oléo nos motores...");
                    break;
                case 80:
                    System.out.println("Preparando as bandeiras de largada...");
                    break;
                case 100:
                   // Mensagem msg = new Mensagem(Command.ComecarCorrida, null, comunicacao.Solicitante.ClienteCad);
                    //transm.enviaMensagem(msg);
                    break;
            }
            System.out.println(".");

        }

    }

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            int repeat = 0;
            //Ta dando Erroooooo
            Admin admin = new Admin();
            admin.conectarCliente();
            do {

                int controle = admin.menuPrincipal();
                switch (controle) {

                    case 1:
                        do {
                            repeat = admin.cadastroCarros();
                        } while (repeat == 1);
                        break;

                    case 2:
                        do {
                            repeat = admin.cadastroJogadores();
                        } while (repeat == 1);
                        break;
                    case 3:

                        admin.iniciaPartida();

                }
            } while (repeat == 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
       

    }

    private static void conectarCliente() throws IOException {
        System.out.println("----- Internet dos brinquedos -----");
        System.out.println("Vamos fazer a conexão com os clientes");
        Conexao c = new Conexao();
        c.rodar();
    }

}
