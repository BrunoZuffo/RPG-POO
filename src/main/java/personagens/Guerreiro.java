package personagens;

public class Guerreiro extends Personagem {

    public Guerreiro(){
        super(100,120,20,20,20,45);
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
