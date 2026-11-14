package personagens;

public class Necromante extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=15;
    private static final int REGEN_STAMINA=3;
    
    private static final int CUSTO_ATAQUE1=15;
    private static final int CUSTO_ATAQUE2=40;

    public Necromante(){
        super(80,30,120,32,8,0,1.0,1.5,
                CUSTO_ATAQUE1,"Mana",
                CUSTO_ATAQUE2,"Mana",
                REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
        setNome("Necromante");
    }

    @Override //estamos "sobrescrevendo" um metodo herdado da classe mae - personagem
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
    public void regenerarAtributos() {
        recuperarVida(REGEN_VIDA);
        recuperarMana(REGEN_MANA);
        recuperarStamina(REGEN_STAMINA);
    }
}
