package comunicacao;

import java.util.ArrayList;
import java.util.Date;
import controladores.ControladorFactory;
import controladores.ControladorCorrida;
import controladores.ControladorDeDados;
import execoes.CorridaNaoIniciadaException;
import execoes.PilotoNaoExisteException;
import execoes.TagInvalidaException;
import execoes.VoltaInvalidaException;
import model.Carro;
import model.Equipe;
import model.Jogador;
import model.Piloto;
import model.TagColetada;
import model.Time;


public class ServidorFacade {

    private ArrayList<ControladorCorrida> contrCorrida;
    private ControladorDeDados Dados;
    private ControladorFactory cf;
    private ControladorCorrida corridaAtual;
    private static ServidorFacade facade;

    /**
     * Méodo construtor se inicializa instanciando cada um os controladores.
     * Essa classe é o que vai acessar todos os controladores e vai ser a classe
     * disponível para quem quiser acessar o programa. As operações só serão
     * feitas a partir desta classe, e nenhum controlador vai ser acessado senão
     * por ela.
     */
    private ServidorFacade() {
        Dados = new ControladorDeDados();
        cf = new ControladorFactory();
        contrCorrida = new ArrayList<>();
        corridaAtual = new ControladorCorrida();
    }
    
    public static synchronized ServidorFacade getInstance(){
        return (facade == null) ? facade = new ServidorFacade() : facade;
    }

    /**
     * **** Primeira parte do Facade dedicada as funções de cadastro no jogo
     * *****
     */
    /**
     * Chama o método para cadastrar uma Equipe dentro do Controlador de dados
     *
     * @param nome nome da Equipe que você quer cadastrar
     * @return Verdadeiro caso o cadastro foi realizado com sucesso
     */
    public boolean cadastrarEquipe(String nome) {
        return Dados.addEquipe(cf.factoryE(nome));
    }

    /**
     * Pega do Controlador de Dados um objeto Equipe a partir do nome
     *
     * @param nome nome da equipe que queira pegar
     * @return objeto equipe e nulo caso não tenha encontrado
     */
    public Equipe getEquipe(String nome) {
        return Dados.getEquipe(nome);
    }

    /**
     * Deleta do Controlador de Dados um objeto Equipe a partir do nome
     *
     * @param nome nome da equipe que queira remover
     * @return caso o método tenha removido a equipe com sucesso
     */
    public boolean deleteEquipe(String nome) {
        return Dados.deleteEquipe(nome);
    }

    /**
     * Esse método printa todas as equipes cadastradas no Controlador de dados
     * no console
     */
    public void listarEquipes() {
        Dados.listarEquipes();
    }

    /**
     * Pega o ArrayList de equipes que foram cadastrados no Controlador de dados
     *
     * @return ArrayList de equipes cadastradas
     */
    public ArrayList<Equipe> getListaDeEquipes() {
        return Dados.getEquipes();
    }

    /**
     * Chama o método para cadastrar um Carro dentro do Controlador de dados
     *
     * @param tag TAG do carro que está sendo cadastrado
     * @param cor cor do carro que está sendo cadastrado
     * @param equipe equipe que o carro pertence em String, pois ela vai ser
     * encontrada com o método de getEquipe
     * @return Verdadeiro se o cadastro foi efetuado com sucesso
     */
    public boolean cadastrarCarro(String tag, String cor, String equipe) {
        Equipe e;
        if (getEquipe(equipe) != null) {
            e = getEquipe(equipe);
            Carro c = cf.factoryC(tag, cor, e);
            e.addCarro(c);
            return Dados.addCarro(c);
        } else if (getEquipe(equipe) == null) {
            e = new Equipe(equipe);
            Dados.addEquipe(e);
            Carro c = cf.factoryC(tag, cor, e);
            e.addCarro(c);
            return Dados.addCarro(c);
        } else {
            return false;
        }

    }

    /**
     * Pega do Controlador de Dados um objeto Carro a partir de sua TAG.
     *
     * @param tag TAG do carro que deseja coletar
     * @return objeto carro pertencente a tag
     */
    public Carro getCarro(String tag) {
        return Dados.getCarro(tag);
    }

    public Carro getCarroPorId(int id) {
        return Dados.getCarroPorId(id);
    }

    /**
     * Deleta um carro do controlador de dados a partir de sua tag
     *
     * @param tag TAG do carro a ser removido
     *
     * @return caso o método tenha removido o carro com sucesso
     */
    public boolean deleteCarro(String tag) {
        return Dados.deleteCarro(tag);
    }

    /**
     * Esse método printa todos os carros cadastrados no Controlador de dados no
     * console
     */
    public void listarCarros() {
        Dados.listarCarros();
    }

    /**
     * Pega o ArrayList de Carros que foram cadastrados no Controlador de dados
     *
     * @return ArrayList de carros
     */
    public ArrayList<Carro> getListaDeCarros() {
        return Dados.getCarros();
    }

    /**
     * Chama o método para cadastrar um Piloto dentro do Controlador de dados
     *
     * @param nome nome do piloto a ser cadastrado
     * @param foto foto do piloto a ser cadastrado
     * @return Verdadeiro se o cadastro foi efetuado com sucesso
     * @throws PilotoNaoExisteException
     */
    public boolean cadastrarPiloto(String nome, String foto) throws PilotoNaoExisteException {
        return Dados.addPiloto(cf.factoryP(nome, foto));
    }

    /**
     * Pega do Controlador de Dados um objeto Piloto a partir do seu nome
     *
     * @param nome
     * @return
     */
    public Piloto getPiloto(String nome) {
        return Dados.getPiloto(nome);
    }

    /**
     * Deleta um carro do controlador de dados a partir de seu nome
     *
     * @param nome nome do piloto a ser removido do controlador de dados
     * @return caso o método tenha removido o piloto com sucesso
     */
    public boolean deletePiloto(String nome) {
        return Dados.deletePiloto(nome);
    }

    /**
     * Esse método printa todos os pilotos cadastrados no Controlador de dados
     * no console
     */
    public void listarPilotos() {
        Dados.listarPilotos();
    }

    /**
     * Pega o ArrayList de pilotos que foram cadastrados no Controlador de dados
     *
     * @return ArrayList com todos os pilotos cadastrados
     */
    public ArrayList<Piloto> getListaDePilotos() {
        return Dados.getPilotos();
    }

    /**
     * Chama o método para cadastrar um Jogador dentro do Controlador de dados A
     * classe jogador é a junção da classe Piloto, Equipe e Carro com os
     * atributos e métodos que um jogador tem durante a partida.
     *
     * @param id id do carro (definida ao cadastrar um carro no sistema)
     * @param piloto nome do piloto (deve estar pré cadastrado no sistema
     * @return Verdadeiro se o cadastro foi efetuado com sucesso
     */
    public boolean CadastrarJogador(int id, String piloto) {
        return Dados.addJogador(cf.factoryJog(getCarroPorId(id), getPiloto(piloto)));
    }

    /**
     * Pega do Controlador de Dados um objeto Jogador a partir do nome da TAG do
     * carro
     *
     * @param tag tag do carro
     * @param piloto nome do piloto
     * @return O objeto jogador
     */
    public Jogador getJogador(String tag, String piloto) {
        return Dados.getJogador(cf.factoryJog(getCarro(tag), getPiloto(piloto)));
    }

    /**
     * Deleta do Controlador de Dados um objeto Jogador a partir do nome do
     * piloto e a TAG do carro
     *
     * @param tag TAG do carro a ser deletado
     * @param piloto nome do piloto
     * @return caso o método tenha removido o jogador com sucesso
     */
    public boolean DeleteJogador(String tag, String piloto) {
        return Dados.deleteJogador(cf.factoryJog(getCarro(tag), getPiloto(piloto)));
    }

    /**
     * Deleta do Controlador de Dados um objeto Jogador a partir do nome do
     * piloto
     *
     * @param piloto nome do piloto
     * @return caso o método tenha removido o jogador com sucesso
     */
    public boolean DeleteJogadorPorPiloto(String piloto) {
        return Dados.deleteJogador(getJogadorPorNomeDoPiloto(piloto));
    }

    /**
     * Pega o ArrayList de jogadores que foram cadastrados no Controlador de
     * dados
     *
     * @return ArrayList com todos jogadores cadastrados
     */
    public ArrayList<Jogador> getListaDeJogadores() {
        return Dados.getJogadores();
    }

    /**
     * Retorna um jogador cadastrado a partir do nome do piloto
     *
     * @param piloto nome do piloto
     * @return o objeto jogador requerido caso não nulo
     */
    public Jogador getJogadorPorNomeDoPiloto(String piloto) {
        return Dados.getJogadorPorNomeDoPiloto(piloto);
    }

    /**
     * **** Segunda parte do Facade dedicada as funções da partida *****
     */
    /**
     * Pega a corrida que está sendo processada no momento
     *
     * @return O controlador dessa corrida
     */
    public ControladorCorrida getCorridaAtual() {
        return corridaAtual;
    }

    /**
     * A partir de um array de nomes de jogadores pré cadastrados no sistem que
     * querem participar da corrida ele joga os Objetos Jogador num array para
     * que seja lançado na corrida atual
     *
     * @param args nome dos jogadores
     * @return ArrayList de jogadores
     */
    public ArrayList<Jogador> selecionarJogadores(String[] args) {
        return selecionarJogadores(args);
    }

    /**
     * Instancia uma nova corrida a partir de seu controlador
     *
     * @param ids
     * @param quantidadeDeVoltas quantidades de voltas que vai ter na corrida
     * @return Caso a corrida foi instanciada com sucesso
     */
    public boolean novaCorrida(int[] ids, int quantidadeDeVoltas) {
        ControladorCorrida c = new ControladorCorrida(getJogadoresPorArrayDeID(ids), quantidadeDeVoltas);
        corridaAtual = c;
        contrCorrida.add(c);
        return c != null;
    }
    
    public ArrayList<Jogador> getJogadoresPorArrayDeID(int[] ids){
        return Dados.getJogadoresPorArrayDeID(ids);
    }

    /**
     * Da o comando para que o cronometro comece a rodar e os métodos do
     * controlador de corrida estejam aptos a serem utilizados
     * @return 
     */
    public boolean comecarCorrida() {
        return corridaAtual.iniciarCorrida();
    }
    
    public boolean statusCorrida(){
        return corridaAtual.getStatus();
    }

    /**
     * Recolhe as tags que são pegadas pelo sensor e jogam diretamente no
     * controlador da corrida
     *
     * @param tag Tag coletada do sensor
     * @param voltaComputada objeto Time coletado pelo sensor
     * @throws TagInvalidaException Se a TAG não estiver cadastrada no Sistema
     * ou na partida atual
     * @throws execoes.CorridaNaoIniciadaException
     * @throws execoes.VoltaInvalidaException
     */
    public void coletorDeTags(TagColetada tag, Time voltaComputada) throws TagInvalidaException, CorridaNaoIniciadaException, VoltaInvalidaException {
        corridaAtual.pushTag(tag, voltaComputada);
    }

}
