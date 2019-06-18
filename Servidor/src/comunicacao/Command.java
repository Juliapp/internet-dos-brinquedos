package comunicacao;

import java.io.Serializable;

/**
 *
 * @author juli
 */
public enum Command implements Serializable {

    /**
     *
     */
    CadPiloto, 

    /**
     *
     */
    CadCarro, 

    /**
     *
     */
    CadJogador, 

    /**
     *
     */
    CadEquipe, 

    /**
     *
     */
    RemPiloto,

    /**
     *
     */
    RemCarro,

    /**
     *
     */
    RemParticipante,

    /**
     *
     */
    RemEquipe,

    /**
     *
     */
    EditPiloto,

    /**
     *
     */
    EditCarro,

    /**
     *
     */
    EditParticipante,

    /**
     *
     */
    EditEquipe,

    /**
     *
     */
    EditCorrida,
    
    /**
     *
     */
    ListaEquipes,
    
    /**
     *
     */
    ListaCarros,
    
    /**
     *
     */
    ListaJogadores,

    /**
     *
     */
    ComecarCorrida,

    /**
     *
     */
    PararCorrida,
    
    /**
     *
     */
    IterarCarros,
    
     /**
     *
     */
    IterarJogadores,
    
    /**
     *
     */
    PreConfiguracaoCorrida,
    
    /**
     *
     */
    EnviarTags,
   
    /**
     *
     */
    StatusCorrida,
    
    
}
