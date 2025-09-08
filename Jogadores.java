import java.util.*;

class Jogadores {
    private Vector<String> listaJogadores;

    public Jogadores() {
        listaJogadores = new Vector<>();
    }

    void add(String novoJogador) throws CadastroRepetidoException{
        for(String n : listaJogadores) {
            if(n.toLowerCase().equals(novoJogador.toLowerCase())) throw new CadastroRepetidoException("Jogador " + n + " ja esta cadastrado"); 
        }
        
        // else
        listaJogadores.add(novoJogador);
    }

    
}
