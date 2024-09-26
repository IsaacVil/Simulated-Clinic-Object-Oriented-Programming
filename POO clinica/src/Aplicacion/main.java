//Isaac Villalobos Bonilla y Kevin Vega Gutierrez
package Aplicacion;
import Conceptos.Cliente;
import java.util.ArrayList;
import presentacion.VentanaPrincipal;
public class main {
    public static void main(String[] args){
        ArrayList<Cliente> clientes = Util.CargadorXML.Cargar("clientes.xml");
        for (Cliente c : clientes){
            System.out.println(c);
        }
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.setVisible(true);
    }
}