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



public class ProfEditarPregunta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recupera los datos de la sesion 
        String id = request.getParameter("id");
        request.getSession().setAttribute("id", id);
        //Recupera la base de datos
        String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/preguntaDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        Element preguntas;
        SAXBuilder SAXbuilder = new SAXBuilder();
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            preguntas = root.getChild("preguntas");
            Element pregunta = null;
            for (int i = 0; i < preguntas.getChildren().size(); i++) {
                Element e = (Element) preguntas.getChildren().get(i);
                if (e.getChild("id").getTextTrim().equals(id)) {
                    pregunta = (Element) preguntas.getChildren().get(i);
                }
            }
            Element opciones = pregunta.getChild("opciones");
            response.getWriter().print("<html>\n"
                    + "\n"
                    + "<head>\n"
                    + "  <title>Editar pregunta</title>\n"
                    + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                    + "  <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n"
                    + "  <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n"
                    + "  <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n"
                    + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n"
                    + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n"
                    + "  <script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n"
                    + "  <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n"
                    + "  <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                    + "  <link rel=\"stylesheet\" href=\"professor/main.css\">\n"
                    + "  <script src=\"professor/main.js\" charset=\"utf-8\"></script>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "  <div class=\"w3-sidebar w3-bar-block w3-black w3-xxlarge center\" style=\"width:70px\">\n"
                    + "    <a href=\"/QuestionWriter/ProfCuestionarios\" class=\"w3-bar-item w3-button\"><i class=\"far fa-clipboard\"></i></a>\n"
                    + "    <a href=\"NuevoCuestionario.html\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-edit\"></i></a>\n"
                    + "    <a href=\"/QuestionWriter/LogOut\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-door-open\"></i></a>\n"
                    + "  </div>\n"
                    + "  <div class=\"container\">\n"
                    + "    <div class=\"card\">\n"
                    + "      <form action=\"/QuestionWriter/GuardarCambiosPregunta\" method=\"get\" name=\"contenidoPreg\" >\n"
                    + "        <div id=\"tabs\">\n"
                    + "          <ul>\n"
                    + "            <li><a href=\"#tabs-1\">Inicio</a></li>\n"
                    + "            <li><a href=\"#tabs-2\">Opciones</a></li>\n"
                    + "            <li><a href=\"#tabs-3\">Assets</a></li>\n"
                    + "          </ul>\n"
                    + "          <div id=\"tabs-1\">\n"
                    + "            <h1>Pregunta " + Utils.fUpper(pregunta.getChild("tipo").getText()) + "</h1>\n"
                    + "            <div class=\"holder\">\n"
                    + "              <input type=\"text\" name=\"contenido\" placeholder=\"" + pregunta.getChildText("contenido") + "\">\n"
                    + "              <br>\n"
                    + "              <h1>Respuestas</h1><br>\n"
                    + "              <table>\n"
                    + "                <tr>\n"
                    + "                  <th>Opcion</th>\n");
            if (pregunta.getChildText("tipo").equals("sequencial")) {
                response.getWriter().print("<th>Correspondencia</th>");
            } else {
                response.getWriter().print("<th>Puntos</th>");
            }
            response.getWriter().print("</tr>\n");
            for (int i = 0; i < 4; i++) {
                char current = (char) (((int) 'A') + i);
                response.getWriter().print("<tr>");
                if (i < pregunta.getChild("respuestas").getChildren().size()) {
                    Element respuesta = (Element) pregunta.getChild("respuestas").getChildren().get(i);
                    response.getWriter().print("<td><input type=\"text\" name=\"text" + current + "\" placeholder=\" " + Utils.exists(respuesta.getChild("contenido"), "Opcion " + current) + "\"></td>\n");
                    if (pregunta.getChildText("tipo").equals("sequencial")) {
                        response.getWriter().print("<td><input type=\"text\" name=\"resp" + current + "\" placeholder=\"" + Utils.exists(respuesta.getChild("correspondencia"), "Correspondencia") + "\"></td>");
                    } else {
                        response.getWriter().print("<td><input type=\"text\" name=\"resp" + current + "\" placeholder=\"" + Utils.exists(respuesta.getChild("puntaje"), "Puntos") + "\"></td>");
                    }
                } else {
                    if (pregunta.getChildText("tipo").equals("sequencial")) {
                        response.getWriter().print("<td><input type=\"text\" name=\"text" + current + "\" placeholder=\"Opcion " + current + "\"></td>\n <td><input type=\"text\" name=\"resp" + current + "\" placeholder=\"Correspondencia " + current + "\"></td>\n");
                    } else {
                        response.getWriter().print("<td><input type=\"text\" name=\"text" + current + "\" placeholder=\"Opcion " + current + "\"></td>\n <td><input type=\"text\" name=\"resp" + current + "\" placeholder=\"Puntos\"></td>\n");
                    }
                }
                response.getWriter().print("</tr>");
            }
            response.getWriter().print(
                    "              </table>\n"
                    + "              <br>\n"
                    + "              <input type=\"submit\" class=\"btn\" value=\"Guardar Cambios\" >\n"
                    + "            </div>\n"
                    + "\n"
                    + "          </div>\n"
                    + "          <div id=\"tabs-2\">\n"
                    + "            <button type=\"button\" class=\"btn\" name=\"button\" id=\"button1\"> FeedBack?</button>\n"
                    + "            <div id=\"effect1\">\n"
                    + "              <input type=\"text\" name=\"sfeed\" placeholder=\"Initial Feedback: " + Utils.exists(opciones.getChild("feedbackInicial")) + "\">\n"
                    + "              <input type=\"text\" name=\"efeed\" placeholder=\"Evaluate Feedback: " + Utils.exists(opciones.getChild("feedbackEvaluate")) + "\">\n"
                    + "              <input type=\"text\" name=\"cfeed\" placeholder=\"Correct Feedback: " + Utils.exists(opciones.getChild("feedbackCorrecto")) + "\">\n"
                    + "              <input type=\"text\" name=\"ifeed\" placeholder=\"Incorrect Feedback: " + Utils.exists(opciones.getChild("feedbackIncorrecto")) + "\">\n"
                    + "              <input type=\"text\" name=\"tfeed\" placeholder=\"Tries Feedback: " + Utils.exists(opciones.getChild("feedbackTries")) + "\">\n"
                    + "            </div>\n"
                    + "          </div>\n"
                    + "          <div id=\"tabs-3\">\n"
                    + "            <input type=\"file\" name=\"file\" >\n"
                    + "          </div>\n"
                    + "        </div>\n"
                    + "      </form>\n"
                    + "    </div>\n"
                    + "  </div>\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>\n"
                    + "");
        } catch (JDOMException e) {
            response.getWriter().print("error en la base de datos");
        }
    }
}
