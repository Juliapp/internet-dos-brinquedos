/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controladores.ControladorDeDados;
import controladores.ControladorFactory;
import model.Carro;
import model.Equipe;
import java.util.Iterator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import clienteCad.CadastroFacade.CadFacade;

/**
 *
 * @author Teeu Guima
 */
public class TesteControladorDeDados {
    ControladorDeDados cdd;
    ControladorFactory ccf;
    CadFacade sf;
    public TesteControladorDeDados() {
        
        this.cdd = new ControladorDeDados();
        this.ccf = new ControladorFactory();
        this.sf = new CadFacade();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testCadastraEquipe(){
       Equipe e = sf.cadastrarEquipe("Topzera");
       assertEquals(e, e);
    }
    
    @Test
    public void testGetNomeEquipe(){
        Equipe e = sf.cadastrarEquipe("Heaisaisa");
        Equipe v = sf.getEquipe("Heaisaisa");
        
        assertEquals(e, v);
    }
    
    @Test
    public void testCadastraCarros(){
      Equipe e = sf.cadastrarEquipe("Velozes");
      Carro c = sf.cadastrarCarro("HHSUAHSUA", "Vermelho", e);
  
      assertEquals(c, c);
        
    }
    
    @Test
    public void testGetCarrosPorCor(){
        Carro c = cdd.getCarroPorCor("Vermelho");
        Carro v = cdd.getCarroPorCor("Vermelho");
        
        assertEquals(v, c);
    }
   
}
