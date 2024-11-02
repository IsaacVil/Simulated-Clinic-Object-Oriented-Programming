package Util;
import Conceptos.estado;
import Conceptos.medico;
import Conceptos.paciente;
import Conceptos.servicio;
import Conceptos.solicitud;
import java.io.File;
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

public class XML_SOLICITUDES {
    private static String getValue(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta);
        if (nodos.getLength() > 0 && nodos.item(0).getChildNodes().getLength() > 0) {
            Node nodo = nodos.item(0).getChildNodes().item(0);
            if (nodo != null) {
                return nodo.getNodeValue();
            }
        }
        return ""; // Return an empty string or another default value if the node is null
    }

    public static ArrayList<solicitud> Cargar(String nombreXML, ArrayList<paciente> pacientesLista, ArrayList<medico> medicosLista, ArrayList<servicio> serviciosLista, ArrayList<estado> estadosLista) {
        ArrayList<solicitud> solicitudLista = new ArrayList<>();

        try {
            File archivo = new File(nombreXML);
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.parse(archivo);
            docXML.getDocumentElement().normalize();
            NodeList nodos = docXML.getElementsByTagName("solicitud");
            for (int i = 0; i < nodos.getLength(); i++) {
                boolean funcional = true;
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String id = elemento.getAttribute("id");
                    String fecha = getValue("fecha_hora", elemento);
                    String nombreservicio = getValue("servicio", elemento);
                    
                    servicio servicio = null;
                    for (int k = 0; k < serviciosLista.size(); k++) {
                        if (serviciosLista.get(k).getNombre().equals(nombreservicio)) {
                            servicio = serviciosLista.get(k);
                            break;
                        }
                    }
                    if (servicio == null) {
                        //System.out.println("Falla el servicio");
                        funcional = false;
                    }

                    String idpaciente = getValue("Paciente", elemento);
                    paciente paciente = null;
                    for (int k = 0; k < pacientesLista.size(); k++) {
                        if (pacientesLista.get(k).getId().equals(idpaciente)) {
                            paciente = pacientesLista.get(k);
                            break;
                        }
                    }
                    if (paciente == null) {
                        //System.out.println("Falla el paciente");
                        funcional = false;
                    }

                    String nombremedico = getValue("Medico", elemento);
                    medico medico = null;
                    for (int k = 0; k < medicosLista.size(); k++) {
                        if (medicosLista.get(k).getNombre().equals(nombremedico)) {
                            medico = medicosLista.get(k);
                            break;
                        }
                    }
                    if (medico == null) {
                        //System.out.println("Falla el doctor");
                        funcional = false;
                    }

                    String nombreestado = getValue("estado", elemento);
                    String estado = null;
                    for (int k = 0; k < estadosLista.size(); k++) {
                        if (estadosLista.get(k).getNombre().equals(nombreestado)) {
                            estado = nombreestado;
                            break;
                        }
                    }
                    if (estado == null) {
                        //System.out.println("Falla el estado");
                        funcional = false;
                    }

                    String observaciones = getValue("observaciones", elemento);
                    ArrayList<servicio> otrosservicios = new ArrayList<>();
                    NodeList serviciosXML = elemento.getElementsByTagName("otros_servicios").item(0).getChildNodes();
                    for (int j = 0; j < serviciosXML.getLength(); j++) {
                        Node servicioNode = serviciosXML.item(j);
                        if (servicioNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element servicioElemento = (Element) servicioNode;
                            String servicioId = servicioElemento.getTextContent();

                            for (int k = 0; k < serviciosLista.size(); k++) {
                                if (serviciosLista.get(k).getId().equals(servicioId)) {
                                    otrosservicios.add(serviciosLista.get(k));
                                    break;
                                }
                            }
                        }
                    }

                    
                    if (funcional) {
                        solicitud solicitud1 = new solicitud(id, fecha, servicio, paciente, medico, estado, observaciones, otrosservicios);
                        solicitudLista.add(solicitud1);
                    }
                }
            }
        } catch (IOException | SAXException ex) {
            System.out.println("Error al leer el archivo XML o parsear los datos.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return solicitudLista;
    }
    
    public static void Guardar(String nombreXML, ArrayList<solicitud> solicitudes) {
        try {
            DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
            DocumentBuilder creador = industria.newDocumentBuilder();
            Document docXML = creador.newDocument(); 

            Element raiz = docXML.createElement("solicitudes");
            docXML.appendChild(raiz);

            for (solicitud sol : solicitudes) {
                Element nuevaSolicitud = docXML.createElement("solicitud");
                nuevaSolicitud.setAttribute("id", sol.consultarId());

                Element fechaHora = docXML.createElement("fecha_hora");
                fechaHora.appendChild(docXML.createTextNode(sol.consultarFecha_hora()));
                nuevaSolicitud.appendChild(fechaHora);

                Element servicio = docXML.createElement("servicio");
                if (sol.consultarServicio() != null) {
                    servicio.appendChild(docXML.createTextNode(sol.consultarServicio().getNombre()));
                } else {
                    // Añadir un nodo vacío si no hay servicio
                    servicio.appendChild(docXML.createTextNode(""));
                }
                nuevaSolicitud.appendChild(servicio);

                Element paciente = docXML.createElement("Paciente");
                if (sol.consultarPaciente() != null) {
                    paciente.appendChild(docXML.createTextNode(sol.consultarPaciente().getId()));
                } else {
                    paciente.appendChild(docXML.createTextNode(""));
                }
                nuevaSolicitud.appendChild(paciente);

                Element medico = docXML.createElement("Medico");
                if (sol.consultarMedico() != null) {
                    medico.appendChild(docXML.createTextNode(sol.consultarMedico().getNombre()));
                } else {
                    medico.appendChild(docXML.createTextNode(""));
                }
                nuevaSolicitud.appendChild(medico);

                Element estado = docXML.createElement("estado");
                if (sol.consultarEstado() != null) {
                    estado.appendChild(docXML.createTextNode(sol.consultarEstado()));
                } else {
                    estado.appendChild(docXML.createTextNode(""));
                }
                nuevaSolicitud.appendChild(estado);

                Element observaciones = docXML.createElement("observaciones");
                if (sol.consultarObservaciones() != null && !sol.consultarObservaciones().isEmpty()) {
                    observaciones.appendChild(docXML.createTextNode(sol.consultarObservaciones()));
                } else {
                    observaciones.appendChild(docXML.createTextNode(""));
                }
                nuevaSolicitud.appendChild(observaciones);

                Element listaOtrosServicios = docXML.createElement("otros_servicios");
                if (sol.consultarOtrosservicios().isEmpty()) {
                    Element servicioId = docXML.createElement("id");
                    listaOtrosServicios.appendChild(servicioId); 
                } else {
                    for (servicio serv : sol.consultarOtrosservicios()) {
                        Element servicioId = docXML.createElement("id");
                        servicioId.appendChild(docXML.createTextNode(serv.getId()));
                        listaOtrosServicios.appendChild(servicioId);
                    }
                }
                nuevaSolicitud.appendChild(listaOtrosServicios);
                raiz.appendChild(nuevaSolicitud);
            }

            TransformerFactory industria2 = TransformerFactory.newInstance();
            Transformer transformador = industria2.newTransformer();

            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "7");
            transformador.setOutputProperty(OutputKeys.STANDALONE, "no");

            DOMSource fuente = new DOMSource(docXML);
            StreamResult resultado = new StreamResult(new File(nombreXML));
            transformador.transform(fuente, resultado);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
