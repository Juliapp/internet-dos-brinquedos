package comunicacao;

import util.Console;
import java.io.IOException;
import model.Carro;
import java.util.Iterator;
import java.util.ArrayList;
import model.Jogador;
import model.PreConfigCorrida;

public class Client0001 {

    String opc = "N";
    private Transmissao transm;

    private Client0001() {
        this.transm = new Transmissao();
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

        String[] obj = {tag, cor, equipe};

        Mensagem msg = new Mensagem(Command.CadCarro, obj, Solicitante.ClienteCad);

        transm.enviaMensagem(msg);

        System.out.println("Deseja cadastrar novamente? S/N");
        op = Console.readString();
        if (op.equals("S")) {
            return 1;
        } else {
            return 0;
        }

    }

    public void iteraArrayCarros() throws IOException, ClassNotFoundException {

        Mensagem msg = new Mensagem(Command.IterarCarros, null, Solicitante.ClienteCad);

        transm.solicitaMensagem(msg);

        ArrayList<Carro> carros = (ArrayList<Carro>) transm.getDadoRecebido();
        Iterator<Carro> iterProx = carros.iterator();

        int count = 1;
        while (iterProx.hasNext()) {
            Carro c = (Carro) iterProx.next();
            System.out.println("------------------------------------------------------------");
            System.out.println("ID: " + count + " " + "Tag: " + c.getTag() + " " + "Cor: " + c.getCor() + " " + "Equipe: " + c.getEquipe().getNome());
            System.out.println("------------------------------------------------------------");
            count++;
        }

    }

    public int cadastroJogadores() throws IOException, ClassNotFoundException {
        String op;

        System.out.println("Informe o seu nome:");
        String nome = Console.readString();

        Mensagem msg0 = new Mensagem(Command.CadPiloto, nome, Solicitante.ClienteCad);
        transm.enviaMensagem(msg0);

        System.out.println("Informe o ID do carro desejado!");
        iteraArrayCarros();

        String idCarro = Console.readString();

        String[] obj = {idCarro, nome};
        Mensagem msg1 = new Mensagem(Command.CadJogador, obj, Solicitante.ClienteCad);

        transm.enviaMensagem(msg1);

        System.out.println("Deseja cadastrar novamente? S/N");
        op = Console.readString();
        if (op.equals("S")) {
            return 1;
        } else {
            return 0;
        }

    }

    public void percorreParticipantes() throws IOException, ClassNotFoundException {
        Mensagem msg = new Mensagem(Command.IterarJogadores, null, Solicitante.ClienteCad);

        transm.solicitaMensagem(msg);

        if (transm.getDadoRecebido() instanceof String) {
            System.out.println(transm.getDadoRecebido());
        } else {
            ArrayList<Jogador> jogadores = (ArrayList<Jogador>) transm.getDadoRecebido();
            Iterator<Jogador> iterJgdrs = jogadores.iterator();

            while (iterJgdrs.hasNext()) {

                Jogador jgdr = (Jogador) iterJgdrs.next();
                System.out.println("--------------------------------------------------------");
                System.out.println(jgdr);
                System.out.println("--------------------------------------------------------");

            }
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

        percorreParticipantes();
        for (int count = 0; count < QuantosVaoJogar; count++) {
            System.out.println("Informe o ID do jogador que deseja cadastrar na corrida!");
            String aux = Console.readString();
            jogadores[count] = Integer.parseInt(aux);
        }

        PreConfigCorrida preConfig = new PreConfigCorrida(voltas, jogadores);
        Mensagem mensagem = new Mensagem(Command.PreConfiguracaoCorrida, preConfig, Solicitante.ClienteCad);
        transm.enviaMensagem(mensagem);

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
                    Mensagem msg = new Mensagem(Command.ComecarCorrida, null, Solicitante.ClienteCad);
                    transm.enviaMensagem(msg);
                    break;
            }
            System.out.println(".");
            
            

        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client0001 client = new Client0001();
        try {
            int repeat = 0;
            //Ta dando Erroooooo
            do {

                int controle = client.menuPrincipal();
                switch (controle) {

                    case 1:
                        do {
                            repeat = client.cadastroCarros();
                        } while (repeat == 1);
                        break;

                    case 2:
                        do {
                            repeat = client.cadastroJogadores();
                        } while (repeat == 1);
                        break;
                    case 3:

                        client.iniciaPartida();

                }
            } while (repeat == 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*System.out.println("Realizar novo cadastro?\n" + "S/N");
            opcao = Console.readString();
            
            //} while (opcao.equals('S'));
     */ /*
        System.out.println("Bem vindo cliente");
        
        System.out.println("Cliente conectado");
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Ok");
        Object obj = (Object) controll.cadastrarUsuario("Mateus", null);
        os.writeObject(obj);
        System.out.println("Enviando Informações");

        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        Participante returnMessage = (Participante) is.readObject();
        System.out.println("return Message is=" + returnMessage.getNome());
        socket.close();
     */

}
