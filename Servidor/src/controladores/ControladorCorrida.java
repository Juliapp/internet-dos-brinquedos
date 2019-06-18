package controladores;

import execoes.CorridaNaoIniciadaException;
import java.util.ArrayList;
import java.util.Iterator;
import execoes.TagInvalidaException;
import execoes.VoltaInvalidaException;
import model.Carro;
import util.Cronometro;
import model.Jogador;
import model.TagColetada;
import model.Time;

public class ControladorCorrida {
    private ArrayList<Jogador> jogadores;
    private String id;
    private int quantidadeDeVoltas;
    private Jogador jogadorDavoltaMaisRapida; 
    private Cronometro cronometro;
    private boolean rodando;
    private boolean corridaConcluida;
    
    /**
     *Instanciação de um controlador de corrida
     * @param jogadores ArrayList com os jogadores que irão participar da corrida
     * @param quantidadeDeVoltas número total de voltas
     */
    public ControladorCorrida(ArrayList<Jogador> jogadores, int quantidadeDeVoltas) {
        this.jogadores = jogadores;
        this.quantidadeDeVoltas = quantidadeDeVoltas;
        rodando = false;
        corridaConcluida = false;
    }

    public ControladorCorrida() {
    }
    

    
 
    
    /**
     *Da o start no cronometro e permite os outros métodos para controlar a corrida serem manipulados
     * @return 
     */
    /*
    public void comecarCorrida(){
        //rodar o cronometro
        cronometro.comecar();
      
        *******************************************
         LEMBRAR DE COLOCAR UM CONTADORZINHO PRA FAZER A CONTAGEM REGRESSIVA PRA A PARTIDA COMEÇAR
         COLOCAR MÚSIQUINHA DE START TAMBÉM É UMA OPÇÃO MUITO BEM-VINDA 
        *******************************************
        
        if(!corridaConcluida){
            rodando = true;
            cronometro.start();
        }
    }
  */
    
    /**
     *Da o start no cronometro e permite os outros métodos para controlar a corrida serem manipulados
     * @return 
     */
    public boolean iniciarCorrida(){
        return this.rodando = true;
    }
    
    public boolean getStatus(){
        return this.rodando;
    }
    
    /**
     *força a parada da thread do cronometro e seta a corrida rodando para false
     */
    
    public void pararCorrida(){
        rodando = false;
        cronometro.stop();
    }
    
    private Jogador getJogadorPorTag(String tag){
        Carro c;
        Jogador jogador;
        
        Iterator<Jogador> it = jogadores.iterator();
        while (it.hasNext()) {
            jogador = it.next();
            c = jogador.getCarro();
            if(c.getTag().equals(tag)) { return jogador; }
        }
        return null;    
    }
    
    /**
     *Tratamento da tag para ser validada e introduzida na corrida
     * @param tag tag coletada do sensor
     * @param voltaComputada objeto Time coletado pelo sensor
     * @throws TagInvalidaException caso a tag não foi cadastrada no sistema ou na partida atual
     * @throws execoes.VoltaInvalidaException caso a volta não foi instanciada ainda
     * @throws execoes.CorridaNaoIniciadaException
     */
    public void pushTag(TagColetada tag, Time voltaComputada) throws TagInvalidaException, VoltaInvalidaException, CorridaNaoIniciadaException {
        if(rodando && !corridaConcluida){
            Jogador jogador = getJogadorPorTag(tag.getTag());

            if(jogador != null){

                /************FAZENDO A VERIFICAÇÃO PRA VALIDAR A VOLTA********************/

                if(validarVolta(jogador,voltaComputada)){
                    jogador.setUltimaVoltaComputada(voltaComputada);
                    verificaMenorVoltaDoJogador(jogador,voltaComputada);
                    verificaMenorVoltaDaCorrida(jogador);
                    jogador.completouVolta();
                    ordenaCorrida(jogador);            
                }else{ throw new VoltaInvalidaException("O sensor coletou uma volta inválida e não foi computada no sistema");}

            }else{throw new TagInvalidaException("A tag não está cadastrada no sistema ou na corrida atual");}
            
        }else{throw new CorridaNaoIniciadaException("A corrida já foi finalizada ou não foi inicializada");}
    }
    
    /**
     *Para a volta ser válida o sensor deve coletar a mesma tag no mínimo 6 segundos após a ultima passagem do mesmo jogador
     * @param joador jogador pertencene a tag que foi coletada
     * @param voltaComputada o tempo em que a tag foi coletada a partir do cronometro interno
     * @return verdadeiro caso a volta seja válida
     */
    private boolean validarVolta(Jogador joador, Time voltaComputada){
        double ultimo = joador.getUltimaVoltaComputada().transformarEmMilisegundos();
        double agora = voltaComputada.transformarEmMilisegundos();
        
        return ultimo <= agora - 6000;
    }
    
    /**
     *Verifica se a tag coletada é a volta mais rápida do jogador, e muda se for verdadeira
     * ela também implemeta a primeira volta do jogador
     * è setado a variação da volta como volta mais rápida
     * @param jogador pertencene a tag que foi coletada
     * @param voltaComputada o tempo em que a tag foi coletada a partir do cronometro interno
     */
    private void verificaMenorVoltaDoJogador(Jogador jogador, Time voltaComputada){
        double ultimo = jogador.getVoltaMaisRapida().transformarEmMilisegundos();
        double agora = voltaComputada.transformarEmMilisegundos();
        
        if(ultimo == 0 || ultimo > agora){
            jogador.setVoltaMaisRapida(jogador.getVoltaMaisRapida().deltaTime(voltaComputada));
        }

    }
    
    /**
     *Veriica se o jogador que acabou de passar fez uma volta mais rápida que a registrada
     * @param jogadorAtual jogador coletado pela tag
     */
    private void verificaMenorVoltaDaCorrida(Jogador jogadorAtual){
        double ultimo = jogadorDavoltaMaisRapida.getVoltaMaisRapida().transformarEmMilisegundos();
        double agora = jogadorAtual.getVoltaMaisRapida().transformarEmMilisegundos();
        
        if(ultimo == 0 || ultimo < agora){
            jogadorDavoltaMaisRapida = jogadorAtual;
        }

    }

    /**
     *Pega o ArrayList de todos os jogadoress cadastrados na corrida
     * @return ArrayList de jogadores
     */
    public ArrayList<Jogador> getJogadoresDaCorridaAtual() {
        return jogadores;
    }

    /**
     *Pega o jogador que fez a volta mais rápida da partida
     * @return Jogador de volta mais rápida
     */
    public Jogador getJogadorDavoltaMaisRapida() {
        return jogadorDavoltaMaisRapida;
    }

    /**
     *pega a referência do cronometro atual
     * @return
     */
    public Cronometro getCronometro() {
        return cronometro;
    }
    
    /**
     *Anuncia que o jogador fez um Pit Stop
     * @param jogador
     */
    public void pitStop(Jogador jogador){
        jogador.pitStop();
    }
    
    /**
     *O método de ordenação primeiramente remove o jogador da lista e jogadores do jogo, e depois o realoca 
     * para a posição logo após o ultimo jogador que passou na mesma volta que ele acabou de dar. 
     * Sendo assim, caso ninguém tenha passado naquela volta ainda e o jogador atual foi o pioneiro, ele sera realocado para 
     * o primeiro lugar do array.
     * Ele também chama o método para averiguar se a corrida já terminou
     * @param jogador jogador coletado
     */
    private void ordenaCorrida(Jogador jogador){
        jogadores.remove(jogador);
        
        Jogador jogadorAux;
        int posicao = 0;
        int volta = jogador.getVolta();
        
        Iterator<Jogador> it = jogadores.iterator();
        while (it.hasNext()) {
            jogadorAux = it.next();
            if(volta >= jogadorAux.getVolta()) { 
                posicao++; 
                
            }
            else{ break; }
        }
        
        jogadores.add(posicao, jogador);
        criterioDeParada(jogador, posicao);
    }

    /**
     * Checa se o jogador ficou em ultima posição e se completou a quantidade de voltas précadastradas
     * @param jogador jogador coletado
     * @param posicao posição do jogador na corrida
     */
    private void criterioDeParada(Jogador jogador, int posicao){
        if(jogador.getVolta() == quantidadeDeVoltas && posicao == jogadores.size() -1){
            pararCorrida();
            corridaConcluida = true;
        }
    }
    
}
