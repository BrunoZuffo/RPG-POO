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

            String formato="%-3s | %-24s | %5s | %4s | %4s | %6s | %5s%n";
            //3 caracteres a esquerda, 24 a esquerda, 5 a direita, 4 a direita, 6 a direita, %n quebra de linha
            System.out.printf(formato,
                "N",
                "Item",
                "Preco",
                "Dano",
                "Cura",
                "Espaco",
                "Extra");

            System.out.println("-".repeat(69));

            for(int i=0;i<catalogo.size();i++){

                Item item=catalogo.get(i);

                System.out.printf(formato,
                    i+1,
                    item.getNome(),
                    item.getValor(),
                    item.getDano(),
                    item.getCura(),
                    item.getTamanho(),
                    item.getArmazenamentoExtra());
            }

            System.out.println("-".repeat(69));

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

            String formato="%-3s | %-24s | %14s%n";

            System.out.printf(formato,"N","Item","Valor de venda");

            System.out.println("-".repeat(47));

            for(int i=0;i<itens.size();i++){

                Item item=itens.get(i);

                System.out.printf(formato,
                    i+1,
                    item.getNome(),
                    item.getValor()/2);
            }

            System.out.println("-".repeat(47));

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