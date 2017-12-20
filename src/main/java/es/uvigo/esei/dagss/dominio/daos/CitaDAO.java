/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


@Stateless
@LocalBean
public class CitaDAO extends GenericoDAO<Cita>{    

    public List<Cita> buscarCitasPorMedico(Medico m){
        Date fecha_actual = Calendar.getInstance().getTime();
        TypedQuery<Cita> q = em.createQuery("SELECT c FROM Cita AS c "
                + " WHERE c.medico = :m"
                + " AND c.fecha = :fecha_actual ", Cita.class);
        
        q.setParameter("m", m);
        q.setParameter("fecha_actual", fecha_actual);
        return q.getResultList();
    }
    
    public void cambiarEstadoCita(Cita c,String estadoCita){
        c.setEstado(EstadoCita.valueOf(estadoCita));      
        actualizar(c);
    };
}
