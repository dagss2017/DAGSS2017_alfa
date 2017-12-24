/*
 Proyecto Java EE, DAGSS-2014
 */
package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Medicamento;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class MedicamentoDAO extends GenericoDAO<Medicamento> {

        public List<Medicamento> buscarPorCampos(String query) {
            TypedQuery<Medicamento> q = em.createQuery("SELECT m FROM Medicamento AS m "
                    + "  WHERE (m.nombre LIKE :query) OR "
                    + "        (m.fabricante LIKE :query) OR (m.principioActivo LIKE :query) OR (m.familia LIKE :query) ", Medicamento.class);
            q.setParameter("query", "%" + query + "%");
            return q.getResultList();
        }
        
        public Medicamento buscarMedicamento(int id){
            TypedQuery<Medicamento> q = em.createQuery("SELECT m FROM Medicamento AS m "
                    + "  WHERE m.id = :id", Medicamento.class);
            q.setParameter("id",id);
            return q.getSingleResult();
        }
}
