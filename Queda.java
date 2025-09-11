import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Queda {
    private MenuInterface menu;
    private Dupla d1;
    private Dupla d2;
    private JFrame frame;

    public Queda(MenuInterface menu, Dupla dupla1, Dupla dupla2, JFrame f) {
        this.menu = menu;
        this.d1 = dupla1;
        this.d2 = dupla2;
        this.frame = f;
    }

    public void iniciarQueda() {
        d1.zerarJogos();
        d2.zerarJogos();
        iniciarNovoJogo();
    }

    private void iniciarNovoJogo() {
        Jogo jogo = new Jogo(this, d1, d2, frame);
        jogo.iniciarJogo();
    }


    public void jogoTerminado() {

        if (d1.getJogosVencidos() >= 2) {
            finalizarQueda(d1, d2);
        } else if (d2.getJogosVencidos() >= 2) {
            finalizarQueda(d2, d1);
        } else {
            iniciarNovoJogo();
        }
    }

    private void finalizarQueda(Dupla ganhadora, Dupla perdedora) {
        ganhadora.attQuedas(true);
        perdedora.attQuedas(false);
        JOptionPane.showMessageDialog(frame, "FIM DE QUEDA! Vencedor: " + ganhadora);
        frame.setVisible(false);
        menu.quedaTerminada();
    }
}
