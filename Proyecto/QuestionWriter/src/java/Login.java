/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author damian
 */
public class Login extends HttpServlet{
  @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException
      {
          String id= request.getParameter("id");
          String password= request.getParameter("password");
          HttpSession sesion=request.getSession();
          sesion.setAttribute("userName",id);
          response.setContentType("text/html;charset=UTF-8");
          RequestDispatcher view = request.getRequestDispatcher("fail.html");
          String uType="Hola:c";
          switch (uType){
              
              case "Admin":
                  view = request.getRequestDispatcher("professor/menu.html");
              break;
              
              case "Professor":
                  view = request.getRequestDispatcher("in.html");
              break;
              
              case "Student":
                  view = request.getRequestDispatcher("in.html");
              break;
              
          }
          view.forward(request, response);
      }
}