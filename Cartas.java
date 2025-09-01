// cartas do jogo, com o "nome", naipe e força para poder ser comparada facilmente
public enum Cartas {
    // manilhas
    QUATRO_PAUS("4", Naipes.PAUS, 14), // Zap
    SETE_COPAS("7", Naipes.COPAS, 13),
    AS_ESPADAS("ÁS", Naipes.ESPADAS, 12), // Espadilha
    SETE_OUROS("7", Naipes.OUROS, 11),

    // cartas normais
    TRES_PAUS("3", Naipes.PAUS, 10),
    TRES_COPAS("3", Naipes.COPAS, 10),
    TRES_ESPADAS("3", Naipes.ESPADAS, 10),
    TRES_OUROS("3", Naipes.OUROS, 10),

    DOIS_PAUS("2", Naipes.PAUS, 9),
    DOIS_COPAS("2", Naipes.COPAS, 9),
    DOIS_ESPADAS("2", Naipes.ESPADAS, 9),
    DOIS_OUROS("2", Naipes.OUROS, 9),

    AS_PAUS("ÁS", Naipes.PAUS, 8),
    AS_COPAS("ÁS", Naipes.COPAS, 8),
    AS_OUROS("ÁS", Naipes.OUROS, 8),

    REI_PAUS("REI", Naipes.PAUS, 7),
    REI_COPAS("REI", Naipes.COPAS, 7),
    REI_ESPADAS("REI", Naipes.ESPADAS, 7),
    REI_OUROS("REI", Naipes.OUROS, 7),

    VALETE_PAUS("VALETE", Naipes.PAUS, 6),
    VALETE_COPAS("VALETE", Naipes.COPAS, 6),
    VALETE_ESPADAS("VALETE", Naipes.ESPADAS, 6),
    VALETE_OUROS("VALETE", Naipes.OUROS, 6),

    DAMA_PAUS("DAMA", Naipes.PAUS, 5),
    DAMA_COPAS("DAMA", Naipes.COPAS, 5),
    DAMA_ESPADAS("DAMA", Naipes.ESPADAS, 5),
    DAMA_OUROS("DAMA", Naipes.OUROS, 5),

    SETE_PAUS("7", Naipes.PAUS, 4),
    SETE_ESPADAS("7", Naipes.ESPADAS, 4),
    SEIS_PAUS("6", Naipes.PAUS, 3),
    SEIS_COPAS("6", Naipes.COPAS, 3),
    SEIS_ESPADAS("6", Naipes.ESPADAS, 3),
    SEIS_OUROS("6", Naipes.OUROS, 3),

    CINCO_PAUS("5", Naipes.PAUS, 2),
    CINCO_COPAS("5", Naipes.COPAS, 2),
    CINCO_ESPADAS("5", Naipes.ESPADAS, 2),
    CINCO_OUROS("5", Naipes.OUROS, 2),

    QUATRO_COPAS("4", Naipes.COPAS, 1),
    QUATRO_ESPADAS("4", Naipes.ESPADAS, 1),
    QUATRO_OUROS("4", Naipes.OUROS, 1);

    private final String valor;
    private final Naipes naipe;
    private final int forca;

    Cartas(String valor, Naipes Naipes, int forca) {
        this.valor = valor;
        this.naipe = Naipes;
        this.forca = forca;
    }

    // funcoes de get
    public String getValor() {
        return valor;
    }

    public Naipes getNaipes() {
        return naipe;
    }

    public int getForca() {
        return forca;
    }
}
