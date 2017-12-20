/*
 Proyecto Java EE, DAGSS-2016
 */
package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class PrescripcionDAO extends GenericoDAO<Prescripcion> {

    public Prescripcion buscarPorIdConRecetas(Long id) {
        TypedQuery<Prescripcion> q = em.createQuery("SELECT p FROM Prescripcion AS p JOIN FETCH p.recetas"
                                                  + "  WHERE p.id = :id", Prescripcion.class);
        q.setParameter("id", id);
        
        return q.getSingleResult();
    }
    
    public List<Prescripcion> buscarPrescipcionesPaciente(Paciente p){
        Date fecha_actual = Calendar.getInstance().getTime();
        TypedQuery<Prescripcion> q = em.createQuery("SELECT pr FROM Prescripcion AS pr "
                + " WHERE pr.paciente = :p"
                + " AND pr.fechaFin > :fecha_actual ", Prescripcion.class);
        
        q.setParameter("p", p);
        q.setParameter("fecha_actual", fecha_actual);
        
        return q.getResultList();
    }
}
