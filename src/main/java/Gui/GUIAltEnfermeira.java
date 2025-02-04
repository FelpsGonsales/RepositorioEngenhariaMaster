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

        pnNorte.setBackground(Color.black);
        pnCentro.setBackground(Color.black);
        pnSul.setBackground(Color.black);
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
        
        

        tfNome.setBackground(Color.black);
        tfNome.setForeground(Color.green);

        lbNome.setForeground(Color.green);
        lbNome.setBackground(Color.black);

        lbcipEnfermeira.setBackground(Color.black);
        lbcipEnfermeira.setForeground(Color.green);

        tfIdade.setForeground(Color.green);
        tfIdade.setBackground(Color.black);

        lbIdade.setBackground(Color.black);
        lbIdade.setForeground(Color.green);
        
        
        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }
        pnSul.add(pnVazio, "vazio");
        pnVazio.setBackground(Color.black);
        pnSul.add(pnAvisos, " ");
        pnAvisos.setBackground(Color.black);
        pnAvisos.setForeground(Color.green);
        pnSul.add(pnListagem, "listagem");
        pnListagem.setBackground(Color.black);
        pnListagem.setForeground(Color.green);
        tabela.setEnabled(false);
        tabela.setBackground(Color.black);
        tabela.setForeground(Color.green);

        pnAvisos.add(new JLabel("Avisos"));

        tfNome.setEditable(false);

        tfcipEnfermeira.setBackground(Color.black);
        tfcipEnfermeira.setForeground(Color.green);
        btBuscar.setForeground(Color.green);
        btBuscar.setBackground(Color.black);
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
                        tfcipEnfermeira.setBackground(Color.black);
                        tfcipEnfermeira.setForeground(Color.green);
                    } catch (Exception x) {
                        tfcipEnfermeira.setOpaque(true);
                        tfcipEnfermeira.selectAll();
                        tfcipEnfermeira.requestFocus();
                        tfcipEnfermeira.setBackground(Color.yellow);

                    }
                }
            }
        });

        btSalvar.setForeground(Color.green);
        btSalvar.setBackground(Color.black);
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
        if (idade <= 18 || idade >= 90) {
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
        btAlterar.setForeground(Color.green);
        btAlterar.setBackground(Color.black);
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
        btExcluir.setForeground(Color.green);
        btExcluir.setBackground(Color.black);
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
        btListar.setForeground(Color.green);
        btListar.setBackground(Color.black);
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
        
        btCancelar.setForeground(Color.green);
        btCancelar.setBackground(Color.black);
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

