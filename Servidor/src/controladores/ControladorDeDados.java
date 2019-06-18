package controladores;

import java.util.ArrayList;
import java.util.Iterator;
import model.Carro;
import model.Equipe;
import model.Jogador;
import model.Piloto;

/**
 *O controlador de dados controla os dados cadastráveis do sistema. Essa classe na segunda versão ganhará um banco de dados.
 */
public class ControladorDeDados {
    private ArrayList<Piloto> pilotos;
    private ArrayList<Equipe> equipes;
    private ArrayList<Carro> carros;
    private ArrayList<Jogador> jogadores;

    /**
     *Instancia das listas encadeadas 
     */
    public ControladorDeDados() {
        pilotos = new ArrayList<>();
        equipes = new ArrayList<>();
        carros  = new ArrayList<>();    
        jogadores = new ArrayList<>();
    }    
    
    /**
     *Adiciona um piloto a lista encadeada se ele não existir 
     * @param piloto piloto a ser cadastrado
     * @return verdadeiro se foi cadastrado com sucesso
     */
    public boolean addPiloto(Piloto piloto) {
        if (this.pilotos.isEmpty()) {
            this.pilotos.add(piloto);
        } else if (!hasPiloto(piloto.getNome())) {
            pilotos.add(piloto);
        } else {
            return false;
        }      
        return true;
    }

    /**
     *Percorre a lista encadeada em busca de um piloto com o mesmo nome.
     *Nesta aplicação os pilotos de mesmo nome são dados como iguais 
     * @param nomeDoPiloto
     * @return Verdadeiro caso o piloto já esteja cadastrado
     */
    public boolean hasPiloto(String nomeDoPiloto) {
        Piloto pilotoAux;
        Iterator<Piloto> it = itPiloto();
        while (it.hasNext()) {
            pilotoAux = it.next();
            if(pilotoAux.getNome().equals(nomeDoPiloto)){ return true; }
        }
        return false; //jogar exceção piloto não existe
    }

    /**
     *Pega o iterador do ArrayList de Pilotos
     * @return Iterador de pilotos
     */
    public Iterator<Piloto> itPiloto() {
        return pilotos.iterator();
    }

    /**
     *Pega o piloto com determinado nome passado por parâmetro
     * @param nome do piloto
     * @return o objeto piloto caso exista na lista encadeada 
     */
    public Piloto getPiloto(String nome) {
        if(hasPiloto(nome)){
            return pilotos.get(getIndexPiloto(nome));
        }
        return null; 
    }
    
    /**
     *Percorre a lista encadeada em busca do index do piloto a partir de seu nome
     * @param nomeDoPiloto nome do piloto
     * @return número do index do piloto 
     */
    public int getIndexPiloto(String nomeDoPiloto){
        Piloto pilotoAux;
        Iterator<Piloto> it = itPiloto();
        int index = 0;
        while (it.hasNext()){
            pilotoAux = it.next();
            if(pilotoAux.getNome().equals(nomeDoPiloto)){ return index; }
            index++;
        }
        return 0;
    }
    
    /**
     *Deleta um piloto da lista encadeada a partir do seu nome, buscando-o pelo index
     * @param nomeDoPiloto nome do piloto a ser removido
     * @return verdadeiro se for removido com sucesso
     */
    public boolean deletePiloto(String nomeDoPiloto) {
        if(hasPiloto(nomeDoPiloto)){
            pilotos.remove(getIndexPiloto(nomeDoPiloto));
            return true;
        }
        return false;
    }
    
    /**
     *Lista no console a lista de pilotos cadastrados no sistema 
     */
    public void listarPilotos() {
        Piloto piloto;
        Iterator<Piloto> it = itPiloto();
        while (it.hasNext()) {
            piloto = it.next();
            System.out.println(piloto.getNome());
        }
    }
    
    /**
     *Pega a referência da lista encadeada de pilotos
     * @return ArrayList de pilotos cadastrados
     */
    public ArrayList<Piloto> getPilotos() {
        return pilotos;
    }    
    
    /**
     *Adiciona uma equipe a lista encadeada se ele não existir 
     * @param equipe
     * @return verdadeiro caso foi cadastrado com sucesso
     */
    public boolean addEquipe(Equipe equipe) {
        if (this.equipes.isEmpty()) {
            this.equipes.add(equipe);
        } else if (!hasEquipe(equipe.getNome())) {
            equipes.add(equipe);
        } else {
            return false;
        }
        return true;
    }
    
    /**
     *Percorre a lista encadeada em busca de uma equipe com o mesmo nome.
     * @param nomeDaEquipe
     * @return Verdadeiro caso a equipe já esteja cadastrado
     */
    public boolean hasEquipe(String nomeDaEquipe) {
        Equipe e;
        Iterator<Equipe> it = itEquipe();
        while (it.hasNext()) {
            e = it.next();
            if (e.getNome().equals(nomeDaEquipe)) { return true; }
        }   
        return false;
    }
    
    /**
     *Pega o iterador do ArrayList de equipes
     * @return Iterador de equipes
     */
    public Iterator<Equipe> itEquipe() {
        return equipes.iterator();
    }

    /**
     *Pega a equipe com determinado nome passado por parâmetro
     * @param nome da equipe
     * @return o objeto equipe caso exista na lista encadeada 
     */
    public Equipe getEquipe(String nome) {
        if(hasEquipe(nome)){
            return equipes.get(getIndexEquipe(nome));
        }
        return null;
    }
    
    /**
     *Percorre a lista encadeada em busca do index da equipe a partir de seu nome
     * @param nome nome da equipe
     * @return número do index da equipe 
     */
    public int getIndexEquipe(String nome){
        Equipe equipe;
        Iterator<Equipe> it = itEquipe();
        int index = 0;
        while (it.hasNext()){
            equipe = it.next();
            if(equipe.getNome().equals(nome)){ return index; }
            index++;
        }
        return 0;
    }
    
    /**
     *Lista no console a lista de pilotos cadastrados no sistema 
     */
    public void listarEquipes() {
        Equipe e;
        Iterator<Equipe> it = itEquipe();
        while (it.hasNext()) {
            e = it.next();
            System.out.println(e.getNome());
        }        
    }
 
    /**
     *Deleta uma equipe da lista encadeada a partir do seu nome, buscando-a pelo index
     * @param nomeDaEquipe da equipe a ser removido
     * @return verdadeiro se for removido com sucesso
     */
    public boolean deleteEquipe(String nomeDaEquipe) {
        if(hasEquipe(nomeDaEquipe)){
            equipes.remove(getIndexEquipe(nomeDaEquipe));
            return true;
        }
        return false;
    }
    
    /**
     *Pega a referência da lista encadeada de equipes
     * @return ArrayList de equipes cadastradas
     */
    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }
    
    /**
     *Adiciona um carro a lista encadeada se ele não existir 
     * @param carro
     * @return verdadeiro caso o carro tenha sido cadastrado com sucesso
     */
    public boolean addCarro(Carro carro) {
        if (this.carros.isEmpty()) {
            this.carros.add(carro);
        } else if (!hasCarro(carro.getTag())) {
            carros.add(carro);
        } else {
            return false;
        }
        return true;
    }
    
    /**
     *Percorre a lista encadeada em busca de um carro com a mesma tag.
     * @param tag tag do carro a ser buscada
     * @return Verdadeiro caso o carro já esteja cadastrado
     */
    public boolean hasCarro(String tag) {
        Carro carro;
        Iterator<Carro> it = itCarro();
        while (it.hasNext()) {
            carro = it.next();
            if(carro.getTag().equals(tag)) { return true; }
        }
        return false;
    }
    
    /**
     *Pega o iterador do ArrayList de carros
     * @return Iterador de carros
     */
    public Iterator<Carro> itCarro() {
        return carros.iterator();
    }
    
    /**
     *Pega o carro com determinada tag passada por parâmetro
     * @param tag tag do carro
     * @return o objeto carro caso exista na lista encadeada 
     */
    public Carro getCarro(String tag) {
        if(hasCarro(tag)){
           return carros.remove(getIndexCarro(tag));
        }
        return null;
    }
    
    /**
     *Percorre a lista encadeada em busca do index do carro a partir de seu nome
     * @param tag tag do carro
     * @return número do index do carro
     */
    public int getIndexCarro(String tag){
        Carro carro;
        Iterator<Carro> it = itCarro();
        int index = 0;
        while (it.hasNext()){
            carro = it.next();
            if(carro.getTag().equals(tag)){ return index; }
            index++;
        }
        return 0; 
    }

    /**
     *Pega o objeto carro com a cor passada por parâmetro
     * @param cor cor do carro
     * @return objeto carro
     */
    public Carro getCarroPorCor(String cor) {
        Carro c;
        while (itCarro().hasNext()) {
            c = (Carro) itCarro().next();
            if (c.getCor().equals(cor)) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * busca o carro pelo ID
     * @param id
     * @return objeto carro
     */
    public Carro getCarroPorId(int id){
        Carro c;
        Iterator<Carro> it = itCarro();
        while (it.hasNext()) {
            c = it.next();
            if (c.getId() == id) {
                return c;
            }
        }
        return null;     
    }
    
    /**
     *Deleta um carro da lista encadeada a partir do seu nome, buscando-o pelo index
     * @param tag TAG do carro a ser removido
     * @return verdadeiro se foi removido com sucecsso
     */
    public boolean deleteCarro(String tag) {
        if(hasCarro(tag)){
           carros.remove(getIndexCarro(tag));
           return true;
        }
        return false;
    }
    
    /**
     *Lista no console a lista de carros cadastradas no sistema no console
     */
    public void listarCarros() {
        Carro c;
        Iterator<Carro> it = itCarro();
        while (it.hasNext()) {
            c = it.next();
            System.out.println(c.getTag());
        }
    }
    
    /**
     *Pega a referência da lista encadeada de carros
     * @return ArrayList de carros cadastrados
     */
    public ArrayList<Carro> getCarros() {
        return carros;
    }
    
    /**
     *Adiciona um jogador a lista encadeada se ele não existir 
     * @param jogador
     * @return
     */
    public boolean addJogador(Jogador jogador) {
        return jogadores.add(jogador);
    }

    /**
     *Percorre a lista encadeada em busca de um jogador
     * @param jogador 
     * @return Verdadeiro caso o jogador já esteja cadastrado
     */
    public boolean hasJogador(Jogador jogador) {
        Jogador jogadorAux;
        Iterator<Jogador> it = itJogadores();
        while(it.hasNext()){
            jogadorAux = it.next();
            if(jogadorAux.equals(jogador)){ return true; }
        }
        return false;
    }
    
    public boolean hasJogador(String jogador) {
        Jogador jogadorAux;
        Iterator<Jogador> it = itJogadores();
        while(it.hasNext()){
            jogadorAux = it.next();
            if(jogadorAux.getPiloto().getNome().equals(jogador)){ return true; }
        }
        return false;
    }    
    
    /**
     *Pega o iterador do ArrayList de jogadores
     * @return Iterador de jogadores
     */
    public Iterator<Jogador> itJogadores() {
        return this.jogadores.iterator();
    }

    /**
     * Pega o jogador a partir do index 
     * @param jogador objeto jogador
     * @return objeto jogador a ser buscado
     */
    public Jogador getJogador(Jogador jogador) {
        if(hasJogador(jogador)){
            return jogadores.get(getIndexJogador(jogador));
        }
        return null;
    }
    
    
    
    /**
     * Percorre a lista encadeada em busca do index do jogador a partir de seu nome
     * @param jogador
     * @return número do index do jogador
     */
    public int getIndexJogador(Jogador jogador){
        Jogador jogadorAux;
        Iterator<Jogador> it = itJogadores();
        int i = 0;
        while(it.hasNext()){
            jogadorAux = it.next();
            if(jogadorAux.equals(jogador)){ return i; }
            i++;
        }
        return 0;
    }
    
    /**
     *Pega o jogador com determinado nome do piloto passado por parâmetro
     * @param nomeDoPiloto nome do piloto
     * @return o objeto piloto caso exista na lista encadeada 
     */
    public Jogador getJogadorPorNomeDoPiloto(String nomeDoPiloto){
        if(hasJogador(nomeDoPiloto)){
            
          Iterator<Jogador> it = itJogadores();
          Jogador jogadorAux;
          
            while(it.hasNext()){
                jogadorAux = it.next();
                if(jogadorAux.getPiloto().getNome().equals(nomeDoPiloto)){ return jogadorAux; }
            }          
        }
        return null;
    }
    
    
    /**
     *Pega o jogador com determinado ID passado por parâmetro
     * @param nomeDoPiloto nome do piloto
     * @return o objeto piloto caso exista na lista encadeada 
     */
    public Jogador getJogadorPorID(int id){
            
          Iterator<Jogador> it = itJogadores();
          Jogador jogadorAux;
          
            while(it.hasNext()){
                jogadorAux = it.next();
                if(jogadorAux.getID() == id){ return jogadorAux; }
            }          
        return null;
    }    
    
    public ArrayList<Jogador> getJogadoresPorArrayDeID(int[] ids){
        ArrayList<Jogador> jogadores = new ArrayList<>();
        for (int id : ids) {
            jogadores.add(getJogadorPorID(id));
        }
        return jogadores;
    }
    /**
     *Deleta um objeto jogador
     * @param jogador a ser removido
     * @return verdadeiro se for removido com sucesso
     */
    public boolean deleteJogador(Jogador jogador){
        if(hasJogador(jogador)){
            jogadores.remove(getIndexJogador(jogador));
            return true;
        }
        return false;
    }
    
    /**
     *Pega a referência da lista encadeada de Jogadores
     * @return ArrayList de jogadores cadastrados
     */
    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
    
    
    /**
     * A partir de um array de nomes de jogadores pré cadastrados no sistema que
     * querem participar da corrida ele joga os Objetos Jogador num array para
     * que seja lançado na corrida atual
     *
     * @param args nome dos jogadores
     * @return ArrayList de jogadores
     */
    public ArrayList<Jogador> selecionarJogadores(String[] args) {
        ArrayList<Jogador> jogadoresDaCorrida = new ArrayList<>();

        for (String a : args) {
            Jogador jogador = getJogadorPorNomeDoPiloto(a);
            if (jogador != null) {
                jogadoresDaCorrida.add(jogador);
            }
        }

        return jogadoresDaCorrida;

    }    
    


}
    
