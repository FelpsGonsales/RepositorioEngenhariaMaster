package Gui;

import DAOs.DAOenfermeira;
//import Gui.GUIEnfermeira;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUILoginEnfermeira extends JFrame {
    JTextField tfCRM = new JTextField(15);
    JButton btLogin = new JButton("Login");
    JButton btCancelar = new JButton("Cancelar");

    public GUILoginEnfermeira() {
        setTitle("Tela de Login- Enfermeira");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("CIP:"));
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
                    // Redirecionar para a tela de CRUD
                    new GUIEnfermeira();
                }
            }
        });

        btCancelar.addActionListener(e -> System.exit(0));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para verificar se a enfermeira existe
public boolean enfermeiraExiste(String cip) {
    return new DAOenfermeira().enfermeiraExiste(cip);
}
    public static void main(String[] args) {
        new GUILoginEnfermeira();
    }
}
