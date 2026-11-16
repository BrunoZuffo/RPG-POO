package personagens;

public class Aranha_Gigante extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=8;
    private static final int REGEN_STAMINA=10;
    
    private static final int CUSTO_ATAQUE1=10;
    private static final int CUSTO_ATAQUE2=20;

    public Aranha_Gigante(){
        super(90,80,50,26,9,0,1.0,1.3,
                CUSTO_ATAQUE1,"Stamina",
                CUSTO_ATAQUE2,"Mana",
                REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
        setNome("Aranha Gigante");
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
