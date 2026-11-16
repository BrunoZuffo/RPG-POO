package personagens;

public class Anão extends Personagem{

    private static final long serialVersionUID=1L;
    
    private static final int REGEN_VIDA=3;
    private static final int REGEN_MANA=1;
    private static final int REGEN_STAMINA=9;
    
    private static final int CUSTO_ATAQUE1=13;
    private static final int CUSTO_ATAQUE2=30;

    public Anão(){
        super(135,140,20,19,22,25,1.0,1.55,
                CUSTO_ATAQUE1,"Stamina",
                CUSTO_ATAQUE2,"Stamina",
                REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
    }

    @Override //estamos "sobrescrevendo" um metodo herdado da classe mae - personagem
    public void ataque1(Personagem alvo){
        gastarStamina(CUSTO_ATAQUE1);
        alvo.setVidaAtual(getDanoAtaque1(alvo));
    }

    @Override 
    public void ataque2(Personagem alvo){
        gastarStamina(CUSTO_ATAQUE2);
        alvo.setVidaAtual(getDanoAtaque2(alvo));
    }

    @Override
    public void regenerarAtributos() {
        recuperarVida(REGEN_VIDA);
        recuperarMana(REGEN_MANA);
        recuperarStamina(REGEN_STAMINA);
    }
}
