package JavaServlets;

import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.jdom.*;
import org.jdom.output.XMLOutputter;

public class ServletXML1 extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      try {
        Element raiz = new Element("Root");
        Element hoja = new Element("Root");
        hoja.setAttribute("numerodehojas","4");
        hoja.setAttribute("ValorDelNodo","");
        raiz.addContent(hoja);

        Document newdoc= new Document(raiz);
        XMLOutputter fmt = new XMLOutputter();
        FileWriter writer = new FileWriter("/home/damian/Documents/pregunta2.xml"); //Hecho en Linux
        fmt.output(newdoc,writer);
        writer.flush();
        writer.close();
      }catch(Exception e){
        e.printStackTrace();
      }
  }
}
