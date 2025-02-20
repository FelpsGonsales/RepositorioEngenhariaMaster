package Gui;

import DAOs.DAOenfermeira;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUILoginEnfermeira extends JFrame {
    JTextField tfCRM = new JTextField(15);
    JButton btLogin = new JButton("Login");
    JButton btCancelar = new JButton("Cancelar");

    public GUILoginEnfermeira() {
        setTitle("Tela de Login - Enfermeira");
        setSize(320, 180); // Aumentei um pouco para acomodar os botões
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Define o fundo da janela
        getContentPane().setBackground(new Color(209, 213, 219));

        // Configurações de cores para os componentes
        Color textColor = new Color(59, 130, 246);
        Color borderColor = new Color(37, 99, 235);

        JLabel lblCIP = new JLabel("CIP:");
        lblCIP.setForeground(textColor);

        tfCRM.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        tfCRM.setForeground(textColor);

        // Aumentando o tamanho dos botões
        Dimension buttonSize = new Dimension(120, 30);
        btLogin.setPreferredSize(buttonSize);
        btCancelar.setPreferredSize(buttonSize);

        btLogin.setForeground(textColor);
        btLogin.setBorder(BorderFactory.createLineBorder(borderColor, 1));

        btCancelar.setForeground(textColor);
        btCancelar.setBorder(BorderFactory.createLineBorder(borderColor, 1));

        add(lblCIP);
        add(tfCRM);
        add(btLogin);
        add(btCancelar);

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpi = tfCRM.getText().trim();
                if (cpi.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o seu CIP.");
                    return;
                }

                // Verificar no banco de dados
                if (enfermeiraExiste(cpi)) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo!");
                    new GUIControleEnfermeira();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado. Redirecionando...");
                    new GUIEnfermeira();
                }
            }
        });

        btCancelar.addActionListener(e -> System.exit(0));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean enfermeiraExiste(String cip) {
        return new DAOenfermeira().enfermeiraExiste(cip);
    }

    public static void main(String[] args) {
        new GUILoginEnfermeira();
    }
}
