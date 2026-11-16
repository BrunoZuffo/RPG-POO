package personagens;

public class Monge extends Personagem{

    private static final long serialVersionUID=1L;

    private static final int REGEN_VIDA=3;
    private static final int REGEN_MANA=6;
    private static final int REGEN_STAMINA=8;

    private static final int CUSTO_ATAQUE1=12;
    private static final int CUSTO_ATAQUE2=28;

    public Monge(){
        super(110,115,80,23,18,30,1.05,1.5,
            CUSTO_ATAQUE1,"Stamina",
            CUSTO_ATAQUE2,"Mana",
            REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
    }

    @Override
    public void ataque1(Personagem alvo){
        gastarStamina(CUSTO_ATAQUE1);
        alvo.setVidaAtual(getDanoAtaque1(alvo));
    }

    @Override
    public void ataque2(Personagem alvo){
        gastarMana(CUSTO_ATAQUE2);
        alvo.setVidaAtual(getDanoAtaque2(alvo));
    }

    @Override
    public void regenerarAtributos(){
        recuperarVida(REGEN_VIDA);
        recuperarMana(REGEN_MANA);
        recuperarStamina(REGEN_STAMINA);
    }
}
