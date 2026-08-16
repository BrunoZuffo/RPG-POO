package inventario;

public enum Item {
    MAO("Mão", 0, 0, 0, 0, 0),

    ADAGA("Adaga", 3, 0, 25, 2, 0),

    ESPADA_CURTA("Espada Curta", 5, 0, 40, 4, 0),
    ESPADA_LONGA("Espada Longa", 8, 0, 70, 6, 0),
    MACHADO("Machado", 10, 0, 90, 7, 0),
    MARTELO_DE_GUERRA("Martelo de Guerra", 12, 0, 120, 9, 0),

    CAJADO_SIMPLES("Cajado Simples", 4, 0, 35, 5, 0),
    CAJADO_MAGICO("Cajado Mágico", 7, 0, 65, 5, 0),
    CAJADO_ANCESTRAL("Cajado Ancestral", 10, 0, 110, 6, 0),

    ARCO("Arco", 6, 0, 55, 6, 0),

    POCAO_PEQUENA("Poção Pequena", 0, 20, 20, 1, 0),
    POCAO_MEDIA("Poção Média", 0, 40, 40, 2, 0),
    POCAO_GRANDE("Poção Grande", 0, 65, 70, 3, 0),
    POCAO_SUPREMA("Poção Suprema", 0, 100, 120, 4, 0),

    ERVA_MEDICINAL("Erva Medicinal", 0, 10, 8, 1, 0),
    PAO("Pão", 0, 5, 3, 1, 0),
    CARNE_ASSADA("Carne Assada", 0, 15, 12, 2, 0),

    ELIXIR("Elixir", 3, 25, 45, 2, 0),

    ESPADA_LENDARIA("Espada Lendária", 15, 0, 180, 7, 0),
    CAJADO_LENDARIO("Cajado Lendário", 15, 0, 180, 6, 0),

    BOLSA_PEQUENA("Bolsa Pequena", 0, 0, 35, 2, 8),
    BOLSA_MEDIA("Bolsa Média", 0, 0, 70, 3, 15),
    BOLSA_GRANDE("Bolsa Grande", 0, 0, 120, 5, 25),
    MOCHILA_AVENTUREIRO("Mochila do Aventureiro", 0, 0, 180, 6, 35),
    BOLSA_MAGICA("Bolsa Mágica", 0, 0, 300, 8, 50);

    private String nome;
    private int dano;
    private int valor;
    private int cura;
    private int tamanho;
    private int armazenamento_extra;

    Item(String nome, int dano, int cura, int valor, int tamanho, int armazenamento_extra){
        this.nome=nome;
        this.dano=dano;
        this.valor=valor;
        this.cura=cura;
        this.tamanho=tamanho;
        this.armazenamento_extra=armazenamento_extra;
    }

    public int getDano(){
        return this.dano;
    }

    public int getCura(){
        return this.cura;
    }

    public int getValor(){
        return this.valor;
    }

    public int getTamanho(){
        return this.tamanho;
    }

    public int getArmazenamentoExtra(){
        return this.armazenamento_extra;
    }
}
