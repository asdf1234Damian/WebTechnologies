/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professorpkg;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;

/**
 *
 * @author damian
 */
public class Utils {

    public static String fUpper(String in) {
        String s;
        s = in.substring(0, 1).toUpperCase() + in.substring(1);
        return s;
    }

    public static void edit(Element e, String name, String value) {
        if (value.equals("")) {
            return;
        }
        Element child;
        if (e.getChild(name) == null) {
            child = new Element(name);
            e.addContent(child);
        } else {
            child = e.getChild(name);
        }
        child.setText(value);
    }

    public static void edit(Element e, int index, Boolean Sequential, String value, Boolean contenido) {
        //Si el valor no cambia
        if (value.equals("")) {
            return;
        }
        //Si la respueta ya existe
        Element respuesta;

        if (index < e.getChild("respuestas").getChildren().size()) {
            respuesta = (Element) e.getChild("respuestas").getChildren().get(index);
        } else {
            respuesta = new Element("respuesta");
            e.getChild("respuestas").addContent(respuesta);
        }

        //Si se va a cambiar el contenido
        if (contenido) {
            if (respuesta.getChild("contenido") != null) {
                respuesta.getChild("contenido").setText(value);
            } else {
                respuesta.addContent(new Element("contenido").setText(value));
            }
        } else if (Sequential) {
            if (respuesta.getChild("correspondencia") != null) {
                respuesta.getChild("correspondencia").setText(value);
            } else {
                respuesta.addContent(new Element("correspondencia").setText(value));
            }
        } else {
            if (respuesta.getChild("puntaje") != null) {
                respuesta.getChild("puntaje").setText(value);
            } else {
                respuesta.addContent(new Element("puntaje").setText(value));
            }
        }
    }

    public static Boolean found(Element tbf, String val, String child) {
        if (child == null) {
            for (int i = 0; i < tbf.getChildren().size(); i++) {
                Element e = (Element) tbf.getChildren().get(i);
                if (e.getValue().equals(val)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < tbf.getContentSize(); i++) {
                Element e = (Element) tbf.getChildren().get(i);
                e.getChild(child);
                if (e.getValue().equals(val)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String exists(Element e) {
        if (e != null) {
            return e.getTextTrim();
        }
        return "";
    }

    public static String exists(Element e, String s) {
        if (e != null) {
            return e.getTextTrim();
        }
        return s;
    }

    public static Element buscarCuestionario(Element root, String autor, String titulo) {
        Element e;
        for (int i = 0; i < root.getChildren().size(); i++) {
            e = (Element) root.getChildren().get(i);//Cuestionario
            if (e.getChild("autor").getTextTrim().equals(autor) && e.getChild("titulo").getTextTrim().equals(titulo)) {
                return e;
            }
        }
        return null;
    }

    public static void sequencial(Element p, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().print(""
                + "<!DOCTYPE html>\n"
                + "<html lang=\"en\" dir=\"ltr\">\n"
                + "    <head>\n"
                + "        <meta charset=\"utf-8\">\n"
                + "        <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n"
                + "        <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n"
                + "        <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n"
                + "        <link rel=\"stylesheet\" href=\"css/preguntas.css\">\n"
                + "        <script src=\"responder.js\" charset=\"utf-8\"></script>\n"
                + "        <title>Secuencial</title> \n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div id=\"drags\">\n"
                + "            <div class=\"drag\" id=\"a\">\n"
                + "                <p>Holaa</p>\n"
                + "            </div>\n"
                + "            <div class=\"drag\" id=\"b\">\n"
                + "                <p>Holaa</p>\n"
                + "            </div>\n"
                + "            <div class=\"drag\" id=\"c\">\n"
                + "                <p>Holaa</p>\n"
                + "            </div>\n"
                + "            <div class=\"drag\" id=\"d\">\n"
                + "                <p>Holaa</p>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "\n"
                + "\n"
                + "        <div id=\"drops\">\n"
                + "            <input  class=\"ui-widget-header drop\" value=\"Drop here\" id=\"holiiii\">\n"
                + "            <input  class=\"ui-widget-header drop\" value=\"Drop here\" id=\"holiiii\">\n"
                + "            <input  class=\"ui-widget-header drop\" value=\"Drop here\" id=\"holiiii\">\n"
                + "            <input  class=\"ui-widget-header drop\" value=\"Drop here\" id=\"holiiii\">\n"
                + "        </div>\n"
                + "\n"
                + "\n"
                + "    </body>\n"
                + "</html>\n"
                + "");
    }

    public static void parcial(Element p, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        for (int i = 0; i < p.getChild("respuestas").getChildren().size(); i++) {
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

    static void shuffleArray(String[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
