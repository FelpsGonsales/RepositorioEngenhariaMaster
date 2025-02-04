package DAOs;

import static DAOs.DAOGenerico.em;
import Entidades.paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOpaciente extends DAOGenerico<paciente> {

    private List<paciente> lista = new ArrayList<>();

    public DAOpaciente() {
        super(paciente.class);
    }

    public int autoidpaciente() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idpaciente) FROM paciente e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<paciente> listByNome(String nome_paciente) {
        return em.createQuery("SELECT e FROM paciente e WHERE e.idpaciente) LIKE :nome_paciente").setParameter("nome_paciente", "%" + nome_paciente + "%").getResultList();
    }

    public List<paciente> listById(int idpaciente) {
        return em.createQuery("SELECT e FROM paciente + e WHERE e.idpaciente= :idpaciente").setParameter("idpaciente", idpaciente).getResultList();
    }
    
      public List<paciente> listByArea(String idade) {
        return em.createQuery("SELECT e FROM paciente e WHERE e.idpaciente) LIKE: idade").setParameter("idade","%" + idade + "%").getResultList();
    }

    public List<paciente> listInOrderNome() {
        return em.createQuery("SELECT e FROM paciente e ORDER BY e.nome_paciente").getResultList();
    }

    public List<paciente> listInOrderId() {
        return em.createQuery("SELECT e FROM paciente e ORDER BY e.idpaciente").getResultList();
    }
    
    public List<paciente> listInOrderArea() {
        return em.createQuery("SELECT e FROM paciente e ORDER BY e.idade").getResultList();
    } 
        

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<paciente> lf;
        if (qualOrdem.equals("idpaciente")) {
            lf = listInOrderId();
        } else {
           lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getidpaciente()+ "-" + lf.get(i).getnome_paciente()+ "-" + lf.get(i).getidade());
        }
        return ls;
    }
 public String[] listInOrderNomeStringsArray() {
        List<paciente> lf = listInOrderNome();
        String[] ls = new String[lf.size()];
        for (int i = 0; i < lf.size(); i++) {
            ls[i]=(lf.get(i).getidpaciente()+ "-" + lf.get(i).getnome_paciente());
        }
        return ls;
    }
public boolean pacienteExiste(String idpaciente) {
    String sql = "SELECT COUNT(*) FROM paciente WHERE idpaciente = ?";
    try (Connection conn = Conectar.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, idpaciente);
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
        DAOpaciente daopaciente = new DAOpaciente();
        List<paciente> listapaciente = daopaciente.list();
        for (paciente paciente : listapaciente) {
            System.out.println(paciente.getidpaciente()+ "-" + paciente.getnome_paciente()+ "-" + paciente.getidade());
        }
    }
}

