package Gui;

import DAOs.DAOenfermeira;
import Gui.GUIAltMedico;
import Gui.GUIEnfermeira;
import Gui.GUIMedico;
import Gui.GUIPaciente;
import Gui.GUITecnico;
import Gui.GUITestePews;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GUIControleEnfermeira extends JFrame {
    JButton btControleEnfermeira = new JButton("Alterar dados enfermeira");
    JButton btControleTecnico = new JButton("Alterar dados tecnico");
    JButton btControlePaciente = new JButton("Cadastrar e alterar dados do paciente");
    JButton btPews = new JButton("QUESTIONÁRIO PEWS");

    public GUIControleEnfermeira() {
        setTitle("Tela de Controle");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configurar o layout do painel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 linhas, 2 colunas, com espaçamento
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margens

        // Adicionar os botões no painel

        panel.add(btControleEnfermeira);
        panel.add(btControleTecnico);
        panel.add(btControlePaciente);
        panel.add(btPews);

        // Configurar o painel no JFrame
        add(panel);

        // Definir ações para os botões

        btControleEnfermeira.addActionListener(e -> new GUIAltEnfermeira());
        btControleTecnico.addActionListener(e -> new GUITecnico());
        btControlePaciente.addActionListener(e -> new GUIPaciente());
        btPews.addActionListener(e -> new GUITestePews());

        // Configurações finais
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
