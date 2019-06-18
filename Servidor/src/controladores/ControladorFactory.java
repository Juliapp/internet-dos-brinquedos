package controladores;

import java.util.List;
import model.Carro;
import model.Equipe;
import model.Jogador;
import model.Piloto;

public class ControladorFactory {

    /**
     *Factory é um projeto de sistema que "fabrica" objeto e devolve suas referências 
     */
    public ControladorFactory() {} 
    
    /**
     *Fabrica um objeto do tipo Carro
     * @param tag Tag do carro
     * @param cor cor do caro
     * @param equipe equipe qual o carro recebe
     * @return carro fabricado
     */
    public Carro factoryC(String tag, String cor, Equipe equipe){
        return new Carro(tag, cor, equipe);
    }    
    
    /**
     *Fabrica um objeto do tipo Equipe
     * @param nome nome da Equipe
     * @return equipe fabricada
     */
    public Equipe factoryE(String nome){
        return new Equipe(nome);
    }
    
    /**
     *Fabrica um objeto do tipo Piloto
     * @param nome nome do piloto
     * @param foto foto do piloto
     * @return piloto fabricado
     */
    public Piloto factoryP(String nome, String foto){
        return new Piloto(nome, foto);
    }
    
    /**
     *Fabrica um objeto do tipo Jogador
     * @param carro carro do joador
     * @param piloto piloto
     * @return jogador fabricado 
     */
    public Jogador factoryJog(Carro carro, Piloto piloto) {
        return new Jogador(carro, piloto);
    }
    
    
    
}
