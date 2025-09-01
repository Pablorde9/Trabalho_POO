// Naipes do Baralho
public enum Naipes {
    PAUS("Paus"),
    COPAS("Copas"),
    ESPADAS("Espadas"),
    OUROS("Ouros");

    // final garante que os naipes nao seram mudados no correr do programa
    private final String nome;

    // funcao de get
    Naipes(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
