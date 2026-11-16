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
import excecoes.RecursoInsuficienteException;

public class Jogo {

    private Scanner scanner;
    private Personagem jogador;
    private Combate combate;
    private Loja loja;

    //para se ele voltar para a vila sabermos onde voltar na floresta
    private int etapaFloresta=0;

    public Jogo(){
        this.scanner = new Scanner(System.in); //criamos um objeto da classe Scanner responsavel por ler a entrada do terminal
        this.jogador = null; //fazendo isso podemos acessar jogador a qualquer momento, antes iniciavamos ele só no iniciar()
        this.combate = new Combate(scanner); //passando o scanner que ja existe em Jogo para o combate para evitar inicializar mais um
        this.loja=new Loja(scanner); //passando o scanner que ja existe, igual a ideia do this.combate
    }

    public void menu(){
        Terminal.limpar();
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
        Terminal.limpar();
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

        String nome=null;
        System.out.println("\nEscolha o nome do seu personagem: ");
        while(nome==null || nome.isBlank()){
            nome=scanner.nextLine();
        }
        Terminal.limpar();
        jogador.setNome(nome);
        System.out.println("\nPersonagem criado com sucesso!\n"+jogador.getNome()+"- "+jogador.toString());

        System.out.println("========================================================");


        //começar a aventura
        etapaFloresta=0;
        Terminal.pausar(scanner);
        aventura();
    }











    //========================JOGO====================================
    public void aventura(){
        introducao();
        Terminal.limpar();

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

        Personagem inimigo=new Goblin();

        if(!realizarEncontro(inimigo,40,20)){
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
        Terminal.pausar(scanner);

        menuVila();
    }












    //mais funcoes
    public void continuar_jogo(){
        
    }

    public void sair(){
        return;
    }

    private void introducao(){
        Terminal.limpar();
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
            Terminal.limpar();

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

            Terminal.pausar(scanner);
        }
    }

    private void menuVila(){
        while(jogador.estaVivo()){
            Terminal.limpar();

            System.out.println("\n============ VILA DE VALENFORD ============");

            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());

            System.out.println("\n1 - Abrir inventario");
            System.out.println("2 - Consultar atributos");
            System.out.println("3 - Descansar na taverna (5 ouro)");
            System.out.println("4 - Explorar a Floresta Sombria");
            if(etapaFloresta<2){
                System.out.println("5 - Covil das Aranhas (Bloqueado)");
            }
            else if(etapaFloresta==2){
                System.out.println("5 - Explorar o Covil das Aranhas");
            }
            else{
                System.out.println("5 - Covil das Aranhas (Concluido)");
            }
            System.out.println("6 - Visitar a loja");
            System.out.println("0 - Sair do jogo");

            System.out.print("\nEscolha: ");

            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==1){
                menuInventario();
            }
            else if(escolha==2){
                consultarAtributos();
            }
            else if(escolha==3){
                descansarNaVila();
                Terminal.pausar(scanner);
            }
            else if(escolha==4){
                explorarFloresta();
            }
            else if(escolha==5){
                explorarCovil();
            }
            else if(escolha==6){
                loja.abrir(jogador);
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

    private boolean realizarEncontro(Personagem inimigo,int xp,int ouro){

        boolean venceu=combate.iniciar(jogador,inimigo);

        if(!venceu){
            System.out.println("\nSua jornada chegou ao fim...");
            System.out.println("Fim de jogo!");
            Terminal.pausar(scanner);
            return false;
        }

        recompensarVitoria(xp,ouro);

        return true;
    }

    private void explorarFloresta(){
        if(etapaFloresta>=2){
            System.out.println("\nVoce ja investigou esta regiao!");
            Terminal.pausar(scanner);
            return;
        }
        Terminal.limpar();

        System.out.println("\n============== CAPITULO 2 ==============");
        System.out.println("             FLORESTA SOMBRIA");
        System.out.println("========================================");

        System.out.println("\nAo deixar Valenford, voce segue uma trilha");
        System.out.println("cercada por arvores antigas e uma nevoa intensa.");
        Terminal.pausar(scanner);
        while(jogador.estaVivo()){
            Terminal.limpar();

            System.out.println("\n=========== FLORESTA SOMBRIA ===========");

            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());

            if(etapaFloresta==0){
                System.out.println("\n1 - Investigar a trilha");
            }
            else if(etapaFloresta==1){
                System.out.println("\n1 - Avancar para a floresta profunda");
            }

            System.out.println("2 - Abrir inventario");
            System.out.println("0 - Retornar para a vila");

            System.out.print("\nEscolha: ");
            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==0){
                return;
            }
            else if(escolha==2){
                menuInventario();
            }
            else if(escolha==1){

                if(etapaFloresta==0){

                    System.out.println("\nVoce encontra rastros de sangue na trilha.");
                    System.out.println("Um rosnado rompe o silencio da floresta.");
                    System.out.println("Um lobo surge entre as arvores!");

                    if(!realizarEncontro(new Lobo(),50,25)){
                        return;
                    }
                    
                    etapaFloresta=1;

                    receberItem(Item.ERVA_MEDICINAL);

                    System.out.println("\nCom o caminho livre, voce encontra");
                    System.out.println("uma carruagem abandonada.");
                    System.out.println("Estranhas teias cobrem toda sua superficie.");
                    Terminal.pausar(scanner);
                }
                else if(etapaFloresta==1){

                    System.out.println("\nVoce segue os rastros da carruagem.");
                    System.out.println("As teias tornam-se cada vez maiores.");

                    System.out.println("\nDe repente, uma criatura desce das arvores!");

                    if(!realizarEncontro(new Aranha_Gigante(),75,40)){
                        return;
                    }

                    etapaFloresta=2;

                    receberItem(Item.POCAO_MEDIA);

                    System.out.println("\nVoce encontra a entrada de uma caverna.");
                    System.out.println("Centenas de ovos cobrem suas paredes.");
                    System.out.println("Algo muito maior se esconde la dentro...");

                    System.out.println("\nVoce decide retornar para Valenford");
                    System.out.println("e contar ao anciao o que descobriu.");
                    Terminal.pausar(scanner);
                    return;
                }
            }
            else{
                System.out.println("Escolha invalida!");
                Terminal.pausar(scanner);
            }
        }
    }

    private void descansarNaVila(){
        int custo=5;

        if(jogador.getVidaAtual()==jogador.getVida()
            && jogador.getStaminaAtual()==jogador.getStamina()
            && jogador.getManaAtual()==jogador.getMana()){

            System.out.println("Seus atributos ja estao cheios!");
            return;
        }

        try{
            jogador.gastarOuro(custo);
            jogador.descansar();

            System.out.println("\nVoce descansou na taverna!");
            System.out.println("Todos os atributos foram recuperados.");
            System.out.println("Ouro gasto: "+custo);
        }
        catch(RecursoInsuficienteException erro){
            System.out.println(erro.getMessage());
        }
    }

    private void consultarAtributos(){
        Terminal.limpar();
        System.out.println("\n============ FICHA DO PERSONAGEM ============");

        System.out.println("Nome: "+jogador.getNome());
        System.out.println("Classe: "+jogador.getClass().getSimpleName());
        System.out.println("Arma equipada: "+jogador.getItemAtual().getNome());

        System.out.println("Dano base: "+jogador.getDano());
        System.out.println("Bonus da arma: "+jogador.getItemAtual().getDano());

        System.out.println("Dano total: "
            +(jogador.getDano()+jogador.getItemAtual().getDano()));

        System.out.println("Defesa: "+jogador.getDefesa());

        System.out.println("\nRegeneracao por rodada:");
        System.out.println(jogador.getRegeneracao());

        Terminal.pausar(scanner);
    }

    private void explorarCovil(){
        if(etapaFloresta<2){
            System.out.println("Voce ainda precisa investigar a floresta!");
            Terminal.pausar(scanner);
            return;
        }

        if(etapaFloresta==3){
            System.out.println("A Aranha Rainha ja foi derrotada!");
            Terminal.pausar(scanner);
            return;
        }
        Terminal.limpar();

        System.out.println("\n============== CAPITULO 3 ==============");
        System.out.println("             COVIL DAS ARANHAS");
        System.out.println("========================================");

        System.out.println("\nAnciao: Existe algo estranho naquela caverna.");
        System.out.println("Anciao: Essas criaturas nao pertencem a nossa floresta.");
        System.out.println("Anciao: Prepare-se antes de partir!");
        Terminal.pausar(scanner);

        while(jogador.estaVivo()){
            Terminal.limpar();

            System.out.println("\n============ COVIL DAS ARANHAS ============");

            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());

            System.out.println("\n1 - Entrar na caverna");
            System.out.println("2 - Abrir inventario");
            System.out.println("0 - Retornar para a vila");

            System.out.print("\nEscolha: ");

            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==0){
                return;
            }
            else if(escolha==2){
                menuInventario();
            }
            else if(escolha==1){

                System.out.println("\nVoce entra lentamente na caverna.");
                System.out.println("O chao esta coberto por ossos e teias.");

                System.out.println("\nUma enorme criatura surge da escuridao.");
                System.out.println("Seus olhos brilham com uma estranha energia.");

                System.out.println("\nA ARANHA RAINHA DESPERTOU!");

                if(!realizarEncontro(new Aranha_Rainha(),250,150)){
                    return;
                }

                etapaFloresta=3;

                receberItem(Item.POCAO_GRANDE);
                receberItem(Item.BOLSA_PEQUENA);

                System.out.println("\n========== BOSS DERROTADO ==========");

                System.out.println("\nCom a criatura derrotada,");
                System.out.println("voce encontra um simbolo gravado nas paredes.");

                System.out.println("Uma estranha magia emana daquele simbolo.");

                System.out.println("\nAo retornar para Valenford,");
                System.out.println("o anciao reconhece o simbolo.");

                System.out.println("\nAnciao: Ja vi essa marca antes...");
                System.out.println("Anciao: Nas antigas ruinas das montanhas.");
                System.out.println("Anciao: Talvez a origem de tudo esteja la.");
                Terminal.pausar(scanner);
                return;
            }
            else{
                System.out.println("Escolha invalida!");
                Terminal.pausar(scanner);
            }
        }
    }

}
