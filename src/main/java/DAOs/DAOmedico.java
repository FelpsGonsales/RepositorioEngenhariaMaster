package DAOs;

import Entidades.medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOmedico extends DAOGenerico<medico> {

    private List<medico> lista = new ArrayList<>();

    public DAOmedico() {
        super(medico.class);
    }

    public int autoCrmmedico() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.crm) FROM medico e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<medico> listByNome(String nome_medico) {
        return em.createQuery("SELECT e FROM medico e WHERE e.crm) LIKE :nome_medico").setParameter("nome_medico", "%" + nome_medico + "%").getResultList();
    }

    public List<medico> listById(int crm) {
        return em.createQuery("SELECT e FROM medico + e WHERE e.crm= :crm").setParameter("crm", crm).getResultList();
    }
    
      public List<medico> listByArea(String area) {
        return em.createQuery("SELECT e FROM medico e WHERE e.crm) LIKE: area").setParameter("area","%" + area + "%").getResultList();
    }

    public List<medico> listInOrderNome() {
        return em.createQuery("SELECT e FROM medico e ORDER BY e.nome_medico").getResultList();
    }

    public List<medico> listInOrderId() {
        return em.createQuery("SELECT e FROM medico e ORDER BY e.crm").getResultList();
    }
    
    public List<medico> listInOrderArea() {
        return em.createQuery("SELECT e FROM medico e ORDER BY e.area").getResultList();
    } 
        

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<medico> lf;
        if (qualOrdem.equals("crm")) {
            lf = listInOrderId();
        } else {
           lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getcrm()+ "-" + lf.get(i).getnome_medico()+ "-" + lf.get(i).getarea());
        }
        return ls;
    }
 public String[] listInOrderNomeStringsArray() {
        List<medico> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i]=(lf.get(i).getcrm()+ "-" + lf.get(i).getnome_medico());
        }
        return ls;
    }
public boolean medicoExiste(String crm) {
    String sql = "SELECT COUNT(*) FROM medico WHERE crm = ?";
    try (Connection conn = Conectar.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, crm);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
    public static void main(String[] args) {
        DAOmedico daomedico = new DAOmedico();
        List<medico> listamedico = daomedico.list();
        for (medico medico : listamedico) {
            System.out.println(medico.getcrm()+ "-" + medico.getnome_medico()+ "-" + medico.getarea());
        }
    }
}

