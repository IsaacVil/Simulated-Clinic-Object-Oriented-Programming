package Util;

import Conceptos.estado;
import Conceptos.medico;
import Conceptos.paciente;
import Conceptos.servicio;
import Conceptos.solicitud;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
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
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static String getValue(String etiqueta, Element elemento) {
        NodeList nodos = elemento.getElementsByTagName(etiqueta);
        if (nodos.getLength() > 0 && nodos.item(0).getChildNodes().getLength() > 0) {
            Node nodo = nodos.item(0).getChildNodes().item(0);
            if (nodo != null) {
                return nodo.getNodeValue().trim(); // Trim para evitar espacios en blanco
            }
        }
        return ""; // Retorna cadena vacía si el nodo es nulo
    }

public static ArrayList<solicitud> Cargar(String nombreXML, ArrayList<paciente> pacientesLista, ArrayList<medico> medicosLista, ArrayList<servicio> serviciosLista, ArrayList<estado> estadosLista) {
    ArrayList<solicitud> solicitudLista = new ArrayList<>();

    try {
        File archivo = new File(nombreXML);

        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("El archivo XML no existe o está vacío: " + nombreXML);
            return solicitudLista; // Retorna lista vacía si no existe el archivo
        }

        DocumentBuilderFactory industria = DocumentBuilderFactory.newInstance();
        DocumentBuilder creador = industria.newDocumentBuilder();
        Document docXML = creador.parse(archivo);

        if (docXML.getDocumentElement() != null) {
            docXML.getDocumentElement().normalize();
        }

        NodeList nodos = docXML.getElementsByTagName("solicitud");

        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);

            if (nodo.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element elemento = (Element) nodo;

            String id = elemento.getAttribute("id");
            String fechaStr = getValue("fecha_hora", elemento);
            String idServicio = getValue("servicio", elemento);
            String idPaciente = getValue("paciente", elemento);
            String idMedico = getValue("medico", elemento);
            String idEstado = getValue("estado", elemento); // Cargar el id del estado
            String observaciones = getValue("observaciones", elemento);

            servicio servicio = serviciosLista.stream().filter(s -> s.getId().equals(idServicio)).findFirst().orElse(null);
            paciente paciente = pacientesLista.stream().filter(p -> p.getId().equals(idPaciente)).findFirst().orElse(null);
            medico medico = medicosLista.stream().filter(m -> m.getId().equals(idMedico)).findFirst().orElse(null);

            // Buscar el estado por ID; si no se encuentra, asignar "000" (Nuevo)
            estado estado = estadosLista.stream().filter(e -> e.getId().equals(idEstado)).findFirst().orElse(null);
            String estadoNombre = (estado != null) ? estado.getNombre() : "Nuevo"; // Nombre del estado
            String estadoId = (estado != null) ? estado.getId() : "000"; // ID del estado

            ArrayList<servicio> otrosServicios = new ArrayList<>();
            NodeList serviciosXML = elemento.getElementsByTagName("otros_servicios");
            if (serviciosXML.getLength() > 0) {
                NodeList serviciosNodes = serviciosXML.item(0).getChildNodes();
                for (int j = 0; j < serviciosNodes.getLength(); j++) {
                    Node servicioNode = serviciosNodes.item(j);
                    if (servicioNode.getNodeType() == Node.ELEMENT_NODE) {
                        String servicioId = servicioNode.getTextContent().trim();
                        servicio s = serviciosLista.stream().filter(serv -> serv.getId().equals(servicioId)).findFirst().orElse(null);
                        if (s != null) {
                            otrosServicios.add(s);
                        }
                    }
                }
            }

            solicitud solicitud1 = new solicitud(id, fechaStr, servicio, paciente, medico, estadoId, observaciones, otrosServicios);
            solicitudLista.add(solicitud1);
        }
    } catch (IOException | SAXException ex) {
        System.out.println("Error al leer el archivo XML o parsear los datos: " + ex.getMessage());
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
            fechaHora.appendChild(docXML.createTextNode(sol.consultarFecha_hora().trim()));
            nuevaSolicitud.appendChild(fechaHora);

            Element servicio = docXML.createElement("servicio");
            servicio.appendChild(docXML.createTextNode(sol.consultarServicio() != null ? sol.consultarServicio().getId().trim() : ""));
            nuevaSolicitud.appendChild(servicio);

            Element paciente = docXML.createElement("paciente");
            paciente.appendChild(docXML.createTextNode(sol.consultarPaciente() != null ? sol.consultarPaciente().getId().trim() : ""));
            nuevaSolicitud.appendChild(paciente);

            Element medico = docXML.createElement("medico");
            medico.appendChild(docXML.createTextNode(sol.consultarMedico() != null ? sol.consultarMedico().getId().trim() : ""));
            nuevaSolicitud.appendChild(medico);

            Element estado = docXML.createElement("estado");
            // Almacenar el ID del estado en lugar del nombre
            estado.appendChild(docXML.createTextNode(sol.consultarEstado() != null && !sol.consultarEstado().isEmpty() ? sol.consultarEstado().trim() : "000"));
            nuevaSolicitud.appendChild(estado);

            Element observaciones = docXML.createElement("observaciones");
            observaciones.appendChild(docXML.createTextNode(sol.consultarObservaciones() != null ? sol.consultarObservaciones().trim() : ""));
            nuevaSolicitud.appendChild(observaciones);

            Element listaOtrosServicios = docXML.createElement("otros_servicios");
            for (servicio serv : sol.consultarOtrosservicios()) {
                Element servicioId = docXML.createElement("id");
                servicioId.appendChild(docXML.createTextNode(serv.getId().trim()));
                listaOtrosServicios.appendChild(servicioId);
            }
            nuevaSolicitud.appendChild(listaOtrosServicios);

            raiz.appendChild(nuevaSolicitud);
        }

        TransformerFactory industria2 = TransformerFactory.newInstance();
        Transformer transformador = industria2.newTransformer();

        transformador.setOutputProperty(OutputKeys.INDENT, "yes");
        transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource fuente = new DOMSource(docXML);
        StreamResult resultado = new StreamResult(new File(nombreXML));
        transformador.transform(fuente, resultado);

        System.out.println("Archivo sobrescrito correctamente: " + nombreXML);

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}







}
