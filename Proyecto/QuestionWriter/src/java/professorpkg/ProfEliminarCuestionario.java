package professorpkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class ProfEliminarCuestionario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SAXBuilder SAXbuilder = new SAXBuilder();
            //Recupera los datos de sesion y del url
            String autor = request.getSession().getAttribute("email").toString().toLowerCase();
            String titulo = request.getParameter("titulo").toLowerCase();
            //Recupera la base de datos
            String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            File DBfile = new File(DBPath);
            Element root = SAXbuilder.build(DBfile).getRootElement();
            //Busca el cuestionario a eliminar. Se supone solo hay un cuestionario con el mismo titulo y autor
            for(int i=0;i<root.getChildren().size(); i++){
                Element e=(Element)root.getChildren().get(i);
                if(e.getChild("autor").getText().toLowerCase().equals(autor) && e.getChild("titulo").getText().toLowerCase().equals(titulo)){
                    //Remueve el cuestionario encontrado
                    root.getChildren().remove(i);
                }
            }          
            //Imprime el resultado
            XMLOutputter xmlout = new XMLOutputter();
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root,writer);
            writer.flush();
            writer.close();
            //Vuelve a cargar la pagina.
            response.sendRedirect("/QuestionWriter/ProfCuestionarios");
        } catch (JDOMException ex) {
            
        }
    }

}

