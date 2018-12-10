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
import professorpkg.Utils;

public class DesplegarPreg extends HttpServlet {

    

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
            String cDBPath = request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            File cDBfile = new File(cDBPath);
            SAXBuilder cSAXbuilder = new SAXBuilder();
            
            String pDBPath = request.getSession().getServletContext().getRealPath("/resources/XML/preguntaDB.xml");
            File pDBfile = new File(pDBPath);
            SAXBuilder pSAXbuilder = new SAXBuilder();
            Element preguntas = pSAXbuilder.build(pDBfile).getRootElement().getChild("preguntas");
            
            Element root = cSAXbuilder.build(cDBfile).getRootElement();
            int index = Integer.parseInt(request.getSession().getAttribute("index").toString());
            Element cuestionario = Utils.buscarCuestionario(root, autor, titulo);
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

            Element pregunta = (Element) cuestionario.getChild("preguntas").getChildren().get(index);
            
            for (int i = 0; i < preguntas.getChildren().size(); i++) {
                Element aux = (Element) preguntas.getChildren().get(i);
                if(pregunta.getValue().equals(aux.getChild("id").getText())){
                    pregunta=aux;
                }
            }
            
            if (pregunta.getChild("tipo").getText().equals("parcial")) {
                Utils.parcial(pregunta, request, response);
            } else {
                Utils.sequencial(pregunta, request, response);
               }
        } catch (JDOMException ex) {
            response.getWriter().print("Error en base de datos");
        }
    }
}
