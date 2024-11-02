package Aplicacion;
import Conceptos.estado;
import Conceptos.medico;
import Conceptos.paciente;
import Conceptos.servicio;
import Conceptos.solicitud;
import Presentacion.Aplicacion;
import Util.XML_ESTADOS;
import Util.XML_MEDICOS;
import Util.XML_PACIENTES;
import Util.XML_SERVICIOS;
import Util.XML_SOLICITUDES;
import java.util.ArrayList;
/**
 * Proyecto 1
 * Isaac Villalobos Bonilla y Kevin Vega. 2024124285 y 2024800616.
 */
public class Main {
    public static void main(String[] args) {
        /*ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        ArrayList<servicio> servicios = XML_SERVICIOS.Cargar("src\\DATA\\servicios.xml");
        ArrayList<medico> medicos = XML_MEDICOS.Cargar("src\\DATA\\medicos.xml", servicios);
        ArrayList<estado> estados = XML_ESTADOS.Cargar("src\\DATA\\estados.xml");
        ArrayList<solicitud> solicitudes = XML_SOLICITUDES.Cargar("src\\DATA\\solicitudes.xml", pacientes, medicos, servicios, estados);
        XML_SOLICITUDES.Guardar("src\\DATA\\solicitudes.xml", solicitudes);*/
        Aplicacion app = new Aplicacion();
        app.setVisible(true);
    }
}