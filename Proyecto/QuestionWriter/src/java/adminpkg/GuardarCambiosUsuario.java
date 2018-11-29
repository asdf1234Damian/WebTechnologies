package adminpkg;

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
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class GuardarCambiosUsuario extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se recuperan los parametros nuevos, aunque esten vacios
        String usrName = request.getParameter("inNombre");
        String pass = request.getParameter("inPassword");
        String grupo = request.getParameter("inGrupo");
        String email = request.getParameter("inCorreo");
        String uType = request.getParameter("inTipo");
        //Se recupera el email origianal
        String original = request.getSession().getAttribute("original").toString();
        //Se crea el dom
        SAXBuilder SAXbuilder = new SAXBuilder();
        String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/userDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element e;
            //Se busca el elemento con el email origianl 
            for(int i=0;i<root.getChildren().size(); i++){
                e = (Element)root.getChildren().get(i);
                if (e.getChild("email").getTextTrim().equals(original)){//Cuando coincide
                    //Solo hace cambios cuando el nuevo no es vacio
                    if(!usrName.equals("")){
                        e.getChild("usrName").setText(usrName);
                    }
                    
                    if(!pass.equals("")){
                        e.getChild("pass").setText(pass);
                    }
                    
                    if(!uType.equals("")){
                        e.getChild("uType").setText(uType);
                        if(!uType.equals("Student")){
                            e.getChild("grupo").detach();
                        }
                    }
                    if(!grupo.equals("")){
                        if(e.getChild("grupo")!=null){
                            e.getChild("grupo").setText(grupo);
                        }else{
                            e.addContent(new Element("grupo").setText(grupo));
                        }
                    }
                    if(!email.equals("")){
                        e.getChild("email").setText(email);
                    }
                }
            }
            //escribe el dom con el nuevo elemento
            XMLOutputter xmlout = new XMLOutputter();
            xmlout.setFormat(Format.getPrettyFormat());
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root,writer);
            writer.flush();
            writer.close(); 
            response.sendRedirect("AdminUsuarios");
            }catch (JDOMException ex) {
                response.getWriter().print("Error con la base de datos"+ ex.getMessage());
        }
    }
}
