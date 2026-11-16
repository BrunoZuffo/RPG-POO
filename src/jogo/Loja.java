package jogo;

import java.util.Scanner;
import java.util.List;
import inventario.Item;
import personagens.Personagem;
import excecoes.InventarioException;
import excecoes.RecursoInsuficienteException;
import java.util.ArrayList;

public class Loja{

    private Scanner scanner;
    private final List<Item> catalogo=new ArrayList<>();

    public Loja(Scanner scanner){
        this.scanner=scanner;

        //loop para automatizar os itens da loja mesmo que adicionemos no futuro
        for(Item item: Item.values()){
            if(item!=Item.MAO){
                catalogo.add(item);
            }
        }
    }

    public void abrir(Personagem jogador){

        while(true){
            Terminal.limpar();
            System.out.println("\n========== LOJA DE VALENFORD ==========");
            System.out.println("Ouro: "+jogador.getOuro());

            System.out.println("\n1 - Comprar itens");
            System.out.println("2 - Vender itens");
            System.out.println("0 - Voltar");

            System.out.print("\nEscolha: ");
            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==1){
                comprar(jogador);
            }
            else if(escolha==2){
                vender(jogador);
            }
            else if(escolha==0){
                return;
            }
            else{
                System.out.println("Escolha invalida!");
                Terminal.pausar(scanner);
            }
        }
    }

    private void comprar(Personagem jogador){

        while(true){
            Terminal.limpar();
            System.out.println("\n========== COMPRAR ITENS ==========");
            System.out.println("Ouro: "+jogador.getOuro());

            System.out.println("Inventario: "+jogador.getEspacoUsado()
                +"/"+jogador.getTamanhoInventario());

            for(int i=0;i<catalogo.size();i++){

                Item item=catalogo.get(i);

                System.out.println((i+1)+" - "+item.getNome()
                    +" | Preco: "+item.getValor()
                    +" | Dano: "+item.getDano()
                    +" | Cura: "+item.getCura()
                    +" | Espaco: "+item.getTamanho()
                    +" | Armazenamento extra: "+item.getArmazenamentoExtra());
            }

            System.out.println("\n0 - Voltar");

            System.out.print("\nEscolha: ");
            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==0){
                return;
            }

            if(escolha<1 || escolha>catalogo.size()){
                System.out.println("Escolha invalida!");
                Terminal.pausar(scanner);
                continue;
            }

            try{
                Item item=catalogo.get(escolha-1);

                System.out.println(jogador.comprarItem(item));
            }
            catch(InventarioException | RecursoInsuficienteException erro){
                System.out.println(erro.getMessage());
            }
            Terminal.pausar(scanner);
        }
    }

    private void vender(Personagem jogador){

        while(true){
            Terminal.limpar();
            System.out.println("\n========== VENDER ITENS ==========");
            System.out.println("Ouro: "+jogador.getOuro());

            List<Item> itens=jogador.getInventario();

            if(itens.isEmpty()){
                System.out.println("Voce nao possui itens para vender!");
                Terminal.pausar(scanner);
                return;
            }

            for(int i=0;i<itens.size();i++){

                Item item=itens.get(i);

                System.out.println((i+1)+" - "+item.getNome()
                    +" | Valor de venda: "+item.getValor()/2);
            }

            System.out.println("\n0 - Voltar");

            System.out.print("\nEscolha: ");
            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==0){
                return;
            }

            try{
                System.out.println(jogador.venderItem(escolha-1));
            }
            catch(InventarioException erro){
                System.out.println(erro.getMessage());
            }
            Terminal.pausar(scanner);
        }
    }
}