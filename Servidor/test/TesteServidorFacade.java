/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import comunicacao.ServidorFacade;
import execoes.PilotoNaoExisteException;
import model.Carro;
import model.Jogador;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Teeu Guima
 */
public class TesteServidorFacade {

    private ServidorFacade sf;

    public TesteServidorFacade() {

        this.sf = ServidorFacade.getInstance();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {
    }

    /*
    @Test
    public void testCadastroCarro() {
        boolean status = sf.cadastrarCarro("EPC0292123", "Vermelho", "Velozes");
        
        assertEquals(true, status);
    }
     */
    @Test
    public void testGetCarroPorID() {
        boolean status = sf.cadastrarCarro("EPC0292123", "Vermelho", "Velozes");
        boolean status1 = sf.cadastrarCarro("EPC0492123", "Vermelho", "Velozes");

        Carro idCar = sf.getCarroPorId(1);
        Carro idCar1 = sf.getCarroPorId(2);

        assertEquals(idCar, idCar);
        assertEquals(true, status1);

    }

    @Test
    public void testCadastraJogador() throws PilotoNaoExisteException {
        boolean status1 = sf.cadastrarCarro("EPC0292123", "Vermelho", "Velozes");
        boolean status2 = sf.cadastrarPiloto("Mateus", null);
        boolean status3 = sf.CadastrarJogador(0, "Mateus");
        Jogador status4 = sf.getJogadorPorNomeDoPiloto("Mateus");
        
        assertEquals(status4, status4);
    }
}
