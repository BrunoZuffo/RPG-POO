package personagens;

public class Anão extends Personagem{
    
    private static final int REGEN_VIDA=5;
    private static final int REGEN_MANA=1;
    private static final int REGEN_STAMINA=15;
    
    private static final int CUSTO_ATAQUE1=12;
    private static final int CUSTO_ATAQUE2=30;

    public Anão(){
        super(150,150,20,10,30,20,1.0,1.5,
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
