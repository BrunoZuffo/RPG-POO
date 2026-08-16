package personagens;

public class Personagem{

    private int vida;
    private int dano;
    private int defesa;

    //construtor da classe personagem
    public Personagem(int vida, int dano, int defesa){
        this.vida=vida;
        this.dano=dano;
        this.defesa=defesa;
    }

    public void ataque1(Personagem alvo){ //ja sabemos quem está atacando, quando formos chamar
        alvo.vida-=this.dano;             // será algo como guerreiro.ataque1(alvo)
                                          // entao é só passar o alvo, e usar o this, para falar que
    }                                     // é o dano de quem chama a funcao

    public void ataque2(Personagem alvo){
        alvo.vida-=this.dano*1.5;
    }

    public int getVida(){
        return this.vida;
    }

    public int getDano(){
        return this.dano;
    }

    public int getDefesa(){
        return this.defesa;
    }



}