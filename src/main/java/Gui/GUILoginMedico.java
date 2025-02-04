package Gui;

import DAOs.DAOmedico;
import Gui.GUIMedico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUILoginMedico extends JFrame {
    JTextField tfCRM = new JTextField(15);
    JButton btLogin = new JButton("Login");
    JButton btCancelar = new JButton("Cancelar");

    public GUILoginMedico() {
        setTitle("Tela de Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("CRM:"));
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
        // Utilize o método criado anteriormente
        return new DAOmedico().medicoExiste(crm); 
    }

    public static void main(String[] args) {
        new GUILoginMedico();
    }
}
