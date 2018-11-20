/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String dir = request.getParameter("dir");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"Style.css\">");
        out.println("<title>Tarea etiqueta bdo</title>");
        out.println("</head>");
        out.println("<body>");
        if (dir.equals("left to right")) {
          out.println("<p><bdo dir=\"ltr\">The bdo tag stands for Bi-Directional-Overdrive.</bdo></p>");
          out.println("<p><bdo dir=\"ltr\">Choose the bdo dir atribute</bdo></p>");
          out.println("<form class=\"\" action=\"Servlet\" method=\"get\">");
        }else{
          out.println("<p><bdo dir=\"rtl\">The bdo tag stands for Bi-Directional-Overdrive.</bdo></p>");
          out.println("<p><bdo dir=\"rtl\">Choose the bdo dir atribute</bdo></p>");
          out.println("<form class=\"\" action=\"Servlet\" method=\"get\">");
        }
        out.println("<input type=\"submit\" name=\"dir\" value=\"left to right\">");
        out.println("<input type=\"submit\" name=\"dir\" value=\"right to left\">");
        out.println("</form>");
        out.println("</body>");
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
     }
   }
