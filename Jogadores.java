import java.util.*;

public class Jogadores {
    private Vector<Jogador> listaJogadores;

    public Jogadores() {
        listaJogadores = new Vector<>();
    }

    void add(Jogador novoJogador) throws CadastroRepetidoException {
        for (Jogador j : listaJogadores) {
            if (j.getNome().equalsIgnoreCase(novoJogador.getNome()))
                throw new CadastroRepetidoException("Jogador " + j.getNome() + " ja esta cadastrado");
        }

        // else
        listaJogadores.add(novoJogador);
    }
}
