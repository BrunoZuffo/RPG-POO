//nesse arquivo vamos só reunir todas as informações que precisamos para voltar com a partida a partir de um certo ponto
package jogo;

import java.io.Serializable;
import personagens.Personagem;

public class EstadoJogo implements Serializable{

    private static final long serialVersionUID=1L;

    private final Personagem jogador;
    private final int etapaFloresta;
    private final int etapaNorte;

    public EstadoJogo(Personagem jogador,int etapaFloresta,int etapaNorte){
        this.jogador=jogador;
        this.etapaFloresta=etapaFloresta;
        this.etapaNorte=etapaNorte;
    }

    //tudo incluso de itens e etc
    public Personagem getJogador(){
        return jogador;
    }

    //saber onde estamos
    public int getEtapaFloresta(){
        return etapaFloresta;
    }
    
    public int getEtapaNorte(){
        return etapaNorte;
    }
}