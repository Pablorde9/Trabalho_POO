import javax.swing.JFrame;

public class Queda {
    private Dupla d1;
    private Dupla d2;
    private Jogo jogo;
    private JFrame frame;

    public Queda(Dupla dupla1, Dupla dupla2, JFrame f) {
        d1 = dupla1;
        d2 = dupla2;
        frame = f;
    }

    void iniciarQueda() {
        zeraAcc();
        jogo = new Jogo(d1, d2, frame);

        while (d1.getjogos() < 2 && d2.getjogos() < 2) {
            jogo.iniciarJogo();
        }

        if (d1.getjogos() == 2) {
            this.attQuedas(d1, d2);
        } else {
            this.attQuedas(d2, d1);
        }
    }

    void zeraAcc() {
        d1.zerarJogos();
        d2.zerarJogos();
    }

    void attQuedas(Dupla ganhadora, Dupla perdedora) {
        perdedora.attQuedas(false);
        ganhadora.attQuedas(true);
    }
    


    
}
