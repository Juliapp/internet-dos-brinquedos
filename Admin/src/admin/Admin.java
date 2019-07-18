package admin;

import facade.ClienteFacade;
import comunicacao.Mensagem;
import comunicacao.ThreadConections;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import model.Carro;
import model.Jogador;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Console;

public class Admin {

    String opc = "N";
    private static ThreadConections tcIO;
    private static ClienteFacade facade;

    public Admin() {
        this.facade = new ClienteFacade();
    }

    private int voltarMenu(String opc) {
        this.opc = opc;
        if (opc.equals("S") | opc.equals("s")) {
            return 1;
        } else {
            return -1;
        }
    }

    public String getOpc() {
        return opc;
    }

    public int menuPrincipal() throws IOException {
        int opc = 0;
        try {
            System.out.println("Menu Internet dos Brinquedos");
            System.out.println("1- Realizar Cadastro de Carros\n"
                    + "2- Realizar Cadastro de Jogadores\n"
                    + "3- Iniciar Partida");
            opc = Console.readInt();

        } catch (NumberFormatException e) {
            System.out.println("Digite só números!");
            System.out.println("");
            menuPrincipal();
        }
        return opc;
    }

    public void testJSON() {
        String dado = "{ \"tag\" : \"AS0293FHUSID\" , \"hora\" : 2 , \"minutos\" : 3 , \"segundos\" : 4 , \"milesimos\" : 5 }";
        JSONObject convert = new JSONObject(dado);
        System.out.println(convert.toString());
    }

    public int cadastroCarros() throws IOException, InterruptedException {
        String op;
        String cor;
        String equipe;

        do {

            System.out.println("Informe a cor do carro!");
            cor = Console.readString();

            System.out.println("Informe a equipe pertencente!");
            equipe = Console.readString();

        } while (!cor.matches("[^\\d]+") | !equipe.matches("[^\\d]+"));

        JSONObject carro = new JSONObject();

        carro.put("cor", cor);
        carro.put("equipe", equipe);
        carro.put("solicitante", "ClienteADM");
        carro.put("command", "CadCarro");

        String dados_carro = carro.toString();

        byte[] bytes = dados_carro.getBytes(StandardCharsets.UTF_8);
        facade.novaMensagem(carro.getString("solicitante"), bytes);

        System.out.println(facade.getRespostaJSON().getString("status"));
        do {
            System.out.println("Deseja cadastrar novamente? S/N");
            op = Console.readString();
        } while (!op.matches("[^\\d]+"));
        if (op.equals("S") | op.equals("s")) {
            return 1;
        } else {
            return 0;
        }

    }

    public void iterarCarros() throws InterruptedException {
        //buscar o byte, converter em JSONArray e ler as informações
        JSONObject iterar = new JSONObject();

        iterar.put("solicitante", "ClienteADM");
        iterar.put("command", "IterarCarros");

        String dados_carro = iterar.toString();

        byte[] bytes = dados_carro.getBytes(StandardCharsets.UTF_8);
        facade.novaMensagem(iterar.getString("solicitante"), bytes);

        JSONArray array = facade.getRespostaJSON().getJSONArray("arrayDeCarros");

        for (int i = 0; i < array.length(); i++) {
            String info = array.getString(i);
            System.out.println(info);
        }

    }

    public int cadastroJogadores() throws IOException, InterruptedException {
        String op;
        String nome;
        do {
            System.out.println("Informe o seu nome:");
            nome = Console.readString();

        } while (!nome.matches("[^\\d]+"));
        JSONObject piloto = new JSONObject();

        piloto.put("nomePiloto", nome);
        piloto.put("solicitante", "ClienteADM");
        piloto.put("command", "CadPiloto");

        String dados_piloto = piloto.toString();

        byte[] bytes = dados_piloto.getBytes(StandardCharsets.UTF_8);
        facade.novaMensagem(piloto.getString("solicitante"), bytes);

        System.out.println(facade.getRespostaJSON().getString("status"));
        iterarCarros();
        int idCarro = 0;

        try {

            System.out.println("Informe o ID do carro desejado!");
            idCarro = Console.readInt();

        } catch (NumberFormatException e) {
            System.out.println("Porfavor digite somente números!");
            System.out.println("Informe o ID do carro desejado!");
            idCarro = Console.readInt();
        }

        JSONObject jogador = new JSONObject();

        jogador.put("idCarro", idCarro);
        jogador.put("nome", nome);
        jogador.put("command", "CadJogador");
        jogador.put("solicitante", "ClienteADM");

        String dados_Jogador = jogador.toString();
        byte[] bytesArray = dados_Jogador.getBytes(StandardCharsets.UTF_8);
        facade.novaMensagem(jogador.getString("solicitante"), bytesArray);
        System.out.println(facade.getRespostaJSON().getString("status"));
        do {
            System.out.println("Deseja cadastrar novamente? S/N");
            op = Console.readString();
        } while (!op.matches("[^\\d]+"));
        if (op.equals("S") | op.equals("s")) {
            return 1;
        } else {
            return 0;
        }

    }

    public void percorreParticipantes() throws InterruptedException {
        JSONObject iterar = new JSONObject();

        iterar.put("solicitante", "ClienteADM");
        iterar.put("command", "IterarJogadores");

        String dados_carro = iterar.toString();

        byte[] bytes = dados_carro.getBytes(StandardCharsets.UTF_8);
        facade.novaMensagem(iterar.getString("solicitante"), bytes);

        JSONArray array = facade.getRespostaJSON().getJSONArray("arrayDeJogadores");

        for (int i = 0; i < array.length(); i++) {
            String info = array.getString(i);
            System.out.println(info);
        }
    }

    public void iniciaPartida() throws IOException, InterruptedException {

        String op;
        int voltas;
        int quant;

        try {
            System.out.println("Quantas voltas deseja?");
            voltas = Console.readInt();

            System.out.println("Quantos jogadores irão jogar?");
            quant = Console.readInt();
        } catch (NumberFormatException e) {
            System.out.println("Digite somente números!!!");
            System.out.println("Quantas voltas deseja?");
            voltas = Console.readInt();

            System.out.println("Quantos jogadores irão jogar?");
            quant = Console.readInt();
        }

        JSONArray jogadores_participantes = new JSONArray();

        percorreParticipantes();
        for (int count = 0; count < quant; count++) {
            System.out.println("Informe o 'Id' do jogador que deseja cadastrar na corrida!");
            String aux = Console.readString();
            jogadores_participantes.put(Integer.parseInt(aux));
        }

        JSONObject dados = new JSONObject();
        dados.put("ids_jogadores", jogadores_participantes);
        dados.put("num_voltas", voltas);
        dados.put("solicitante", "ClienteADM");
        dados.put("command", "PreConfigCorrida");

        String inicia_partida = dados.toString();

        byte[] bytes = inicia_partida.getBytes();
        facade.novaMensagem(dados.getString("solicitante"), bytes);

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
                    dados.put("command", "ComeçarCorrida");
                    inicia_partida = dados.toString();

                    bytes = inicia_partida.getBytes();
                    facade.novaMensagem(dados.getString("solicitante"), bytes);
                    System.out.println(facade.getRespostaJSON().getString("status"));

                    Thread.sleep(1000);
                    dados.put("command", "StatusCorrida");
                    inicia_partida = dados.toString();
                    bytes = inicia_partida.getBytes();
                    facade.novaMensagem(dados.getString("solicitante"), bytes);
                    while (facade.getRespostaJSON().getString("status").equals("Rodando")) {
                        Thread.sleep(2000);
                        facade.novaMensagem(dados.getString("solicitante"), bytes);
                    }

                    break;
            }

        }

    }

    public static void main(String[] args) throws InterruptedException {
        try {
            int repeat = 0;
            Admin admin = new Admin();
            conectarClientes();
            tcIO = new ThreadConections(facade.getConectionIOADM());
            new Thread(tcIO).start();

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
                        break;
                }
            } while (repeat == 0);
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro:" + e);
        }

    }

    private static void conectarClientes() throws IOException {
        System.out.println("----- Internet dos brinquedos -----");
        System.out.println("Conexão do cliente Administrador");

        facade.iniciarClienteADM();
    }

}
