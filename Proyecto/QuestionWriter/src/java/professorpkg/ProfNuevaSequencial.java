/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professorpkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ProfNuevaSequencial extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SAXBuilder SAXbuilder = new SAXBuilder();
        //Recupera los datos de la sesion 
        String autor = request.getSession().getAttribute("autor").toString();
        String titulo= request.getSession().getAttribute("titulo").toString();
        //Recupera los datos del form 
        Element contenido = new Element("contenido");
        contenido.setText(request.getParameter("contenido"));
        Element respuestas = new Element("respuestas");
        Element respuestaA = new Element("respuesta");
        Element contenidoA = new Element("contenido");
        contenidoA.setText(request.getParameter("textA"));
        respuestaA.addContent(contenidoA);
        Element correspondenciaA = new Element("correspondencia");
        correspondenciaA.setText(request.getParameter("respA"));
        respuestaA.addContent(correspondenciaA);
        respuestas.addContent(respuestaA);

        if (!request.getParameter("textB").equals("")) {
            Element respuestaB = new Element("respuesta");
            Element contenidoB = new Element("contenido");
            contenidoB.setText(request.getParameter("textB"));
            respuestaB.addContent(contenidoB);
            if (!request.getParameter("respB").equals("")) {
                Element correspondenciaB = new Element("correspondencia");
                correspondenciaB.setText(request.getParameter("respB"));
                respuestaB.addContent(correspondenciaB);
            }
            respuestas.addContent(respuestaB);
        }

        if (!request.getParameter("textC").equals("")) {
            Element respuestaC = new Element("respuesta");
            Element contenidoC = new Element("contenido");
            contenidoC.setText(request.getParameter("textC"));
            respuestaC.addContent(contenidoC);
            if (!request.getParameter("respC").equals("")) {
                Element correspondenciaC = new Element("correspondencia");
                correspondenciaC.setText(request.getParameter("respC"));
                respuestaC.addContent(correspondenciaC);
            }
            respuestas.addContent(respuestaC);
        }

        if (!request.getParameter("textD").equals("")) {
            Element respuestaD = new Element("respuesta");
            Element contenidoD = new Element("contenido");
            contenidoD.setText(request.getParameter("textD"));
            respuestaD.addContent(contenidoD);
            if (!request.getParameter("respD").equals("")) {
                Element correspondenciaD = new Element("correspondencia");
                correspondenciaD.setText(request.getParameter("respD"));
                respuestaD.addContent(correspondenciaD);
            }
            respuestas.addContent(respuestaD);
        }

        Element opciones = new Element("opciones");
        if (!request.getParameter("sfeed").equals("")) {
            Element sfeed = new Element("feedbackInicial");
            sfeed.setText(request.getParameter("sfeed"));
            opciones.addContent(sfeed);
        }

        if (!request.getParameter("efeed").equals("")) {
            Element efeed = new Element("feedbackEvaluate");
            efeed.setText(request.getParameter("efeed"));
            opciones.addContent(efeed);
        }

        if (!request.getParameter("cfeed").equals("")) {
            Element cfeed = new Element("feedbackCorrecto");
            cfeed.setText(request.getParameter("cfeed"));
            opciones.addContent(cfeed);
        }

        if (!request.getParameter("ifeed").equals("")) {
            Element ifeed = new Element("feedbackIncorrecto");
            ifeed.setText(request.getParameter("ifeed"));
            opciones.addContent(ifeed);
        }

        if (!request.getParameter("tfeed").equals("")) {
            Element tfeed = new Element("feedbackTries");
            tfeed.setText(request.getParameter("tfeed"));
            opciones.addContent(tfeed);
        }

        //Recupera la base de datos
        String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/preguntaDB.xml");
        File DBfile = new File(DBPath);
        Element root, preguntas;
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            preguntas = root.getChild("preguntas");
            int current = Integer.parseInt(root.getChildText("current"));
            current++;
            root.getChild("current").setText(String.valueOf(current));
            Element id = new Element("id").setText(String.valueOf(current));
            Element pregunta = new Element("pregunta");
            Element tipo = new Element("tipo");
            tipo.setText("secuencial");
            Element aut = new Element("autor").setText(autor);
            pregunta.addContent(id);
            pregunta.addContent(aut);
            pregunta.addContent(tipo);
            pregunta.addContent(contenido);
            pregunta.addContent(respuestas);
            pregunta.addContent(opciones);

            //Se agrega al elemento raiz
            root.getChild("preguntas").addContent(pregunta);
            XMLOutputter xmlout = new XMLOutputter();
            xmlout.setFormat(Format.getPrettyFormat());
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root, writer);
            writer.flush();
            writer.close();
            response.sendRedirect("/QuestionWriter/ProfEditarCuestionario?titulo=" + titulo);
//        Element Pregunta
        } catch (JDOMException e) {
            response.getWriter().print("Error JDOM");
        }
    }
}
