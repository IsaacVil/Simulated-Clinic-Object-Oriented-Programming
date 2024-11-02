package Util;

import Conceptos.estado;
import Conceptos.medico;
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

public class XML_ESTADOS {
    private static String getValue(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node nodo = (Node) nodos.item(0);
        return nodo.getNodeValue();
    }

    public static ArrayList<estado> Cargar(String nombreXML) {
        ArrayList<estado> estadosLista = new ArrayList<>();

        try {
            File archivo = new File(nombreXML);
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.parse(archivo);
            docXML.getDocumentElement().normalize();

            NodeList nodos = docXML.getElementsByTagName("estado");

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String id = elemento.getAttribute("id");
                    String nombre = getValue("nombre", elemento);

                    estado estado1 = new estado(id, nombre);
                    estadosLista.add(estado1);
                }
            }
        } catch (IOException | SAXException ex) {
            System.out.println("Error al leer el archivo XML o parsear los datos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return estadosLista;
    }
    
    public static void Guardar(String nombreXML, ArrayList<estado> estados) {
        try {
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.newDocument(); 

            Element raiz = docXML.createElement("estados");
            docXML.appendChild(raiz);

            for (estado p : estados) {
                Element nuevoPaciente = docXML.createElement("estado");
                nuevoPaciente.setAttribute("id", p.getId());

                Element nuevoNombre = docXML.createElement("nombre");
                nuevoNombre.appendChild(docXML.createTextNode(p.getNombre()));
                nuevoPaciente.appendChild(nuevoNombre);

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

        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
