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
        String autor = request.getSession().getAttribute("autor").toString();
        String titulo = request.getSession().getAttribute("titulo").toString();
        int index = Integer.parseInt(request.getParameter("pos"));
        //Recupera la base de datos
        String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        SAXBuilder SAXbuilder = new SAXBuilder();
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element cuestionario = null;
            for (int i = 0; i < root.getChildren().size(); i++) {
                Element e=(Element) root.getChildren().get(i);
                if (e.getChild("autor").getTextTrim().equals(autor) && e.getChild("titulo").getTextTrim().equals(titulo)){
                    cuestionario = (Element) root.getChildren().get(i);
                }
            }
            cuestionario.getChild("preguntas").getChildren().remove(index);
            //Imprime el resultado
            XMLOutputter xmlout = new XMLOutputter();
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root,writer);
            writer.flush();
            writer.close();
            response.sendRedirect("/QuestionWriter/ProfEditarCuestionario?titulo="+titulo);
        } catch (JDOMException ex) {
            Logger.getLogger(ProfEliminarPregunta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
