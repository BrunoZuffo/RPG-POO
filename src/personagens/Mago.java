package personagens;

public class Mago extends Personagem {

    private static final int REGEN_VIDA=2; //private pq só mago consegue acessar, static pq todo mago criado terá mesmos atributos definidos aqui, final pq é imutavel
    private static final int REGEN_MANA=15;
    private static final int REGEN_STAMINA=3;
    
    private static final int CUSTO_ATAQUE1=10;
    private static final int CUSTO_ATAQUE2=30;

    public Mago(){
        super(70,20,120,30,10,30,1.0,1.5,
                CUSTO_ATAQUE1,"Mana",
                CUSTO_ATAQUE2,"Mana",
                REGEN_VIDA,REGEN_STAMINA,REGEN_MANA);
    }

    @Override 
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
