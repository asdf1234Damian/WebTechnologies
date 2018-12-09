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
        String titulo = request.getParameter("titulo");
        request.getSession().setAttribute("titulo", titulo);
        request.getSession().setAttribute("autor", autor);
        String username = request.getSession().getAttribute("userName").toString();
        Element contenidoCuestionario = null;
        Element aux;
        try {
            String cDBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            String pDBPath = request.getSession().getServletContext().getRealPath("/resources/XML/preguntaDB.xml");
            File cDBfile = new File(cDBPath);
            File pDBfile = new File(pDBPath);
            SAXBuilder SAXbuilder = new SAXBuilder();
            Element cuestionarios = SAXbuilder.build(cDBfile).getRootElement();
            //Recupera el contendio del cuestionario
            for (int i = 0; i < cuestionarios.getChildren().size(); i++) {
                aux = (Element) cuestionarios.getChildren().get(i);//Cuestionario
                if (aux.getChild("autor").getTextTrim().equals(autor) && aux.getChild("titulo").getTextTrim().equals(titulo)) {
                    contenidoCuestionario = aux.getChild("preguntas");//preguntas del cuestionario
                }
            }
            Element preguntas = SAXbuilder.build(pDBfile).getRootElement().getChild("preguntas");
            response.getWriter().println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "    <title>Menu Professor</title>\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n"
                    + "  <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n"
                    + "  <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>"
                    + "    <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"professor/edit.css\">\n"
                    + "    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n"
                    + "<script language='javascript'>" + "function confirmar(id) {\n"
                    + "  if (confirm('Seguro que quiere eliminar la pregunta con id: '+id+'?')){\n"
                    + "    window.location.href = ('ProfEliminarPregunta?id='+id);\n"
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
                    + "                <h1>Cuestionario " + Utils.fUpper(titulo) + " por " + username + "</h1>"
                    + "            </div>"
                    + "        </div>"
                    + "<div>\n"
                    + "      <a style=\"margin-left: 100px;\" class=\"ui-button ui-widget ui-corner-all\" href=\"professor/NuevaPregunta.html\">Nueva Pregunta</a>\n"
                    + "</div>"
                    + "<div>"
                    + "<form action='GuardarCambiosCuestionario' method='get'>"
                    + "  <table>"
                    + "    <tr>"
                    + "      <th width='10px'>ID</th>"
                    + "      <th width='10px'></th>"
                    + "      <th>Contenido</th>"
                    + "      <th>Tipo</th>"
                    + "      <th>Ver</th>"
                    + "      <th>Editar</th>"
                    + "      <th>Borrar</th>"
                    + "    </tr>");
            //Busca las pregutnas del contenido en la db de las preguntas

            for (int j = 0; j < preguntas.getChildren().size(); j++) {
                aux = (Element) preguntas.getChildren().get(j);
                String id = aux.getChildText("id");
                if(aux.getChild("autor").getText().equals(autor)){
                    response.getWriter().print("<tr>");
                response.getWriter().print("<th>" + id + "</th>");
                if (Utils.found(contenidoCuestionario, id, null)) {
                    response.getWriter().print("<th> <input type='checkbox' name='" + id + "' checked></th>");
                } else {
                    response.getWriter().print("<th> <input type='checkbox' name='" + id + "'></th>");
                }
                response.getWriter().print("<th>" + aux.getChildText("contenido") + "</th>");
                response.getWriter().print("<th>" + Utils.fUpper(aux.getChildText("tipo")) + "</th>");
                response.getWriter().println("<th><a style='text-decoration:none;' href='ProfVerPregunta?id=" + id + "' class=\"far fa-eye\"/></th>");
                response.getWriter().println("<th><a style='text-decoration:none;' href='ProfEditarPregunta?id=" + id + "' class=\"far fa-edit\"/></th>");
                response.getWriter().println("<th><a style='text-decoration:none;' onclick=\"confirmar('" + id + "')\" class=\"fas fa-trash-alt\"/></th> </tr>");
                response.getWriter().print("</tr>");
                }
            }
            response.getWriter().println(
                    "  </table>"
                    + "<input type='submit' class='btn ui-button ui-widget ui-corner-all' value='Guardar cambios' >"
                    + "</form >"
                    + "</div>"
                    + "</body>"
                    + "</html>\n"
                    + "");
        } catch (JDOMException ex) {
            response.getWriter().print("Error en base de datos" + ex.getMessage());
        }
    }

}
