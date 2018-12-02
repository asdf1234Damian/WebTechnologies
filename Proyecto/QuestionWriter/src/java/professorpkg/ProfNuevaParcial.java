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

public class ProfNuevaParcial extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SAXBuilder SAXbuilder = new SAXBuilder();
        //Recupera los datos de la sesion 
        String autor = request.getSession().getAttribute("autor").toString();
        String titulo = request.getSession().getAttribute("titulo").toString();
        //Recupera los datos del form 

        Element contenido = new Element("contenido");
        contenido.setText(request.getParameter("contenido"));

        Element respuestas = new Element("respuestas");

        Element respuestaA = new Element("respuesta");
        Element contenidoA = new Element("contenido");
        contenidoA.setText(request.getParameter("textA"));
        respuestaA.addContent(contenidoA);
        Element puntajeA = new Element("puntaje");
        puntajeA.setText(request.getParameter("respA"));
        respuestaA.addContent(puntajeA);
        respuestas.addContent(respuestaA);

        if (!request.getParameter("textB").equals("")) {
            Element respuestaB = new Element("respuesta");
            Element contenidoB = new Element("contenido");
            contenidoA.setText(request.getParameter("textB"));
            respuestaB.addContent(contenidoB);
            if (!request.getParameter("respB").equals("")) {
                Element puntajeB = new Element("puntaje");
                puntajeB.setText(request.getParameter("respB"));
                respuestaB.addContent(puntajeB);
            }
            respuestas.addContent(respuestaB);
        }

        if (!request.getParameter("textC").equals("")) {
            Element respuestaC = new Element("respuesta");
            Element contenidoC = new Element("contenido");
            contenidoC.setText(request.getParameter("textC"));
            respuestaC.addContent(contenidoC);
            if (!request.getParameter("respC").equals("")) {
                Element puntajeC = new Element("puntaje");
                puntajeC.setText(request.getParameter("respC"));
                respuestaC.addContent(puntajeC);
            }
            respuestas.addContent(respuestaC);
        }

        if (!request.getParameter("textD").equals("")) {
            Element respuestaD = new Element("respuesta");
            Element contenidoD = new Element("contenido");
            contenidoD.setText(request.getParameter("textD"));
            respuestaD.addContent(contenidoD);
            if (!request.getParameter("respD").equals("")) {
                Element puntajeD = new Element("puntaje");
                puntajeD.setText(request.getParameter("respD"));
                respuestaD.addContent(puntajeD);
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

        if (!request.getParameter("Navigation").equals("")) {
            Element nav = new Element("navigation");
            nav.setText(request.getParameter("Navigation"));
            opciones.addContent(nav);
        }

        if (!request.getParameter("weight").equals("")) {
            Element weight = new Element("weighting");
            weight.setText(request.getParameter("weight"));
            opciones.addContent(weight);
        }

        //Crea la pregunta 
        Element pregunta = new Element("pregunta");
        Element tipo = new Element("tipo");
        tipo.setText("parcial");
        pregunta.addContent(tipo);
        pregunta.addContent(contenido);
        pregunta.addContent(respuestas);
        pregunta.addContent(opciones);
        //Recupera la base de datos
        String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element cuestionario = null;
            for (int i = 0; i < root.getChildren().size(); i++) {
                Element e=(Element) root.getChildren().get(i);
                if (e.getChild("autor").getTextTrim().equals(autor) && e.getChild("titulo").getTextTrim().equals(titulo)){
                    cuestionario = (Element) root.getChildren().get(i);
                }
            }
            if (cuestionario.getChild("preguntas") != null) {
                //Se agrega al elemento raiz
                cuestionario.getChild("preguntas").addContent(pregunta);
                XMLOutputter xmlout = new XMLOutputter();
                xmlout.setFormat(Format.getPrettyFormat());
                FileWriter writer = new FileWriter(DBfile);
                xmlout.output(root, writer);
                writer.flush();
                writer.close();
                response.sendRedirect("/QuestionWriter/ProfEditarCuestionario?titulo="+titulo);
            } else {
                response.getWriter().print("No hay preguntas en este cuestionario >:v");
            }
//        Element Pregunta
        } catch (JDOMException e) {
            response.getWriter().print("Error JDOM");
        }
    }
}
