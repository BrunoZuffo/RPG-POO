package jogo;

import java.util.Scanner;

import personagens.Personagem;
import personagens.Anão;
import personagens.Elfo;
import personagens.Guerreiro;
import personagens.Mago;
import personagens.Goblin;
import personagens.Lobo;
import personagens.Aranha_Gigante;
import personagens.Aranha_Rainha;
import personagens.Dragão_Ancestral;
import personagens.Gigante_de_Pedra;
import personagens.Necromante;
import personagens.Ogro;
import personagens.Orc;

public class Jogo {

    private Scanner scanner;
    private Personagem jogador;

    public Jogo(){
        this.scanner = new Scanner(System.in);
        this.jogador = null; //fazendo isso podemos acessar jogador a qualquer momento, antes iniciavamos ele só no iniciar()
    }

    public void menu(){
        System.out.println("========================= RPG =========================\n\n");
        System.out.println("1 - Continuar jogo\n");
        System.out.println("2 - Comecar novo jogo\n");
        System.out.println("3 - Sair\n");
        System.out.println("=======================================================");

        int escolha=Entrada.lerInteiro(scanner);
        while(escolha!=1&&escolha!=2&&escolha!=3){
            System.out.println("Digite um valor válido!");       
            escolha=Entrada.lerInteiro(scanner);     
        }
        if(escolha==1){
                continuar_jogo();
            }
            else if(escolha==2){
                iniciar();
            }
            else if(escolha==3){
                sair();
            }
    }

    public void iniciar(){
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
            int escolha=Entrada.lerInteiro(scanner);
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

        scanner.nextLine(); // limpa o Enter que ficou do nextInt() do lerInteiro()
        String nome=null;
        System.out.println("\nEscolha o nome do seu personagem: ");
        while(nome==null || nome.isBlank()){
            nome=scanner.nextLine();
        }

        jogador.setNome(nome);
        System.out.println("\nPersonagem criado com sucesso!\n"+jogador.getNome()+"- "+jogador.toString());

        System.out.println("========================================================");

        //começar a aventura
        aventura();
    }

    public void aventura(){
        introducao();

        System.out.println("\n================ CAPITULO 1 ================");
        System.out.println("              VILA DE VALENFORD");
        System.out.println("============================================\n");

        System.out.println("Apos dias de viagem, voce finalmente chega");
        System.out.println("a uma pequena vila chamada Valenford.");

        System.out.println("\nUm senhor se aproxima de voce.");

        System.out.println("\nAnciao: Seja bem-vindo, viajante!");
        System.out.println("Anciao: Qual e o seu nome?");

        System.out.println("\n"+jogador.getNome()+": Meu nome e "+jogador.getNome()+".");

        System.out.println("\nAnciao: Chegou em tempos sombrios...");
        System.out.println("Anciao: Criaturas estao atacando nossa vila.");
        System.out.println("Anciao: Precisamos de ajuda!");

        System.out.println("\nAntes que voce consiga responder,");
        System.out.println("um grito ecoa pela entrada da vila.");

        System.out.println("\nUma criatura invade o vilarejo.");
        System.out.println("Os moradores correm desesperados.");
        System.out.println("Voce se prepara para lutar.");

        System.out.println("\nPressione ENTER para iniciar o combate...");
        scanner.nextLine();
        while(!scanner.nextLine().isEmpty()){
            System.out.println("Pressione apenas ENTER!");
        }

        Personagem inimigo=new Goblin();

        Combate combate=new Combate(scanner);

        boolean venceu=combate.iniciar(jogador,inimigo);

        if(!venceu){
            System.out.println("\nSua jornada terminou antes mesmo de comecar.");
            System.out.println("Fim de jogo!");
            return;
        }

        System.out.println("\n============================================");
        System.out.println("               A VILA ESTA SALVA!");
        System.out.println("============================================\n");

        System.out.println("Os moradores comemoram sua vitoria!");

        System.out.println("\nAnciao: Voce nos salvou, "+jogador.getNome()+"!");
        System.out.println("Anciao: Mas temo que isso seja apenas o comeco.");
        System.out.println("Anciao: Precisamos descobrir de onde essas");
        System.out.println("criaturas estao vindo.");
    }

    public void continuar_jogo(){
        
    }

    public void sair(){
        return;
    }

    private void introducao(){
        System.out.println("\n========================================================");
        System.out.println("             RPG - A ASCENSAO DAS SOMBRAS");
        System.out.println("========================================================\n");

        System.out.println("Durante seculos, o reino de Eldoria viveu em paz.");
        System.out.println("Uma antiga magia mantinha adormecidas as criaturas");
        System.out.println("mais perigosas do continente.");

        System.out.println("\nEntretanto, algo mudou...");

        System.out.println("\nCriaturas comecaram a atacar viajantes.");
        System.out.println("Aldeias desapareceram misteriosamente.");
        System.out.println("E um antigo mal parece ter despertado.");

        System.out.println("\nO destino de Eldoria esta prestes a mudar.");

        System.out.println("\nPressione ENTER para iniciar sua jornada...");
        scanner.nextLine();
        while(!scanner.nextLine().isEmpty()){
            System.out.println("Pressione apenas ENTER!");
        }
    }
}
