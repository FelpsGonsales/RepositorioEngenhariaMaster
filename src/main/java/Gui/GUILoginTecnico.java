package Gui;

import DAOs.DAOtecnico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUILoginTecnico extends JFrame {
    JTextField tfId = new JTextField(15);
    JButton btLogin = new JButton("Login");
    JButton btCancelar = new JButton("Cancelar");

    public GUILoginTecnico() {
        setTitle("Tela de Login - Técnico");
        setSize(320, 180); // Ajustei o tamanho para acomodar melhor os botões
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Define o fundo da janela
        getContentPane().setBackground(new Color(209, 213, 219));

        // Configurações de cores para os componentes
        Color textColor = new Color(59, 130, 246);
        Color borderColor = new Color(37, 99, 235);

        JLabel lblIdentificador = new JLabel("Identificador:");
        lblIdentificador.setForeground(textColor);

        tfId.setBorder(BorderFactory.createLineBorder(borderColor, 1));
        tfId.setForeground(textColor);

        // Aumentando o tamanho dos botões
        Dimension buttonSize = new Dimension(120, 30);
        btLogin.setPreferredSize(buttonSize);
        btCancelar.setPreferredSize(buttonSize);

        btLogin.setForeground(textColor);
        btLogin.setBorder(BorderFactory.createLineBorder(borderColor, 1));

        btCancelar.setForeground(textColor);
        btCancelar.setBorder(BorderFactory.createLineBorder(borderColor, 1));

        add(lblIdentificador);
        add(tfId);
        add(btLogin);
        add(btCancelar);

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificador = tfId.getText().trim();
                if (identificador.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira seu Identificador.");
                    return;
                }

                // Verificar no banco de dados
                if (tecnicoExiste(identificador)) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo!");
                    new GUIControleTecnico();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado. Redirecionando...");
                    new GUITecnico();
                }
            }
        });

        btCancelar.addActionListener(e -> System.exit(0));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para verificar se o técnico existe
    public boolean tecnicoExiste(String identificador) {
        return new DAOtecnico().tecnicoExiste(identificador);
    }

    public static void main(String[] args) {
        new GUILoginTecnico();
    }
}
