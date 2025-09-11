import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

public class Jogo {
    private Dupla d1;
    private Dupla d2;
    private Rodada rodada;
    private List<Jogador> lista_jogadores = new ArrayList<>();
    private JFrame frame;

    public Jogo(Dupla dupla1, Dupla dupla2, JFrame f) {
        d1 = dupla1;
        d2 = dupla2;
        lista_jogadores.add(d1.getJ1());
        lista_jogadores.add(d2.getJ1());
        lista_jogadores.add(d1.getJ2());
        lista_jogadores.add(d2.getJ2());
        frame = f;
    }

    void iniciarJogo() {
        d1.zerarTentos();
        d2.zerarTentos();
        rodada = new Rodada(d1, d2, lista_jogadores, frame);
        while(d1.getTentos() < 12 && d2.getTentos() < 12) {
           rodada.jogarRodada();
           if (rodada.getVencedorDaRodada() == null) {
            break;
           }
           incrementaTentos(rodada.getVencedorDaRodada(), rodada.getTentosMao());
        }


        if(d1.getTentos() >= 12) {
            d1.incrementaJogos();
        } else {
            d2.incrementaJogos();
        }
    }

    void incrementaTentos(Dupla d, int v) {
        d.incrementaTentos(v);
    }
}