import java.util.Vector;


class Duplas {
    private Vector<Dupla> listaDuplas;

    public Duplas() {
        listaDuplas = new Vector<>();
    }

    void add(Dupla novaDupla) throws CadastroRepetidoException {
        for (Dupla d : listaDuplas) {
            if ((d.getJ1().equals(novaDupla.getJ1()) || d.getJ1().equals(novaDupla.getJ2()))
                    && (d.getJ2().equals(novaDupla.getJ1()) || d.getJ2().equals(novaDupla.getJ2()))) {
                throw new CadastroRepetidoException(
                        "Dupla " + novaDupla.getJ1() + " e " + novaDupla.getJ2() + " ja esta cadastrada");
            }
        }
        listaDuplas.add(novaDupla);
    }
}

class Dupla {
    private Jogador j1;
    private Jogador j2;
    private int quedasT;
    private int tentos;
    private int quedasG;
    private int jogosVencidos;

    public Dupla(Jogador n1, Jogador n2) {
        j1 = n1;
        j2 = n2;
        quedasT = 0;
        quedasG = 0;
        tentos = 0;
        jogosVencidos = 0;
    }

    public boolean contemJogador(Jogador jogador) {
        return this.j1.equals(jogador) || this.j2.equals(jogador);
    }

    void zerarTentos() {
        tentos = 0;
    }

    void zerarJogos() {
        jogosVencidos = 0;
    }

    void incrementaTentos(int valor) {
        tentos += valor;
    }

    void incrementaJogos() {
        jogosVencidos++;
    }

    void attQuedas(boolean ganha) {
        quedasT++;
        if (ganha)
            quedasG++;
    }

    Jogador getJ1() {
        return j1;
    }

    Jogador getJ2() {
        return j2;
    }

    public int getQuedasT() {
        return quedasT;
    }

    public int getQuedasG() {
        return quedasG;
    }

    public int getTentos() {
        return tentos;
    }

    public int getJogosVencidos() {
        return jogosVencidos;
    }

    @Override
    public String toString() {
        return j1.getNome() + " e " + j2.getNome();
    }
}
