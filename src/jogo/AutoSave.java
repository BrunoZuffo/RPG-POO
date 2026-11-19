package jogo;

public class AutoSave extends Thread{ //extends Thread cria nossa prova thread ( subtarefa de um processo maior )
                                      //é a menor sequência de instruções que um processador pode gerenciar e executar de forma independente dentro de um programa.
    private final Runnable tarefa; //operação que a thread vai fazer
    private final long intervaloMs; //aguarda um intervalo sem consumir cpu em um loop
    private final Object controle = new Object(); 
    private boolean ativo = true;

    public AutoSave(Runnable tarefa, long intervaloMs) { //construtor
        this.tarefa = tarefa;
        this.intervaloMs = intervaloMs;

        setDaemon(true); //evita que a thread sozinha impeça o encerramento da jvm
        setName("Autosave-RPG");
    }

    @Override
    public void run() {
        while (true) {
            synchronized (controle) {
                if (!ativo) {
                    return;
                }

                try {
                    controle.wait(intervaloMs);
                }
                catch (InterruptedException erro) {
                    Thread.currentThread().interrupt();
                    return;
                }

                if (!ativo) {
                    return;
                }
            }

            tarefa.run();
        }
    }

    public void encerrar() {
        synchronized (controle) {
            ativo = false;
            controle.notifyAll();
        }

        try {
            join(); //aguarda a thread terminar, evitando que um autosave antigo continue após sair da partida.
        }
        catch (InterruptedException erro) {
            Thread.currentThread().interrupt();
        }
    }
}