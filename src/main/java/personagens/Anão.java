package personagens;

public class Anão extends Personagem{
    
    public Anão(){
        super(150,150,20,10,30,20);
    }

    public void ataque1(Personagem alvo){
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.0-alvo.getDefesa());
        if(dano_causado<0){
            dano_causado=0;
        }
        alvo.setVidaAtual(dano_causado);
    }

    public void ataque2(Personagem alvo){
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*1.5-alvo.getDefesa());
        if(dano_causado<0){
            dano_causado=0;
        }
        alvo.setVidaAtual(dano_causado);
    }
}
