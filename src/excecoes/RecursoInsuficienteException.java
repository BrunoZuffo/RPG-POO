package excecoes;

public class RecursoInsuficienteException extends RuntimeException{

    public RecursoInsuficienteException(String mensagem){ //construtor 
        super(mensagem);
    }
}