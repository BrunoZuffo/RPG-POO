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

import inventario.Item;
import java.util.List;
import excecoes.InventarioException;

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











    //========================JOGO====================================
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

        System.out.println("\nAnciao: Aceite estes itens como agradecimento.");
        System.out.println("Anciao: Eles serao uteis durante sua jornada.");

        receberItem(Item.ADAGA);
        receberItem(Item.POCAO_PEQUENA);

        recompensarVitoria(40,20);

        menuVila();
    }












    //mais funcoes
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
        while(!scanner.nextLine().isEmpty()){
            System.out.println("Pressione apenas ENTER!");
        }
    }

    private void menuInventario(){
        while(true){

            System.out.println("\n================ INVENTARIO ================");

            System.out.println("Arma equipada: "+jogador.getItemAtual().getNome());

            System.out.println("Espaco utilizado: "+jogador.getEspacoUsado()
                +"/"+jogador.getTamanhoInventario());

            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());


            List<Item> itens=jogador.getInventario();

            System.out.println("--------------------------------------------");

            for(int i=0;i<itens.size();i++){

                Item item=itens.get(i);

                System.out.println((i+1)+" - "+item.getNome()
                    +" | Dano: "+item.getDano()
                    +" | Cura: "+item.getCura()
                    +" | Espaco: "+item.getTamanho());
            }

            System.out.println("\n0 - Voltar");
            System.out.print("\nEscolha um item para utilizar: ");

            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==0){
                return;
            }

            try{
                System.out.println(jogador.usarItem(escolha-1));
            }
            catch(InventarioException erro){
                System.out.println(erro.getMessage());
            }
        }
    }

    private void menuVila(){
        while(true){

            System.out.println("\n============ VILA DE VALENFORD ============");

            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());

            System.out.println("\n1 - Abrir inventario");
            System.out.println("2 - Consultar atributos");
            System.out.println("0 - Sair do jogo");

            System.out.print("\nEscolha: ");

            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==1){
                menuInventario();
            }
            else if(escolha==2){
                System.out.println(jogador.toString());
                System.out.println(jogador.getAtributosAtuais());
                System.out.println(jogador.getProgresso());
            }
            else if(escolha==0){
                return;
            }
            else{
                System.out.println("Escolha invalida!");
            }
        }
    }

    private void receberItem(Item item){ //generalizar para nao ficar add sempre
        try{
            System.out.println(jogador.adicionarItem(item));
        }
        catch(InventarioException erro){
            System.out.println(erro.getMessage());
        }
    }

    //metodo reutilizavel de novo para facilitar na hora do jogo
    private void recompensarVitoria(int xp,int ouro){
        int nivelAntes=jogador.getNivel();

        jogador.ganharExperiencia(xp);
        jogador.ganharOuro(ouro);

        System.out.println("\n============== RECOMPENSAS ==============");

        System.out.println("Experiencia recebida: +"+xp+" XP");
        System.out.println("Ouro recebido: +"+ouro);

        if(jogador.getNivel()>nivelAntes){

            System.out.println("\nLEVEL UP!");
            System.out.println("Voce alcancou o nivel "+jogador.getNivel()+"!");
            System.out.println("Seus atributos foram melhorados!");
        }

        System.out.println("\n"+jogador.getProgresso());
    }

}
