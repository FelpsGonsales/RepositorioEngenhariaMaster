package Gui;

import DAOs.DAOenfermeira;
import Entidades.enfermeira;
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

public class GUIAltEnfermeira extends JDialog {

    Container cp;

    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JLabel lbcipEnfermeira = new JLabel("CPI da Enfermeira");
    JTextField tfcipEnfermeira = new JTextField(20);

    JLabel lbNome = new JLabel("Nome");
    JTextField tfNome = new JTextField(50);
    

    JLabel lbIdade = new JLabel("Idade");
    JTextField tfIdade = new JTextField();
    

    JButton btBuscar = new JButton("Buscar");
    
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");

    DAOenfermeira daoEnfermeira = new DAOenfermeira();
    enfermeira enfermeira = new enfermeira();
    String acao = "";

    String[] colunas = new String[]{"CPI", "Nome", "Idade"};
    String[][] dados = new String[0][colunas.length];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));
    private CardLayout cardLayout;

    public GUIAltEnfermeira() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("CRUD - Enfermeira");
        setAlwaysOnTop(true);
        
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setBackground(new Color(209,213,219));
        pnCentro.setBackground(new Color(209,213,219));
        pnSul.setBackground(new Color(209,213,219));
        pnSul.setBorder(BorderFactory.createLineBorder(Color.gray));

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbcipEnfermeira);
        pnNorte.add(tfcipEnfermeira);
        pnNorte.add(btBuscar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        pnNorte.add(btSalvar);
        pnNorte.add(btCancelar);

        btSalvar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btCancelar.setVisible(false);

        pnCentro.setLayout(new GridLayout(3, 6));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbIdade);
        pnCentro.add(tfIdade);
        
        

        tfNome.setBackground(new Color(209,213,219));
        tfNome.setForeground(new Color(59, 130, 246));

        lbNome.setForeground(new Color(59, 130, 246));
        lbNome.setBackground(new Color(209,213,219));

        lbcipEnfermeira.setBackground(new Color(209,213,219));
        lbcipEnfermeira.setForeground(new Color(59, 130, 246));

        tfIdade.setForeground(new Color(59, 130, 246));
        tfIdade.setBackground(new Color(209,213,219));

        lbIdade.setBackground(new Color(209,213,219));
        lbIdade.setForeground(new Color(59, 130, 246));
        
        
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

        tfNome.setEditable(false);

        tfcipEnfermeira.setBackground(new Color(209,213,219));
        tfcipEnfermeira.setForeground(new Color(59, 130, 246));
        btBuscar.setForeground(new Color(59, 130, 246));
        btBuscar.setBackground(new Color(209,213,219));
        btBuscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                enfermeira = new enfermeira();
                tfcipEnfermeira.setText(tfcipEnfermeira.getText().trim());//caso tenham sido digitados espaços

                if (tfcipEnfermeira.getText().equals("")) {
                    List<String> listaAuxiliar = daoEnfermeira.listInOrderNomeStrings("crm");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btBuscar.getLocationOnScreen();
                        lc.x = lc.x + btBuscar.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            tfcipEnfermeira.setText(aux[0]);
                            btBuscar.doClick();
                        } else {
                            tfcipEnfermeira.requestFocus();
                            tfcipEnfermeira.selectAll();
                        }
                    }

                    tfcipEnfermeira.requestFocus();
                    tfcipEnfermeira.selectAll();
                } else {
                    try {
                        enfermeira.setcip(Integer.valueOf(tfcipEnfermeira.getText()));
                        enfermeira = daoEnfermeira.obter(enfermeira.getcip());
                        if (enfermeira != null) { //se encontrou na lista
                           tfNome.setText(String.valueOf(enfermeira.getnome_enfermeira()));
                           tfIdade.setText(String.valueOf(enfermeira.getidade()));


                            btAlterar.setVisible(true);
                            btExcluir.setVisible(true);
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(false);
                            btListar.setVisible(true);
                            acao = "encontrou";
                        } else {
                            btSalvar.setVisible(false);
                            btCancelar.setVisible(false);
                            btBuscar.setVisible(true);
                            btListar.setVisible(true);
                        }
                        tfcipEnfermeira.setBackground(new Color(209,213,219));
                        tfcipEnfermeira.setForeground(new Color(59, 130, 246));
                    } catch (Exception x) {
                        tfcipEnfermeira.setOpaque(true);
                        tfcipEnfermeira.selectAll();
                        tfcipEnfermeira.requestFocus();
                        tfcipEnfermeira.setBackground(Color.yellow);

                    }
                }
            }
        });

        btSalvar.setForeground(new Color(59, 130, 246));
        btSalvar.setBackground(new Color(209,213,219));
        btSalvar.addActionListener((ActionEvent e) -> {
                  try {
        int cip = Integer.parseInt(tfcipEnfermeira.getText().trim());
        if (cip < 1 || cip > 999999999) {
            JOptionPane.showMessageDialog(cp, "O cpi deve estar acima de zero, espertinho.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            tfcipEnfermeira.requestFocus();
            tfcipEnfermeira.setEditable(true);
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(cp, "O cpi deve ser um número inteiro válido.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
        tfcipEnfermeira.requestFocus();
        tfcipEnfermeira.setEditable(true);
        return;
    }
             try {
        int idade = Integer.parseInt(tfIdade.getText().trim());
        if (idade <= 17 || idade >= 90) {
            JOptionPane.showMessageDialog(cp, "A idade deve estar entre 18 e 90 anos.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
            tfIdade.requestFocus();
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(cp, "A idade deve ser um número inteiro válido.", "Erro de validação", JOptionPane.ERROR_MESSAGE);
        tfIdade.requestFocus();
        return;
    }
            if (acao.equals("Adicionar")) {
                enfermeira = new enfermeira();
            }

            enfermeira.setcip(Integer.valueOf(tfcipEnfermeira.getText()));
            enfermeira.setnome_enfermeira(tfNome.getText());
            enfermeira.setidade(tfIdade.getText());
            
            
            if (acao.equals("Adicionar")) {
                daoEnfermeira.inserir(enfermeira);
            } else {
                daoEnfermeira.atualizar(enfermeira);
            }
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            tfcipEnfermeira.setEnabled(true);
            tfcipEnfermeira.setEditable(true);
            tfcipEnfermeira.requestFocus();

            tfcipEnfermeira.setText("");
            tfNome.setText("");
            tfIdade.setText("");
            
            btBuscar.setVisible(true);
            btListar.setVisible(true);
            
            tfNome.setEditable(false);
            tfIdade.setEditable(false);
        });
        btAlterar.setForeground(new Color(59, 130, 246));
        btAlterar.setBackground(new Color(209,213,219));
        btAlterar.addActionListener((ActionEvent e) -> {
            btBuscar.setVisible(false);
            btAlterar.setVisible(false);
            tfNome.requestFocus();
            tfcipEnfermeira.setEditable(false);
            tfNome.setEditable(true);
            tfIdade.setEditable(true);

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
                daoEnfermeira.remover(enfermeira);
                System.out.println(enfermeira.toString());
            }
            btExcluir.setVisible(false);
            tfcipEnfermeira.setEnabled(true);
            tfcipEnfermeira.setEditable(true);
            tfcipEnfermeira.requestFocus();
            tfcipEnfermeira.setText("");
            tfNome.setText("");
            tfIdade.setText("");

            btBuscar.setVisible(true);

            tfNome.setEditable(false);

            btAlterar.setVisible(false);
            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btBuscar.setVisible(true);
            btListar.setVisible(true);
        });
        btListar.setForeground(new Color(59, 130, 246));
        btListar.setBackground(new Color(209,213,219));
        btListar.addActionListener((ActionEvent e) -> {
            List<enfermeira> listaMedico = daoEnfermeira.listInOrderNome();
            String[] colunas1 = {"Cip", "Nome da Enfermeira", "Idade"};
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
        });
        
        btCancelar.setForeground(new Color(59, 130, 246));
        btCancelar.setBackground(new Color(209,213,219));
        btCancelar.addActionListener((ActionEvent e) -> {
            btCancelar.setVisible(false);
            tfcipEnfermeira.setText("");
            tfcipEnfermeira.requestFocus();
            tfcipEnfermeira.setEnabled(true);
            tfcipEnfermeira.setEditable(true);
            tfNome.setText("");

            tfIdade.setText("");

            tfIdade.setEditable(false);
            tfNome.setEditable(false);

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
        GUIEnfermeira guiEnfermeira = new GUIEnfermeira();
    }

}
