package Conceptos;

import java.util.ArrayList;

public class solicitud {
    String id;
    String fecha_hora;
    servicio servicio;
    paciente paciente;
    medico medico;
    String estado;
    String Observaciones;
    ArrayList<servicio> otrosservicios;

    public solicitud(String id, String fecha_hora, servicio servicio, paciente paciente, medico medico, String estado, String Observaciones, ArrayList<servicio> otrosservicios) {
        this.id = id;
        this.fecha_hora = fecha_hora;
        this.servicio = servicio;
        this.paciente = paciente;
        this.medico = medico;
        this.estado = estado;
        this.Observaciones = Observaciones;
        this.otrosservicios = otrosservicios;
    }

    public String consultarId() {
        return id;
    }

    public String consultarFecha_hora() {
        return fecha_hora;
    }

    public servicio consultarServicio() {
        return servicio;
    }

    public paciente consultarPaciente() {
        return paciente;
    }

    public medico consultarMedico() {
        return medico;
    }

    public String consultarEstado() {
        return estado;
    }

    public String consultarObservaciones() {
        return Observaciones;
    }

    public ArrayList<servicio> consultarOtrosservicios() {
        return otrosservicios;
    }

    public void atenderId(String id) {
        this.id = id;
    }

    public void atenderFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public void atenderServicio(servicio servicio) {
        this.servicio = servicio;
    }

    public void atenderPaciente(paciente paciente) {
        this.paciente = paciente;
    }

    public void atenderMedico(medico medico) {
        this.medico = medico;
    }

    public void atenderEstado(String estado) {
        this.estado = estado;
    }

    public void atenderObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public void atenderOtrosservicios(ArrayList<servicio> otrosservicios) {
        this.otrosservicios = otrosservicios;
    }
    
    
}

