package personagens;

public class Dragão_Ancestral extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=15;
    private static final int REGEN_STAMINA=18;
    
    private static final int CUSTO_ATAQUE1=20;
    private static final int CUSTO_ATAQUE2=60;

    public Dragão_Ancestral(){
        super(320,200,180,45,25,0,1.1,1.8,
                CUSTO_ATAQUE1,"Stamina",
                CUSTO_ATAQUE2,"Mana",
                REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
        setNome("Dragão Ancestral");
    }

    @Override //estamos "sobrescrevendo" um metodo herdado da classe mae - personagem
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
