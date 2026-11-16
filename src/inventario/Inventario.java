//criamos esse Inventario.java para tirar toda a responsabilidade de funcoes do inventario do Personagem.java e deixar as coisas melhores definidas e s=organizadas

package inventario;

import java.util.List;
import java.util.ArrayList;
import excecoes.InventarioException;

public class Inventario{

    private final int capacidadeBase;
    private List<Item> itens;

    public Inventario(int capacidadeBase){

        if(capacidadeBase<0){
            throw new IllegalArgumentException("Capacidade negativa!");
        }

        this.capacidadeBase=capacidadeBase;
        this.itens=new ArrayList<>();
    }

    public int getCapacidade(){

        int capacidadeTotal=capacidadeBase;

        for(Item item: itens){
            capacidadeTotal+=item.getArmazenamentoExtra();
        }

        return capacidadeTotal;
    }

    public int getEspacoUsado(){

        int espacoUsado=0;

        for(Item item: itens){
            espacoUsado+=item.getTamanho();
        }

        return espacoUsado;
    }

    public void adicionarItem(Item item){

        if(item==null){
            throw new IllegalArgumentException("Item nulo!");
        }

        if(getEspacoUsado()+item.getTamanho()>getCapacidade()+item.getArmazenamentoExtra()){
            throw new InventarioException("Espaco insuficiente no inventario!");
        }

        itens.add(item);
    }

    public Item getItem(int indice){

        if(indice<0 || indice>=itens.size()){
            throw new InventarioException("Item invalido!");
        }

        return itens.get(indice);
    }

    public void removerConsumivel(int indice){

        Item item=getItem(indice);

        if(item.getCura()<=0){
            throw new InventarioException("Este item nao e consumivel!");
        }

        removerItem(indice);
    }

    public List<Item> getItens(){
        return List.copyOf(itens);
    }

    public void removerItem(int indice){
        Item item=getItem(indice);

        int espacoRestante=getEspacoUsado()-item.getTamanho();
        int capacidadeRestante=getCapacidade()-item.getArmazenamentoExtra();

        if(espacoRestante>capacidadeRestante){
            throw new InventarioException("Espaco insuficiente para remover este item!");
        }

        itens.remove(indice);
    }

    

}