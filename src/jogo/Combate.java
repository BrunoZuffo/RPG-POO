package jogo;

import personagens.Personagem;
import excecoes.RecursoInsuficienteException;
import java.util.Scanner;

public class Combate{

    private Scanner scanner;

    public Combate(Scanner scanner){
        this.scanner=scanner;
    }

    public boolean iniciar(Personagem jogador,Personagem inimigo){

        System.out.println("\nUm inimigo apareceu!");
        System.out.println("Voce encontrou: "+inimigo.getNome());

        int rodada=1;

        while(jogador.estaVivo() && inimigo.estaVivo()){

            System.out.println("\n========== COMBATE | RODADA "+rodada+" ==========\n");

            System.out.println("JOGADOR - "+jogador.getNome());
            System.out.println(jogador.getAtributosAtuais());

            System.out.println("\nINIMIGO - "+inimigo.getNome());
            System.out.println(inimigo.getAtributosAtuais());

            System.out.println("\n1 - Ataque basico");
            System.out.println("2 - Ataque especial");
            System.out.println("3 - Descansar");

            System.out.print("\nEscolha: ");
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
                else{
                    System.out.println("Escolha invalida!");
                    continue;
                }
            }
            catch(RecursoInsuficienteException erro){
                System.out.println(erro.getMessage());
                continue;
            }

            //mostrando o dano causado pelo jogador
            if(escolha==1 || escolha==2){
                System.out.println("Voce causou "+(vidaAntes-inimigo.getVidaAtual())+" de dano!");
            }

            if(!inimigo.estaVivo()){
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

}