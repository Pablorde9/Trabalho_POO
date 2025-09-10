import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuInterface {

    private JPanel painelMenu;
    private JFrame frameMenu;
    private GridBagConstraints alinhamentoMenu;

    private JFrame frameJogador;
    private JPanel painelJogador;
    private JButton novoJogador;
    private JButton fecharJogador;
    private JButton cadastrarJogador;
    private JTextField novoJogadorTxt;
    private JLabel novoJogadorLabel;
    private GridBagConstraints alinhamentoJogador;
    private Jogadores listaJogadores;

    private JFrame frameDupla;
    private JPanel painelDupla;
    private JButton novaDupla;
    private GridBagConstraints alinhamentoDupla;
    private JButton fecharDupla;
    private JButton cadastrarDupla;
    private JComboBox<Object> duplaJ1;
    private JComboBox<Object> duplaJ2;
    private JLabel novaDuplaLabel;
    private Duplas listaDuplas;

    private JFrame frameJogo;
    private JPanel painelJogo;
    private JButton novoJogo;
    private JLabel novoJogoLabel;
    private GridBagConstraints alinhamentoJogo;
    private JButton fecharJogo;
    private JButton iniciarJogo;
    private JComboBox<Object> ldupla1;
    private JComboBox<Object> ldupla2;

    public MenuInterface() {

        /* ============================================ MENU GUI ===================================== */

        frameMenu = new JFrame("MENU");
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        novoJogador = new JButton("Cadastrar Jogador");
        novaDupla = new JButton("Cadastrar Dupla");
        novoJogo = new JButton("Iniciar Jogo");

        novaDupla.setPreferredSize(new Dimension(novoJogador.getPreferredSize()));
        novoJogo.setPreferredSize(new Dimension(novoJogador.getPreferredSize()));

        painelMenu = new JPanel();
        painelMenu.setLayout(new GridBagLayout());

        alinhamentoMenu = new GridBagConstraints();
        alinhamentoMenu.gridx = 0;
        alinhamentoMenu.gridy = 0;
        alinhamentoMenu.insets = new Insets(10, 0, 10, 0);

        painelMenu.add(novoJogador, alinhamentoMenu);

        alinhamentoMenu.gridy = 1;
        alinhamentoMenu.insets = new Insets(0, 0, 10, 0);
        painelMenu.add(novaDupla, alinhamentoMenu);

        alinhamentoMenu.gridy = 2;
        alinhamentoMenu.insets = new Insets(0, 0, 10, 0);
        painelMenu.add(novoJogo, alinhamentoMenu);

        frameMenu.add(painelMenu);
        frameMenu.setSize(300, 400);
        frameMenu.setLocationRelativeTo(null);
        frameMenu.setVisible(true);

        /* ==================================== NOVO JOGADOR GUI ==================================================*/

        listaJogadores = new Jogadores();

        frameJogador = new JFrame("Novo Jogador");
        frameJogador.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        novoJogadorLabel = new JLabel("Digite o nome do jogador:");

        novoJogadorTxt = new JTextField(30);

        fecharJogador = new JButton("Fechar");
        cadastrarJogador = new JButton("Cadastrar");

        painelJogador = new JPanel();
        painelJogador.setLayout(new GridBagLayout());

        alinhamentoJogador = new GridBagConstraints();
        alinhamentoJogador.gridx = 0;
        alinhamentoJogador.gridy = 0;
        alinhamentoJogador.insets = new Insets(10, 0, 10, 0);

        painelJogador.add(novoJogadorLabel, alinhamentoJogador);

        alinhamentoJogador.gridy = 1;
        alinhamentoJogador.insets = new Insets(0, 0, 10, 0);
        painelJogador.add(novoJogadorTxt, alinhamentoJogador);

        alinhamentoJogador.gridy = 2;
        alinhamentoJogador.insets = new Insets(0, 0, 50, 0);

        painelJogador.add(cadastrarJogador, alinhamentoJogador);

        alinhamentoJogador.gridy = 3;
        alinhamentoJogador.insets = new Insets(100, 0, 0, 0);
        painelJogador.add(fecharJogador, alinhamentoJogador);

        frameJogador.add(painelJogador);
        frameJogador.setSize(500, 400);
        frameJogador.setLocationRelativeTo(null);

        /* ============================================ NOVA DUPLA GUI =============================================*/

        listaDuplas = new Duplas();

        frameDupla = new JFrame("Nova Dupla");
        frameDupla.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        novaDuplaLabel = new JLabel("Escolha os jogadores da Dupla:");

        duplaJ1 = new JComboBox<>();
        duplaJ1.addItem("Selecione o primeiro jogador");
        duplaJ1.setPreferredSize(new Dimension(300, duplaJ1.getPreferredSize().height));

        duplaJ2 = new JComboBox<>();
        duplaJ2.addItem("Selecione o segundo jogador");
        duplaJ2.setPreferredSize(new Dimension(300, duplaJ2.getPreferredSize().height));

        cadastrarDupla = new JButton("Cadastrar");
        fecharDupla = new JButton("Fechar");

        painelDupla = new JPanel();
        painelDupla.setLayout(new GridBagLayout());

        alinhamentoDupla = new GridBagConstraints();
        alinhamentoDupla.gridx = 0;
        alinhamentoDupla.gridy = 0;
        alinhamentoDupla.insets = new Insets(10, 0, 10, 0);

        painelDupla.add(novaDuplaLabel, alinhamentoDupla);

        alinhamentoDupla.gridy = 1;
        alinhamentoDupla.insets = new Insets(0, 0, 10, 0);
        painelDupla.add(duplaJ1, alinhamentoDupla);

        alinhamentoDupla.gridy = 2;
        painelDupla.add(duplaJ2, alinhamentoDupla);

        alinhamentoDupla.gridy = 3;
        alinhamentoDupla.insets = new Insets(0, 0, 50, 0);
        painelDupla.add(cadastrarDupla, alinhamentoDupla);

        alinhamentoDupla.gridy = 4;
        alinhamentoDupla.insets = new Insets(100, 0, 0, 0);
        painelDupla.add(fecharDupla, alinhamentoDupla);

        frameDupla.add(painelDupla);
        frameDupla.setSize(500, 400);
        frameDupla.setLocationRelativeTo(null);

        /* ============================================ NOVO JOGO GUI =============================================*/


        frameJogo = new JFrame("Novo Jogo");
        frameDupla.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        novoJogoLabel = new JLabel("Escolha as duplas que irao jogar:");

        ldupla1 = new JComboBox<>();
        ldupla1.addItem("Selecione a primeira dupla");
        ldupla1.setPreferredSize(new Dimension(300, ldupla1.getPreferredSize().height));

        ldupla2 = new JComboBox<>();
        ldupla2.addItem("Selecione a segunda dupla");
        ldupla2.setPreferredSize(new Dimension(300, ldupla2.getPreferredSize().height));

        iniciarJogo = new JButton("Iniciar");
        fecharJogo = new JButton("Voltar");

        painelJogo = new JPanel();
        painelJogo.setLayout(new GridBagLayout());

        alinhamentoJogo = new GridBagConstraints();
        alinhamentoJogo.gridx = 0;
        alinhamentoJogo.gridy = 0;
        alinhamentoJogo.insets = new Insets(10, 0, 10, 0);

        painelJogo.add(novoJogoLabel, alinhamentoJogo);

        alinhamentoJogo.gridy = 1;
        alinhamentoJogo.insets = new Insets(0, 0, 10, 0);
        painelJogo.add(ldupla1, alinhamentoJogo);

        alinhamentoJogo.gridy = 2;
        painelJogo.add(ldupla2, alinhamentoJogo);

        alinhamentoJogo.gridy = 3;
        alinhamentoJogo.insets = new Insets(0, 0, 50, 0);
        painelJogo.add(iniciarJogo, alinhamentoJogo);

        alinhamentoJogo.gridy = 4;
        alinhamentoJogo.insets = new Insets(100, 0, 0, 0);
        painelJogo.add(fecharJogo, alinhamentoJogo);

        frameJogo.add(painelJogo);
        frameJogo.setSize(500, 400);
        frameJogo.setLocationRelativeTo(null);

        /* ================================= FUNCIONALIDADES ===================================== */

        novoJogador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameJogador.setVisible(true);

            }
        });

        fecharJogador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameJogador.setVisible(false);

            }
        });

        cadastrarJogador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    campoVazio(novoJogadorTxt);
                    String nomeJogador = novoJogadorTxt.getText();
                    Jogador novoJogador = new Jogador(nomeJogador);
                    listaJogadores.add(novoJogador);
                    JOptionPane.showMessageDialog(frameJogador, "Jogador " + nomeJogador + " cadastrado com sucesso!");
                    duplaJ1.addItem(novoJogador);
                    duplaJ2.addItem(novoJogador);
                } catch (CampoVazioException er) {
                    JOptionPane.showMessageDialog(frameJogador, "Erro! " + er.getMessage());
                } catch (CadastroRepetidoException er) {
                    JOptionPane.showMessageDialog(frameJogador, "Erro! " + er.getMessage());
                } finally {
                    limpaCampo(novoJogadorTxt);
                }

            }
        });

        novaDupla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameDupla.setVisible(true);

            }
        });

        fecharDupla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameDupla.setVisible(false);

            }
        });

        cadastrarDupla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    campoPlaceholder(duplaJ1, "primeiro");
                    campoPlaceholder(duplaJ2, "segundo");
                    membroDuplicado(duplaJ2, duplaJ1);
                    Jogador jogador1 = (Jogador) duplaJ1.getSelectedItem();
                    Jogador jogador2 = (Jogador) duplaJ2.getSelectedItem();
                    Dupla dupla = new Dupla(jogador1, jogador2);
                    listaDuplas.add(dupla);
                    JOptionPane.showMessageDialog(frameDupla,
                            "Dupla " + jogador1 + " e " + jogador2 + " cadastrada com sucesso!");
                    ldupla1.addItem(dupla);
                    ldupla2.addItem(dupla);
                } catch (CampoVazioException er) {
                    JOptionPane.showMessageDialog(frameDupla, "Erro! " + er.getMessage());
                } catch(MembroDuplicadoException er) {
                    JOptionPane.showMessageDialog(frameDupla, "Erro! Selecione jogadores diferentes");
                } catch (CadastroRepetidoException er) {
                    JOptionPane.showMessageDialog(frameDupla, "Erro! " + er.getMessage() + " jogador");
                } finally {
                    voltaPlaceholder(duplaJ1);
                    voltaPlaceholder(duplaJ2);
                }

            }
        });

        novoJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameJogo.setVisible(true);

            }
        });

        fecharJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameJogo.setVisible(false);

            }
        });

        iniciarJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    campoPlaceholder(ldupla1, "primeira");
                    campoPlaceholder(ldupla2, "segunda");
                    membroDuplicado(ldupla2, ldupla1);
                    Dupla dupla1 = (Dupla) ldupla1.getSelectedItem();
                    Dupla dupla2 = (Dupla) ldupla2.getSelectedItem();
                    JOptionPane.showMessageDialog(frameDupla,
                            "Duplas" + dupla1 + " e " + dupla2 + " irao jogar!");
                } catch (CampoVazioException er) {
                    JOptionPane.showMessageDialog(frameDupla, "Erro! " + er.getMessage() + " dupla");
                } catch(MembroDuplicadoException er) {
                    JOptionPane.showMessageDialog(frameDupla, "Erro! Selecione duplas diferentes");
                } finally {
                    voltaPlaceholder(ldupla1);
                    voltaPlaceholder(ldupla2);
                }

            }
        });

    }

    void limpaCampo(JTextField txt) {
        txt.setText("");
    }

    void campoVazio(JTextField txt) throws CampoVazioException {
        if (txt.getText().isEmpty())
            throw new CampoVazioException("Digite um nome valido");
    }

    // checa se o menu suspenso(JComboBox) esta no placeholder
    void campoPlaceholder(JComboBox<Object> lista, String nmr) throws CampoVazioException {
        if (lista.getSelectedIndex() == 0)
            throw new CampoVazioException("Selecione a " + nmr);
    }

    void membroDuplicado(JComboBox<Object> e1, JComboBox<Object> e2) throws MembroDuplicadoException {
        if (e1.getSelectedItem().equals(e2.getSelectedItem())) throw new MembroDuplicadoException("");
    }

    void voltaPlaceholder(JComboBox<Object> lista) {
        lista.setSelectedIndex(0);
    }

}

public class Menu {
    public static void main(String[] args) {
        new MenuInterface();
    }
}
