import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private ArrayList<Cartas> mao;

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
        mao.set(mao.indexOf(carta), null);
    }

    public Cartas maiorCarta() {
        Cartas a = mao.get(0);
        Cartas b = mao.get(1);
        Cartas c = mao.get(2);

        if (a == null && b == null) return c;
        if (a == null && c == null) return b;
        if (b == null && c == null) return a;

        if(a == null) a = b;
        if (b == null) b = a;
        if (c == null) c = a;

        if (a.getForca() >= b.getForca() && a.getForca() >= c.getForca()) {
            return a;
        } else if (b.getForca() >= a.getForca() && b.getForca() >= c.getForca()) {
            return b;

        } else {
            return c;
        }

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
