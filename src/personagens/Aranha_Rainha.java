package personagens;

public class Aranha_Rainha extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=12;
    private static final int REGEN_STAMINA=15;
    
    private static final int CUSTO_ATAQUE1=15;
    private static final int CUSTO_ATAQUE2=35;

    public Aranha_Rainha(){
        super(180,150,100,35,18,0);
    }

    @Override //estamos "sobrescrevendo" um metodo herdado da classe mae - personagem
    public void ataque1(Personagem alvo){
        gastarStamina(CUSTO_ATAQUE1);
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.1-alvo.getDefesa());
        if(dano_causado<1){
            dano_causado=1;
        }
        alvo.setVidaAtual(dano_causado);
    }

    @Override 
    public void ataque2(Personagem alvo){
        gastarMana(CUSTO_ATAQUE2);
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.5-alvo.getDefesa());
        if(dano_causado<1){
            dano_causado=1;
        }
        alvo.setVidaAtual(dano_causado);
    }

    @Override
    public void regenerarAtributos() {
        recuperarVida(REGEN_VIDA);
        recuperarMana(REGEN_MANA);
        recuperarStamina(REGEN_STAMINA);
    }
}
