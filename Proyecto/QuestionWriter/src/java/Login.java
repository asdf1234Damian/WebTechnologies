import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Login extends HttpServlet{

    private Element genUser(String id, String name, String email, String pass, String type){
        Element user = new Element("user");
        user.addContent(new Element("id").addContent(id));
        user.addContent(new Element("usrName").addContent(name));
        user.addContent(new Element("email").addContent(email));
        user.addContent(new Element("pass").addContent(pass));
        user.addContent(new Element("uType").addContent(type));
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //Inicializacion
        /////////////////////////////////////////////////////////////////////////////////
        //http://www.jdom.org/downloads/oraoscon01-jdom.pdf pagina 14
        SAXBuilder SAXbuilder = new SAXBuilder();
        /////////////////////////////////////////////////////////////////////////////////
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion=request.getSession();
        //Se recuperan los parametros
        String mail= request.getParameter("email");
        String password= request.getParameter("password");
        String userName="TBD";
        String uType="TBD";
        //Decide que pagina regresara, fail por default
        try {
            //Crea el Jdom
            String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/userDB.xml");
            File DBfile = new File(DBPath);
            Element root = SAXbuilder.build(DBfile).getRootElement();
            List users = root.getChildren();
            Element e;
            //Busca el usuario en  la base
            for(int i=0;i<users.size(); i++){
                e = (Element)users.get(i);
                if (e.getChild("email").getTextTrim().equals(mail) &&
                    e.getChild("pass").getTextTrim().equals(password)){
                    userName=e.getChild("usrName").getTextTrim();
                    uType=e.getChild("uType").getTextTrim();
                    break;
                }
            }
            //Guarda el nombre de usuario en sesion
            sesion.setAttribute("userName",userName);
            sesion.setAttribute("uType",uType);
            sesion.setAttribute("email",mail);
            switch (uType){
                case "Admin":
                    request.getRequestDispatcher("admin/menu.html").forward(request, response);
                break;

                case "Professor":
                    Document htmlresponse ;
                    request.getRequestDispatcher("professor/menu.jsp").forward(request, response);
                    
                break;

                case "Student":
                    request.getRequestDispatcher("student/menu.html").forward(request, response);
                break;

                default:
                    request.getRequestDispatcher("fail.html").forward(request, response);
                break;
            }
        } catch (JDOMException ex) {
            //En caso de no poder crear el JDOM regresa una respuesta diferente
            //https://examples.javacodegeeks.com/enterprise-java/servlet/java-servlet-requestdispatcher-tutorial/
            request.getRequestDispatcher("databaseerror.html").forward(request,response);
        }

    }
}
