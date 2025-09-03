import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Baralho {
    private ArrayList<Cartas> baralho = new ArrayList<>();

    public Baralho() {
        montarBaralho();
        embaralhar();
    }

    private void montarBaralho() {
        baralho.addAll(Arrays.asList(Cartas.values()));
    }

    void embaralhar() {
        Collections.shuffle(this.baralho);
    }

    public void imprime() {
        System.out.println("--- Baralho ---");
        for (Cartas carta : baralho) {
            System.out.println(carta.getTudo());
        }
    }
}
