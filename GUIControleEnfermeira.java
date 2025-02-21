package Gui;

import DAOs.DAOenfermeira;
import javax.swing.*;
import java.awt.*;

public class GUIControleEnfermeira extends JFrame {
    JButton btControleEnfermeira = new JButton("Alterar dados enfermeira");
    JButton btControleTecnico = new JButton("Alterar dados técnico");
    JButton btControlePaciente = new JButton("Cadastrar e alterar dados do paciente");
    JButton btPews = new JButton("QUESTIONÁRIO PEWS");

    public GUIControleEnfermeira() {
        setTitle("Tela de Controle - Enfermeira");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configurar fundo da janela
        getContentPane().setBackground(new Color(209, 213, 219));

        // Criar painel central com GridLayout
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(209, 213, 219));

        // Configuração de cores
        Color buttonTextColor = new Color(59, 130, 246);
        Color buttonBorderColor = new Color(37, 99, 235);

        // Configuração dos botões
        JButton[] buttons = {btControleEnfermeira, btControleTecnico, btControlePaciente, btPews};
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(300, 60)); // Aumentando o tamanho dos botões
            button.setForeground(buttonTextColor);
            button.setBorder(BorderFactory.createLineBorder(buttonBorderColor, 2));
            panel.add(button);
        }

        add(panel);

        btControleEnfermeira.addActionListener(e -> new GUIAltEnfermeira());
        btControleTecnico.addActionListener(e -> new GUITecnico());
        btControlePaciente.addActionListener(e -> new GUIPaciente());
        btPews.addActionListener(e -> {
            PEWS pews = new PEWS();
            pews.setVisible(true);
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean medicoExiste(String cip) {
        return new DAOenfermeira().enfermeiraExiste(cip);
    }

    public static void main(String[] args) {
        new GUIControleEnfermeira();
    }
}
