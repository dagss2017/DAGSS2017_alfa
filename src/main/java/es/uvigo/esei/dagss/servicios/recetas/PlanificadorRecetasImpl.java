/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.servicios.recetas;

import es.uvigo.esei.dagss.dominio.entidades.EstadoReceta;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.Days;
import org.omnifaces.el.functions.Dates;

/**
 *
 * @author angel
 */
public class PlanificadorRecetasImpl implements PlanificadorRecetas{

    public PlanificadorRecetasImpl(){}
    
    @Override
    public List<Receta> planificar(Prescripcion p) {
        List<Receta> listaRecetas= new ArrayList<>();
        int numeroDosis = p.getMedicamento().getNumeroDosis();
        int dosis = p.getDosis();
        int totalDias = Dates.daysBetween(p.getFechaInicio(), p.getFechaFin());
        int numRecetas = (int) Math.ceil(dosis*totalDias/numeroDosis);
        EstadoReceta estado = EstadoReceta.GENERADA;
        Date inicioValidez = p.getFechaInicio();
        int diasDuracionCaja = numeroDosis / dosis;
        Date finValidez = Dates.addDays(inicioValidez, diasDuracionCaja);
        for(int i = 0; i < numRecetas; i++){
            Receta r = new Receta(p, 1, inicioValidez, finValidez, estado);
            inicioValidez = finValidez;
            finValidez = Dates.addDays(inicioValidez, diasDuracionCaja);
            listaRecetas.add(r);
        }              
        return listaRecetas;
    }
    
}
