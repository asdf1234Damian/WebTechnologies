package paquete;
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
public class Servlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            /*no hay catch y try porque es tarea del servidor(?)*/
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session =  request.getSession();
        String cadena= (String) session.getAttribute("clave");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> ServletProyecto1</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> Hola mundo" +cadena+" </h1>");
        out.println("</body>");
        out.println("</html>");
     }
}
