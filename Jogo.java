import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private Queda queda;
    private Dupla d1, d2;
    private List<Jogador> lista_jogadores;
    private int primeiroJogadorIndice;

    private JFrame framePrincipal;
    private JLabel placarRot;
    private JPanel painelRodadaAtual; 

    public Jogo(Queda queda, Dupla dupla1, Dupla dupla2, JFrame frame) {
        this.queda = queda;
        this.d1 = dupla1;
        this.d2 = dupla2;
        this.framePrincipal = frame;

        this.lista_jogadores = new ArrayList<>();
        lista_jogadores.add(d1.getJ1());
        lista_jogadores.add(d2.getJ1());
        lista_jogadores.add(d1.getJ2());
        lista_jogadores.add(d2.getJ2());
        this.primeiroJogadorIndice = 0;
    }

    public void iniciarJogo() {
        d1.zerarTentos();
        d2.zerarTentos();
        prepararInterfaceJogo();
        iniciarNovaRodada();
    }

    private void prepararInterfaceJogo() {
        framePrincipal.getContentPane().removeAll();
        framePrincipal.setLayout(new BorderLayout());

        placarRot = new JLabel();
        placarRot.setHorizontalAlignment(SwingConstants.CENTER);
        placarRot.setFont(new Font("Arial", Font.BOLD, 20));
        atualizarPlacar();

        framePrincipal.add(placarRot, BorderLayout.NORTH);
    }

    private void iniciarNovaRodada() {

        if (painelRodadaAtual != null) {
            framePrincipal.remove(painelRodadaAtual);
        }

 
        Rodada rodada = new Rodada(this, d1, d2, lista_jogadores, primeiroJogadorIndice);
        painelRodadaAtual = rodada.getPainel();


        framePrincipal.add(painelRodadaAtual, BorderLayout.CENTER);
        framePrincipal.revalidate();
        framePrincipal.repaint();
    }

    public void rodadaTerminada(Dupla vencedora, int tentos) {
        vencedora.incrementaTentos(tentos);
        primeiroJogadorIndice = (primeiroJogadorIndice + 1) % 4;

        atualizarPlacar();

        if (d1.getTentos() >= 12) {
            finalizarJogo(d1);
        } else if (d2.getTentos() >= 12) {
            finalizarJogo(d2);
        } else {
            iniciarNovaRodada();
        }
    }

    private void finalizarJogo(Dupla vencedora) {
        JOptionPane.showMessageDialog(framePrincipal, "A dupla " + vencedora + " venceu o jogo!");
        vencedora.incrementaJogos();
        queda.jogoTerminado();
    }

    private void atualizarPlacar() {
        placarRot.setText(String.format("Placar: %s (%d) x (%d) %s",
                d1.toString(), d1.getTentos(), d2.getTentos(), d2.toString()));
    }
}
