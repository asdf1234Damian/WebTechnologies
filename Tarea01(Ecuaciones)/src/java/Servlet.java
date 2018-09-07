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
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int[][] eqSys= new int [2][2];
        int n=1; 
            for (int i = 0; i < 2; i++) {
                for(int j=0; i <2; j++){
                    eqSys[i][j]=Integer.parseInt(request.getParameter("parametro"+n));
                    n++;
                }
        }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Solucion</title>");            
            out.println("</head>");
            out.println("<body style=\" text-align: center\">");
            out.println("<h1>Solucion del sistema</h1>");
            
            if((eqSys[0][0]%eqSys[1][0])==0 && (eqSys[0][1]%eqSys[1][1])==0){//Lineas pararelas.
                if (c1==c2) { //Son la misma linea.
                out.println("<h3> Soluciones infinitas</h3>"); 
               }else{//Solo pararelas.
                out.println("<h3> No existe solucion</h3>");
                }
            }else{
                int sol_y, sol_x,a,b,c;
                if(x1!=0){
                    //y1/=x1;
                    //c1/=x1;
                }else if(x2!=0){
                    //y2/=x2;
                    //c2/=x2;
                }else{
                    sol_x=0;
                    sol_y=10;
                }
                out.println("<h3> Y= "+sol_y+"</h3>");
                out.println("<h3> X= "+sol_x+"</h3>");
            }
            out.println("</body>");
            out.println("</html>");
    }   
}
