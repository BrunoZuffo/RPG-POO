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

        while(jogador.estaVivo() && inimigo.estaVivo()){

            System.out.println("\n================ COMBATE ================");

            System.out.println(jogador.getNome());
            System.out.println(jogador.getAtributosAtuais());

            System.out.println(inimigo.getNome());
            System.out.println(inimigo.getAtributosAtuais());

            System.out.println("\n1 - Ataque basico");
            System.out.println("2 - Ataque especial");
            System.out.println("3 - Descansar");

            int escolha=Entrada.lerInteiro(scanner);

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

            if(!inimigo.estaVivo()){
                break;
            }

            turnoInimigo(inimigo,jogador);

            if(!jogador.estaVivo()){
                break;
            }

            jogador.regenerarAtributos();
            inimigo.regenerarAtributos();
        }

        if(jogador.estaVivo()){
            System.out.println("\nVoce venceu o combate!");
            return true;
        }

        System.out.println("\nVoce foi derrotado!");
        return false;
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