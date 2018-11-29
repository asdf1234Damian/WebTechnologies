import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Login extends HttpServlet{
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
            Element e;//Elemento auxiliar
            //Crea el Jdom para la base de usuarios
            String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/userDB.xml");
            File DBfile = new File(DBPath);
            Element root = SAXbuilder.build(DBfile).getRootElement();
            List users = root.getChildren();
            //Crea el jdmom parala base de questionarios
            DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/questionarioDB.xml");
            DBfile = new File(DBPath);
            root = SAXbuilder.build(DBfile).getRootElement();
            List questionarios = root.getChildren();
            
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
                    response.sendRedirect("AdminUsuarios");
                break;

                case "Professor":
                    response.sendRedirect("ProfCuestionarios");                    
                break;

                case "Student":
                    request.getRequestDispatcher("student/menu.html").forward(request, response);
                break;

                default:
                    request.getRequestDispatcher("fail.html").forward(request, response);
                break;
            }
        } catch (JDOMException ex) {
            request.getRequestDispatcher("databaseerror.html").forward(request,response);
        }

    }
        
}
