package personagens;

public class Ogro extends Personagem{
    
    private static final int REGEN_VIDA=0;
    private static final int REGEN_MANA=0;
    private static final int REGEN_STAMINA=10;
    
    private static final int CUSTO_ATAQUE1=15;
    private static final int CUSTO_ATAQUE2=40;

    public Ogro(){
        super(120,120,0,30,16,0);
    }

    @Override //estamos "sobrescrevendo" um metodo herdado da classe mae - personagem
    public void ataque1(Personagem alvo){
        gastarStamina(CUSTO_ATAQUE1);
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.0-alvo.getDefesa());
        if(dano_causado<1){
            dano_causado=1;
        }
        alvo.setVidaAtual(dano_causado);
    }

    @Override 
    public void ataque2(Personagem alvo){
        gastarStamina(CUSTO_ATAQUE2);
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.6-alvo.getDefesa());
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
