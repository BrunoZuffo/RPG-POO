package personagens;

import personagens.Personagem;
import inventario.Item;

public class Mago extends Personagem {

    private static final int REGEN_VIDA=2; //private pq só mago consegue acessar, static pq todo mago criado terá mesmos atributos definidos aqui, final pq é imutavel
    private static final int REGEN_MANA=15;
    private static final int REGEN_STAMINA=3;

    public Mago(){
        super(70,20,120,30,10,30);
    }

    @Override 
    public void ataque1(Personagem alvo){
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.0-alvo.getDefesa());
        if(dano_causado<0){
            dano_causado=0;
        }
        alvo.setVidaAtual(dano_causado);
    }

    @Override 
    public void ataque2(Personagem alvo){
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.5-alvo.getDefesa());
        if(dano_causado<0){
            dano_causado=0;
        }
        alvo.setVidaAtual(dano_causado);
    }

    @Override
    public void regenerarAtributos() {
        recuperarVida(REGEN_VIDA);
        recuperarMana(REGEN_MANA);
        recuperarStamina(REGEN_STAMINA);
    }
}
