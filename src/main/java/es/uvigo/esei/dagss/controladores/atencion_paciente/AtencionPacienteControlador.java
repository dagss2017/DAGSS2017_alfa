/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.atencion_paciente;

import es.uvigo.esei.dagss.controladores.autenticacion.AutenticacionControlador;
import es.uvigo.esei.dagss.controladores.medico.MedicoControlador;
import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.daos.RecetaDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.Farmacia;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.dominio.entidades.Receta;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author José Angel
 */
@Named
@SessionScoped
public class AtencionPacienteControlador implements Serializable {
    
    private String nts;
    private List<Receta> recetas;
    private List<Cita> citas;
    private List<Prescripcion> prescripciones;
    
    public Cita getCitaActual() {
        return citaActual;
    }

    public void setCitaActual(Cita citaActual) {
        this.citaActual = citaActual;
    }
    private Cita citaActual;
    private String nombre_paciente;
    
    @Inject
    private AutenticacionControlador autenticacionControlador;
    
    @EJB
    private RecetaDAO recetaDAO;
    
    @EJB
    private CitaDAO citaDAO;
    
    @EJB
    private PrescripcionDAO prescripcionDAO;

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
    
    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
    
    public List<Prescripcion> getPrescripciones() {
        return prescripciones;
    }

    public void setPrescripciones(List<Prescripcion> prescripciones) {
        this.prescripciones = prescripciones;
    }
    
    
    //Gestión Recetas
    public String doBuscarRecetasEnVigor(){
        recetas = recetaDAO.buscarRecetasNts(nts);
        if(!recetas.isEmpty()){
        nombre_paciente = recetas.get(0).getPrescripcion().getPaciente().getNombre()   
                   + " " + recetas.get(0).getPrescripcion().getPaciente().getApellidos();
        }
        
        return "index";
    }
    
    public String doServirRecetaPaciente(Receta receta, Farmacia farmaciaDispensadora){
        recetaDAO.servirRecetas(receta,farmaciaDispensadora);
        recetas = recetaDAO.buscarRecetasNts(getNts());
        return "index";
    }
    
    //Gestión Citas
    public List<Cita> buscarCitasHoy(Medico m){
        return citaDAO.buscarCitasPorMedico(m);
    }
    
    public String doAtenderCita(Cita cita){
        this.citaActual = cita;
        return "atencionPaciente";
    }
    
    public String doListarPrescripciones(Paciente paciente){
        prescripciones = prescripcionDAO.buscarPrescipcionesPaciente(paciente);
        return "atencionPaciente";    
    }
   
    
}
