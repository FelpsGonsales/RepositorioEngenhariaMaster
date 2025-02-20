package Gui;

import DAOs.DAOmedico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUILoginMedico extends JFrame {
    JTextField tfCRM = new JTextField(15);
    JButton btLogin = new JButton("Login");
    JButton btCancelar = new JButton("Cancelar");

    public GUILoginMedico() {
        setTitle("Tela de Login - Médico");
        setSize(320, 180); // Ajustei o tamanho para acomodar melhor os botões
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Define o fundo da janela
        getContentPane().setBackground(new Color(209, 213, 219));

        // Configurações de cores para os componentes
        Color textColor = new Color(59, 130, 246);
        Color borderColor = new Color(37, 99, 235);

        JLabel lblCRM = new JLabel("CRM:");
        lblCRM.setForeground(textColor);

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

        add(lblCRM);
        add(tfCRM);
        add(btLogin);
        add(btCancelar);

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String crm = tfCRM.getText().trim();
                if (crm.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um CRM.");
                    return;
                }

                // Verificar no banco de dados
                if (medicoExiste(crm)) {
                    JOptionPane.showMessageDialog(null, "Bem-vindo!");
                    new GUIControleMedico();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado. Redirecionando...");
                    new GUIMedico();
                }
            }
        });

        btCancelar.addActionListener(e -> System.exit(0));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para verificar se o médico existe
    public boolean medicoExiste(String crm) {
        return new DAOmedico().medicoExiste(crm);
    }

    public static void main(String[] args) {
        new GUILoginMedico();
    }
}
