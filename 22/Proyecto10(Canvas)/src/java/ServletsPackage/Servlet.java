/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletsPackage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String numLineas = request.getParameter("numLineas");
        session.setAttribute("numLineasClave",numLineas);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ServletProyecto1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"Servlet2\" method=\"get\">");
        for (int i=1;i<=Integer.parseInt(numLineas); i++) {
          out.println("Xi"+i+"<input type=\"text\" name=\"xi"+i+"\">");
          out.println("Yi"+i+"<input type=\"text\" name=\"yi"+i+"\">");
          out.println("<br>");
          out.println("Xf"+i+"<input type=\"text\" name=\"xf"+i+"\">");
          out.println("Yf"+i+"<input type=\"text\" name=\"yf"+i+"\">");
          out.println("<br>");
        }
        out.println("<input type=\"submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
     }
}
