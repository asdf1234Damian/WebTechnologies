package professorpkg;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ProfVerPregunta extends HttpServlet {
    
    public String fUpper(String in){
        String s;
        s=in.substring(0,1).toUpperCase()+in.substring(1);
        return s;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recupera los datos de la sesion 
        String autor = request.getSession().getAttribute("autor").toString();
        String titulo = request.getSession().getAttribute("titulo").toString();
        int index = Integer.parseInt(request.getParameter("posicion"));
        //Recupera la base de datos
        String DBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        SAXBuilder SAXbuilder = new SAXBuilder();
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element cuestionario = null;
            for (int i = 0; i < root.getChildren().size(); i++) {
                Element e = (Element) root.getChildren().get(i);
                if (e.getChild("autor").getTextTrim().equals(autor) && e.getChild("titulo").getTextTrim().equals(titulo)) {
                    cuestionario = (Element) root.getChildren().get(i);
                }
            }
            Element pregunta= (Element) cuestionario.getChild("preguntas").getChildren().get(index);
            response.getWriter().print("<html>\n" +
"\n" +
"<head>\n" +
"  <title>Nuevo usuario</title>\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"  <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n" +
"  <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
"  <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n" +
"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
"  <script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n" +
"  <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n" +
"  <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n" +
"  <link rel=\"stylesheet\" href=\"professor/main.css\">\n" +
"  <script src=\"professor/main.js\" charset=\"utf-8\"></script>\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"  <div class=\"w3-sidebar w3-bar-block w3-black w3-xxlarge center\" style=\"width:70px\">\n" +
"    <a href=\"/QuestionWriter/ProfCuestionarios\" class=\"w3-bar-item w3-button\"><i class=\"far fa-clipboard\"></i></a>\n" +
"    <a href=\"NuevoCuestionario.html\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-edit\"></i></a>\n" +
"    <a href=\"/QuestionWriter/LogOut\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-door-open\"></i></a>\n" +
"  </div>\n" +
"  <div class=\"container\">\n" +
"    <div class=\"card\">\n" +
"      <h2>Contenido:"+pregunta.getChild("contenido").getText()+"</h2>\n"+
"      <h3>Tipo:"+fUpper(pregunta.getChild("tipo").getText())+"</h3>\n");
            response.getWriter().print("<h3>");
            for(int i=0;i<pregunta.getChild("respuestas").getChildren().size();i++){
                Element respuesta = (Element) pregunta.getChild("respuestas").getChildren().get(i);
                response.getWriter().print("<h3> Opcion "+ ((char)(65+i)) +":");
                response.getWriter().print("<u>"+respuesta.getChild("contenido").getText().toString()+"</u>    ");
                if(respuesta.getChild("puntaje")!=null){
                    response.getWriter().print(respuesta.getChild("puntaje").getText().toString()+"<h3>");    
                }    
            }
            
            for(int i=0;i<pregunta.getChild("opciones").getChildren().size();i++){
                Element opcion = (Element) pregunta.getChild("opciones").getChildren().get(i);                
                response.getWriter().print("<h3>"+fUpper(opcion.getName()));    
                response.getWriter().print(": <u>"+opcion.getText().toString()+"</u><br/>");    
            }
            response.getWriter().print("</h3>");
response.getWriter().print("<a class=\"btn\" href=\"ProfEditarCuestionario?titulo="+titulo+"\">Regresar</a>"+
"    </div>\n" +
"</body>\n" +
"\n" +
"</html>");
        } catch (JDOMException ex) {
            Logger.getLogger(ProfEliminarPregunta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
