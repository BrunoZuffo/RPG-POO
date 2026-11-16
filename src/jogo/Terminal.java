//vamos criar esse arquivo para melhorar visualmente o terminal
//quando selecionamos atualmente alguma opcao, só printamos novas coisas continuas do jogo
//isso deixa tudo muito poluido, entao vamos tratar isso aqui
package jogo;

import java.util.Scanner;

public class Terminal{

    public static void limpar(){ //static pq nao precisamos criar um objeto para chamar
                                 //basta chamar a função Terminal.limpar()
        //Comando	Função
        //\033	    Caractere de escape
        //[H	    Move o cursor para o início da tela
        //[2J	    Limpa a tela
        //flush()	Envia imediatamente a saída pendente
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pausar(Scanner scanner){
        System.out.println("\nPressione ENTER para continuar...");

        while(!scanner.nextLine().isEmpty()){//quando clicamos enter enviamos "", entao isEmpty retorna true e o ! faz ser 0-> sai do loop
            System.out.println("Pressione apenas ENTER!");
        }
    }
}