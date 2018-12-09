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
import org.jdom.output.XMLOutputter;

public class ProfEliminarPregunta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recupera los datos de la sesion   
        String titulo = request.getSession().getAttribute("titulo").toString();
        String id = request.getParameter("id");
        //Recupera la base de datos
        String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/preguntaDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        SAXBuilder SAXbuilder = new SAXBuilder();
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element preguntas = root.getChild("preguntas");
            for (int i = 0; i < preguntas.getChildren().size(); i++) {
                Element e = (Element) preguntas.getChildren().get(i);
                if (e.getChild("id").getTextTrim().equals(id)) {
                    preguntas.getChildren().remove(i);
                }
            }
            //Imprime el resultado
            XMLOutputter xmlout = new XMLOutputter();
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root, writer);
            writer.flush();
            writer.close();

            DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            DBfile = new File(DBPath);
            SAXbuilder = new SAXBuilder();
            root = SAXbuilder.build(DBfile).getRootElement();
            for (int i = 0; i < root.getChildren().size(); i++) {
                Element e = (Element) root.getChildren().get(i);
                e = e.getChild("preguntas");//e=preguntas
                //Recorre las preguntas en busca de una con id
                for (int j = 0; j < e.getChildren().size(); j++) {
                    Element currentID = (Element) e.getChildren().get(j);
                    if (currentID.getValue().equals(id)) {
                        e.getChildren().remove(j);
                    }
                }
            }
            //Imprime el resultado
            xmlout = new XMLOutputter();
            writer = new FileWriter(DBfile);
            xmlout.output(root, writer);
            writer.flush();
            writer.close();
            response.sendRedirect("/QuestionWriter/ProfEditarCuestionario?titulo=" + titulo);
        } catch (JDOMException ex) {
            Logger.getLogger(ProfEliminarPregunta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
