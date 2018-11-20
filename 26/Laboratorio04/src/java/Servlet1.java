/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author damian
 */
public class Servlet1 extends HttpServlet {
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
          out.println("<h2>Nombre del pdf"+i+"</h2>");
          out.println("<input type=\"text\" name=\"pdf"+i+"\">");
          out.println("<br>");
        }
        out.println("<input type=\"submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
     }
}
