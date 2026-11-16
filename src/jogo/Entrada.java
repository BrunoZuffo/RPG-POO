package jogo;

import java.util.Scanner;

public class Entrada{

    public static int lerInteiro(Scanner scanner){

        while(true){
            try{    //parseInt converte string num int
                return Integer.parseInt(scanner.nextLine().trim()); //trim() remove todos os espaços em branco do começo e final de uma string
            }
            catch(NumberFormatException erro){
                System.out.println("Entrada invalida! Digite um numero inteiro.");
            }
        }
    }
}