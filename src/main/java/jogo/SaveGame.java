package jogo;

import java.util.List;
import inventario.Item;

public class SaveGame {

    private String nome;
    private String classe;
    private int vidaAtual;
    private int staminaAtual;
    private int manaAtual;
    private Item itemUsando;
    private List<Item> inventario;

    public SaveGame(String nome, String classe, int vidaAtual,int staminaAtual, int manaAtual, Item itemUsando, List<Item> inventario) {
        this.nome = nome;
        this.classe = classe;
        this.vidaAtual = vidaAtual;
        this.staminaAtual = staminaAtual;
        this.manaAtual = manaAtual;
        this.itemUsando = itemUsando;
        this.inventario = inventario;
    }

    public String getNome() {
        return nome;
    }

    public String getClasse() {
        return classe;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getStaminaAtual(){
        return this.staminaAtual;
    }

    public int getManaAtual(){
        return this.manaAtual;
    }

    public Item getItemUsando() {
        return itemUsando;
    }

    public List<Item> getInventario() {
        return inventario;
    }
}
