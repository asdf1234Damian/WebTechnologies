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

/**
 *
 * @author damian
 */
public class Servlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            /*no hay catch y try porque es tarea del servidor(?)*/
        response.setContentType("text/html;charset=UTF-8");
        String video= request.getParameter("parametro1");
        PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletProyecto6</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<video controls>");
            out.println("<source src='"+video+"' type='video/mp4'>");
            out.println("Tu navegador no soporta este formato");
            out.println("</video>");
            out.println("</body>");
            out.println("</html>");
     }
}