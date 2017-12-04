/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;


import es.uvigo.esei.dagss.dominio.entidades.EstadoReceta;
import es.uvigo.esei.dagss.dominio.entidades.Farmacia;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
@LocalBean
public class RecetaDAO extends GenericoDAO<Receta>{
 
    public List<Receta> buscarRecetasNts(String nts){
        Date fecha_actual = Calendar.getInstance().getTime();
        
        TypedQuery<Receta> q = em.createQuery("SELECT r FROM Receta AS r "
                                              + "  WHERE r.prescripcion.paciente.numeroTarjetaSanitaria = :nts"
                                              + " AND r.finValidez > :fecha_actual ", Receta.class);
        q.setParameter("nts", nts);
        q.setParameter("fecha_actual", fecha_actual);
        return q.getResultList();
    }
    
    public void servirRecetas(Receta receta,Farmacia farmaciaDispensadora){
        receta.setFarmaciaDispensadora(farmaciaDispensadora);
        receta.setEstadoReceta(EstadoReceta.SERVIDA);
        actualizar(receta);
    };
}
