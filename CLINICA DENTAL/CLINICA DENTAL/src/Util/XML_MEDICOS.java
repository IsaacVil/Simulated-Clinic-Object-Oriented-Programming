package Util;

import Conceptos.medico;
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

public class XML_MEDICOS {
    
    private static String getValue(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node nodo = (Node) nodos.item(0);
        return nodo.getNodeValue();
    }

    public static ArrayList<medico> Cargar(String nombreXML, ArrayList<servicio> serviciosLista) {
        ArrayList<medico> medicosLista = new ArrayList<>();

        try {
            File archivo = new File(nombreXML);
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.parse(archivo);
            docXML.getDocumentElement().normalize();

            NodeList nodos = docXML.getElementsByTagName("medico");

            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String id = elemento.getAttribute("id");
                    String nombre = getValue("nombre", elemento);
                    String puesto = getValue("puesto", elemento);
                    String telefono = getValue("telefono", elemento);

                    ArrayList<servicio> serviciosdelmedico = new ArrayList<>();
                    NodeList serviciosXML = elemento.getElementsByTagName("servicios").item(0).getChildNodes();
                    for (int j = 0; j < serviciosXML.getLength(); j++) {
                        Node servicioNode = serviciosXML.item(j);
                        if (servicioNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element servicioElemento = (Element) servicioNode;
                            String servicioId = servicioElemento.getTextContent();

                            for (int k = 0; k < serviciosLista.size(); k++) 
                            {
                                servicio serv = serviciosLista.get(k);
                                if (serv.getId().equals(servicioId)) 
                                {
                                    serviciosdelmedico.add(serv);
                                    break; 
                                }
                            }
                        }
                    }
                    medico medico1 = new medico(id, nombre, puesto, telefono, serviciosdelmedico);
                    medicosLista.add(medico1);
                }
            }
        } catch (IOException | SAXException ex) {
            System.out.println("Error al leer el archivo XML o parsear los datos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return medicosLista;
    }
    
    public static void Guardar(String nombreXML, ArrayList<medico> medicos) {
        try {
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.newDocument(); 

            Element raiz = docXML.createElement("medicos");
            docXML.appendChild(raiz);

            for (medico p : medicos) {
                Element nuevoPaciente = docXML.createElement("medico");
                nuevoPaciente.setAttribute("id", p.getId());

                Element nuevoNombre = docXML.createElement("nombre");
                nuevoNombre.appendChild(docXML.createTextNode(p.getNombre()));
                nuevoPaciente.appendChild(nuevoNombre);

                Element nuevoEmail = docXML.createElement("puesto");
                nuevoEmail.appendChild(docXML.createTextNode(p.getPuesto()));
                nuevoPaciente.appendChild(nuevoEmail);

                Element nuevoTelefono = docXML.createElement("telefono");
                nuevoTelefono.appendChild(docXML.createTextNode(p.getTelefono()));
                nuevoPaciente.appendChild(nuevoTelefono);

                Element listaServicios = docXML.createElement("servicios");

                if (p.getServicios().isEmpty()) {
                    Element nuevoId = docXML.createElement("id"); 
                    // Se añade el elemento id vacío en lugar de cerrar solo
                    listaServicios.appendChild(nuevoId); 
                } else {
                    for (servicio servicio : p.getServicios()) { 
                        Element nuevoId = docXML.createElement("id"); 
                        String idServicio = servicio.getId(); 
                        nuevoId.appendChild(docXML.createTextNode(idServicio)); 
                        listaServicios.appendChild(nuevoId); 
                    }
                }
                nuevoPaciente.appendChild(listaServicios);
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