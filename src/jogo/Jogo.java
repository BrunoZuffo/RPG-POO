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
import personagens.Ladino;
import personagens.Paladino;
import personagens.Barbaro;
import personagens.Druida;
import personagens.Monge;

import inventario.Item;
import java.util.List;
import excecoes.InventarioException;
import excecoes.RecursoInsuficienteException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Jogo {

    private Scanner scanner;
    private Personagem jogador;
    private Combate combate;
    private Loja loja;

    //para se ele voltar para a vila sabermos onde voltar na floresta e na etapa norte
    private int etapaFloresta=0;
    private int etapaNorte=0;

    public Jogo(){
        this.scanner = new Scanner(System.in); //criamos um objeto da classe Scanner responsavel por ler a entrada do terminal
        this.jogador = null; //fazendo isso podemos acessar jogador a qualquer momento, antes iniciavamos ele só no iniciar()
        this.combate = new Combate(scanner); //passando o scanner que ja existe em Jogo para o combate para evitar inicializar mais um
        this.loja=new Loja(scanner); //passando o scanner que ja existe, igual a ideia do this.combate
    }

    public void menu(){
        while(true){
            Terminal.limpar();

            System.out.println("========================= RPG =========================");
            System.out.println("\n1 - Continuar jogo");
            System.out.println("2 - Comecar novo jogo");
            System.out.println("3 - Sair");
            System.out.println("\n=======================================================");
            System.out.print("\nEscolha: ");

            int escolha=Entrada.lerInteiro(scanner);
            if(escolha==1){
                continuar_jogo();
            }
            else if(escolha==2){
                iniciar();
            }
            else if(escolha==3){
                if(sair()){
                    return;
                }
            }
            else{
                System.out.println("Digite um valor valido!");
                Terminal.pausar(scanner);
            }
        }
    }

    public void iniciar(){
        Terminal.limpar();
        Personagem[] personagens={
            new Guerreiro(),
            new Mago(),
            new Anão(),
            new Elfo(),
            new Ladino(),
            new Paladino(),
            new Barbaro(),
            new Druida(),
            new Monge()
        };

        System.out.println("========== ESCOLHA SEU PERSONAGEM ==========\n");

        String formato="%-3s | %-12s | %5s | %5s | %6s | %7s | %5s | %4s%n";
        System.out.printf(formato,"N","Classe","Vida","Dano","Defesa","Stamina","Mana","Inv.");
        System.out.println("-".repeat(68));

        for(int i=0;i<personagens.length;i++){
            Personagem p=personagens[i];
            System.out.printf(formato,i+1,p.getClass().getSimpleName(),
                p.getVida(),p.getDano(),p.getDefesa(),p.getStamina(),
                p.getMana(),p.getTamanhoInventario());
        }

        System.out.println("-".repeat(68));
        jogador=null;

        while(jogador==null){
            System.out.print("\nEscolha: ");
            int escolha=Entrada.lerInteiro(scanner);
            if(escolha>=1 && escolha<=personagens.length){
                jogador=personagens[escolha-1];
            }
            else{
                System.out.println("Selecione um valor valido!");
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
        etapaNorte=0;
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

        if(!realizarEncontro(inimigo,40,25)){
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
        try{
            EstadoJogo estado=Salvamento.carregar();
            Terminal.limpar(); //só limpa se carregar o jogo
            this.jogador=estado.getJogador();
            this.etapaFloresta=estado.getEtapaFloresta();
            this.etapaNorte=estado.getEtapaNorte();
            System.out.println("Jogo carregado com sucesso!");
            System.out.println("\nBem-vindo de volta, "+jogador.getNome()+"!");
            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());

            if(etapaNorte>=5){
                System.out.println("\nVoce ja concluiu a campanha!");
                Terminal.pausar(scanner);
                jogador=null;
                return;
            }

            Terminal.pausar(scanner);
            menuVila();
        }
        catch(NoSuchFileException erro){
            System.out.println("Nenhum jogo salvo encontrado!");
            Terminal.pausar(scanner);
        }
        catch(IOException | ClassNotFoundException erro){
            System.out.println("Erro ao carregar o jogo!");
            System.out.println(erro.getMessage());
            Terminal.pausar(scanner);
        }
    }

    public boolean sair(){
        if(jogador!=null && jogador.estaVivo()){
            if(!salvarJogo()){
                System.out.println("Saida cancelada: nao foi possivel salvar!");
                Terminal.pausar(scanner);
                return false;
            }
        }
        return true;
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
            System.out.println("Armadura equipada: "+jogador.getArmaduraAtual().getNome());
            System.out.println("Defesa total: "+jogador.getDefesaTotal());

            System.out.println("Espaco utilizado: "+jogador.getEspacoUsado()
                +"/"+jogador.getTamanhoInventario());

            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());


            List<Item> itens=jogador.getInventario();

            System.out.println("--------------------------------------------");

            String formato="%-3s | %-24s | %4s | %6s | %4s | %6s | %5s%n";

            System.out.printf(formato,
                "N","Item","Dano","Defesa","Cura","Espaco","Extra");

            System.out.println("-".repeat(70));

            for(int i=0;i<itens.size();i++){

                Item item=itens.get(i);

                System.out.printf(formato,
                    i+1,
                    item.getNome(),
                    item.getDano(),
                    item.getDefesa(),
                    item.getCura(),
                    item.getTamanho(),
                    item.getArmazenamentoExtra());
            }

            System.out.println("-".repeat(70));

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
        AutoSave autoSave = new AutoSave(() -> {
            synchronized(this){ // para que o autosave aguarde uma ação terminar antes de salvar os dados no meio e dar erro
                if(jogador!=null && jogador.estaVivo() && etapaNorte<5){
                    salvarJogo(true);
                }
            }
        }, 60000);

        autoSave.start();

        try{
            while(jogador.estaVivo() && etapaNorte<5){
            Terminal.limpar();

            System.out.println("\n============ VILA DE VALENFORD ============");
            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());

            //personagem e servicos da vila
            System.out.println("\n----------- PERSONAGEM E SERVICOS -----------");

            System.out.println("1 - Abrir inventario");
            System.out.println("2 - Consultar atributos");
            System.out.println("3 - Descansar na taverna (12 ouro)");
            System.out.println("4 - Visitar a loja");

            //exploracao e progresso da campanha
            System.out.println("\n---------------- EXPLORACAO ----------------");
            if(etapaFloresta>=2){
                System.out.println("5 - Floresta Sombria (Concluido)");
            }
            else{
                System.out.println("5 - Explorar a Floresta Sombria");
            }
            if(etapaFloresta<2){
                System.out.println("6 - Covil das Aranhas (Bloqueado)");
            }
            else if(etapaFloresta==2){
                System.out.println("6 - Explorar o Covil das Aranhas");
            }
            else{
                System.out.println("6 - Covil das Aranhas (Concluido)");
            }
            if(etapaFloresta<3){
                System.out.println("7 - Montanhas (Bloqueado)");
            }
            else{
                System.out.println("7 - Explorar "+getProximaRegiao());
            }

            //gerenciamento da partida
            System.out.println("\n------------------ PARTIDA ------------------");
            System.out.println("8 - Salvar jogo");
            System.out.println("0 - Voltar ao menu principal (Salvando)");
            System.out.println("\n=============================================");
            System.out.print("\nEscolha: ");
            int escolha=Entrada.lerInteiro(scanner);

            synchronized(this){
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
                    loja.abrir(jogador);
                }
                else if(escolha==5){
                    explorarFloresta();
                }
                else if(escolha==6){
                    explorarCovil();
                }
                else if(escolha==7){
                    explorarNorte();
                }
                else if(escolha==8){
                    salvarJogo();
                    Terminal.pausar(scanner);
                }
                else if(escolha==0){
                    if(salvarJogo()){
                        Terminal.pausar(scanner);
                        jogador=null;
                        return;
                    }

                    System.out.println("Nao foi possivel voltar sem salvar!");
                    Terminal.pausar(scanner);
                }
                else{
                    System.out.println("Escolha invalida!");
                    Terminal.pausar(scanner);
                }
            }
        }
        }
        finally{
            autoSave.encerrar();
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

                    if(!realizarEncontro(new Lobo(),50,30)){
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

                    if(!realizarEncontro(new Aranha_Gigante(),75,50)){
                        return;
                    }

                    etapaFloresta=2;

                    receberItem(Item.ELIXIR);

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
        int custo=12;

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

        System.out.println("Armadura equipada: "+jogador.getArmaduraAtual().getNome());
        System.out.println("Defesa base: "+jogador.getDefesa());
        System.out.println("Bonus da armadura: "+jogador.getArmaduraAtual().getDefesa());
        System.out.println("Defesa total: "+jogador.getDefesaTotal());

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

                if(!realizarEncontro(new Aranha_Rainha(),180,150)){
                    return;
                }

                etapaFloresta=3;

                receberItem(Item.POCAO_MEDIA);
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

    private String getProximaRegiao(){
        switch(etapaNorte){
            case 0:
                return "Montanhas";
            case 1:
                return "Montanhas Profundas";
            case 2:
                return "Ruinas de Pedra";
            case 3:
                return "Fortaleza Sombria";
            case 4:
                return "Pico Ancestral";
            default:
                return "Campanha concluida";
        }
    }

    private void explorarNorte(){
        if(etapaFloresta<3){
            System.out.println("Voce precisa derrotar a Aranha Rainha!");
            Terminal.pausar(scanner);
            return;
        }

        if(etapaNorte>=5){
            System.out.println("Voce ja concluiu sua jornada!");
            Terminal.pausar(scanner);
            return;
        }

        while(jogador.estaVivo() && etapaNorte<5){

            Terminal.limpar();

            System.out.println("\n========== "+getProximaRegiao()+" ==========");

            System.out.println(jogador.getAtributosAtuais());
            System.out.println(jogador.getProgresso());

            System.out.println("\n1 - Continuar explorando");
            System.out.println("2 - Abrir inventario");
            System.out.println("0 - Retornar para a vila");

            System.out.print("\nEscolha: ");
            int escolha=Entrada.lerInteiro(scanner);

            if(escolha==0){
                return;
            }
            else if(escolha==2){
                menuInventario();
                continue;
            }
            else if(escolha!=1){
                System.out.println("Escolha invalida!");
                Terminal.pausar(scanner);
                continue;
            }

            Terminal.limpar();

            Personagem inimigo;
            int xp;
            int ouro;
            Item premio;
            String desfecho;

            switch(etapaNorte){

                case 0:

                    System.out.println("============== CAPITULO 4 ==============");
                    System.out.println("          MONTANHAS DE ELDORIA");
                    System.out.println("========================================");

                    System.out.println("\nApos horas de caminhada, voce chega as montanhas.");
                    System.out.println("O frio aumenta conforme voce avanca.");

                    System.out.println("\nUma caravana destruida bloqueia a estrada.");
                    System.out.println("Entre os destrocos, voce encontra cristais escuros.");

                    System.out.println("\nOs cristais possuem o mesmo simbolo");
                    System.out.println("encontrado no Covil das Aranhas.");

                    System.out.println("\nUma voz interrompe sua investigacao.");

                    System.out.println("Orc: Ninguem deve descobrir o que transportamos!");
                    System.out.println("Orc: Voce nao saira daqui vivo!");

                    inimigo=new Orc();
                    xp=145;
                    ouro=80;
                    premio=Item.ELIXIR;

                    desfecho="\nCom o Orc derrotado, voce investiga a caravana."
                        +"\nEntre os destrocos, encontra um mapa das montanhas."
                        +"\nUma antiga ruina esta marcada no documento."
                        +"\nVoce decide seguir naquela direcao.";

                    break;

                case 1:

                    System.out.println("Voce segue o caminho indicado pelo mapa.");
                    System.out.println("A vegetacao desaparece e o silencio domina.");

                    System.out.println("\nCristais escuros estao espalhados pelo caminho.");

                    System.out.println("Uma enorme criatura bloqueia uma passagem.");
                    System.out.println("Seus olhos brilham com uma energia sombria.");

                    System.out.println("\nO OGRO AVANCA EM SUA DIRECAO!");

                    inimigo=new Ogro();
                    xp=160;
                    ouro=110;
                    premio=Item.POCAO_MEDIA;

                    desfecho="\nA energia sombria abandona o corpo do Ogro."
                        +"\nVoce percebe que aquela criatura estava corrompida."
                        +"\nA passagem finalmente esta livre."
                        +"\nAo longe, surgem as antigas ruinas de Eldoria.";

                    break;

                case 2:

                    System.out.println("============== CAPITULO 5 ==============");
                    System.out.println("             RUINAS DE PEDRA");
                    System.out.println("========================================");

                    System.out.println("\nVoce entra em um templo abandonado.");
                    System.out.println("Inscricoes antigas cobrem suas paredes.");

                    System.out.println("\nAs inscricoes contam a historia de um dragao.");
                    System.out.println("Durante seculos, um selo magico manteve");
                    System.out.println("a criatura adormecida nas montanhas.");

                    System.out.println("\nNo centro do templo, voce encontra o selo destruido.");
                    System.out.println("Uma enorme estatua comeca a se movimentar.");

                    System.out.println("\nO antigo guardiao do templo foi corrompido!");

                    inimigo=new Gigante_de_Pedra();
                    xp=250;
                    ouro=230;
                    premio=Item.POCAO_GRANDE;

                    desfecho="\nO Gigante de Pedra finalmente desmorona."
                        +"\nEntre as pedras, voce encontra um pergaminho."
                        +"\nEle descreve um ritual proibido de necromancia."
                        +"\nO responsavel se esconde na Fortaleza Sombria."
                        +"\nVoce precisa impedi-lo antes que seja tarde.";

                    break;

                case 3:

                    System.out.println("============== CAPITULO 6 ==============");
                    System.out.println("             FORTALEZA SOMBRIA");
                    System.out.println("========================================");

                    System.out.println("\nVoce atravessa os portoes da fortaleza.");
                    System.out.println("Uma estranha energia toma conta do lugar.");

                    System.out.println("\nNo centro de um enorme salao,");
                    System.out.println("um homem realiza um ritual proibido.");

                    System.out.println("\nNecromante: Entao voce chegou ate aqui...");
                    System.out.println("Necromante: Durante anos preparei este momento.");
                    System.out.println("Necromante: As criaturas, os cristais e o selo...");
                    System.out.println("Necromante: Tudo fazia parte do meu plano!");

                    System.out.println("\nNecromante: Eldoria se curvara diante de mim!");

                    inimigo=new Necromante();
                    xp=200;
                    ouro=300;
                    premio=Item.POCAO_MEDIA;

                    desfecho="\nNecromante: Tolo... voce chegou tarde demais..."
                        +"\nNecromante: O ritual ja foi concluido..."
                        +"\n\nA fortaleza inteira comeca a tremer."
                        +"\nUm rugido ecoa por todas as montanhas."
                        +"\nO Dragao Ancestral finalmente despertou."
                        +"\nResta apenas uma chance de salvar Eldoria.";

                    break;

                case 4:

                    System.out.println("============== CAPITULO 7 ==============");
                    System.out.println("              PICO ANCESTRAL");
                    System.out.println("========================================");

                    System.out.println("\nVoce inicia a ultima subida da sua jornada.");
                    System.out.println("Raios iluminam o ceu sobre as montanhas.");

                    System.out.println("\nDo alto do pico, voce observa Eldoria.");
                    System.out.println("Valenford pode ser vista ao longe.");

                    System.out.println("\nVoce se lembra de todos aqueles que ajudou.");
                    System.out.println("O destino do reino agora esta em suas maos.");

                    System.out.println("\nUma enorme sombra cobre o ceu.");
                    System.out.println("O calor das chamas toma conta do lugar.");

                    System.out.println("\nO DRAGAO ANCESTRAL DESPERTOU!");
                    System.out.println("A BATALHA FINAL VAI COMECAR!");

                    inimigo=new Dragão_Ancestral();
                    xp=500;
                    ouro=300;
                    premio=null;

                    desfecho="\nO Dragao solta seu ultimo rugido."
                        +"\nOs cristais sombrios perdem seu brilho."
                        +"\nAs nuvens comecam a desaparecer.";

                    break;

                default:
                    return;
            }

            //realizamos o combate sem sair do while de exploracao
            if(!realizarEncontro(inimigo,xp,ouro)){
                return;
            }

            etapaNorte++;

            if(premio!=null){
                receberItem(premio);
            }

            //exibindo a continuacao da historia depois da vitoria
            System.out.println(desfecho);

            if(etapaNorte==5){
                finalizarCampanha();
                return;
            }

            System.out.println("\nProximo destino: "+getProximaRegiao());

            Terminal.pausar(scanner);
        }
    }
    private void finalizarCampanha(){
        Terminal.pausar(scanner);
        Terminal.limpar();

        System.out.println("\n==========================================");
        System.out.println("             ELDORIA ESTA SALVA!");
        System.out.println("==========================================");

        System.out.println("\nCom a derrota do Dragao Ancestral,");
        System.out.println("a magia sombria finalmente desaparece.");

        System.out.println("As criaturas retornam aos seus habitats.");
        System.out.println("A paz volta ao reino de Eldoria.");

        System.out.println("\nSeu nome sera lembrado por geracoes,");
        System.out.println(jogador.getNome()+", heroi de Eldoria!");

        System.out.println("\n========== ESTATISTICAS FINAIS ==========");

        System.out.println(jogador.toString());
        System.out.println(jogador.getAtributosAtuais());
        System.out.println(jogador.getProgresso());

        System.out.println("\nObrigado por jogar!");
        System.out.println("FIM");

        Terminal.pausar(scanner);
    }

    private synchronized boolean salvarJogo(){
        return salvarJogo(false);
    }

    private synchronized boolean salvarJogo(boolean automatico){
        if(jogador==null){
            return true;
        }
        try{
            EstadoJogo estado=new EstadoJogo(
                jogador,etapaFloresta,etapaNorte
            );
            Salvamento.salvar(estado);

            if(!automatico){
                System.out.println("\nJogo salvo com sucesso!");
            }
            return true;
        }
        catch(IOException erro){
            if(automatico){
                System.err.println(
                    "Erro no autosave: "+erro.getMessage()
                );
            }
            else{
                System.out.println("Erro ao salvar o jogo!");
                System.out.println(erro.getMessage());
            }
            return false;
        }
    }

}
