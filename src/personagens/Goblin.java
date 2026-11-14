package personagens;

public class Goblin extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=0;
    private static final int REGEN_STAMINA=8;
    
    private static final int CUSTO_ATAQUE1=8;
    private static final int CUSTO_ATAQUE2=20;

    public Goblin(){
        super(40,60,0,16,5,0,1.0,1.3);
        setNome("Goblin"); //todos os bots precisam disso para terem nomes
        //os nossos personagens criados nao pq o nome é dado por nós no menu também com setNome
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
