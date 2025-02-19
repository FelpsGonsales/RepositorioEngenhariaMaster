package Gui;

import Entidades.medico;
import Entidades.paciente;
import javax.persistence.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PEWS extends JFrame {

    private JTextField leitoField, dihField, diagnosticoField, idadeField, dataAvaliacaoField;
    private JTextField freqCardiacaField, freqRespiratoriaField;
    private JTextField avaliacaoNeurologicaField, avaliacaoCardiovascularField, avaliacaoRespiratoriaField;
    private JTextField nebulizacaoResgateField, emesePosOperatorioField;
    private JTextArea resultadoArea;
    private JComboBox<medico> medicoComboBox;
    private JComboBox<paciente> pacienteComboBox; // ComboBox para pacientes

    private EntityManagerFactory emf;
    private EntityManager em;

    public PEWS() {
        // Configuração do JFrame
        setTitle("PEWS - Sistema de Avaliação");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(18, 2, 10, 10)); // Layout com 18 linhas e 2 colunas
        panel.setBackground(Color.WHITE);

        // Adicionando o ComboBox para selecionar o médico
        panel.add(new JLabel("Médico:"));
        medicoComboBox = new JComboBox<>();
        panel.add(medicoComboBox);

        // Adicionando o ComboBox para selecionar o paciente
        panel.add(new JLabel("Paciente:"));
        pacienteComboBox = new JComboBox<>();
        panel.add(pacienteComboBox);

        // Outros campos já existentes

        panel.add(new JLabel("Leito:"));
        leitoField = new JTextField();
        panel.add(leitoField);

        panel.add(new JLabel("DIH:"));
        dihField = new JTextField();
        panel.add(dihField);

        panel.add(new JLabel("Diagnóstico:"));
        diagnosticoField = new JTextField();
        panel.add(diagnosticoField);

        panel.add(new JLabel("Idade:"));
        idadeField = new JTextField();
        panel.add(idadeField);

        panel.add(new JLabel("Data da Avaliação:"));
        dataAvaliacaoField = new JTextField();
        panel.add(dataAvaliacaoField);

        panel.add(new JLabel("Frequência Cardíaca:"));
        freqCardiacaField = new JTextField();
        panel.add(freqCardiacaField);

        panel.add(new JLabel("Frequência Respiratória:"));
        freqRespiratoriaField = new JTextField();
        panel.add(freqRespiratoriaField);

        panel.add(new JLabel("Avaliação Neurológica (0-3):"));
        avaliacaoNeurologicaField = new JTextField();
        panel.add(avaliacaoNeurologicaField);

        panel.add(new JLabel("Avaliação Cardiovascular (0-3):"));
        avaliacaoCardiovascularField = new JTextField();
        panel.add(avaliacaoCardiovascularField);

        panel.add(new JLabel("Avaliação Respiratória (0-3):"));
        avaliacaoRespiratoriaField = new JTextField();
        panel.add(avaliacaoRespiratoriaField);

        panel.add(new JLabel("Nebulização de Resgate (0 ou 1):"));
        nebulizacaoResgateField = new JTextField();
        panel.add(nebulizacaoResgateField);

        panel.add(new JLabel("Êmese Pós-Operatório (0 ou 1):"));
        emesePosOperatorioField = new JTextField();
        panel.add(emesePosOperatorioField);

        // Botão para calcular
        JButton calcularButton = new JButton("Calcular");
        calcularButton.setBackground(Color.GREEN);
        calcularButton.setForeground(Color.WHITE);
        panel.add(calcularButton);

        // Área de resultados
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setBackground(Color.LIGHT_GRAY);
        resultadoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        // Adiciona o painel de entradas e botões
        add(panel, BorderLayout.NORTH);

        // Configurar o EntityManager para JPA
        emf = Persistence.createEntityManagerFactory("UP"); // Nome da unidade de persistência (definido no persistence.xml)
        em = emf.createEntityManager();

        // Carregar médicos e pacientes do banco de dados
        carregarMedicos();
        carregarPacientes();

        // Ação do botão "Calcular"
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularResultados();
            }
        });
    }

    private void carregarMedicos() {
        // Consultar médicos usando JPA
        try {
            TypedQuery<medico> query = em.createNamedQuery("medico.findAll", medico.class);
            List<medico> medicos = query.getResultList();

            for (medico m : medicos) {
                medicoComboBox.addItem(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar médicos do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void carregarPacientes() {
        // Consultar pacientes usando JPA
        try {
            TypedQuery<paciente> query = em.createNamedQuery("paciente.findAll", paciente.class);
            List<paciente> pacientes = query.getResultList();

            for (paciente p : pacientes) {
                pacienteComboBox.addItem(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar pacientes do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

 private void calcularResultados() {
    try {
        // Coletar dados dos campos
        medico medicoSelecionado = (medico) medicoComboBox.getSelectedItem();
        int crm = medicoSelecionado != null ? medicoSelecionado.getcrm() : -1;

        paciente pacienteSelecionado = (paciente) pacienteComboBox.getSelectedItem();
        int idpaciente = pacienteSelecionado != null ? pacienteSelecionado.getidpaciente() : -1;

        String leito = leitoField.getText();
        String dih = dihField.getText();
        String diagnostico = diagnosticoField.getText();
        int idade = Integer.parseInt(idadeField.getText());
        String dataAvaliacao = dataAvaliacaoField.getText();

        int freqCardiaca = Integer.parseInt(freqCardiacaField.getText());
        int freqRespiratoria = Integer.parseInt(freqRespiratoriaField.getText());

        int avaliacaoNeurologica = Integer.parseInt(avaliacaoNeurologicaField.getText());
        int avaliacaoCardiovascular = Integer.parseInt(avaliacaoCardiovascularField.getText());
        int avaliacaoRespiratoria = Integer.parseInt(avaliacaoRespiratoriaField.getText());

        int nebulizacaoResgate = Integer.parseInt(nebulizacaoResgateField.getText());
        int emesePosOperatorio = Integer.parseInt(emesePosOperatorioField.getText());

        // Calcular pontuação
        int pontuacao = avaliacaoNeurologica + avaliacaoCardiovascular + avaliacaoRespiratoria + nebulizacaoResgate + emesePosOperatorio;

        // Construir o texto do resultado
        StringBuilder resultadoTexto = new StringBuilder();
        resultadoTexto.append("=== Resultado da Avaliação ===\n");
        resultadoTexto.append("Médico: " + medicoSelecionado.getnome_medico() + " (CRM: " + crm + ")\n");
        resultadoTexto.append("Paciente: " + pacienteSelecionado.getnome_paciente() + " (ID: " + idpaciente + ")\n");
        resultadoTexto.append("Pontuação Final: " + pontuacao + "\n");
        resultadoTexto.append("Intervenção Recomendada: \n");

        if (pontuacao == 0) {
            resultadoTexto.append("Manter rotina de avaliação. PEWS a cada 24 horas.\n");
            resultadoTexto.append("Sinais Vitais de 6/6 horas.\n");
        } else if (pontuacao <= 3) {
            resultadoTexto.append("Aumento da frequência de monitoramento.\n");
            resultadoTexto.append("Avaliação PEWS a cada 4 horas.\n");
        } else if (pontuacao <= 6) {
            resultadoTexto.append("Revisão clínica imediata por um médico.\n");
            resultadoTexto.append("Avaliação PEWS a cada 2 horas.\n");
        } else {
            resultadoTexto.append("Alerta máximo! Acionar equipe de emergência.\n");
            resultadoTexto.append("Monitoramento contínuo e possível transferência para UTI.\n");
        }

        // Exibir o resultado como um popup
        int resposta = JOptionPane.showOptionDialog(
            this, 
            resultadoTexto.toString(), 
            "Resultado da Avaliação", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            new Object[]{} , 
            null
        );

        // Se o usuário clicar em OK ou em qualquer outra ação que fechar o popup, o programa será fechado
        if (resposta == JOptionPane.CLOSED_OPTION || resposta == JOptionPane.OK_OPTION) {
            System.exit(0); // Fechar o programa
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PEWS().setVisible(true);
            }
        });
    }
}
