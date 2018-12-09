/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professorpkg;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author damian
 */
public class ProfVerCuestionario extends HttpServlet {

    public Element buscarCuestionario(Element root, String autor, String titulo) {
        Element e;
        for (int i = 0; i < root.getChildren().size(); i++) {
            e = (Element) root.getChildren().get(i);//Cuestionario
            if (e.getChild("autor").getTextTrim().equals(autor) && e.getChild("titulo").getTextTrim().equals(titulo)) {
                return e;
            }
        }
        return null;
    }

    public void desplegarCuestionario(Element cuestionario, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print("<head>\n"
                + "  <title>Nuevo usuario</title>\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "  <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n"
                + "  <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js\"></script>\n"
                + "  <script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n"
                + "  <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n"
                + "  <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                + "  <link rel=\"stylesheet\" href=\"css/responder.css\">\n"
                + "  <script src=\"responder.js\" charset=\"utf-8\"></script>\n"
                + "  <script>\n"
                + "    function validateForm() {\n"
                + "      var x = document.forms[\"formNombre\"][\"inNombre\"].value;\n"
                + "      var y = document.forms[\"formNombre\"][\"inGrupo\"].value;\n"
                + "      if (x == \"\") {\n"
                + "        alert(\"El cuestionario necesita un nombre\");\n"
                + "        return false;\n"
                + "      }\n"
                + "\n"
                + "      if (y == \"\") {\n"
                + "        alert(\"El cuestionario necesita un grupo\");\n"
                + "        return false;\n"
                + "      }\n"
                + "    }\n"
                + "\n"
                + "    $(function() { //Si el usuario es student, muestra el campo grupo\n"
                + "      var grupo = $(\"#inGrupo\");\n"
                + "      $(\"#inTipo\").selectmenu();\n"
                + "      $(\"#inTipo\").selectmenu({\n"
                + "        change: function(event, data) {\n"
                + "          if (data.item.value !== \"Student\") {\n"
                + "            grupo.css(\"visibility\", \"hidden\");\n"
                + "            grupo.css(\"height\", \"0\");\n"
                + "            grupo.css(\"margin-bottom\", \"0px\");\n"
                + "          } else {\n"
                + "            grupo.css(\"visibility\", \"visible\");\n"
                + "            grupo.css(\"height\", \"44px\");\n"
                + "            grupo.css(\"margin-bottom\", \"10px\");\n"
                + "          }\n"
                + "        }\n"
                + "      });\n"
                + "    });\n"
                + "  </script>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "  <div class=\"w3-sidebar w3-bar-block w3-black w3-xxlarge center\" style=\"width:70px\">\n"
                + "    <a href=\"/QuestionWriter/ProfCuestionarios\" class=\"w3-bar-item w3-button\"><i class=\"far fa-clipboard\"></i></a>\n"
                + "    <a href=\"NuevoCuestionario.html\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-edit\"></i></a>\n"
                + "    <a href=\"/QuestionWriter/LogOut\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-door-open\"></i></a>\n"
                + "  </div>\n"
                + "  <div class=\"container\">\n"
                + "    <div class=\"card card-container\">\n"
                + "      <h1>Examen</h1>\n"
                + "      <div class=\"Evaluacion\">\n"
                + "        <iframe height=\"100%\" width=\"100%\" src=\"DesplegarPreg?instruccion=iniciar\" name=\"compEvaluacion\"></iframe>\n"
                + "      </div>\n"
                + "\n"
                + "      <div class=\"control\">\n"
                + "        <button type=\"button\" class=\"big\" onClick=\"document.compEvaluacion.innerForm.submit();\" >\n"
                + "            <i class='far fa-edit'></i>\n"
                + "        </button>\n"
                + "\n"
                + "        <button name=\"Nav\" value=\"start\" class=\" navB\">\n"
                + "          <a target=\"compEvaluacion\" href=\"DesplegarPreg?instruccion=iniciar\" title=\" Inicio\">\n"
                + "            <i class='fas fa-angle-double-left'></i>\n"
                + "          </a>\n"
                + "        </button>\n"
                + "\n"
                + "        <button name=\"Nav\" value=\"start\" class=\" navB\">\n"
                + "          <a target=\"compEvaluacion\" href=\"DesplegarPreg?instruccion=prev\" title=\" Anterior\">\n"
                + "            <i class='fas fa-angle-left'></i>\n"
                + "          </a>\n"
                + "        </button>\n"
                + "\n"
                + "        <button name=\"Nav\" value=\"start\" class=\" navB\">\n"
                + "          <a target=\"compEvaluacion\" href=\"DesplegarPreg?instruccion=sig\" title=\"Siguiente\">\n"
                + "            <i class='fas fa-angle-right'></i>\n"
                + "          </a>\n"
                + "        </button>\n"
                + "\n"
                + "        <button name=\"Nav\" value=\"start\" class=\" navB\">\n"
                + "          <a target=\"compEvaluacion\" href=\"DesplegarPreg?instruccion=fin\" title=\" Fin\">\n"
                + "            <i class='fas fa-angle-double-right'></i>\n"
                + "          </a>\n"
                + "        </button>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</body>\n"
                + "\n"
                + "</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String autor = request.getSession().getAttribute("email").toString();
        //Se guardan las variables para usar en las siguientes llamadas
        request.getSession().setAttribute("titulo", titulo);
        request.getSession().setAttribute("autor", autor);
        try {
            //Se recupera el cuestionario;
            String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            File DBfile = new File(DBPath);
            SAXBuilder SAXbuilder = new SAXBuilder();
            Element root = SAXbuilder.build(DBfile).getRootElement();
            Element cuestionario = buscarCuestionario(root, autor, titulo);
            desplegarCuestionario(cuestionario, request, response);
        } catch (JDOMException ex) {
            response.getWriter().print("Error enb base de datos");
        }
    }

}
