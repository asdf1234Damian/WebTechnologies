package adminpkg;

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

public class EliminarUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SAXBuilder SAXbuilder = new SAXBuilder();
            String usuario = request.getParameter("user").toLowerCase();
            String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/userDB.xml");
            File DBfile = new File(DBPath);
            Element root = SAXbuilder.build(DBfile).getRootElement();
            for(int i=0;i<root.getChildren().size(); i++){
                Element e=(Element)root.getChildren().get(i);
                response.getWriter().print(e.getValue()+"Original");
            } 
            for(int i=0;i<root.getChildren().size(); i++){
                Element e=(Element)root.getChildren().get(i);
                if(e.getChild("email").getText().toLowerCase().equals(usuario)){
                    response.getWriter().print(e.getValue()+"Borrar");
                    root.getChildren().remove(i);
                }
            }
            for(int i=0;i<root.getChildren().size(); i++){
                Element e=(Element)root.getChildren().get(i);
                response.getWriter().print(e.getValue()+i);
            }
            
            
            XMLOutputter fmt = new XMLOutputter();
            FileWriter writer = new FileWriter(DBfile);
            fmt.output(root,writer);
            writer.flush();
            writer.close();
            response.sendRedirect("/QuestionWriter/AdminUsuarios");
        } catch (JDOMException ex) {
            Logger.getLogger(EliminarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

