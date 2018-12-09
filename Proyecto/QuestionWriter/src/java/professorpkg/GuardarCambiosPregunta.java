/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professorpkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author damian
 */
public class GuardarCambiosPregunta extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recupera los datos de la sesion 
        String id  = request.getSession().getAttribute("id").toString();
        //Recupera la base de datos
        String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/preguntaDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        Element preguntas;
        Element pregunta = null;
        SAXBuilder SAXbuilder = new SAXBuilder();
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            preguntas= root.getChild("preguntas");
            for (int i = 0; i < preguntas.getChildren().size(); i++) {
                Element e = (Element) preguntas.getChildren().get(i);
                if (e.getChild("id").getTextTrim().equals(id)) {
                    pregunta = e;         
                } 
            }
            //Para saber si es o no sequencial
              Boolean isSeq = pregunta.getChildText("tipo").trim().equals("sequencial");
            //Se cambian los valores
            Utils.edit(pregunta, "contenido", request.getParameter("contenido"));
            //Cambia los valores de las respuestas
            for (int i = 0; i < 4;i++){
                char current=(char)(((int)'A')+i);
                //Se cambia el contenido
                Utils.edit(pregunta,i,isSeq,request.getParameter("text"+current),true);
                //Se cambia la correspondencia/puntaje
                Utils.edit(pregunta,i,isSeq,request.getParameter("resp"+current),false);
            }
            //Cambia los valores de las opciones
            Element opciones = pregunta.getChild("opciones");
            Utils.edit(opciones,"feedbackCorrecto",request.getParameter("cfeed"));
            Utils.edit(opciones,"feedbackIncorrecto",request.getParameter("ifeed"));
            Utils.edit(opciones,"feedbackInicial",request.getParameter("sfeed"));
            Utils.edit(opciones,"feedbackEvaluate",request.getParameter("efeed"));
            Utils.edit(opciones,"feedbackTries",request.getParameter("tfeed"));
            //Imprime el resultado
            XMLOutputter xmlout = new XMLOutputter();
            xmlout.setFormat(Format.getPrettyFormat());
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root, writer);
            writer.flush();
            writer.close();
            response.sendRedirect("/QuestionWriter/ProfVerPregunta?id="+id);
        } catch (JDOMException ex) {
            Logger.getLogger(ProfEliminarPregunta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
