
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
    private int inventario_tamanho;
    private Item item_usando;
    private List<Item> inventario=new ArrayList<>();

    public void setNome(String nome){
        this.nome=nome;
    }
    
    public String getNome(){
        return this.nome;
    }

    //construtor da classe personagem
    public Personagem(int vida, int dano, int defesa, int inventario_tamanho){
        this.vida=vida;
        this.vida_atual=vida;
        this.dano=dano;
        this.defesa=defesa;
        this.inventario_tamanho=inventario_tamanho;
        this.item_usando=Item.MAO;
        this.inventario=new ArrayList<>();
    }

    public void ataque1(Personagem alvo){
        //arredondando o valor usando Math.round para o inteiro mais proximo e convertendo pra int
        int dano_causado=(int)Math.round((this.item_usando.getDano()+this.dano)*1.0-alvo.defesa);
        if(dano_causado<0){
            dano_causado=0;
        }                        //ja sabemos quem está atacando, quando formos chamar
        alvo.vida-=dano_causado; // será algo como guerreiro.ataque1(alvo)
                                 // entao é só passar o alvo, e usar o this, para falar que
    }                            // é o dano de quem chama a funcao

    public void ataque2(Personagem alvo){
        int dano_causado=(int)Math.round((this.item_usando.getDano()+this.dano)*1.5-alvo.defesa);
        if(dano_causado<0){
            dano_causado=0;
        }
        alvo.vida-=dano_causado;
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


}