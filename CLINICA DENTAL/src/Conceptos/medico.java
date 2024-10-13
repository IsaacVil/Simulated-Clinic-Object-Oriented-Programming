package Conceptos;

import java.util.ArrayList;

public class medico {
    private String id;
    private String nombre;
    private String puesto;
    private String telefono;
    private ArrayList<servicio> servicios;

    public medico(String id, String nombre, String puesto, String telefono, ArrayList<servicio> servicios) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.telefono = telefono;
        this.servicios = servicios;
    }
    
    public medico() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<servicio> servicios) {
        this.servicios = servicios;
    }
}
