package professorpkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class ProfNuevoCuestionario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail = request.getSession().getAttribute("email").toString();
        String titulo = request.getParameter("inNombre");
        String grupo = request.getParameter("inGrupo");
        SAXBuilder SAXbuilder = new SAXBuilder();
        String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element e;
            for(int i=0;i<root.getChildren().size(); i++){
                e = (Element)root.getChildren().get(i);
                if (e.getChild("autor").getTextTrim().equals(mail) && e.getChild("titulo").getTextTrim().equals(titulo)  ){
                    response.sendRedirect("professor/repetido.html");
                }
            }
            //Se crea el elemento con los datos proporcionados
            Element questionario= new Element("questionario");
            Element tituloE= new Element("titulo");
            tituloE.setText(titulo);
            Element autorE= new Element("autor");
            autorE.setText(mail);
            Element grupoE= new Element("grupo");
            grupoE.setText(grupo);
            questionario.addContent(tituloE);
            questionario.addContent(autorE);
            questionario.addContent(grupoE);
            //Se agrega al elemento raiz
            root.addContent(questionario);
            XMLOutputter xmlout = new XMLOutputter();
            xmlout.setFormat(Format.getPrettyFormat());
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root,writer);
            writer.flush();
            writer.close(); 
            response.sendRedirect("ProfCuestionarios");
            }catch (JDOMException ex) {
                
            response.getWriter().print("Error con la base de datos"+ ex.getMessage());
        }
    }
}
