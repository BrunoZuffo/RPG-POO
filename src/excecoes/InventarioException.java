package excecoes;

public class InventarioException extends RuntimeException{

    public InventarioException(String mensagem){
        super(mensagem);
    }
}