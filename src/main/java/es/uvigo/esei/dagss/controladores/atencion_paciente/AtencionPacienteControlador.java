/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.atencion_paciente;

import es.uvigo.esei.dagss.controladores.autenticacion.AutenticacionControlador;
import es.uvigo.esei.dagss.dominio.daos.RecetaDAO;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author José Angel
 */
@Named()
@SessionScoped
public class AtencionPacienteControlador implements Serializable {
    
    private String nts;
    private List<Receta> recetas;
    private String nombre_paciente;
    
    @Inject
    private AutenticacionControlador autenticacionControlador;
    
    @EJB
    private RecetaDAO recetaDAO;

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }
    
    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }
        
    public String getNts() {
        return nts;
    }

    public void setNts(String nts) {
        this.nts = nts;
    }
    
    public String doBuscarRecetasEnVigor(){
        recetas = recetaDAO.buscarRecetasNts(nts);
        if(!recetas.isEmpty()){
        nombre_paciente = recetas.get(0).getPrescripcion().getPaciente().getNombre()   
                   + " " + recetas.get(0).getPrescripcion().getPaciente().getApellidos();
        }
        
        return "index";
    }
    
 
}
