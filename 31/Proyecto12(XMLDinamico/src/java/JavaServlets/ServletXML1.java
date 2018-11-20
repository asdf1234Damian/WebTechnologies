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
        Element raiz = new Element("stratus-config");
        Element fb = new Element("form-beans");
        raiz.addContent(fb);
        Element ge = new Element("global-exceptions");
        raiz.addContent(ge);
        Element gf = new Element("global-forwards");
        raiz.addContent(gf);
        Element fw = new Element("forward");
        fw.setAttribute("name","Welcome");
        fw.setAttribute("path","\\Welcome.do\\");
        gf.addContent(fw);
        raiz.addContent(gf);
        Element cn = new Element("controller");
        cn.setAttribute("processorClass", "org.apache.struts.tiles.TilesRequestProcessor");
        raiz.addContent(cn);

        Element mr = new Element("message-result");
        cn.setAttribute("parameter", "com/myapp/struts/ApplicationResource");
        raiz.addContent(mr);

        Element pi = new Element("plug-in");
        pi.setAttribute("className","org.apache.struts.tiles.TilesPlugin");

        Element sp = new Element("set-property");
        sp.setAttribute("property","definitions-config");
        sp.setAttribute("property","definitions-config");
        pi.addContent(sp)

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
