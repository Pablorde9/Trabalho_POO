public class Jogo {
    private Dupla d1;
    private Dupla d2;
    private Baralho baralho;
    private Rodada rodada;

    public Jogo(Dupla dupla1, Dupla dupla2, Baralho b) {
        d1 = dupla1;
        d2 = dupla2;
        baralho = b;
    }

    void iniciarJogo() {
        rodada = new Rodada();
        d1.zerarTentos();
        d2.zerarTentos();
        while(d1.getTentos() < 12 && d2.getTentos() < 12) {
            rodada.iniciarRodada();
        }


        if(d1.getTentos() == 12) {
            d1.incrementaJogos();
        } else {
            d2.incrementaJogos();
        }
    }
}