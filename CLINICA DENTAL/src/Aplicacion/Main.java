package Aplicacion;
import Conceptos.medico;
import Conceptos.paciente;
import Conceptos.servicio;
import Util.XML_MEDICOS;
import Util.XML_PACIENTES;
import Util.XML_SERVICIOS;
import java.util.ArrayList;

/**
 * @author Isaac Villalobos Bonilla y Kevin Vega 2024124285 y 2024800616
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<paciente> pacientes = XML_PACIENTES.Cargar("src\\DATA\\pacientes.xml");
        ArrayList<servicio> servicios = XML_SERVICIOS.Cargar("src\\DATA\\servicios.xml");
        ArrayList<medico> medicos = XML_MEDICOS.Cargar("src\\DATA\\medicos.xml", servicios);
        
        System.out.println("PACIENTES \n");
        for (paciente paciente : pacientes) {
            System.out.println("ID: " + paciente.getId());
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("Teléfono: " + paciente.getTelefono());
            System.out.println("Email: " + paciente.getEmail());
            System.out.println("-------------------------------------------");
        }
        
        System.out.println("\n \nSERVICIOS \n");
        for (servicio servicios1 : servicios) {
            System.out.println("ID: " + servicios1.getId());
            System.out.println("Nombre: " + servicios1.getNombre());
            System.out.println("Teléfono: " + servicios1.getPrecio());
            System.out.println("-------------------------------------------");
        }
        
        System.out.println("\n \nMEDICOS \n");
        for (medico medico1 : medicos) {
            System.out.println("ID: " + medico1.getId());
            System.out.println("Nombre: " + medico1.getNombre());
            System.out.println("Teléfono: " + medico1.getPuesto());
            System.out.println("Teléfono: " + medico1.getTelefono());
            System.out.println("------SERVICIOS QUE TIENE AUTORIZADOS------");
            for (servicio servicios2 : medico1.getServicios()){
             System.out.print("ID: " + servicios2.getId() + ", ");
            System.out.print("Nombre: " + servicios2.getNombre() + ", ");
            System.out.println("Teléfono: " + servicios2.getPrecio() + ".");
            System.out.println("-------------------------------------------");
            }
            System.out.println("-------------------------------------------");
        }
        
    }
}