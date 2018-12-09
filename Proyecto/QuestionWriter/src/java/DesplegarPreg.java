/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

public class DesplegarPreg extends HttpServlet {

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

    public void sequencial(Element p, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print("");
    }

    public void parcial(Element p, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print("<!DOCTYPE html>\n"
                + "<html lang=\"en\" dir=\"ltr\">\n"
                + "\n"
                + "<head>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n"
                + "  <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n"
                + "  <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                + "  <link rel=\"stylesheet\" href=\"css/preguntas.css\">\n"
                + "  <script src=\"responder.js\" charset=\"utf-8\"></script>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "  <div class=\"columnA\">\n"
                + "    <img src=\"resources/placeholder.png\" alt=\":c\">\n"
                + "  </div>\n"
                + "  <div class=\"columnB\">\n"
                + "    <form id='innerForm'  name='innerForm' action=\"DesplegarPreg\" method=\"get\">\n"
                + "      <h1>" + p.getChildText("contenido") + "</h1>"
                + "      <fieldset>\n"
                + "        <legend>Seleccione una opcion: </legend>\n");
        for (int i = 0; i < 4; i++) {
            Element respuesta = (Element) p.getChild("respuestas").getChildren().get(i);
            if (respuesta != null) {
                response.getWriter().print("<label for=\"radio-" + i + "\">" + respuesta.getChildText("contenido") + "</label><br>\n"
                        + "        <input type=\"radio\" name=\"respuesta\" id=\"radio-" + i + "\" value=\"" + respuesta.getChildText("contenido") + "\">\n");
            }
        }
        response.getWriter().print(
                "        <input type=\"text\" name=\"instruccion\" value=\"siguiente\" style='visibility:hidden;' \">\n"
                + "      </fieldset>\n"
                + "    </form>\n"
                + "  </div>\n"
                + "</body>\n"
                + "</html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("instruccion").equals("iniciar")) {
            request.getSession().setAttribute("index", 0);
        }
        try {
            //Se recupera el cuestionario;
            String titulo = request.getSession().getAttribute("titulo").toString();
            String autor = request.getSession().getAttribute("email").toString();
            request.getSession().setAttribute("titulo", titulo);
            request.getSession().setAttribute("autor", autor);
            String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            File DBfile = new File(DBPath);
            SAXBuilder SAXbuilder = new SAXBuilder();
            Element root = SAXbuilder.build(DBfile).getRootElement();
            int index = Integer.parseInt(request.getSession().getAttribute("index").toString());
            Element cuestionario = buscarCuestionario(root, autor, titulo);
            String instruccion = request.getParameter("instruccion");
            
            if (cuestionario == null) {
                response.sendRedirect("<h1>Error cargando el cuestionario</h1>");
            }

            
            if (instruccion.equals("fin")) {
                index = cuestionario.getChild("preguntas").getChildren().size() - 1;
                request.getSession().setAttribute("index", index);
            }

            if (instruccion.equals("sig")) {
                if (index < cuestionario.getChild("preguntas").getChildren().size() - 1) {
                    index++;
                } else {
                    response.getWriter().print("<h1>Fin del cuestionario</h1>");
                }
                request.getSession().setAttribute("index", index);
            }

            if (instruccion.equals("prev")) {
                if (index > 0) {
                    index--;
                } else {
                    response.getWriter().print("<h1>No se puede retroceder más </h1>");
                }
                request.getSession().setAttribute("index", index);
            }

            Element pregunta = (Element) cuestionario.getChild("preguntas").getChildren().get(0);
            if (pregunta.getChild("tipo").getText().equals("parcial")) {
                parcial(pregunta, request, response);
            } else {
                sequencial(pregunta, request, response);
            }
        } catch (JDOMException ex) {
            response.getWriter().print("Error en base de datos");
        }
    }
}
