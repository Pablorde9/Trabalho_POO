import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Cartas> mao;

    public Jogador(String n) {
        this.nome = n;
        this.mao = new ArrayList<>();
    }

    public void receberCarta(Cartas carta) {
        if (this.mao.size() < 3) {
            this.mao.add(carta);
        }
    }

    public void jogarCarta(Cartas carta) {
        this.mao.remove(carta);
    }

    public void limparMao() {
        this.mao.clear();
    }

    public String getNome() {
        return this.nome;
    }

    public List<Cartas> getMao() {
        return new ArrayList<>(this.mao);
    }

    @Override
    public String toString() {
        return getNome();
    }
}
