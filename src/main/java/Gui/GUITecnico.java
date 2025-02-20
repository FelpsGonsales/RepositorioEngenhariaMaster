package Gui;

import DAOs.DAOtecnico;
import Entidades.tecnico;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import myUtil.JanelaPesquisar;

public class GUITecnico extends JDialog {

    Container cp;

    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbIdTecnico = new JLabel("Id do Técnico");
    JTextField tfIdTecnico = new JTextField(20);

    JLabel lbNomeT = new JLabel("Nome do Técnico");
    JTextField tfNomeT = new JTextField(50);
    

    JLabel lbAreaT = new JLabel("Idade");
    JTextField tfAreaT = new JTextField();
    

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    DAOtecnico daoTecnico = new DAOtecnico();
    tecnico tecnico = new tecnico();
    String acao = "";

    String[] colunas = new String[]{"Id do Paciente", "Nome do Paciente", "Idade"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));
    private CardLayout cardLayout;

    public GUITecnico() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Técnico");
        setAlwaysOnTop(true);
        
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(new Color(209,213,219));
        pnCentro.setBackground(new Color(209,213,219));
        pnSul.setBackground(new Color(209,213,219));
        pnSul.setBorder(BorderFactory.createLineBorder(new Color(37, 99, 235),1));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbIdTecnico);
        pnNorte.add(tfIdTecnico);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        btSalvar.setVisible(false);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);

        pnCentro.setLayout(new GridLayout(3, 6));
        pnCentro.add(lbNomeT);
        pnCentro.add(tfNomeT);
        pnCentro.add(lbAreaT);
        pnCentro.add(tfAreaT);
        
        

        tfNomeT.setBackground(new Color(209,213,219));
        tfNomeT.setForeground(new Color(59, 130, 246));

        lbNomeT.setForeground(new Color(59, 130, 246));
        lbNomeT.setBackground(new Color(209,213,219));

        lbIdTecnico.setBackground(new Color(209,213,219));
        lbIdTecnico.setForeground(new Color(59, 130, 246));

        tfAreaT.setForeground(new Color(59, 130, 246));
        tfAreaT.setBackground(new Color(209,213,219));

        lbAreaT.setBackground(new Color(209,213,219));
        lbAreaT.setForeground(new Color(59, 130, 246));
        
        
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnVazio.setBackground(new Color(209,213,219));
        pnSul.add(pnAvisos, " ");
        pnAvisos.setBackground(new Color(209,213,219));
        pnAvisos.setForeground(new Color(59, 130, 246));
        pnSul.add(pnListagem, "listagem");
        pnListagem.setBackground(new Color(209,213,219));
        pnListagem.setForeground(new Color(59, 130, 246));
        tabela.setEnabled(false);
        tabela.setBackground(new Color(209,213,219));
        tabela.setForeground(new Color(59, 130, 246));

        pnAvisos.add(new JLabel("Avisos"));

        tfNomeT.setEditable(false);

        tfIdTecnico.setBackground(new Color(209,213,219));
        tfIdTecnico.setForeground(new Color(59, 130, 246));
        btBuscar.setForeground(new Color(59, 130, 246));
        btBuscar.setBackground(new Color(209,213,219));
        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                tecnico = new tecnico();
                tfIdTecnico.setText(tfIdTecnico.getText().trim());//caso tenham sido digitados espaços

                if (tfIdTecnico.getText().equals("")) {
                    List<String> listaAuxiliar = daoTecnico.listInOrderNomeStrings("crm");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btBuscar.getLocationOnScreen();
                        lc.x = lc.x + btBuscar.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            tfIdTecnico.setText(aux[0]);
                            btBuscar.doClick();
                        } else {
                            tfIdTecnico.requestFocus();
                            tfIdTecnico.selectAll();
                        }
                    }

                    tfIdTecnico.requestFocus();
                    tfIdTecnico.selectAll();
                } else {
                    try {
                        tecnico.setidentificador(Integer.valueOf(tfIdTecnico.getText()));
                        tecnico = daoTecnico.obter(tecnico.getidentificador());
                        if (tecnico != null) { //se encontrou na lista
                           tfNomeT.setText(String.valueOf(tecnico.getnome_tecnico()));
                           tfAreaT.setText(String.valueOf(tecnico.getarea_tecnico()));

                            btAdicionar.setVisible(false);
                            btAlterar.setVisible(true);
                            btExcluir.setVisible(true);
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(false);
                            btListar.setVisible(true);
                            acao = "encontrou";
                        } else {
                            btAdicionar.setVisible(true);
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListar.setVisible(true);
                        }
                        tfIdTecnico.setBackground(new Color(209,213,219));
                        tfIdTecnico.setForeground(new Color(59, 130, 246));
                    } catch (Exception x) {
                        tfIdTecnico.setOpaque(true);
                        tfIdTecnico.selectAll();
                        tfIdTecnico.requestFocus();
                        tfIdTecnico.setBackground(Color.yellow);

                    }
                }
            }
        });
        btAdicionar.setForeground(new Color(59, 130, 246));
        btAdicionar.setBackground(new Color(209,213,219));
        btAdicionar.addActionListener((ActionEvent e) -> {
            requestFocus();
            tfNomeT.requestFocus();
            tfIdTecnico.setEnabled(false);
            tfNomeT.setEditable(true);
            tfAreaT.setEditable(true);
            tfAreaT.setText("");

            btAdicionar.setVisible(false);
            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btBuscar.setVisible(false);
            btListar.setVisible(false);
            acao = "Adicionar";
        });
        btSalvar.setForeground(new Color(59, 130, 246));
        btSalvar.setBackground(new Color(209,213,219));
        btSalvar.addActionListener((ActionEvent e) -> {
            if (acao.equals("Adicionar")) {
                tecnico = new tecnico();
            }

            tecnico.setidentificador(Integer.valueOf(tfIdTecnico.getText()));
            tecnico.setnome_tecnico(tfNomeT.getText());
            tecnico.setarea_tecnico(tfAreaT.getText());
            
            
            if (acao.equals("Adicionar")) {
                daoTecnico.inserir(tecnico);
            } else {
                daoTecnico.atualizar(tecnico);
            }
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            tfIdTecnico.setEnabled(true);
            tfIdTecnico.setEditable(true);
            tfIdTecnico.requestFocus();

            tfIdTecnico.setText("");
            tfNomeT.setText("");
            tfAreaT.setText("");
            
            btBuscar.setVisible(true);
            btListar.setVisible(true);
            
            tfNomeT.setEditable(false);
            tfAreaT.setEditable(false);
        });
        btAlterar.setForeground(new Color(59, 130, 246));
        btAlterar.setBackground(new Color(209,213,219));
        btAlterar.addActionListener((ActionEvent e) -> {
            btBuscar.setVisible(false);
            btAlterar.setVisible(false);
            tfNomeT.requestFocus();
            tfIdTecnico.setEditable(false);
            tfNomeT.setEditable(true);
            tfAreaT.setEditable(true);

            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btListar.setVisible(false);
            btExcluir.setVisible(false);
            acao = "alterar";
        });
        btExcluir.setForeground(new Color(59, 130, 246));
        btExcluir.setBackground(new Color(209,213,219));
        btExcluir.addActionListener((ActionEvent e) -> {
            int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                daoTecnico.remover(tecnico);
                System.out.println(tecnico.toString());
            }
            btExcluir.setVisible(false);
            tfIdTecnico.setEnabled(true);
            tfIdTecnico.setEditable(true);
            tfIdTecnico.requestFocus();
            tfIdTecnico.setText("");
            tfNomeT.setText("");
            tfAreaT.setText("");

            btBuscar.setVisible(true);

            tfNomeT.setEditable(false);

            btAlterar.setVisible(false);
            btAdicionar.setVisible(false);
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btListar.setVisible(true);
        });
        btListar.setForeground(new Color(59, 130, 246));
        btListar.setBackground(new Color(209,213,219));
        btListar.addActionListener((ActionEvent e) -> {
            List<tecnico> listaMedico = daoTecnico.listInOrderNome();
            String[] colunas1 = {"Id do Técnico", "Nome do Técnico", "Área do Técnico"};
            Object[][] dados1 = new Object[listaMedico.size()][colunas1.length];
            String aux[];
            for (int i = 0; i < listaMedico.size(); i++) {
                aux = listaMedico.get(i).toString().split(";");
                for (int j = 0; j < colunas1.length; j++) {
                    try {
                        dados1[i][j] = aux[j];
                    } catch (Exception x1) {
                    }
                }
            }
            cardLayout.show(pnSul, "listagem");
            scrollTabela.setPreferredSize(tabela.getPreferredSize());
            pnListagem.add(scrollTabela);
            scrollTabela.setViewportView(tabela);
            model.setDataVector(dados1, colunas1);
            btAlterar.setVisible(false);
            btExcluir.setVisible(false);
            btBuscar.setVisible(true);
            btAdicionar.setVisible(false);
        });
        
        btCancelar.setForeground(new Color(59, 130, 246));
        btCancelar.setBackground(new Color(209,213,219));
        btCancelar.addActionListener((ActionEvent e) -> {
            btCancelar.setVisible(false);
            tfIdTecnico.setText("");
            tfIdTecnico.requestFocus();
            tfIdTecnico.setEnabled(true);
            tfIdTecnico.setEditable(true);
            tfNomeT.setText("");

            tfAreaT.setText("");

            tfAreaT.setEditable(false);
            tfNomeT.setEditable(false);

            btBuscar.setVisible(true);
            btListar.setVisible(true);
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                dispose();
            }
        });

        setModal(true);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        GUIMedico guiMedico = new GUIMedico();
    }

}
