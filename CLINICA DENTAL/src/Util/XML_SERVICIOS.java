package Util;
import Conceptos.paciente;
import Conceptos.servicio;
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

public class XML_SERVICIOS {

    private static String getValue(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node nodo = (Node) nodos.item(0);
        return nodo.getNodeValue();
    }

    public static ArrayList<servicio> Cargar(String nombreXML) {
        ArrayList<servicio> servicioslista = new ArrayList<>(); 

        try {
            File archivo = new File(nombreXML);
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.parse(archivo);
            docXML.getDocumentElement().normalize();

            NodeList nodos = docXML.getElementsByTagName("servicio"); 

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String id = elemento.getAttribute("id");
                    String nombre = getValue("nombre", elemento);
                    String precio = getValue("precio", elemento);
                    servicio servicio1 = new servicio(id, nombre, precio);
                    servicioslista.add(servicio1);
                }
            }
        } catch (IOException | SAXException ex) { 
            System.out.println("Error al leer el archivo XML o parsear los datos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return servicioslista;
    }
    
    public static void Guardar(String nombreXML, ArrayList<servicio> servicios) {
            try {
                DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
                DocumentBuilder creador = industria.newDocumentBuilder();
                Document docXML = creador.newDocument(); 

                Element raiz = docXML.createElement("servicios");
                docXML.appendChild(raiz);

                for (servicio p : servicios) {
                    Element nuevoServicio = docXML.createElement("servicio");
                    nuevoServicio.setAttribute("id", p.getId());

                    Element nuevoNombre = docXML.createElement("nombre");
                    nuevoNombre.appendChild(docXML.createTextNode(p.getNombre()));
                    nuevoServicio.appendChild(nuevoNombre);

                    Element nuevoPrecio = docXML.createElement("precio");
                    nuevoPrecio.appendChild(docXML.createTextNode(p.getPrecio()));
                    nuevoServicio.appendChild(nuevoPrecio);

                    raiz.appendChild(nuevoServicio);
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