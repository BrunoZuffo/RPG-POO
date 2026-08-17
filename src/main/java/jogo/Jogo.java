package jogo;

import java.util.Scanner;

import personagens.Personagem;
import personagens.Anão;
import personagens.Elfo;
import personagens.Guerreiro;
import personagens.Mago;
import persistencia.GerenciadorSave;

public class Jogo {

    private Scanner scanner;

    public Jogo(){
        this.scanner = new Scanner(System.in);
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
        System.out.println("1- Guerreiro - atributos base: | Vida: "+ guerreiro.getVida()+" | Dano: " + guerreiro.getDano()+" | Defesa: "+ guerreiro.getDefesa()+" |\n\n");
        System.out.println("2- Mago - atributos base: | Vida: "+ mago.getVida()+" | Dano: " + mago.getDano()+" | Defesa: "+ mago.getDefesa()+" |\n\n");
        System.out.println("3- Anão - atributos base: | Vida: "+ anão.getVida()+" | Dano: " + anão.getDano()+" | Defesa: "+ anão.getDefesa()+" |\n\n");
        System.out.println("4- Elfo - atributos base: | Vida: "+ elfo.getVida()+" | Dano: " + elfo.getDano()+" | Defesa: "+ elfo.getDefesa()+" |\n\n");

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
        System.out.println("\nPersonagem criado com sucesso! \n\nNome: "+ jogador.getNome() +"\nClasse: "+ jogador.getClass().getSimpleName() + "\nAtributos base: | Vida= "+ jogador.getVida()+" | Dano: " + jogador.getDano()+" | Defesa: "+ jogador.getDefesa()+" |\n\n");
        System.out.println("========================================================");

        salvarJogo(jogador);
    }

    public void continuar_jogo(){
        SaveGame save = GerenciadorSave.carregar();

        if(save == null){
            return;
        }

        Personagem jogador;

        if(save.getClasse().equals("Guerreiro")){
            jogador = new Guerreiro();
        }
        else if(save.getClasse().equals("Mago")){
            jogador = new Mago();
        }
        else{
            System.out.println("Classe salva desconhecida.");
            return;
        }

        jogador.setNome(save.getNome());
        jogador.carregarEstado(save.getVidaAtual(), save.getItemUsando(), save.getInventario());

        System.out.println("\nJogo carregado com sucesso!");
        System.out.println("Nome: " + jogador.getNome());
        System.out.println("Classe: " + jogador.getClass().getSimpleName());
        System.out.println("Vida atual: " + jogador.getVidaAtual());
        System.out.println("Item equipado: " + jogador.getItemAtual());
    }

    public void sair(){
        return;
    }

    private void salvarJogo(Personagem jogador){
        SaveGame save= new SaveGame(jogador.getNome(), jogador.getClass().getSimpleName(), jogador.getVidaAtual(), jogador.getItemAtual(), jogador.getInventario());
        GerenciadorSave.salvar(save);
    }
}
