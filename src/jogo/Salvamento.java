package jogo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Salvamento{
    //caminho
    private static final Path ARQUIVO=Path.of("saves","save.dat");

    public static void salvar(EstadoJogo estado) throws IOException{
        //criando a pasta caso ainda nao exista
        Files.createDirectories(ARQUIVO.getParent());

        //salvando primeiro em um arquivo temporario para evitar qualquer erro e perdermos 2 arquivos, temporario e atual
        Path temporario=Path.of("saves","save.tmp");

        try{
            try(ObjectOutputStream saida=new ObjectOutputStream( //escrever arquivos serializados
                Files.newOutputStream(temporario))){

                saida.writeObject(estado); //salvando objeto
            }

            //substituindo o save anterior somente apos a escrita
            Files.move(temporario,ARQUIVO,
                StandardCopyOption.REPLACE_EXISTING);
        }
        finally{
            Files.deleteIfExists(temporario);
        }
    }

    public static EstadoJogo carregar()
        throws IOException,ClassNotFoundException{

        try(ObjectInputStream entrada=new ObjectInputStream( //lendo arquivo serializado
            Files.newInputStream(ARQUIVO))){

            Object objeto=entrada.readObject(); //recuperando objeto

            if(!(objeto instanceof EstadoJogo)){ //ve em tempo de execução se o objeto eh instanciado ou herda ou se implementa da classe EstadoJogo
                throw new IOException("Arquivo de salvamento invalido!");
            }

            return (EstadoJogo)objeto;
        }
    }
}