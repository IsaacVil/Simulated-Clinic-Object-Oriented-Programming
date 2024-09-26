package Util;
import Conceptos.Cliente;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CargadorXML {

    private static String getValue(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node nodo = (Node) nodos.item(0);
        return nodo.getNodeValue();
    }

    public static ArrayList<Cliente> Cargar(String nombreXML) {
        ArrayList<Cliente> clientes = new ArrayList<>(); 

        try {
            File archivo = new File(nombreXML);
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.parse(archivo);
            docXML.getDocumentElement().normalize();

            NodeList nodos = docXML.getElementsByTagName("cliente"); 

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String nombre = getValue("nombre", elemento);
                    String codigo = getValue("codigo", elemento);
                    String telefono = getValue("telefono", elemento);
                    Cliente cliente1 = new Cliente(nombre, codigo, telefono);
                    clientes.add(cliente1);
                }
            }
        } catch (IOException | SAXException ex) { 
            System.out.println("Error al leer el archivo XML o parsear los datos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientes;
    }
}