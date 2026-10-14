package personagens;

import inventario.Item;
import java.util.List;
import java.util.ArrayList;

public class Personagem{

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

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getNome(){
        return this.nome;
    }

    public Personagem(int vida,int stamina, int mana, int dano, int defesa, int inventario_tamanho){
        this.vida=vida;
        this.vida_atual =vida;
        this.stamina=stamina;
        this.mana=mana;
        this.dano=dano;
        this.defesa=defesa;
        this.inventario_tamanho=inventario_tamanho;
        this.item_usando=Item.MAO;
        this.inventario=new ArrayList<>();
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

    public String adicionarItem(Item item){
        if(getEspacoUsado()+item.getTamanho()>getTamanhoInventario()+item.getArmazenamentoExtra()){
            return "Espaco insuficiente no inventário";
        }
        inventario.add(item);
        return "Espaco coletado";
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
        return inventario;
    }

    public void carregarEstado(int vidaAtual, Item itemUsando, List<Item> inventario) {
        this.vida_atual = vidaAtual;
        this.item_usando = itemUsando;
        this.inventario = new ArrayList<>(inventario);
    }

    public int setVidaAtual(int dano){
        this.vida_atual-=dano;
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
        return "| Vida Atual: "+ getVidaAtual() +" | Stamina Atual: "+ getStaminaAtual() +"| Mana Atual: "+ getManaAtual();
    }
}
