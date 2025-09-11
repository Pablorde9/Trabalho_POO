import java.util.ArrayList;
import java.util.List;

public class Rodada {
    private Dupla dupla1;
    private Dupla dupla2;
    int[] vazas;
    private List<Jogador> ordemJogadores;
    private Baralho baralho = new Baralho();
    int jogador_primeiro = 0;
    private int tentos_mao = 1; // tentos que a mao ta valendo

    public Rodada(Dupla dupla1, Dupla dupla2, List<Jogador> ordemJogadores) {
        this.dupla1 = dupla1;
        this.dupla2 = dupla2;
        this.ordemJogadores = ordemJogadores;
        this.vazas = new int[3];
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

}
