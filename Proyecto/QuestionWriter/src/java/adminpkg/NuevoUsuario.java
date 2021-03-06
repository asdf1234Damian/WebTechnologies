/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author damian
 */
public class NuevoUsuario extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String usrName = request.getParameter("inNombre");
        String pass = request.getParameter("inPassword");
        String grupo = request.getParameter("inGrupo");
        String email = request.getParameter("inCorreo");
        String uType = request.getParameter("inTipo");
        SAXBuilder SAXbuilder = new SAXBuilder();
        String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/userDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element e;
            for(int i=0;i<root.getChildren().size(); i++){
                e = (Element)root.getChildren().get(i);
                if (e.getChild("email").getTextTrim().equals(email)){
                    response.sendRedirect("admin/repetido.html");
                    return;
                }
            }
            //Se crea el elemento con los datos proporcionados
            Element user= new Element("user");
            Element usrNameE= new Element("usrName");
            usrNameE.setText(usrName);
            Element emailE= new Element("email");
            emailE.setText(email);
            Element passE= new Element("pass");
            passE.setText(pass);
            Element uTypeE= new Element("uType");
            uTypeE.setText(uType);
            user.addContent(usrNameE);
            user.addContent(emailE);
            user.addContent(passE);
            user.addContent(uTypeE);
            if(uType.equals("Student")){
                Element grupoE= new Element("grupo");
                grupoE.setText(grupo);
                user.addContent(grupoE);
            }
            root.addContent(user);
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
