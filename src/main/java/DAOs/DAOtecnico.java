package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.tecnico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOtecnico extends DAOGenerico<tecnico> {

    private List<tecnico> lista = new ArrayList<>();

    public DAOtecnico() {
        super(tecnico.class);
    }

    public int autoidentificador() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.identificador) FROM tecnico_enfermagem e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<tecnico> listByNome(String nome_tecnico) {
        return em.createQuery("SELECT e FROM tecnico_enfermagem e WHERE e.identificador) LIKE :nome_tecnico").setParameter("nome_tecnico", "%" + nome_tecnico + "%").getResultList();
    }

    public List<tecnico> listById(int identificador) {
        return em.createQuery("SELECT e FROM tecnico_enfermagem + e WHERE e.identificador= :identificador").setParameter("identificador", identificador).getResultList();
    }
    
      public List<tecnico> listByArea(String area_tecnico) {
        return em.createQuery("SELECT e FROM tecnico_enfermagem e WHERE e.identificador) LIKE: area_tecnico").setParameter("area_tecnico","%" + area_tecnico + "%").getResultList();
    }

    public List<tecnico> listInOrderNome() {
        return em.createQuery("SELECT e FROM tecnico_enfermagem e ORDER BY e.nome_tecnico").getResultList();
    }

    public List<tecnico> listInOrderId() {
        return em.createQuery("SELECT e FROM tecnico_enfermagem e ORDER BY e.identificador").getResultList();
    }
    
    public List<tecnico> listInOrderArea() {
        return em.createQuery("SELECT e FROM tecnico_enfermagem e ORDER BY e.area_tecnico").getResultList();
    } 
        

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<tecnico> lf;
        if (qualOrdem.equals("identificador")) {
            lf = listInOrderId();
        } else {
           lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getidentificador()+ "-" + lf.get(i).getnome_tecnico()+ "-" + lf.get(i).getarea_tecnico());
        }
        return ls;
    }
 public String[] listInOrderNomeStringsArray() {
        List<tecnico> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i]=(lf.get(i).getidentificador()+ "-" + lf.get(i).getnome_tecnico());
        }
        return ls;
    }
public boolean tecnicoExiste(String identificador) {
    String sql = "SELECT COUNT(*) FROM tecnico_enfermagem WHERE identificador = ?";
    try (Connection conn = Conectar.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, identificador);
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
        DAOtecnico daotecnico_enfermagem = new DAOtecnico();
        List<tecnico> listatecnico_enfermagem = daotecnico_enfermagem.list();
        for (tecnico tecnico_enfermagem : listatecnico_enfermagem) {
            System.out.println(tecnico_enfermagem.getidentificador()+ "-" + tecnico_enfermagem.getnome_tecnico()+ "-" + tecnico_enfermagem.getarea_tecnico());
        }
    }
}

