import javax.swing.*;
import java.awt.*;


class MenuInterface {

    // Frames
    private JFrame frameMenu;
    private JFrame frameJogador;
    private JFrame frameDupla;
    private JFrame frameJogoSetup;
    private JFrame frameQueda;


    private Jogadores listaJogadores;
    private Duplas listaDuplas;


    private JTextField novoJogadorTxt;
    private JComboBox<Object> duplaJ1, duplaJ2; 


    private JComboBox<Object> ldupla1, ldupla2; 

    public MenuInterface() {
        listaJogadores = new Jogadores();
        listaDuplas = new Duplas();
        criarMenuPrincipal();
        criarFrameCadastroJogador();
        criarFrameCadastroDupla();
        criarFrameNovoJogo();
        criarFrameQueda();
    }

    public void exibirMenu() {
        frameMenu.setVisible(true);
    }


    public void quedaTerminada() {
        frameQueda.setVisible(false);
        frameMenu.setVisible(true);
    }

    private void criarMenuPrincipal() {
        frameMenu = new JFrame("MENU");
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setSize(300, 400);
        frameMenu.setLocationRelativeTo(null);

        JPanel painelMenu = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);
        c.fill = GridBagConstraints.HORIZONTAL;

        JButton novoJogadorBtn = new JButton("Cadastrar Jogador");
        novoJogadorBtn.addActionListener(e -> frameJogador.setVisible(true));
        c.gridy = 0;
        painelMenu.add(novoJogadorBtn, c);

        JButton novaDuplaBtn = new JButton("Cadastrar Dupla");
        novaDuplaBtn.addActionListener(e -> frameDupla.setVisible(true));
        c.gridy = 1;
        painelMenu.add(novaDuplaBtn, c);

        JButton novoJogoBtn = new JButton("Iniciar Jogo");
        novoJogoBtn.addActionListener(e -> frameJogoSetup.setVisible(true));
        c.gridy = 2;
        painelMenu.add(novoJogoBtn, c);

        frameMenu.add(painelMenu);
    }

    private void criarFrameCadastroJogador() {
        frameJogador = new JFrame("Novo Jogador");
        frameJogador.setSize(500, 400);
        frameJogador.setLocationRelativeTo(null);
        frameJogador.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        painel.add(new JLabel("Digite o nome do jogador:"), c);

        c.gridy = 1;
        novoJogadorTxt = new JTextField(20);
        painel.add(novoJogadorTxt, c);

        c.gridy = 2;
        JButton cadastrarBtn = new JButton("Cadastrar");
        cadastrarBtn.addActionListener(e -> cadastrarJogador());
        painel.add(cadastrarBtn, c);

        frameJogador.add(painel);
    }

    private void cadastrarJogador() {
        try {
            if (novoJogadorTxt.getText().isEmpty())
                throw new CampoVazioException("Digite um nome válido");

            Jogador novoJogador = new Jogador(novoJogadorTxt.getText());
            listaJogadores.add(novoJogador);

            JOptionPane.showMessageDialog(frameJogador, "Jogador " + novoJogador.getNome() + " cadastrado!");

   
            duplaJ1.addItem(novoJogador);
            duplaJ2.addItem(novoJogador);

            novoJogadorTxt.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frameJogador, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarFrameCadastroDupla() {
        frameDupla = new JFrame("Nova Dupla");
        frameDupla.setSize(500, 400);
        frameDupla.setLocationRelativeTo(null);
        frameDupla.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridy = 0;
        painel.add(new JLabel("Escolha os jogadores da dupla:"), c);

        duplaJ1 = new JComboBox<>();
        duplaJ1.addItem("Selecione o primeiro jogador");
        c.gridy = 1;
        painel.add(duplaJ1, c);

        duplaJ2 = new JComboBox<>();
        duplaJ2.addItem("Selecione o segundo jogador");
        c.gridy = 2;
        painel.add(duplaJ2, c);

        JButton cadastrarBtn = new JButton("Cadastrar Dupla");
        cadastrarBtn.addActionListener(e -> cadastrarDupla());
        c.gridy = 3;
        painel.add(cadastrarBtn, c);

        frameDupla.add(painel);
    }

    private void cadastrarDupla() {
        try {
            if (duplaJ1.getSelectedIndex() == 0 || duplaJ2.getSelectedIndex() == 0)
                throw new CampoVazioException("Selecione dois jogadores.");
            if (duplaJ1.getSelectedItem() == duplaJ2.getSelectedItem())
                throw new MembroDuplicadoException("Os jogadores devem ser diferentes.");

            Jogador j1 = (Jogador) duplaJ1.getSelectedItem();
            Jogador j2 = (Jogador) duplaJ2.getSelectedItem();
            Dupla novaDupla = new Dupla(j1, j2);
            listaDuplas.add(novaDupla);

            JOptionPane.showMessageDialog(frameDupla, "Dupla " + novaDupla + " cadastrada!");

            ldupla1.addItem(novaDupla);
            ldupla2.addItem(novaDupla);

            duplaJ1.setSelectedIndex(0);
            duplaJ2.setSelectedIndex(0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frameDupla, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarFrameNovoJogo() {
        frameJogoSetup = new JFrame("Novo Jogo");
        frameJogoSetup.setSize(500, 400);
        frameJogoSetup.setLocationRelativeTo(null);
        frameJogoSetup.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        c.gridy = 0;
        painel.add(new JLabel("Escolha as duplas que irão jogar:"), c);

        ldupla1 = new JComboBox<>();
        ldupla1.addItem("Selecione a primeira dupla");
        c.gridy = 1;
        painel.add(ldupla1, c);

        ldupla2 = new JComboBox<>();
        ldupla2.addItem("Selecione a segunda dupla");
        c.gridy = 2;
        painel.add(ldupla2, c);

        JButton iniciarBtn = new JButton("INICIAR QUEDA");
        iniciarBtn.addActionListener(e -> iniciarQueda());
        c.gridy = 3;
        painel.add(iniciarBtn, c);

        frameJogoSetup.add(painel);
    }

    private void iniciarQueda() {
        try {
            if (ldupla1.getSelectedIndex() == 0 || ldupla2.getSelectedIndex() == 0)
                throw new CampoVazioException("Selecione duas duplas.");
            if (ldupla1.getSelectedItem() == ldupla2.getSelectedItem())
                throw new MembroDuplicadoException("As duplas devem ser diferentes.");

            Dupla d1 = (Dupla) ldupla1.getSelectedItem();
            Dupla d2 = (Dupla) ldupla2.getSelectedItem();

            frameMenu.setVisible(false);
            frameJogoSetup.setVisible(false);
            frameQueda.setVisible(true);

            Queda queda = new Queda(this, d1, d2, frameQueda);
            queda.iniciarQueda();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frameJogoSetup, "Erro: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarFrameQueda() {
        frameQueda = new JFrame("TRUCO");
        frameQueda.setSize(800, 600);
        frameQueda.setLocationRelativeTo(null);
        frameQueda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


public class Menu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MenuInterface menu = new MenuInterface();
                menu.exibirMenu();
            }
        });
    }
}
