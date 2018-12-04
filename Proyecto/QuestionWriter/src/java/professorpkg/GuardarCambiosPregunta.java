/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professorpkg;

import java.io.File;
import java.io.FileWriter;
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
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author damian
 */
public class GuardarCambiosPregunta extends HttpServlet {

    public void edit(Element e, String name, String value) {
        if (value.equals("")) {
            return;
        }
        Element child;
        if(e.getChild(name)== null){
            child =new Element(name);
            e.addContent(child);
        }else{
            child=e.getChild(name);
        }
        child.setText(value);
    }

    public void edit(Element e, int index, Boolean Sequential, String value,Boolean contenido) {
        //Si el valor no cambia
        if (value.equals("")) {
            return;
        }
        //Si la respueta ya existe
        Element respuesta;
        if(index<e.getChild("respuestas").getChildren().size()){
            respuesta = (Element) e.getChild("respuestas").getChildren().get(index);
        }else{
            respuesta = new Element("respuesta");
            e.addContent(respuesta);
        }
        //Si se va a cambiar el contenido
        if(contenido){
            respuesta.getChild("contenido").setText(value);
            return;
        }
        
        //Si se va a cambiar la correspondencia o puntaje
        if (Sequential) {
            respuesta.getChild("correspondencia").setText(value);
        } else {
            respuesta.getChild("puntaje").setText(value);
        }
    }
    
    public void edit(){
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recupera los datos de la sesion 
        String autor = request.getSession().getAttribute("autor").toString();
        String titulo = request.getSession().getAttribute("titulo").toString();
        int index = Integer.parseInt(request.getSession().getAttribute("indice").toString());
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
            Element pregunta = (Element) cuestionario.getChild("preguntas").getChildren().get(index);
            //Para saber si es o no sequencial
            Boolean isSeq = pregunta.getChildText("tipo").trim().equals("sequencial");
            //Se cambian los valores
            edit(pregunta, "contenido", request.getParameter("contenido"));
            //Cambia los valores de las preguntas
            for (int i = 0; i < 4;i++){
                char current=(char)(((int)'A')+i);
                //Se cambia el contenido
                edit(pregunta,i,isSeq,request.getParameter("text"+current),true);
                //Se cambia la correspondencia/puntaje
                edit(pregunta,i,isSeq,request.getParameter("resp"+current),false);
            }
            //Cambia los valores de las opciones
            
            Element opciones = pregunta.getChild("opciones");
            edit(opciones,"feedbackCorrecto",request.getParameter("cfeed"));
            edit(opciones,"feedbackIncorrecto",request.getParameter("ifeed"));
            edit(opciones,"feedbackInicial",request.getParameter("sfeed"));
            edit(opciones,"feedbackEvaluate",request.getParameter("efeed"));
            edit(opciones,"feedbackTries",request.getParameter("tfeed"));
            edit(opciones,"navigation",request.getParameter("Navigation"));
            edit(opciones,"weighting",request.getParameter("weight"));
            //Imprime el resultado
            XMLOutputter xmlout = new XMLOutputter();
            FileWriter writer = new FileWriter(DBfile);
            xmlout.output(root, writer);
            writer.flush();
            writer.close();
            response.sendRedirect("/QuestionWriter/ProfVerPregunta?posicion="+index);
        } catch (JDOMException ex) {
            Logger.getLogger(ProfEliminarPregunta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
