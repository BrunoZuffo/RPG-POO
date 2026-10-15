package jogo;

import java.util.Scanner;
import java.util.InputMismatchException;

import personagens.Personagem;
import personagens.Anão;
import personagens.Elfo;
import personagens.Guerreiro;
import personagens.Mago;

public class Jogo {

    private Scanner scanner;

    public Jogo(){
        this.scanner = new Scanner(System.in);
    }

    private int lerInteiro() {
        while (true) {
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException erro) {
                System.out.println("Entrada invalida! Digite um numero inteiro.");
                scanner.nextLine();
            }
        }
    }

    public void menu(){
        System.out.println("========================= RPG =========================\n\n");
        System.out.println("1 - Continuar jogo\n");
        System.out.println("2 - Comecar novo jogo\n");
        System.out.println("3 - Sair\n");
        System.out.println("=======================================================");

        int escolha=scanner.nextInt();
        if(escolha==1){
            continuar_jogo();
        }
        else if(escolha==2){
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
        Anão anão=new Anão();
        Elfo elfo=new Elfo();

        System.out.println("=======================================================");
        System.out.println("\nEscolha seu personagem:\n\n");
        System.out.println("1- "+guerreiro.toString());
        System.out.println("2- "+mago.toString());
        System.out.println("3- "+anão.toString());
        System.out.println("4- "+elfo.toString());

        jogador=null;
        
        while(jogador==null){
            int escolha=scanner.nextInt();
            if(escolha==1){
                jogador=guerreiro;
            }
            else if(escolha==2){
                jogador=mago;
            }
            else if(escolha==3){
                jogador=anão;
            }
            else if(escolha==4){
                jogador=elfo;
            }
            else{
                System.out.println("Selecione um valor válido.");
            }
        }

        scanner.nextLine(); // limpa o Enter que ficou do nextInt()
        String nome=null;
        System.out.println("\nEscolha o nome do seu personagem: ");
        while(nome==null || nome.isBlank()){
            nome=scanner.nextLine();
        }

        jogador.setNome(nome);
        System.out.println("\nPersonagem criado com sucesso!\n"+jogador.getNome()+"- "+jogador.toString());

        System.out.println("========================================================");
    }

    public void continuar_jogo(){
        
    }

    public void sair(){
        return;
    }

}
