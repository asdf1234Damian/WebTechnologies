package paquete;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.pow;
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
            /*no hay catch y try porque es tarea del servidor(?)*/
        response.setContentType("text/html;charset=UTF-8");
        int a,b,c;
        a=Integer.parseInt(request.getParameter("parametro1"));
        b=Integer.parseInt(request.getParameter("parametro2"));
        c=Integer.parseInt(request.getParameter("parametro3"));
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> ServletProyecto1</title>");
        out.println("</head>");
        out.println("<body>");
        
        if((4*a*c>pow((double)b,2.0))){
            out.println("<h1>La ecuacion tiene soluciones imaginarias</h1>");    
        }else{
            float s1,s2;
            s1=(float) (-b+pow((pow(b,2)-a*4*c),1/2)/2*a);
            s2=(float) (-b-pow((pow(b,2)-a*4*c),1/2)/2*a);
            out.println("<h1>Solucion 1: "+  Float.toString(s1) +"</h1>");
            out.println("<h1>Solucion 1: "+  Float.toString(s2) +"</h1>");
        }
        out.println("</body>");
        out.println("</html>");
     }
}
