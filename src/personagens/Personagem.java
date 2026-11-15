package personagens;

import inventario.Item;
import java.util.List;
import java.util.ArrayList;
import excecoes.RecursoInsuficienteException;
import excecoes.InventarioException;

public abstract class Personagem{ //abstract para podermos fazer abstract ataque 1 e 2 pra cada personagem ter o seu

    private String nome;
    private int vida;
    private int vida_atual;
    private int dano;
    private int defesa;
    private int stamina;
    private int staminaAtual;
    private int mana;
    private int manaAtual;
    private int inventario_tamanho;
    private Item item_usando;
    private List<Item> inventario=new ArrayList<>();
    private double multiplicadorAtaque1;
    private double multiplicadorAtaque2;
    private final int custoAtaque1;
    private final int custoAtaque2;
    private final String recursoAtaque1;
    private final String recursoAtaque2;
    private final int regenVida;
    private final int regenStamina;
    private final int regenMana;
    private int nivel=1;
    private int experiencia=0;
    private int ouro=0;

    public void setNome(String nome){
        if(nome==null || nome.isBlank()){
            throw new IllegalArgumentException("Nome inválido!");
        }
        this.nome=nome;
    }

    public String getNome(){
        return this.nome;
    }

    public Personagem(int vida, int stamina, int mana, int dano, int defesa, int inventario_tamanho, double multiplicadorAtaque1, double multiplicadorAtaque2, int custoAtaque1, String recursoAtaque1, int custoAtaque2, String recursoAtaque2, int regenVida, int regenStamina, int regenMana){
        this.vida=vida;
        this.vida_atual =vida;
        this.stamina=stamina;
        this.mana=mana;
        this.manaAtual=mana; //na criação do personagem mana é a total
        this.staminaAtual=stamina; // idem
        this.dano=dano;
        this.defesa=defesa;
        this.inventario_tamanho=inventario_tamanho;
        this.item_usando=Item.MAO;
        this.inventario=new ArrayList<>();
        this.multiplicadorAtaque1=multiplicadorAtaque1;
        this.multiplicadorAtaque2=multiplicadorAtaque2;
        this.custoAtaque1=custoAtaque1;
        this.custoAtaque2=custoAtaque2;
        this.recursoAtaque1=recursoAtaque1;
        this.recursoAtaque2=recursoAtaque2;
        this.regenVida=regenVida;
        this.regenStamina=regenStamina;
        this.regenMana=regenMana;
        //nao precisamos desses 3 pq ja inicializamos com valores la atras, como //private int nivel=1, etc
        //this.nivel=nivel;
        //this.experiencia=experiencia;
        //this.ouro=ouro;
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

    public int getTamanhoInventario(){
        int inventario_TamanhoTotal=this.inventario_tamanho;
        for(Item item: inventario){
            inventario_TamanhoTotal+=item.getArmazenamentoExtra();
        }
        return inventario_TamanhoTotal;
    }

    public int getEspacoUsado(){
        int espacoUsado=0;
        for(Item item: inventario){
            espacoUsado+=item.getTamanho();
        }
        return espacoUsado;
    }    

    public int getVidaAtual(){
        return this.vida_atual;
    }

    public Item getItemAtual(){
        return this.item_usando;
    }

    public List<Item> getInventario() {
        return List.copyOf(inventario); //se retornarmos somente o inventario podemos acessar o inventario indiretamente e adicionar coisas la
    }

    public int setVidaAtual(int dano){
        if(dano<0){
            throw new IllegalArgumentException("Dano negativo!");
        }

        if((this.vida_atual-dano)<0){
            this.vida_atual=0;
        }
        else{
            this.vida_atual-=dano;
        }
        return this.vida_atual;
    }

    public int getStamina(){
        return this.stamina;
    }

    public int getMana(){
        return this.mana;
    }

    public int getManaAtual(){
        return this.manaAtual;
    }

    public int getStaminaAtual(){
        return this.staminaAtual;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() +"- atributos base: | Vida: "+ getVida()+" | Dano: " + getDano()+" | Defesa: "+ getDefesa()+" | Stamina: "+ getStamina() +" | Mana: "+ getMana() +" |\n\n";
    }
    
    public String getAtributosAtuais(){
        return "Vida: "+getVidaAtual()+"/"+getVida()
            +" | Stamina: "+getStaminaAtual()+"/"+getStamina()
            +" | Mana: "+getManaAtual()+"/"+getMana();
    }

    public abstract void ataque1(Personagem alvo); //vamos retornar void pq estamos alterando a vida do inimigo diretamente na nossa logica ao inves d decrementar algum valor da vidaAtual do inimigo

    public abstract void ataque2(Personagem alvo);

    protected void recuperarMana(int quantidade){
        if(quantidade<0){
            throw new IllegalArgumentException("Recuperação negativa de mana!");
        }


        manaAtual=Math.min(mana,quantidade+manaAtual); // evitamos ultrapassar a mana máxima
    }

    protected void recuperarStamina(int quantidade){
        if(quantidade<0){
            throw new IllegalArgumentException("Recuperação negativa de stamina!");
        }
        staminaAtual=Math.min(stamina,quantidade+staminaAtual);
    }

    protected void recuperarVida(int quantidade){
        if(quantidade<0){
            throw new IllegalArgumentException("Recuperação negativa de vida");
        }
        vida_atual=Math.min(vida,quantidade+vida_atual);
    }

    public abstract void regenerarAtributos(); // no jogo vamos recuperar os atributos de todos por turno, public facilita

    protected void gastarMana(int quantidade) {
        if(quantidade<0){
            throw new IllegalArgumentException("Quantidade negativa de mana!");
        }
        if(manaAtual<quantidade){
            throw new RecursoInsuficienteException("Mana insuficiente!");
        }

        manaAtual-=quantidade;
    }

    protected void gastarStamina(int quantidade) {
        if(quantidade<0){
            throw new IllegalArgumentException("Quantidade negativa de Stamina!");
        }
        if(staminaAtual<quantidade){
            throw new RecursoInsuficienteException("Stamina insuficiente!");
        }

        staminaAtual-=quantidade;
    }

    public boolean estaVivo(){
        return this.vida_atual>0; //se retorna 1 está vivo, true, caso contrário, false, 0
    }

    public double getMultiplicadorAtaque1(){
        return this.multiplicadorAtaque1;
    }

    public double getMultiplicadorAtaque2(){
        return this.multiplicadorAtaque2;
    }

    public int getDanoAtaque1(Personagem alvo){
        return calcularDano(alvo,this.multiplicadorAtaque1);
    }

    public int getDanoAtaque2(Personagem alvo){
        return calcularDano(alvo,this.multiplicadorAtaque2);
    }

    private int calcularDano(Personagem alvo,double multiplicador){
        int dano_causado=(int)Math.round((this.getItemAtual().getDano()+this.getDano())*multiplicador-alvo.getDefesa());

        if(dano_causado<1){
            dano_causado=1;
        }

        return dano_causado;
    }

    public String getCustoAtaque1(){
        return custoAtaque1+" de "+recursoAtaque1;
    }

    public String getCustoAtaque2(){
        return custoAtaque2+" de "+recursoAtaque2;
    }

    public String getRegeneracao(){
        return "Vida: ate +"+regenVida
            +" | Stamina: ate +"+regenStamina
            +" | Mana: ate +"+regenMana;
    }

    public String usarItem(int indice){
        if(indice<0 || indice>=inventario.size()){
            throw new InventarioException("Item invalido!");
        }

        Item item=inventario.get(indice);

        //verificando se o item e consumivel
        if(item.getCura()>0){

            if(getVidaAtual()==getVida()){
                return "Sua vida ja esta cheia!";
            }

            int vidaAntes=getVidaAtual();

            recuperarVida(item.getCura());
            inventario.remove(indice);

            return "Voce recuperou "+(getVidaAtual()-vidaAntes)+" de vida!";
        }

        //verificando se o item e uma arma
        if(item.getDano()>0){
            this.item_usando=item;

            return "Voce equipou: "+item.getNome();
        }

        return "Este item nao precisa ser equipado!";
    }

    public String adicionarItem(Item item){
        if(item==null){
            throw new IllegalArgumentException("Item nulo!");
        }

        if(getEspacoUsado()+item.getTamanho()>getTamanhoInventario()+item.getArmazenamentoExtra()){
            throw new InventarioException("Espaco insuficiente no inventario!");
        }

        inventario.add(item);

        return "Item coletado: "+item.getNome();
    }

    public int getNivel(){
        return this.nivel;
    }

    public int getExperiencia(){
        return this.experiencia;
    }

    public int getOuro(){
        return this.ouro;
    }

    public int getXpParaProximoNivel(){
        return 100+50*(getNivel()-1);
    }

    private void subirNivel(){
        nivel++;

        int aumentoVida=(int)Math.round(vida*0.15);
        int aumentoMana=(int)Math.round(mana*0.10);
        int aumentoStamina=(int)Math.round(stamina*0.10);

        //aumentando os atributos maximos
        vida+=aumentoVida;
        mana+=aumentoMana;
        stamina+=aumentoStamina;

        dano+=(int)Math.round(dano*0.15);
        defesa+=(int)Math.round(defesa*0.10);

        //aumentando tambem os atributos atuais
        vida_atual+=aumentoVida;
        manaAtual+=aumentoMana;
        staminaAtual+=aumentoStamina;
    }

    public void ganharExperiencia(int quantidade){
        if(quantidade<0){
            throw new IllegalArgumentException("Experiencia negativa!");
        }

        experiencia+=quantidade;

        //ja que podemos subir mais d 1 nivel de uma vez, temos que usar laco de repeticao
        while(experiencia>=getXpParaProximoNivel()){
            //gastando o xp
            experiencia-=getXpParaProximoNivel();

            subirNivel();
        }
    }
    
    public void ganharOuro(int quantidade){
        if(quantidade<0){
            throw new IllegalArgumentException("Quantidade negativa de ouro!");
        }
        ouro+=quantidade;
    }

    //semelhante ao get atributos atuais
    public String getProgresso(){
        return "Nivel: "+nivel
            +" | XP: "+experiencia+"/"+getXpParaProximoNivel()
            +" | Ouro: "+ouro;
    }

}
