package jogo;

import java.util.Scanner;

import personagens.Personagem;
import personagens.Guerreiro;
import personagens.Mago;


public class Jogo {

    private Scanner scanner;

    public Jogo(){
        this.scanner = new Scanner(System.in);
    }

    public void menu(){
        System.out.println("========================= RPG =========================\n\n");
        System.out.println("1 - Iniciar\n");
        System.out.println("2 - Sair\n");
        System.out.println("=======================================================");

        int escolha=scanner.nextInt();
        if(escolha==1){
            iniciar();
        }
        else{
            sair();
        }
    }

    public void iniciar(){
        Personagem jogador;
        Guerreiro guerreiro=new Guerreiro();
        Mago mago=new Mago();

        System.out.println("=======================================================");
        System.out.println("\nEscolha seu personagem:\n\n");
        System.out.println("1- Guerreiro - atributos base: | Vida= "+ guerreiro.getVida()+" | Dano: " + guerreiro.getDano()+" | Defesa: "+ guerreiro.getDefesa()+" |\n\n");
        System.out.println("2- Mago - atributos base: | Vida= "+ mago.getVida()+" | Dano: " + mago.getDano()+" | Defesa: "+ mago.getDefesa()+" |\n\n");

        int escolha=scanner.nextInt();

        scanner.nextLine(); // limpa o Enter que ficou do nextInt()
        String nome=null;
        while(nome==null){
            System.out.println("\nEscolha o nome do seu personagem: ");
            nome=scanner.nextLine();
        }

        jogador=null;
        while(jogador==null){
            if(escolha==1){
                jogador=guerreiro;
            }
            else if(escolha==2){
                jogador=mago;
            }
            else{
                System.out.println("Selecione um valor válido.");
            }
        }
        jogador.setNome(nome);
        System.out.println("\nPersonagem criado com sucesso! \n\nNome: "+ jogador.getNome() +"\nClasse: "+ jogador.getClass().getSimpleName() + "\nAtributos base: | Vida= "+ jogador.getVida()+" | Dano: " + jogador.getDano()+" | Defesa: "+ jogador.getDefesa()+" |\n\n");
        System.out.println("========================================================");
    }

    public void sair(){
        return;
    }

}
