package Gui;

import DAOs.DAOtecnico;
import Gui.GUITecnico;
import Gui.GUITestePews;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUIControleTecnico extends JFrame {

    JButton btControleTecnico = new JButton("Alterar dados tecnico");
    JButton btPews = new JButton("QUESTIONÁRIO PEWS");

    public GUIControleTecnico() {
        setTitle("Tela de Controle");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configurar o layout do painel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 linhas, 2 colunas, com espaçamento
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens

        // Adicionar os botões no painel

        panel.add(btControleTecnico);
        panel.add(btPews);

        // Configurar o painel no JFrame
        add(panel);

        // Definir ações para os botões

        btControleTecnico.addActionListener(e -> new GUITecnico());
        btPews.addActionListener(e -> new GUITestePews());

        // Configurações finais
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean tecnicoExiste(String identificador) {
        return new DAOtecnico().tecnicoExiste(identificador);
    }

    public static void main(String[] args) {
        new GUIControleTecnico();
    }
}
