package personagens;

public class Lobo extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=0;
    private static final int REGEN_STAMINA=15;
    
    private static final int CUSTO_ATAQUE1=10;
    private static final int CUSTO_ATAQUE2=25;

    public Lobo(){
        super(64,90,0,22,5,0,1.0,1.4,
                CUSTO_ATAQUE1,"Stamina",
                CUSTO_ATAQUE2,"Stamina",
                REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
        setNome("Lobo");
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
