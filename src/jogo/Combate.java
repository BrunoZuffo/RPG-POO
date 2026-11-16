package jogo;

import personagens.Personagem;
import excecoes.RecursoInsuficienteException;
import java.util.Scanner;
import inventario.Item;
import java.util.List;
import excecoes.InventarioException;

public class Combate{

    private Scanner scanner;

    public Combate(Scanner scanner){
        this.scanner=scanner;
    }

    public boolean iniciar(Personagem jogador,Personagem inimigo){

        System.out.println("\nUm inimigo apareceu!");
        System.out.println("Voce encontrou: "+inimigo.getNome());

        Terminal.pausar(scanner);

        int rodada=1;

        while(jogador.estaVivo() && inimigo.estaVivo()){

            Terminal.limpar();

            System.out.println("\n================ RPG | RODADA "+rodada+" ================\n");

            String formato="%-30s | %-30s%n"; //estamos definindo um formato de string para usarmos quando formos printar
            //as informações do jogador e inimigo

            //exibindo os personagens lado a lado
            System.out.printf(formato,"JOGADOR - "+jogador.getNome(),"INIMIGO - "+inimigo.getNome()); //passamos o formato, entao ele espera 2 strings ( pelo %s 2x na declaração )
            // e elas sao relacionadas em seguida nesse caso com o JOGADOR e INIMIGO
            System.out.println("---------------------------------------------------------------");

            System.out.printf(formato,
                "Vida: "+jogador.getVidaAtual()+"/"+jogador.getVida(),
                "Vida: "+inimigo.getVidaAtual()+"/"+inimigo.getVida());

            System.out.printf(formato,
                "Stamina: "+jogador.getStaminaAtual()+"/"+jogador.getStamina(),
                "Stamina: "+inimigo.getStaminaAtual()+"/"+inimigo.getStamina());

            System.out.printf(formato,
                "Mana: "+jogador.getManaAtual()+"/"+jogador.getMana(),
                "Mana: "+inimigo.getManaAtual()+"/"+inimigo.getMana());

            System.out.printf(formato,
                "Dano base: "+jogador.getDano(),
                "Dano base: "+inimigo.getDano());

            System.out.printf(formato,
                "Defesa: "+jogador.getDefesa(),
                "Defesa: "+inimigo.getDefesa());

            //exibindo as acoes disponiveis
            System.out.println("\n================ ACOES DISPONIVEIS ================\n");

            System.out.printf("%-21s | %-10s | %s%n",
                "1 - Ataque basico",
                "Dano: "+jogador.getDanoAtaque1(inimigo),
                "Custo: "+jogador.getCustoAtaque1());

            System.out.printf("%-21s | %-10s | %s%n",
                "2 - Ataque especial",
                "Dano: "+jogador.getDanoAtaque2(inimigo),
                "Custo: "+jogador.getCustoAtaque2());

            System.out.printf("%-21s | %-10s | %s%n",
                "3 - Descansar",
                "Dano: 0",
                "Sem custo");

            System.out.println("4 - Utilizar consumivel");

            System.out.println("\n============== REGENERACAO POR RODADA ==============\n");

            System.out.println(jogador.getRegeneracao());

            System.out.println("\n====================================================");

            System.out.print("\nEscolha sua acao: ");
            int escolha=Entrada.lerInteiro(scanner);

            System.out.println("\n--------------- ACOES ---------------");

            int vidaAntes=inimigo.getVidaAtual();

            try{
                if(escolha==1){
                    jogador.ataque1(inimigo);
                }
                else if(escolha==2){
                    jogador.ataque2(inimigo);
                }
                else if(escolha==3){
                    System.out.println("Voce decidiu descansar neste turno.");
                }
                else if(escolha==4){
                    if(!escolherConsumivel(jogador)){
                        continue;
                    }
                }
                else{
                    System.out.println("Escolha invalida!");
                    Terminal.pausar(scanner);
                    continue;
                }
            }
            catch(RecursoInsuficienteException | InventarioException erro){
                System.out.println(erro.getMessage());
                Terminal.pausar(scanner);
                continue;
            }

            //mostrando o dano causado pelo jogador
            if(escolha==1 || escolha==2){
                System.out.println("Voce causou "+(vidaAntes-inimigo.getVidaAtual())+" de dano!");
            }

            if(!inimigo.estaVivo()){
                jogador.regenerarAtributos();
                System.out.println("Atributos regenerados.");
                break;
            }

            //mostrando o dano recebido pelo jogador
            vidaAntes=jogador.getVidaAtual();

            turnoInimigo(inimigo,jogador);

            int danoRecebido=vidaAntes-jogador.getVidaAtual();

            if(danoRecebido>0){
                System.out.println("Voce recebeu "+danoRecebido+" de dano!");
            }

            if(!jogador.estaVivo()){
                break;
            }

            jogador.regenerarAtributos();
            inimigo.regenerarAtributos();

            System.out.println("Atributos regenerados.");
            Terminal.pausar(scanner);
            rodada++;
        }

        System.out.println("\n=============== FIM DO COMBATE ===============");

        boolean venceu=jogador.estaVivo();

        if(venceu){
            System.out.println("VITORIA! "+jogador.getNome()+" derrotou "+inimigo.getNome()+".");
        }
        else{
            System.out.println("DERROTA! Voce foi derrotado!");
        }

        System.out.println("\nAtributos atuais de "+jogador.getNome()+":");
        System.out.println(jogador.getAtributosAtuais());
        System.out.println("Dano base: "+jogador.getDano()+" | Defesa: "+jogador.getDefesa());

        System.out.println("=============================================");

        return venceu;
    }

    private void turnoInimigo(Personagem inimigo,Personagem jogador){

        try{
            if(Math.random()<0.3){
                inimigo.ataque2(jogador);
                System.out.println(inimigo.getNome()+" utilizou um ataque especial!");
            }
            else{
                inimigo.ataque1(jogador);
                System.out.println(inimigo.getNome()+" utilizou um ataque basico!");
            }
        }
        catch(RecursoInsuficienteException erro){

            try{
                inimigo.ataque1(jogador);
                System.out.println(inimigo.getNome()+" utilizou um ataque basico!");
            }
            catch(RecursoInsuficienteException erro2){
                System.out.println(inimigo.getNome()+" precisou descansar!");
            }
        }
    }

    private boolean escolherConsumivel(Personagem jogador){

        Terminal.limpar();
        
        List<Item> itens=jogador.getInventario();

        boolean possuiConsumivel=false;

        System.out.println("\n========== CONSUMIVEIS ==========");

        for(int i=0;i<itens.size();i++){

            Item item=itens.get(i);

            if(item.getCura()>0){

                System.out.println((i+1)+" - "+item.getNome()
                    +" | Cura: "+item.getCura());

                possuiConsumivel=true;
            }
        }

        if(!possuiConsumivel){
            System.out.println("Voce nao possui consumiveis!");
            Terminal.pausar(scanner);
            return false;
        }

        System.out.println("0 - Voltar");

        System.out.print("\nEscolha: ");

        int escolha=Entrada.lerInteiro(scanner);

        if(escolha==0){
            return false;
        }

        System.out.println(jogador.usarConsumivel(escolha-1));

        return true;
    }

}