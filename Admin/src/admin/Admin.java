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
 
    /**Controla a opção de retorno
     * ao menu principal.
     * 
     * @param opc - String que recebe o caractere S ou s para verificação.
     * @return Retorna um inteiro, 1 para voltar ao menu ou -1 para continuar na operação.
     */
    private int voltarMenu(String opc) {
        this.opc = opc;
        if (opc.equals("S") | opc.equals("s")) {
            return 1;
        } else {
            return -1;
        }
    }
    
    /** Getter para obter valor da string que captura a
     * opção de retorno ao menu.
     *
     * @return String da opção designada. 
     */
    public String getOpc() {
        return opc;
    }
    
    /**Para criação do menu principal
     * pro usuário.
     * 
     * @return inteiro com a opção desejada.
     * @throws IOException 
     */
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
    
    /**Coleta dos dados de carros
     * para enviar pro servidor, visando a criação de partidas. 
     * 
     * @return inteiro para controlar repetição.
     * @throws IOException
     * @throws InterruptedException 
     */
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

        JSONObject carro = new JSONObject(); //Json para encapsular as informações sobre o carro

        carro.put("cor", cor);
        carro.put("equipe", equipe);
        carro.put("solicitante", "ClienteADM");
        carro.put("command", "CadCarro");

        String dados_carro = carro.toString();

        byte[] bytes = dados_carro.getBytes(StandardCharsets.UTF_8); //Convertendo os dados em um array de bytes
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
    
    /**Responsável pela iteração dos 
     * carros cadastrados no sistema, exibindo
     * as informações principais como cor, equipe
     * id e tag do carro.
     * 
     * @throws InterruptedException 
     */
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

    /**Coleta dos dados relacionados ao jogador
     * como o seu nome e o carro desejado para
     * a corrida
     * 
     * @return inteiro correspondente a opção do carro.
     * @throws IOException
     * @throws InterruptedException 
     */
    public int cadastroJogadores() throws IOException, InterruptedException {
        String op;
        String nome;
        do {
            System.out.println("Informe o seu nome:");
            nome = Console.readString();

        } while (!nome.matches("[^\\d]+"));
        JSONObject piloto = new JSONObject(); //Json que encapsula o nome do jogador

        piloto.put("nomePiloto", nome);
        piloto.put("solicitante", "ClienteADM");
        piloto.put("command", "CadPiloto");

        String dados_piloto = piloto.toString();

        byte[] bytes = dados_piloto.getBytes(StandardCharsets.UTF_8); //Convertendo os dados em um vetor de byte
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

        JSONObject jogador = new JSONObject(); //Json que encapsula o id do carro e nome.

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

    /**Percorre todos os jogadores já cadastrados.
     * 
     * @throws InterruptedException 
     */
    public void percorreParticipantes() throws InterruptedException {
        JSONObject iterar = new JSONObject();

        iterar.put("solicitante", "ClienteADM");
        iterar.put("command", "IterarJogadores");

        String dados_carro = iterar.toString();

        byte[] bytes = dados_carro.getBytes(StandardCharsets.UTF_8);
        facade.novaMensagem(iterar.getString("solicitante"), bytes); //Solicitando o JSONArray

        JSONArray array = facade.getRespostaJSON().getJSONArray("arrayDeJogadores"); //Recebendo o JSONArray do servidor

        /*
        Laço for para printar as informações principais dos jogadores
        */
        for (int i = 0; i < array.length(); i++) {
            String info = array.getString(i);
            System.out.println(info);
        }
    }

    /**Dá largada a corrida
     * 
     * @throws IOException
     * @throws InterruptedException 
     */
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

        percorreParticipantes(); //Percorrendo os jogadores já cadastrados
        /*
        Laço for onde deverá selecionar os jogadores
        participantes da corrida, baseado na quantidade
        de jogadores escolhida
        */
        for (int count = 0; count < quant; count++) {
            System.out.println("Informe o 'Id' do jogador que deseja cadastrar na corrida!");
            String aux = Console.readString();
            jogadores_participantes.put(Integer.parseInt(aux)); //Adicionando os respectivos id dos jogadores
        }

        JSONObject dados = new JSONObject(); //Json que encapsula os dados necessário para preconfiguração da corrida
        dados.put("ids_jogadores", jogadores_participantes);
        dados.put("num_voltas", voltas);
        dados.put("solicitante", "ClienteADM");
        dados.put("command", "PreConfigCorrida");

        String inicia_partida = dados.toString();

        byte[] bytes = inicia_partida.getBytes();
        facade.novaMensagem(dados.getString("solicitante"), bytes); //Enviando pré-configuração da corrida.

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
                    facade.novaMensagem(dados.getString("solicitante"), bytes); //Dando largada
                    System.out.println(facade.getRespostaJSON().getString("status")); // Printando retorno do servidor

                    Thread.sleep(1000); //Espera 1 segundo para solicitar status da corrida!
                    dados.put("command", "StatusCorrida");
                    inicia_partida = dados.toString();
                    bytes = inicia_partida.getBytes();
                    facade.novaMensagem(dados.getString("solicitante"), bytes);
                    
                    /*
                    Laço while para verificar o andamento da corrida!
                    */
                    while (facade.getRespostaJSON().getString("status").equals("Rodando")) {
                        Thread.sleep(2000); //Espera 2 segundos para verificar novamente
                        facade.novaMensagem(dados.getString("solicitante"), bytes);
                    }

                    break;
            }
            System.out.println(".");
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
