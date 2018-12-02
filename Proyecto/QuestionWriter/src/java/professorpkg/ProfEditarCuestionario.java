/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professorpkg;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ProfEditarCuestionario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String autor = request.getSession().getAttribute("email").toString();
        String titulo= request.getParameter("titulo");
        request.getSession().setAttribute("titulo",titulo);
        request.getSession().setAttribute("autor",autor);
        String username = request.getSession().getAttribute("userName").toString();
        Element e;
        try {
            String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            File DBfile = new File(DBPath);
            SAXBuilder SAXbuilder = new SAXBuilder();
            Element root = SAXbuilder.build(DBfile).getRootElement();
            response.getWriter().println("<!DOCTYPE html>\n" +
            "<html>\n" +
            "    <title>Menu Professor</title>\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n" +
            "  <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n" +
            "  <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>"+
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
            "<script language='javascript'>"+"function confirmar(pos) {\n" +
            "  if (confirm('Seguro que quiere eliminar la pregunta?')){\n" +
            "    window.location.href = ('ProfEliminarPregunta?pos='+pos);\n" +
            "  }"+
            "}"+ 
            "</script>"+
                 
            "    <body>\n" +
            "        <div class=\"w3-sidebar w3-bar-block w3-black w3-xxlarge center\" style=\"width:70px\">\n" +
            "            <a href=\"/QuestionWriter/ProfCuestionarios\" class=\"w3-bar-item w3-button\"><i class=\"far fa-clipboard\"></i></a> \n" +
            "            <a href=\"professor/NuevoCuestionario.html\" class=\"w3-bar-item w3-button\"><i class=\"far fa-edit\"></i></a> \n" +
            "            <a href=\"/QuestionWriter/LogOut\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-door-open\"></i></a>"+
            "        </div>\n" +
            "\n" +
            "        <div style=\"margin-left:70px\">\n" +
            "            <div class=\"w3-container header\">\n" +
            "                <h1>Cuestionario "+titulo +" por "+username+"</h1>" +
            "            </div>" +
            "        </div>" +
            "<div>\n" +
            "      <a style=\"margin-left: 100px;\" class=\"ui-button ui-widget ui-corner-all\" href=\"professor/NuevaPregunta.html\">Nueva Pregunta</a>\n" +
            "</div>"+
            "<div>" +
            "  <table>" +
            "    <tr>"+
            "      <th>Numero</th>" +
            "      <th>Contenido</th>" +
            "      <th>Editar</th>" +
            "      <th>Borrar</th>"+
            "    </tr>" );
            //Agrega a la tabla todos los cuestionarios con autor igual al email en sesion
            for(int i=0;i<root.getChildren().size(); i++){
                e = (Element)root.getChildren().get(i);//Cuestionario
                if (e.getChild("autor").getTextTrim().equals(autor) && e.getChild("titulo").getTextTrim().equals(titulo)){
                    for(int j=0;j<e.getChild("preguntas").getChildren().size();j++){
                        Element pregunta = (Element) e.getChild("preguntas").getChildren().get(j);
                        response.getWriter().print("<tr>");
                        response.getWriter().print("<th>"+(j+1)+"</th>");
                        response.getWriter().print("<th>"+pregunta.getChildText("contenido")+"</th>");
                        response.getWriter().println("<th><a style='text-decoration:none;' href='ProfEditarPregunta?posicion="+j+"' class=\"far fa-edit\"/></th>");
                        response.getWriter().println("<th><a style='text-decoration:none;' onclick=\"confirmar('"+j+"')\" class=\"fas fa-trash-alt\"/></th> </tr>");
                        response.getWriter().print("</tr>");
                    }
                }
            }
            response.getWriter().println(
            "  </table>" +
            "</div>"+
            "</body>" +
            "</html>\n" +
            "");    
            
        } catch (JDOMException ex) {
            response.getWriter().print("Error enb base de datos");
        }
    }

}
