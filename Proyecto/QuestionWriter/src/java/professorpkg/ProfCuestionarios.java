package professorpkg;

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

public class ProfCuestionarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Element e;
            response.setContentType("text/html;charset=UTF-8");
            //http://www.jdom.org/downloads/oraoscon01-jdom.pdf pagina 14
            SAXBuilder SAXbuilder = new SAXBuilder();
            /////////////////////////////////////////////////////////////////////////////////
            response.setContentType("text/html;charset=UTF-8");
            HttpSession sesion = request.getSession();
            //Se recuperan los parametros
            String mail = sesion.getAttribute("email").toString();
            String username = sesion.getAttribute("userName").toString();
            //Crea el Jdom para la base de usuarios
            String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            File DBfile = new File(DBPath);
            Element root = SAXbuilder.build(DBfile).getRootElement();
            List questionarios = root.getChildren();
            //Crea el jdmom parala base de questionarios
            response.getWriter().println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <title>Menu Professor</title>\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "    <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n"
                    + "    \n" + "<style>\n"
                    + "        table {\n"
                    + "    border-collapse: collapse;\n"
                    + "    width: 100%;\n"
                    + "margin-left:70px;\n"
                    + "}\n"
                    + "\n"
                    + "th, td {\n"
                    + "    text-align: left;\n"
                    + "    padding: 8px;\n"
                    + "}\n"
                    + "\n"
                    + "tr:nth-child(even){background-color: #f2f2f2}\n"
                    + "    </style>"
                    + "<script language='javascript'>" + "function confirmar( titulo) {\n"
                    + "  if (confirm('Seguro que quiere eliminar el cuestionario:' + titulo)){\n"
                    + "    window.location.href = ('ProfEliminarCuestionario?titulo='+titulo);\n"
                    + "  }"
                    + "}"
                    + "</script>"
                    + "    <body>\n"
                    + "        <div class=\"w3-sidebar w3-bar-block w3-black w3-xxlarge center\" style=\"width:70px\">\n"
                    + "            <a href=\"/QuestionWriter/ProfCuestionarios\" class=\"w3-bar-item w3-button\"><i class=\"far fa-clipboard\"></i></a> \n"
                    + "            <a href=\"professor/NuevoCuestionario.html\" class=\"w3-bar-item w3-button\"><i class=\"far fa-edit\"></i></a> \n"
                    + "            <a href=\"/QuestionWriter/LogOut\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-door-open\"></i></a>"
                    + "        </div>\n"
                    + "\n"
                    + "        <div style=\"margin-left:70px\">\n"
                    + "            <div class=\"w3-container header\">\n"
                    + "                <h1>Bienvenido " + username + "</h1>"
                    + "            </div>"
                    + "        </div>"
                    + "<div>"
                    + "  <table>"
                    + "    <tr>"
                    + "      <th>Nombre del Cuestionario</th>"
                    + "      <th>Grupo</th>"
                    + "      <th>Ver</th>"
                    + "      <th>Editar</th>"
                    + "      <th>Borrar</th>"
                    + "    </tr>");
            //Agrega a la tabla todos los cuestionarios con autor igual al email en sesion
            for (int i = 0; i < questionarios.size(); i++) {
                e = (Element) questionarios.get(i);
                if (e.getChild("autor").getTextTrim().equals(mail)) {
                    response.getWriter().println("<tr><th><a href=\"ProfEditarCuestionario?titulo=" + e.getChild("titulo").getTextTrim() + "\">" + e.getChild("titulo").getTextTrim() + "</a></th>");
                    response.getWriter().println("<th>" + e.getChild("grupo").getTextTrim() + "</th>");
                    response.getWriter().println("<th><a style='text-decoration:none;' href='ProfVerCuestionario?titulo=" + e.getChild("titulo").getTextTrim() + "' class=\"far fa-eye\"/></th>");
                    response.getWriter().println("<th><a style='text-decoration:none;' href='ProfEditarCuestionario?titulo=" + e.getChild("titulo").getTextTrim() + "' class=\"far fa-edit\"/></th>");                    response.getWriter().println("<th><a style='text-decoration:none;' onclick=\"confirmar('" + e.getChild("titulo").getTextTrim() + "')\" class=\"fas fa-trash-alt\"/></th> </tr>");
                }
            }
            response.getWriter().println(
                    "  </table>"
                    + "</div>"
                    + "</body>"
                    + "</html>\n"
                    + "");
        } catch (JDOMException ex) {
            Logger.getLogger(ProfCuestionarios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
