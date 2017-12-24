/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.servicios.recetas;

import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.daos.RecetaDAO;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


/**
 *
 * @author angel
 */
public class PrescripcionServicio {
    
    @Inject 
    PrescripcionDAO prescripcionDAO;
    
    @Inject
    RecetaDAO recetaDAO;
    
    @Inject
    PlanificadorRecetas planificadorRecetas;
   
    List<Receta> listaRecetas = new ArrayList<>();
    
    public PrescripcionServicio(){}

    public PrescripcionDAO getPrescripcionDAO() {
        return prescripcionDAO;
    }

    public void setPrescripcionDAO(PrescripcionDAO prescripcionDAO) {
        this.prescripcionDAO = prescripcionDAO;
    }

    public RecetaDAO getRecetaDAO() {
        return recetaDAO;
    }

    public void setRecetaDAO(RecetaDAO recetaDAO) {
        this.recetaDAO = recetaDAO;
    }

    public PlanificadorRecetas getPlanificadorRecetas() {
        return planificadorRecetas;
    }

    public void setPlanificadorRecetas(PlanificadorRecetas planificadorRecetas) {
        this.planificadorRecetas = planificadorRecetas;
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }
       
    public void procesarPrescripcion(Prescripcion p){
        listaRecetas = planificadorRecetas.planificar(p);
        listaRecetas.stream().map((Receta next) -> {
            return next;
        }).forEachOrdered((next) -> {
            recetaDAO.crear(next);
        });
    }
}
