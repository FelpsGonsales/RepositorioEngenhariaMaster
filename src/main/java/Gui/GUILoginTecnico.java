package Gui;


import DAOs.DAOtecnico;
import Gui.GUIMedico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUILoginTecnico extends JFrame {
    JTextField tfCRM = new JTextField(15);
    JButton btLogin = new JButton("Login");
    JButton btCancelar = new JButton("Cancelar");

    public GUILoginTecnico() {
        setTitle("Tela de Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Identificador:"));
        add(tfCRM);
        add(btLogin);
        add(btCancelar);

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificador = tfCRM.getText().trim();
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
                    new GUIMedico();
                }
            }
        });

        btCancelar.addActionListener(e -> System.exit(0));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para verificar se o médico existe
    public boolean tecnicoExiste(String identificador) {
        // Utilize o método criado anteriormente
        return new DAOtecnico().tecnicoExiste(identificador); 
    }

    public static void main(String[] args) {
        new GUILoginMedico();
    }
}
