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

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String numRect = (String)session.getAttribute("numRectSession");
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
        for (int i=1;i<=Integer.parseInt(numRect); i++) {
          out.println("context.fillStyle=\""+request.getParameter("color"+i)+"\";");
          out.println("context.fillRect("+request.getParameter("start"+i)+","+request.getParameter("size"+i)+","+request.getParameter("size"+i)+");");
        }
        out.println("</script>");
        for (int i=1;i<=Integer.parseInt(numRect); i++) {
            out.println("<p> context.fillStyle="+request.getParameter("color"+i)+"; context.fillRect("+request.getParameter("start"+i)+","+request.getParameter("size"+i)+","+request.getParameter("size"+i)+");</p>");
        }
        
        out.println("<body>");
        out.println("</body>");
        out.println("</html>");
     }
}
