package personagens;

public class Druida extends Personagem{

    private static final long serialVersionUID=1L;

    private static final int REGEN_VIDA=4;
    private static final int REGEN_MANA=9;
    private static final int REGEN_STAMINA=3;

    private static final int CUSTO_ATAQUE1=12;
    private static final int CUSTO_ATAQUE2=31;

    public Druida(){
        super(105,30,130,23,16,35,1.0,1.5,
            CUSTO_ATAQUE1,"Mana",
            CUSTO_ATAQUE2,"Mana",
            REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
    }

    @Override
    public void ataque1(Personagem alvo){
        gastarMana(CUSTO_ATAQUE1);
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
