package personagens;


//nao podemos criar um objeto diretamente da fabricaPersonagem pq nao sabemos qual objeto será criado de fato
public abstract class FabricaPersonagem {

    
    public abstract Personagem criar(); //vamos criar é um metodo que vai retornar um objeto do tipo Personagem

    public static FabricaPersonagem[] opcoes() { //construtor 
        return new FabricaPersonagem[]{
            new FabricaGuerreiro(),
            new FabricaMago(),
            new FabricaAnao(),
            new FabricaElfo(),
            new FabricaLadino(),
            new FabricaPaladino(),
            new FabricaBarbaro(),
            new FabricaDruida(),
            new FabricaMonge()
        };
    }


    //fábricas especificas para cada personagem
    private static class FabricaGuerreiro extends FabricaPersonagem {
        @Override //sobrescrevendo algo
        public Personagem criar() {
            return new Guerreiro();
        }
    }

    private static class FabricaMago extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Mago();
        }
    }

    private static class FabricaAnao extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Anão();
        }
    }

    private static class FabricaElfo extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Elfo();
        }
    }

    private static class FabricaLadino extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Ladino();
        }
    }

    private static class FabricaPaladino extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Paladino();
        }
    }

    private static class FabricaBarbaro extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Barbaro();
        }
    }

    private static class FabricaDruida extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Druida();
        }
    }

    private static class FabricaMonge extends FabricaPersonagem {
        @Override
        public Personagem criar() {
            return new Monge();
        }
    }
}