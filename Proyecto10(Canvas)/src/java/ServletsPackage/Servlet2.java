
package ServletsPackage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String numRect= (String)session.getAttribute("clave");
        String numLineas = request.getParameter("numLineasClave");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ServletProyecto1</title>");
        out.println("</head>");
        out.println("<canvas id=\"myCanvas\" width=\"578\" height=\"200\"></canvas>");
        out.println("<script>");
        out.println("var canvas = document.getElementById('myCanvas');");
        out.println("var context = canvas.getContext('2d');");
        out.println("context.beginPath();");
        for (int i=1;i<=5; i++) {
          //out.println("context.moveTo("+request.getParameter("xi"+i)+","+request.getParameter("yi"+i)+");");
          //out.println("context.moveTo("+Integer.parseInt(request.getParameter("xf"+i))+","+Integer.parseInt(request.getParameter("yf"+i))+");");
          //out.println("context.lineTo(450, 50);");
          //out.println("context.stroke();");
        }
        out.println("</script>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
        out.println(request.getParameter(numLineas));
     }
}
