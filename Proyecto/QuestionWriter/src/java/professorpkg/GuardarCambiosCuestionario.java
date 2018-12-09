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
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author damian
 */
public class GuardarCambiosCuestionario extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String autor = request.getSession().getAttribute("email").toString();
        String titulo = request.getSession().getAttribute("titulo").toString();
        String username = request.getSession().getAttribute("userName").toString();
        Element contenidoCuestionario = null;
        Element aux;
        try {
            String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            File DBfile = new File(DBPath);
            SAXBuilder SAXbuilder = new SAXBuilder();
            Element cuestionarios = SAXbuilder.build(DBfile).getRootElement();
            //Recupera el contendio del cuestionario
            for (int i = 0; i < cuestionarios.getChildren().size(); i++) {
                aux = (Element) cuestionarios.getChildren().get(i);//Cuestionario
                if (aux.getChild("autor").getTextTrim().equals(autor) && aux.getChild("titulo").getTextTrim().equals(titulo)) {
                    aux.removeChild("preguntas");
                    //TODO aqui se genera el nuevo contenido
                    request.getParameterNames();
                    Enumeration<String> parameterNames = request.getParameterNames();
                    Element nuevoContenido = new Element("preguntas");
                    while (parameterNames.hasMoreElements()) {
                        String id = parameterNames.nextElement();
                        nuevoContenido.addContent(new Element("id").setText(id));
                    }
                    aux.addContent(nuevoContenido);
                }
            }
            //Imprime el resultado
            XMLOutputter xmlout = new XMLOutputter();
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(cuestionarios, writer);
            writer.flush();
            writer.close();
            response.sendRedirect("/QuestionWriter/ProfEditarCuestionario?titulo=" + titulo);
        } catch (JDOMException ex) {

        }
    }

}
