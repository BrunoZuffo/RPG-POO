package personagens;

public class Aranha_Rainha extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=12;
    private static final int REGEN_STAMINA=15;
    
    private static final int CUSTO_ATAQUE1=15;
    private static final int CUSTO_ATAQUE2=35;

    public Aranha_Rainha(){
        super(120,150,100,28,10,0,1.1,1.5,
                CUSTO_ATAQUE1,"Stamina",
                CUSTO_ATAQUE2,"Mana",
                REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
        setNome("Aranha Rainha");
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
