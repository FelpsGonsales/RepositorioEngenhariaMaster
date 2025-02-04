package DAOs;

import DAOs.Conectar;
import static DAOs.DAOGenerico.em;
import Entidades.enfermeira;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOenfermeira extends DAOGenerico<enfermeira> {

    private List<enfermeira> lista = new ArrayList<>();

    public DAOenfermeira() {
        super(enfermeira.class);
    }

    public int autoCrmenfermeira() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cip) FROM enfermeira e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<enfermeira> listByNome(String nome_enfermeira) {
        return em.createQuery("SELECT e FROM enfermeira e WHERE e.cip) LIKE :nome_enfermeira").setParameter("nome_enfermeira", "%" + nome_enfermeira + "%").getResultList();
    }

    public List<enfermeira> listById(int cip) {
        return em.createQuery("SELECT e FROM enfermeira + e WHERE e.cip= :cip").setParameter("cip", cip).getResultList();
    }
    
      public List<enfermeira> listByIdade(String idade) {
        return em.createQuery("SELECT e FROM enfermeira e WHERE e.crm) LIKE: idade").setParameter("idade","%" + idade + "%").getResultList();
    }

    public List<enfermeira> listInOrderNome() {
        return em.createQuery("SELECT e FROM enfermeira e ORDER BY e.nome_enfermeira").getResultList();
    }

    public List<enfermeira> listInOrderId() {
        return em.createQuery("SELECT e FROM enfermeira e ORDER BY e.crm").getResultList();
    }
    
    public List<enfermeira> listInOrderArea() {
        return em.createQuery("SELECT e FROM enfermeira e ORDER BY e.area").getResultList();
    } 
        

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<enfermeira> lf;
        if (qualOrdem.equals("crm")) {
            lf = listInOrderId();
        } else {
           lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getcip()+ "-" + lf.get(i).getnome_enfermeira()+ "-" + lf.get(i).getidade());
        }
        return ls;
    }
 public String[] listInOrderNomeStringsArray() {
        List<enfermeira> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i]=(lf.get(i).getcip()+ "-" + lf.get(i).getnome_enfermeira());
        }
        return ls;
    }

    public boolean enfermeiraExiste(String cip) {
        String sql = "SELECT COUNT(*) FROM enfermeira WHERE cip = ?";
        try (Connection conn = Conectar.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cip);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se encontrar pelo menos um registro
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Retorna false caso ocorra algum problema
    }

    public static void main(String[] args) {
        DAOenfermeira daoenfermeira = new DAOenfermeira();
        List<enfermeira> listaenfermeira = daoenfermeira.list();
        for (enfermeira enfermeira : listaenfermeira) {
            System.out.println(enfermeira.getcip()+ "-" + enfermeira.getnome_enfermeira()+ "-" + enfermeira.getidade());
        }
    }
}

