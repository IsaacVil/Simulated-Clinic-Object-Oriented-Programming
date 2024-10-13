package Aplicacion;
import Conceptos.medico;
import Conceptos.paciente;
import Conceptos.servicio;
import Presentacion.Aplicacion;
import Util.XML_MEDICOS;
import Util.XML_PACIENTES;
import Util.XML_SERVICIOS;
import java.util.ArrayList;
/**
 * @author Isaac Villalobos Bonilla y Kevin Vega. 2024124285 y 2024800616.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        ArrayList<servicio> servicios = XML_SERVICIOS.Cargar("src\\DATA\\servicios.xml");
        ArrayList<medico> medicos = XML_MEDICOS.Cargar("src\\DATA\\medicos.xml", servicios);
        
        Aplicacion app = new Aplicacion();
        app.setVisible(true);
    }
}