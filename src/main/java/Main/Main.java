package Main;

import Gui.GUILoginEnfermeira;
import Gui.GUILoginMedico;
import Gui.GUILoginTecnico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout()); 

        // Define o fundo da janela como preto
        getContentPane().setBackground(Color.BLACK);

        JLabel lblBemVindo = new JLabel("Bem-Vindo!", SwingConstants.CENTER); 
        lblBemVindo.setForeground(Color.GREEN);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 20));
        lblBemVindo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        add(lblBemVindo, BorderLayout.NORTH); 
        
         JLabel vazio = new JLabel(" ", SwingConstants.CENTER); 
         add(lblBemVindo, BorderLayout.NORTH); 
        
        JLabel lbInstrucao = new JLabel("Selecione o seu nível.", SwingConstants.CENTER); 
        lbInstrucao.setForeground(Color.GREEN);
        lbInstrucao.setFont(new Font("Arial", Font.BOLD, 20));
        lbInstrucao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        add(lbInstrucao, BorderLayout.NORTH); 

        // Painel para os botões
        JPanel panelBotoes = new JPanel(new GridLayout(3, 1));
        panelBotoes.setBackground(Color.BLACK);

        // Criação dos botões
        JButton btnMedico = new JButton("Médico(a)");
        JButton btnEnfermeiro = new JButton("Enfermeiro(a)");
        JButton btnTecnico = new JButton("Técnico(a)");

        // Configuração de estilo para os botões
        configurarBotao(btnMedico);
        configurarBotao(btnEnfermeiro);
        configurarBotao(btnTecnico);

        // Adiciona os botões ao painel
        panelBotoes.add(btnMedico);
        panelBotoes.add(btnEnfermeiro);
        panelBotoes.add(btnTecnico);

        // Adiciona o painel de botões ao centro da janela
        add(panelBotoes, BorderLayout.CENTER);

        // Ações para os botões
        btnMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUILoginMedico();
            }
        });

        btnEnfermeiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new GUILoginEnfermeira();
            }
        });

        btnTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new GUILoginTecnico();
            }
        });

        setVisible(true);
    }
    private void configurarBotao(JButton botao) {
        botao.setBackground(Color.BLACK); // Fundo preto
        botao.setForeground(Color.GREEN); // Texto verde
        botao.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte maior e em negrito
        botao.setFocusPainted(false); // Remove o destaque ao redor do botão
        botao.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // Adiciona uma borda verde
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}
