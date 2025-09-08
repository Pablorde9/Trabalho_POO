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
    private JComboBox<String> duplaJ1;
    private JComboBox<String> duplaJ2;
    private JLabel novaDuplaLabel;
    private Duplas listaDuplas;

    public MenuInterface() {

        /* ============================================ MENU GUI ============================================================== */

        frameMenu = new JFrame("MENU");
        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        novoJogador = new JButton("Cadastrar Jogador");
        novaDupla = new JButton("Cadastrar Dupla");

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

        frameMenu.add(painelMenu);
        frameMenu.setSize(300, 400);
        frameMenu.setLocationRelativeTo(null);
        frameMenu.setVisible(true);


        /*  ============================================ NOVO JOGADOR GUI ============================================================== */


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



        /*  ============================================ NOVA DUPLA GUI ============================================================== */


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

        



        /*  ============================================ FUNCIONALIDADES ============================================================== */


        novoJogador.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                frameJogador.setVisible(true);
               
            }
        });

        fecharJogador.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                frameJogador.setVisible(false);
               
            }
        });

        cadastrarJogador.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                try {
                    campoVazio(novoJogadorTxt);
                    listaJogadores.add(novoJogadorTxt.getText());
                    JOptionPane.showMessageDialog(frameJogador, "Jogador " + novoJogadorTxt.getText() + " cadastrado com sucesso!");
                    duplaJ1.addItem(novoJogadorTxt.getText());
                    duplaJ2.addItem(novoJogadorTxt.getText());
                } catch (CampoVazioException er) {
                    JOptionPane.showMessageDialog(frameJogador, "Erro! " + er.getMessage());
                }  catch (CadastroRepetidoException er) {
                     JOptionPane.showMessageDialog(frameJogador, "Erro! " + er.getMessage());
                } finally {
                    limpaCampo(novoJogadorTxt);
                }
               
            }
        });

        novaDupla.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                frameDupla.setVisible(true);
               
            }
        });

        fecharDupla.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                frameDupla.setVisible(false);
               
            }
        });

        cadastrarDupla.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                try {
                    campoPlaceholder(duplaJ1, "primeiro");
                    campoPlaceholder(duplaJ2, "segundo");
                    listaDuplas.add(new Dupla((String) duplaJ1.getSelectedItem(), (String) duplaJ2.getSelectedItem()));
                    JOptionPane.showMessageDialog(frameDupla, "Dupla " + (String) duplaJ1.getSelectedItem() + " e " + (String) duplaJ2.getSelectedItem() + " cadastrada com sucesso!");
                } catch (CampoVazioException er) {
                    JOptionPane.showMessageDialog(frameDupla, "Erro! " + er.getMessage());
                }  catch (CadastroRepetidoException er) {
                     JOptionPane.showMessageDialog(frameDupla, "Erro! " + er.getMessage());
                } finally {
                    voltaPlaceholder(duplaJ1);
                    voltaPlaceholder(duplaJ2);
                }
               
            }
        });

    }

    void limpaCampo(JTextField txt) {
        txt.setText("");
    }

    void campoVazio(JTextField txt) throws CampoVazioException {
        if(txt.getText().isEmpty()) throw new CampoVazioException("Digite um nome valido");
    }

    // checa se o menu suspenso(JComboBox) esta no placeholder
    void campoPlaceholder(JComboBox<String> lista, String nmr) throws CampoVazioException {
        if(lista.getSelectedIndex() == 0) throw new CampoVazioException("Selecione o " + nmr + " jogador");
    }

    void voltaPlaceholder(JComboBox<String> lista) {
        lista.setSelectedIndex(0);
    }

}





public class Menu {
    public static void main(String[] args) {
        new MenuInterface();
    }    
}
