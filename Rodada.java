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
    private Dupla duplaQueTrucou; // controla quem ta trucando
    private int valorPropostaTruco; // variavel para mudar o valor do botao de trucar

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
        this.duplaQueTrucou = null;
        this.valorPropostaTruco = 3;

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

        // alguem fez 2 vazas
        if (vitoriasD1 >= 2)
            return dupla1;
        if (vitoriasD2 >= 2)
            return dupla2;

        // testa empate
        if (vazaAtual >= 2) {
            // ganhou a primeira e empatou a segunda
            if (vazas[0] == 1 && vazas[1] == 0)
                return dupla1;
            if (vazas[0] == 2 && vazas[1] == 0)
                return dupla2;

            // empatou a primeira e ganhou a segunda
            if (vazas[0] == 0 && vazas[1] == 1)
                return dupla1;
            if (vazas[0] == 0 && vazas[1] == 2)
                return dupla2;
        }

        // se tudo empatar quem ganhou a primeira ganha
        if (vazaAtual == 3 && vitoriasD1 == 1 && vitoriasD2 == 1) {
            if (vazas[0] == 1)
                return dupla1;
            if (vazas[0] == 2)
                return dupla2;
        }

        // ainda nao decidiu
        return null;
    }

    private Dupla encontrarDupla(Jogador jogador) {
        if (dupla1.contemJogador(jogador)) {
            return dupla1;
        } else {
            return dupla2;
        }
    }

    private void atualizarBotaoTruco() {
        Jogador jogadorAtual = ordemJogadores.get(jogadorVezIndice);
        Dupla duplaAtual = encontrarDupla(jogadorAtual);

        // checa se alguem ja trucou e se a mesma dupla ta trucando duas vezes
        if (duplaQueTrucou == null || !duplaQueTrucou.equals(duplaAtual)) {
            // checa se vai passar dos pontos pra vencer
            int pontosParaVencerD1 = 12 - dupla1.getTentos();
            int pontosParaVencerD2 = 12 - dupla2.getTentos();

            // se o truco for passar nao pode trucar mais
            if (tentosDaMao >= pontosParaVencerD1 || tentosDaMao >= pontosParaVencerD2) {
                pedirTruco.setEnabled(false);
                pedirTruco.setText("TRUCO");
                return;
            }

            // se tudo der certo muda o valor para trucar
            switch (valorPropostaTruco) {
                case 3:
                    pedirTruco.setText("TRUCO");
                    break;
                case 6:
                    pedirTruco.setText("PEDIR 6");
                    break;
                case 9:
                    pedirTruco.setText("PEDIR 9");
                    break;
                case 12:
                    pedirTruco.setText("PEDIR 12");
                    break;
                default:
                    // se for 12, desativa
                    pedirTruco.setEnabled(false);
                    return;
            }
            pedirTruco.setEnabled(true);
        } else {
            // nao pode trucar duas vezes
            pedirTruco.setEnabled(false);
        }
    }

    // metodo que vai ser usado pelo jogo
    public JPanel getPainel() {
        return painelPrincipal;
    }

    // --------------metodos graficos
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
        atualizarBotaoTruco();
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
            Dupla duplaTrucou = encontrarDupla(ordemJogadores.get(jogadorVezIndice));// o jogador que pede o truco eh o
                                                                                     // atual
            Dupla duplaAdv;
            if (duplaTrucou == dupla1) {
                duplaAdv = dupla2;
            } else {
                duplaAdv = dupla1;
            }
            if (duplaTrucou.getTentos() >= 10 || duplaAdv.getTentos() >= 10) {
                JOptionPane.showMessageDialog(painelPrincipal,
                        duplaTrucou.toString() + " pediram truco na mão de dez e perdeu o jogo! :(");
                int pontosParaVencer = 12 - duplaAdv.getTentos();
                jogo.rodadaTerminada(duplaAdv, pontosParaVencer > 0 ? pontosParaVencer : 1);
            } else {

                String pergunta = "A dupla " + duplaAdv + " aceita o pedido de " + pedirTruco.getText() + "?";

                int resposta = JOptionPane.showConfirmDialog(painelPrincipal, pergunta,
                        "Pedido de " + pedirTruco.getText(), JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    tentosDaMao = valorPropostaTruco;
                    duplaQueTrucou = duplaTrucou;

                    if (valorPropostaTruco < 12) {
                        valorPropostaTruco += 3;
                    }

                    JOptionPane.showMessageDialog(painelPrincipal,
                            "Aceito! A mão agora vale " + tentosDaMao + " pontos.");
                    atualizarBotaoTruco();

                } else {
                    // se recusar a dupla ganha os pontos da aposta anterior
                    int pontosGanhos;
                    if (tentosDaMao == 1) {
                        pontosGanhos = 1;
                    } else {
                        pontosGanhos = valorPropostaTruco - 3;
                    }

                    JOptionPane.showMessageDialog(painelPrincipal,
                            "Recusado! A dupla " + duplaTrucou
                                    + " ganhou " + pontosGanhos + " pontos");
                    jogo.rodadaTerminada(duplaTrucou, pontosGanhos);
                }
            }
        });
    }
}
