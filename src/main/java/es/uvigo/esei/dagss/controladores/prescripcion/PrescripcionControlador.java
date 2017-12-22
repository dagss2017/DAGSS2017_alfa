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
@Named
@SessionScoped
public class PrescripcionControlador implements Serializable {
    
    private Prescripcion prescripcionActual;
            
    @EJB
    private MedicamentoDAO medicamentoDAO;
        
    @EJB
    private PrescripcionDAO prescripcionDAO;
    
    public Prescripcion getPrescripcionActual() {
        return prescripcionActual;
    }

    public void setPrescripcionActual(Prescripcion prescripcionActual) {
        this.prescripcionActual = prescripcionActual;
    }
    
    public List<Medicamento> completeText(String query) {       
        return medicamentoDAO.buscarPorCampos(query);
    }
    
    public String renderizarConfeccionarPrescripcion(){
        this.prescripcionActual = new Prescripcion();
        return "formularioPrescripcion";
    }
    
    public String doConfeccionarPrescripcion(Cita c){
        prescripcionActual.setMedico(c.getMedico());
        prescripcionActual.setPaciente(c.getPaciente());
        prescripcionDAO.crear(prescripcionActual);
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

}