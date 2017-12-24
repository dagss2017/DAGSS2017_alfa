/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.prescripcion;

import es.uvigo.esei.dagss.controladores.atencion_paciente.AtencionPacienteControlador;
import es.uvigo.esei.dagss.dominio.daos.MedicamentoDAO;
import es.uvigo.esei.dagss.dominio.daos.PrescripcionDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.Medicamento;
import es.uvigo.esei.dagss.dominio.entidades.Prescripcion;
import es.uvigo.esei.dagss.servicios.recetas.PlanificadorRecetas;
import es.uvigo.esei.dagss.servicios.recetas.PlanificadorRecetasImpl;
import es.uvigo.esei.dagss.servicios.recetas.PrescripcionServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author José Angel
 */
@Named
@SessionScoped
public class PrescripcionControlador implements Serializable {
    

    List<Medicamento> filteredMedicamentos;
    
    Prescripcion prescripcionActual;
    PrescripcionServicio prescripcionServicio;
    
    @Inject
    AtencionPacienteControlador atencionPaciente;
    
    @EJB
    private MedicamentoDAO medicamentoDAO;
        
    @EJB
    private PrescripcionDAO prescripcionDAO;
    
    @PostConstruct
    public void inicializar(){
        this.prescripcionServicio = new PrescripcionServicio();
    }
    
    public Prescripcion getPrescripcionActual() {
        return prescripcionActual;
    }

    public void setPrescripcionActual(Prescripcion prescripcionActual) {
        this.prescripcionActual = prescripcionActual;
    }
    
    public List<Medicamento> completeText(String query) {  
        filteredMedicamentos = medicamentoDAO.buscarPorCampos(query);
        return filteredMedicamentos;
    }
    
    public String renderizarConfeccionarPrescripcion(){
        this.prescripcionActual = new Prescripcion();
        return "formularioPrescripcion";
    }
    
    public String doConfeccionarPrescripcion(){
        prescripcionActual.setMedicamento(medicamentoDAO.buscarMedicamento(1));
        prescripcionActual.setMedico(atencionPaciente.getCitaActual().getMedico());
        prescripcionActual.setPaciente(atencionPaciente.getCitaActual().getPaciente());
        System.out.println(atencionPaciente.getCitaActual().getPaciente().getNombre());
        prescripcionDAO.crear(prescripcionActual);
        prescripcionServicio.procesarPrescripcion(prescripcionActual);
        return "atencionPaciente";
    }
    
    public String doEliminarPrescripcion(Prescripcion p){
        prescripcionDAO.eliminar(p);
        return "atencionPaciente";
    }
    
    public String doRenderizarEditarPrescripcion(Prescripcion p){
        prescripcionActual = p;
        return "editarPrescripcion";
    }
    
      public String doEditarPrescripcion(){
        prescripcionDAO.actualizar(prescripcionActual);
        return "atencionPaciente";
    }
      
      
    public List<Medicamento> getFilteredMedicamentos() {
        return filteredMedicamentos;
}

}