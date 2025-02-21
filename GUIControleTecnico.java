package Gui;

import DAOs.DAOtecnico;
import javax.swing.*;
import java.awt.*;

public class GUIControleTecnico extends JFrame {

    JButton btControleTecnico = new JButton("Alterar dados técnico");
    JButton btPews = new JButton("QUESTIONÁRIO PEWS");

    public GUIControleTecnico() {
        setTitle("Tela de Controle - Técnico");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configurar fundo da janela
        getContentPane().setBackground(new Color(209, 213, 219));

        // Criar painel central com GridLayout
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(209, 213, 219));

        // Configuração de cores
        Color buttonTextColor = new Color(59, 130, 246);
        Color buttonBorderColor = new Color(37, 99, 235);

        // Configuração dos botões
        JButton[] buttons = {btControleTecnico, btPews};
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(300, 60)); // Botões maiores
            button.setForeground(buttonTextColor);
            button.setBorder(BorderFactory.createLineBorder(buttonBorderColor, 2));
            panel.add(button);
        }

        add(panel);

        // Definir ações para os botões
        btControleTecnico.addActionListener(e -> new GUITecnico());
        btPews.addActionListener(e -> {
            PEWS pews = new PEWS();
            pews.setVisible(true);
            dispose();
        });

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
