package Util;
import Conceptos.paciente;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XML_PACIENTES {

    private static String getValue(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node nodo = (Node) nodos.item(0);
        return nodo.getNodeValue();
    }

    public static ArrayList<paciente> Cargar(String nombreXML) {
        ArrayList<paciente> pacientes = new ArrayList<>(); 

        try {
            File archivo = new File(nombreXML);
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.parse(archivo);
            docXML.getDocumentElement().normalize();

            NodeList nodos = docXML.getElementsByTagName("paciente"); 

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String id = elemento.getAttribute("id");
                    String nombre = getValue("nombre", elemento);
                    String telefono = getValue("telefono", elemento);
                    String email = getValue("email", elemento);
                    paciente paciente1 = new paciente(id, nombre, telefono, email);
                    pacientes.add(paciente1);
                }
            }
        } catch (IOException | SAXException ex) { 
            System.out.println("Error al leer el archivo XML o parsear los datos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pacientes;
    }
    
    public static void Guardar(String nombreXML, ArrayList<paciente> pacientes) {
            try {
                DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
                DocumentBuilder creador = industria.newDocumentBuilder();
                Document docXML = creador.newDocument(); 

                Element raiz = docXML.createElement("pacientes");
                docXML.appendChild(raiz);

                for (paciente p : pacientes) {
                    Element nuevoPaciente = docXML.createElement("paciente");
                    nuevoPaciente.setAttribute("id", p.getId());

                    Element nuevoNombre = docXML.createElement("nombre");
                    nuevoNombre.appendChild(docXML.createTextNode(p.getNombre()));
                    nuevoPaciente.appendChild(nuevoNombre);

                    Element nuevoTelefono = docXML.createElement("telefono");
                    nuevoTelefono.appendChild(docXML.createTextNode(p.getTelefono()));
                    nuevoPaciente.appendChild(nuevoTelefono);

                    Element nuevoEmail = docXML.createElement("email");
                    nuevoEmail.appendChild(docXML.createTextNode(p.getEmail()));
                    nuevoPaciente.appendChild(nuevoEmail);

                    raiz.appendChild(nuevoPaciente);
                }

                TransformerFactory industria2 = TransformerFactory.newInstance();
                Transformer transformador = industria2.newTransformer();

                transformador.setOutputProperty(OutputKeys.INDENT, "yes");
                transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "7");
                transformador.setOutputProperty(OutputKeys.STANDALONE, "no");

                DOMSource fuente = new DOMSource(docXML);
                StreamResult resultado = new StreamResult(new FileOutputStream(nombreXML));
                transformador.transform(fuente, resultado);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
}