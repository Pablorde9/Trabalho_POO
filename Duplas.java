import java.util.*;

class Duplas {
    private Vector<Dupla> listaDuplas;

    public Duplas() {
        listaDuplas = new Vector<>();
    }

    void add(Dupla novaDupla) throws CadastroRepetidoException{
        for(Dupla d : listaDuplas) {
            if((d.getJ1().equals(novaDupla.getJ1()) || d.getJ1().equals(novaDupla.getJ2())) && (d.getJ2().equals(novaDupla.getJ1()) || d.getJ2().equals(novaDupla.getJ2()))) {
                throw new CadastroRepetidoException("Dupla " + novaDupla.getJ1() + " e " + novaDupla.getJ2() + " ja esta cadastrada");
            }
        }
        // else
        listaDuplas.add(novaDupla);
    }
}

class Dupla {
    private String j1;
    private String j2;
    private int quedasT;
    private int quedasG;

    public Dupla(String n1, String n2) {
        j1 = n1;
        j2 = n2;
        quedasT = 0;
        quedasG = 0;
    }

    void attQuedas(boolean ganha) {
        quedasT += 1;
        if (ganha) quedasG += 1;   
    }

    String getJ1() {
        return j1;
    }

    String getJ2() {
        return j2;
    }

}
