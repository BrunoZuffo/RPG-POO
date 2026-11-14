package jogo;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Entrada{

    public static int lerInteiro(Scanner scanner){
        while(true){
            try{
                return scanner.nextInt();
            }
            catch(InputMismatchException erro){
                System.out.println("Entrada invalida! Digite um numero inteiro.");
                scanner.nextLine();
            }
        }
    }
}