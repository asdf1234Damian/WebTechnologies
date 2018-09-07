/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaServlet;

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
        session.setAttribute("numRectSession",request.getParameter("numRect"));
        int numRect = Integer.parseInt(request.getParameter("numRect"));
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Colores</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Introduzca el color de los rectangulos</h1>");
        out.println("<form action=\"Servlet2\" method=\"get\">");
        for (int i=1;i<=numRect;i++ ) {
          out.println("<br>");
          out.println("<h3>Rectangulo"+i+"</h3>");
          out.println("Color:<input type=\"text\" name=\"color"+i+"\">");
          out.println("<br>");
          out.println("Tamaño:<input type=\"text\" name=\"size"+i+"\">");
          out.println("<br>");
          out.println("Punto de inicio:<input type=\"text\" name=\"start"+i+"\">");
          out.println("<br>");
        }
        out.println("<input type=\"submit\" value=\"Enviar\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
     }
}
