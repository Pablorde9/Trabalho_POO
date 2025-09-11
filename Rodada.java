import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rodada {

    // controle do jogo
    private Jogo jogo; // referencia o jogo superior
    private Dupla dupla1;
    private Dupla dupla2;
    private List<Jogador> ordemJogadores;
    private Baralho baralho;

    // controle da rodada
    private int vazaAtual;
    private int jogadorVezIndice;
    private int tentosDaMao;
    private int[] vazas; // 0: Empate, 1: Dupla1, 2: Dupla2

    // controle da vaza
    private List<Jogador> jogadoresDaVaza;
    private List<Cartas> cartasJogadasNaVaza;
    private Cartas maiorCartaNaVaza;
    private Jogador jogadorMaiorCarta;

    // elementos GUI
    private JPanel painelPrincipal;
    private JLabel jogadorDaVezRot;
    private JLabel maiorCartaVazaRot;
    private JComboBox<Cartas> cartasMaoGUI;
    private JButton jogarCarta;
    private JButton pedirTruco;

    public Rodada(Jogo jogo, Dupla d1, Dupla d2, List<Jogador> ordem, int primeiroJogadorId) {
        this.jogo = jogo;
        this.dupla1 = d1;
        this.dupla2 = d2;
        this.ordemJogadores = ordem;
        this.jogadorVezIndice = primeiroJogadorId;

        this.baralho = new Baralho();
        this.tentosDaMao = 1;
        this.vazas = new int[3];
        this.vazaAtual = 0;

        iniciarGUI();
        distribuirCartas();
        iniciarVaza();
    }

    private void distribuirCartas() {
        for (Jogador j : ordemJogadores) {
            j.limparMao();
            for (int i = 0; i < 3; i++) {
                j.receberCarta(baralho.comprarCarta());
            }
        }
    }

    private void iniciarVaza() {
        cartasJogadasNaVaza = new ArrayList<>();
        jogadoresDaVaza = new ArrayList<>();
        maiorCartaNaVaza = null;
        jogadorMaiorCarta = null;
        atualizarInterfaceParaProximoJogador();
    }

    // metodo que recebe a carta escolhida na interface
    private void processarJogada() {
        Jogador jogadorAtual = ordemJogadores.get(jogadorVezIndice);
        Cartas cartaJogada = (Cartas) cartasMaoGUI.getSelectedItem();

        if (cartaJogada == null)
            return;

        jogadorAtual.jogarCarta(cartaJogada);
        cartasJogadasNaVaza.add(cartaJogada);
        jogadoresDaVaza.add(jogadorAtual);

        if (maiorCartaNaVaza == null || cartaJogada.getForca() > maiorCartaNaVaza.getForca()) { // checa se a carta
                                                                                                // jogada eh a mais
                                                                                                // forte
            maiorCartaNaVaza = cartaJogada;
            jogadorMaiorCarta = jogadorAtual;
        }

        if (cartasJogadasNaVaza.size() == 4) { // se foi jogadas todas as cartas bora pra proxima vaza
            finalizarVaza();
        } else {
            jogadorVezIndice = (jogadorVezIndice + 1) % 4;
            atualizarInterfaceParaProximoJogador();
        }
    }

    private void finalizarVaza() {
        List<Jogador> vencedoresVaza = new ArrayList<>();
        for (int i = 0; i < cartasJogadasNaVaza.size(); i++) {
            if (cartasJogadasNaVaza.get(i).getForca() == maiorCartaNaVaza.getForca()) {
                vencedoresVaza.add(jogadoresDaVaza.get(i)); // pega os jogadores que jogaram as maiores cartas
            }
        }

        boolean empate = false;
        if (vencedoresVaza.size() > 1) {
            Dupla d1 = encontrarDupla(vencedoresVaza.get(0));
            Dupla d2 = encontrarDupla(vencedoresVaza.get(1));

            // tem mais de um vencedor e eles nao sao da mesma dupla
            if (!d1.equals(d2)) {
                empate = true;
            }
        }
        if (empate) {
            vazas[vazaAtual] = 0; // se deu emparte
        } else {
            Dupla vencedora = encontrarDupla(jogadorMaiorCarta);
            vazas[vazaAtual] = (vencedora == dupla1) ? 1 : 2; // colocar o numero da dupla que ganhou a vaza
            jogadorVezIndice = ordemJogadores.indexOf(jogadorMaiorCarta); // o proximo jogador eh o que ganhou
        }
        vazaAtual++;
        Dupla vencedorRodada = getVencedorDaRodada();
        if (vencedorRodada != null || vazaAtual == 3) {
            if (vencedorRodada == null) {
                vencedorRodada = (encontrarDupla(ordemJogadores.get(0)) == dupla1) ? dupla2 : dupla1;
            }
            jogo.rodadaTerminada(vencedorRodada, tentosDaMao);
        } else {
            iniciarVaza();
        }
    }

    private Dupla getVencedorDaRodada() {
        int vitoriasD1 = 0, vitoriasD2 = 0;
        for (int i = 0; i < vazaAtual; i++) {
            if (vazas[i] == 1)
                vitoriasD1++;
            if (vazas[i] == 2)
                vitoriasD2++;
        }

        if (vitoriasD1 >= 2)
            return dupla1;
        if (vitoriasD2 >= 2)
            return dupla2;

        if (vazas[0] == 1 && vazas[1] == 0)
            return dupla1;
        if (vazas[0] == 2 && vazas[1] == 0)
            return dupla2;
        if (vazas[0] == 0 && vazas[1] == 1)
            return dupla1;
        if (vazas[0] == 0 && vazas[1] == 2)
            return dupla2;

        if (vazaAtual == 3 && vitoriasD1 > vitoriasD2)
            return dupla1;
        if (vazaAtual == 3 && vitoriasD2 > vitoriasD1)
            return dupla2;

        return null;
    }

    private Dupla encontrarDupla(Jogador jogador) {
        if (dupla1.contemJogador(jogador)) {
            return dupla1;
        } else {
            return dupla2;
        }
    }

    // metodo que vai ser usado pelo jogo
    public JPanel getPainel() {
        return painelPrincipal;
    }
    //--------------metodos graficos
    private void atualizarInterfaceParaProximoJogador() {
        Jogador jogadorAtual = ordemJogadores.get(jogadorVezIndice);
        jogadorDaVezRot.setText("Vez de: " + jogadorAtual.getNome());

        if (maiorCartaNaVaza != null) {
            maiorCartaVazaRot.setText("Maior carta na vaza: " + maiorCartaNaVaza.toString());
        } else {
            maiorCartaVazaRot.setText("Seja o primeiro a jogar!");
        }

        cartasMaoGUI.removeAllItems();
        for (Cartas c : jogadorAtual.getMao()) {
            cartasMaoGUI.addItem(c);
        }
    }

    private void iniciarGUI() {
        painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        jogadorDaVezRot = new JLabel();
        jogadorDaVezRot.setFont(new Font("Arial", Font.BOLD, 16));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        painelPrincipal.add(jogadorDaVezRot, c);

        maiorCartaVazaRot = new JLabel();
        c.gridy = 1;
        painelPrincipal.add(maiorCartaVazaRot, c);

        cartasMaoGUI = new JComboBox<>();
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(cartasMaoGUI, c);

        jogarCarta = new JButton("Jogar Carta");
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.NONE;
        painelPrincipal.add(jogarCarta, c);

        pedirTruco = new JButton("TRUCO");
        pedirTruco.setForeground(Color.RED);
        c.gridx = 1;
        painelPrincipal.add(pedirTruco, c);

        jogarCarta.addActionListener(e -> processarJogada());
        pedirTruco.addActionListener(e -> {
            if(dupla1.getTentos() == 10 || dupla2.getTentos() == 10) {
                JOptionPane.showMessageDialog(painelPrincipal, encontrarDupla(ordemJogadores.get(jogadorVezIndice)).toString() + " pediu truco na mao de 10 e perdeu o jogo!");
                jogo.rodadaTerminada(encontrarDupla(ordemJogadores.get((jogadorVezIndice + 1) % 4)), (12 - encontrarDupla(ordemJogadores.get((jogadorVezIndice + 1) % 4)).getTentos()));
            } else {
                int resposta = JOptionPane.showConfirmDialog(painelPrincipal,
                    encontrarDupla(ordemJogadores.get((jogadorVezIndice + 1) % 4)) + ", aceita o TRUCO?",
                    "Pedido de Truco", JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    if (tentosDaMao == 1)
                        tentosDaMao = 3;
                    else
                        tentosDaMao += 3;
                    JOptionPane.showMessageDialog(painelPrincipal, "Truco aceito! A mão vale " + tentosDaMao + " pontos.");
                } else {
                    JOptionPane.showMessageDialog(painelPrincipal, "Truco recusado!");
                    jogo.rodadaTerminada(encontrarDupla(ordemJogadores.get(jogadorVezIndice)), tentosDaMao);
                }
            }
            
        });
    }
}
