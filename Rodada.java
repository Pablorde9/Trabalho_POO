import java.util.ArrayList;
import java.util.List;

public class Rodada {
    private Dupla dupla1;
    private int ganhou_d1 = 0;
    private Dupla dupla2;
    private int ganhou_d2 = 0;
    private List<Jogador> ordemJogadores;
    private Baralho baralho;
    int jogador_primeiro = 0;
    private int tentos_mao = 1; // tentos que a mao ta valendo

    public Rodada(Dupla dupla1, Dupla dupla2, List<Jogador> ordemJogadores, Baralho baralho) {
        this.dupla1 = dupla1;
        this.dupla2 = dupla2;
        this.ordemJogadores = ordemJogadores;
        this.baralho = baralho;
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
        for (int i = 0; i < 4; i++) {
            int indiceJogadores = (jogador_primeiro + i) % 4;
            Jogador jogadorAtual = this.ordemJogadores.get(indiceJogadores);

            Cartas cartaJogada = jogadorAtual.maiorCarta();
            jogadorAtual.jogarCarta(cartaJogada);
            jogadoresVaza[i] = jogadorAtual;
            cartasVaza[i] = cartaJogada;
            forcasVaza[i] = cartaJogada.getForca();

        }
        int maiorForca = 0;
        for (int forca : forcasVaza) {
            if (forca > maiorForca) {
                maiorForca = forca;
            }
        }
        List<Jogador> forcaMaxima = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (forcasVaza[i] == maiorForca) {
                forcaMaxima.add(jogadoresVaza[i]);
            }
        }
        if (forcaMaxima.size() == 1) {
            Jogador vencedor = forcaMaxima.get(0);
            atualizaVaza(vencedor);
            this.jogador_primeiro = this.ordemJogadores.indexOf(vencedor);
        } else {
            Dupla primeiraDupla = encontrarDupla(forcaMaxima.get(0));
            boolean empate = false;
               for (Jogador jogador : forcaMaxima) {
            if (!primeiraDupla.contemJogador(jogador)) {
                empate = true; // testando se o jogador que jogou a carta forte eh da outra dupla
                break;
            }
        }
        if (empate) {
            if(vaza == 0){
            }
        }
        }

    }

    private void atualizaVaza(Jogador jogador) {
        if (this.dupla1.contemJogador(jogador)) {
            this.ganhou_d1++;
        }
        if (this.dupla2.contemJogador(jogador)) {
            this.ganhou_d2++;
        }
    }

    private Dupla encontrarDupla(Jogador jogador) {
        if (this.dupla1.contemJogador(jogador)) {
            return this.dupla1;
        }
        return this.dupla2;
    }
}
