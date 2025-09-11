import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class stringtoGUI {
    String text;

    public stringtoGUI(String txt) {
        text = txt;
    }

    void copy(String txt) {
        text = txt;
    }

    String getText() {
        return text;
    }
}

class inttoGUI {
    int valor;

    public inttoGUI(int v) {
        valor = v;
    }

    void copy(int v) {
        valor = v;
    }

    int getValor() {
        return valor;
    }
}



public class Rodada {
    private Dupla dupla1;
    private Dupla dupla2;
    int[] vazas;
    private List<Jogador> ordemJogadores;
    private Baralho baralho = new Baralho();
    int jogador_primeiro = 0;
    private int tentos_mao = 1; // tentos que a mao ta valendo
    private Boolean it_loop = true;


    // elementos GUI
    private JFrame frame;
    private JLabel jgAtual;
    private JLabel jg;
    private JLabel maiorcarta;
    private JPanel painel;
    private JButton jogarCarta;
    private JButton pedirTruco;
    private JComboBox<Cartas> cartasMaoGUI;
    private GridBagConstraints alinhamento;

    public Rodada(Dupla dupla1, Dupla dupla2, List<Jogador> ordemJogadores, JFrame f) {
        this.dupla1 = dupla1;
        this.dupla2 = dupla2;
        this.ordemJogadores = ordemJogadores;
        this.vazas = new int[3];
        frame = f;
        iniciarGUI();
        frame.setVisible(true);
    }

    public void jogarRodada() {
        baralho = new Baralho();
        distribuirCartas();
        for (int i = 0; i < 3; i++) {
            jogarVaza(i);
        }
    }

    private void distribuirCartas() {
        for (Jogador j : ordemJogadores) {
            j.limparMao();
            for (int i = 0; i < 3; i++) {
                j.receberCarta(baralho.comprarCarta());
            }
        }
    }

    // pedir truco e tambem 6 9 12
    public void pedirTruco() {
        if (this.tentos_mao == 1) {
            this.tentos_mao = 3;
        } else {
            this.tentos_mao += 3;
        }
    }

    private void jogarVaza(int vaza) {
        Jogador[] jogadoresVaza = new Jogador[4];
        Cartas[] cartasVaza = new Cartas[4];
        int[] forcasVaza = new int[4];
        inttoGUI maiorForcaGUI = new inttoGUI(0);

        stringtoGUI maiorCarta = new stringtoGUI("");
        stringtoGUI maiorJg = new stringtoGUI("");
        for (int i = 0; i < 4; i++) {

            inttoGUI iterador = new inttoGUI(i);
            inttoGUI chegou_final = new inttoGUI(0);

            cartasMaoGUI.removeAllItems();
            int indiceJogadores = (jogador_primeiro + i) % 4;
            Jogador jogadorAtual = this.ordemJogadores.get(indiceJogadores);

            for(Cartas c : jogadorAtual.getMao()) {
                cartasMaoGUI.addItem(c);
            }
            
            
            jgAtual.setText(jogadorAtual.getNome());
            maiorcarta.setText("maior carta: " + maiorCarta.getText() + maiorJg.getText());

            
            jogarCarta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Cartas cartaJogada = (Cartas) cartasMaoGUI.getSelectedItem();

                    jogadoresVaza[iterador.getValor()] = jogadorAtual;
                    cartasVaza[iterador.getValor()] = cartaJogada;
                    forcasVaza[iterador.getValor()] = cartaJogada.getForca();

                    if(forcasVaza[iterador.getValor()] > maiorForcaGUI.getValor()) {maiorForcaGUI.copy(forcasVaza[iterador.getValor()]); maiorCarta.copy(cartaJogada.getTudo()); maiorJg.copy(("(" + jogadorAtual.getNome() + ")"));}

                    jogadorAtual.jogarCarta(cartaJogada);
                    chegou_final.copy(1);


                }
            });
            
            while(it_loop) {
                if (chegou_final.getValor() == 1) break;
            }

        }
        cartasMaoGUI.removeAllItems();

        List<Jogador> forcaMaxima = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (forcasVaza[i] == maiorForcaGUI.getValor()) {
                forcaMaxima.add(jogadoresVaza[i]);
            }
        }
        if (forcaMaxima.size() == 1) {
            Jogador vencedor = forcaMaxima.get(0);
            atualizaVaza(vencedor, vaza);
            this.jogador_primeiro = this.ordemJogadores.indexOf(vencedor);
        } else {
            // varios jogadores jogaram grande
            Dupla primeiraDupla = encontrarDupla(forcaMaxima.get(0));
            boolean empate = false;
            for (Jogador jogador : forcaMaxima) {
                if (!primeiraDupla.contemJogador(jogador)) {
                    empate = true; // tem um jogador de outra dupla que jogou grande
                    break;
                }
            }

            if (empate) {
                this.vazas[vaza] = 0;

            } else {

                Jogador vencedorDaDupla = forcaMaxima.get(0);
                atualizaVaza(vencedorDaDupla, vaza);
                this.jogador_primeiro = this.ordemJogadores.indexOf(vencedorDaDupla);
            }
        }
    }

    private void atualizaVaza(Jogador jogador, int i) {
        if (this.dupla1.contemJogador(jogador)) {
            this.vazas[i] = 1;
        }
        if (this.dupla2.contemJogador(jogador)) {
            this.vazas[i] = 2;
        }
    }

    private Dupla encontrarDupla(Jogador jogador) {
        if (this.dupla1.contemJogador(jogador)) {
            return this.dupla1;
        }
        return this.dupla2;
    }

    // funcao para as superiores saberem quem ganhou a rodada
    public Dupla getVencedorDaRodada() {
        // Ganhou a primeira vaza e empatou a segunda
        if (vazas[0] == 1 && vazas[1] == 0)
            return dupla1;
        if (vazas[0] == 2 && vazas[1] == 0)
            return dupla2;

        // Empatou a primeira vaza e ganhou a segunda
        if (vazas[0] == 0 && vazas[1] == 1)
            return dupla1;
        if (vazas[0] == 0 && vazas[1] == 2)
            return dupla2;

        // ve quem chegou em 2 vitorias
        int vitoriasDupla1 = 0;
        int vitoriasDupla2 = 0;
        for (int resultado : vazas) {
            if (resultado == 1)
                vitoriasDupla1++;
            if (resultado == 2)
                vitoriasDupla2++;
        }
        if (vitoriasDupla1 >= 2)
            return dupla1;
        if (vitoriasDupla2 >= 2)
            return dupla2;

        // se empatou na 3, quem ganhou a primeira ganha
        if (vazas[0] == 1)
            return dupla1;
        if (vazas[0] == 2)
            return dupla2;

        // empatou primeira e segunda, ganhou terceira
        if(vazas[2] == 1) return dupla1;
        if(vazas[2] == 2) return dupla2;

        // se empatou tudo
        if (vazas[0] == 0 && vazas[1] == 0 && vazas[2] == 0) {
            // o cara que abriu que ganha
            Jogador mao = this.ordemJogadores.get(0);
            return encontrarDupla(mao);
        }
        
        return null; // se tudo der errado, tiver algum caso que nao cobri
    }

    public int getTentosMao() {
        return tentos_mao;
    }

    void iniciarGUI() {
        jg = new JLabel("jogador atual:");
        jg.setFont(new Font("Arial", Font.PLAIN, 13));

        jgAtual = new JLabel();
        jgAtual.setFont(new Font("Arial", Font.PLAIN, 16));

        maiorcarta = new JLabel("maior carta: ");
        maiorcarta.setFont(new Font("Arial", Font.BOLD, 12));

        jogarCarta = new JButton("Jogar Carta");
        pedirTruco = new JButton("Truco");

        cartasMaoGUI = new JComboBox<>();
        cartasMaoGUI.setSize(100, 100);
        
        painel = new JPanel(new GridBagLayout());
        GridBagConstraints alinhamento = new GridBagConstraints();

        alinhamento.gridx = 0;
        alinhamento.gridy = 0;
        alinhamento.insets = new Insets(10, 0, 10, 0);
        painel.add(jg, alinhamento);

        alinhamento.gridy = 1;
        painel.add(jgAtual, alinhamento);

        alinhamento.gridy = 2;
        painel.add(maiorcarta, alinhamento);

        alinhamento.gridy = 3;
        painel.add(cartasMaoGUI, alinhamento);

        alinhamento.gridy = 4;
        painel.add(pedirTruco, alinhamento);

        alinhamento.gridx = 1;
        painel.add(jogarCarta, alinhamento);

        frame.add(painel);
        

    }


}
