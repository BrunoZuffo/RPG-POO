package personagens;

public class Anão extends Personagem{
    
    private static final int REGEN_VIDA=5;
    private static final int REGEN_MANA=1;
    private static final int REGEN_STAMINA=15;
    
    private static final int CUSTO_ATAQUE1=12;
    private static final int CUSTO_ATAQUE2=30;

    public Anão(){
        super(150,150,20,10,30,20);
    }

    @Override //estamos "sobrescrevendo" um metodo herdado da classe mae - personagem
    public void ataque1(Personagem alvo){
        if (!gastarStamina(CUSTO_ATAQUE1)) {
            System.out.println("Stamina insuficiente!");
            return;
        }
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.0-alvo.getDefesa());
        if(dano_causado<0){
            dano_causado=0;
        }
        alvo.setVidaAtual(dano_causado);
    }

    @Override 
    public void ataque2(Personagem alvo){
        if (!gastarStamina(CUSTO_ATAQUE2)) {
            System.out.println("Stamina insuficiente!");
            return;
        }
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.5-alvo.getDefesa());
        if(dano_causado<0){
            dano_causado=0;
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
