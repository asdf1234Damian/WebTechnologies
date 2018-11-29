package adminpkg;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class AdminUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            Element e;
            response.setContentType("text/html;charset=UTF-8");
            //http://www.jdom.org/downloads/oraoscon01-jdom.pdf pagina 14
            SAXBuilder SAXbuilder = new SAXBuilder();
            /////////////////////////////////////////////////////////////////////////////////
            response.setContentType("text/html;charset=UTF-8");
            HttpSession sesion=request.getSession();
            //Se recuperan los parametros
            String mail = sesion.getAttribute("email").toString();
            String username=  sesion.getAttribute("userName").toString();
            //Crea el Jdom para la base de usuarios
            String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/userDB.xml");
            File DBfile = new File(DBPath);
            Element root = SAXbuilder.build(DBfile).getRootElement();
            List usuarios = root.getChildren();
            //Crea el jdmom parala base de questionarios
            response.getWriter().println("<!DOCTYPE html>\n" +
            "<html>\n" +
            "    <title>Menu Admin</title>\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
            "    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n" +
            "    \n" +"<style>\n" +
            "        table {\n" +
            "    border-collapse: collapse;\n" +
            "    width: 100%;\n" +
            "margin-left:70px;\n" +
            "}\n" +
            "\n" +
            "th, td {\n" +
            "    text-align: left;\n" +
            "    padding: 8px;\n" +
            "}\n" +
            "\n" +
            "tr:nth-child(even){background-color: #f2f2f2}\n" +
            "    </style>"+
            "<script language='javascript'>"+"function confirmar( usuario) {\n" +
            "  if (confirm('Seguro que quiere eliminar al usuario:' + usuario)){\n" +
            "    window.location.href = ('EliminarUsuario?user='+usuario);\n" +
            "  }"+
            "}"+ 
            "</script>"+
            "    <body>\n" +
            "        <div class=\"w3-sidebar w3-bar-block w3-black w3-xxlarge center\" style=\"width:70px\">\n" +
            "            <a href=\"AdminUsuarios\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-address-book\"></i></a> \n" +
            "            <a href=\"admin\\NuevoUsuario.html\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-address-card\"></i></a> \n" +
            "            <a href=\"/QuestionWriter/LogOut\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-door-open\"></i></a>"+
            "        </div>\n" +
            "\n" +
            "        <div style=\"margin-left:70px\">\n" +
            "            <div class=\"w3-container header\">\n" +
            "                <h1>Bienvenido "+username+"</h1>" +
            "            </div>" +
            "        </div>" +
            "<div>" +
            "  <table>" +
            "    <tr>" +
            "      <th>Usuarios</th>" +
            "      <th>Correo</th>" +
            "      <th>Tipo</th>" +
            "      <th>Grupo</th>" +
            "      <th>Editar</th>" +
            "      <th>Borrar</th></tr>" );
            //Agrega a la tabla todos los cuestionarios con autor igual al email en sesion
            for(int i=0;i<usuarios.size(); i++){
                e = (Element)usuarios.get(i);
                if (!e.getChild("email").getTextTrim().equals(mail)){
                    response.getWriter().println("<tr><th><a href=\"VerUsuario?"+e.getChild("usrName").getTextTrim()+"\">"+e.getChild("usrName").getTextTrim()+"</a></th>");
                    response.getWriter().println("<th>"+e.getChild("email").getTextTrim()+"</th>");
                    response.getWriter().println("<th>"+e.getChild("uType").getTextTrim()+"</th>");
                    if(e.getChild("grupo")==null){
                        response.getWriter().println("<th>-NA-</th>");                        
                    }else{
                        response.getWriter().println("<th>"+e.getChild("grupo").getTextTrim()+"</th>");                        
                    }
                    response.getWriter().println("<th><a style='text-decoration:none;' href='EditarUsuario?correo="+e.getChild("email").getTextTrim()+"' class=\"far fa-edit\"/></th>");
                    response.getWriter().println("<th><a style='text-decoration:none;' onclick=\"confirmar('"+e.getChild("email").getTextTrim()+"')\" class=\"fas fa-trash-alt\"/></th></tr>");
                }
            }
            response.getWriter().println(
            "  </table>" +
            "</div>"+
            "</body>" +
            "</html>\n" +
            "");    
        } catch (JDOMException ex) {    
            //Logger.getLogger(ProfCuestionarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
 }
