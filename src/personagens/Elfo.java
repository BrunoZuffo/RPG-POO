package personagens;

public class Elfo extends Personagem{

    private static final long serialVersionUID=1L;
    
    private static final int REGEN_VIDA=2;
    private static final int REGEN_MANA=7;
    private static final int REGEN_STAMINA=7;

    private static final int CUSTO_ATAQUE1=12;
    private static final int CUSTO_ATAQUE2=28;

    public Elfo(){
        super(105,110,100,25,17,35,1.0,1.45,
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
    public void regenerarAtributos() {
        recuperarVida(REGEN_VIDA);
        recuperarMana(REGEN_MANA);
        recuperarStamina(REGEN_STAMINA);
    }
}