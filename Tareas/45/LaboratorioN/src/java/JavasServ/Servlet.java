/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavasServ;

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
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String widht = request.getParameter("width");
        String height = request.getParameter("height");
        String BackC = request.getParameter("BackC");
        String BorderC = request.getParameter("BorderC");
        String lh = request.getParameter("lh");

        String topMar = request.getParameter("topMar");
        String rightMar = request.getParameter("rightMar");
        String botMar = request.getParameter("botMar");
        String leftMar = request.getParameter("leftMar");

        String topBor = request.getParameter("topBor");
        String rightBor = request.getParameter("rightBor");
        String botBor = request.getParameter("botBor");
        String leftBor = request.getParameter("leftBor");

        String topPad = request.getParameter("topPad");
        String rightPad = request.getParameter("rightPad");
        String botPad = request.getParameter("botPad");
        String leftPad = request.getParameter("leftPad");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Modelo cajas</title>");
        out.println("</head>");
        out.println("<title>MODELO DE CAJAS</title>");
        out.println("<style>");
        out.println(".centro{");
        out.println("display:inline-block;");
        out.println("width :"+widht+"px;");
        out.println("height :"+height+"px;");
        out.println("background :"+BackC+";");
        out.println("line-height :"+lh+"px;");
        out.println("border-style:solid;");
        out.println("border-color:"+BorderC+";");
        out.println("padding :"+topPad+"px "+rightPad+"px "+botPad+"px "+leftPad+"px;");

        out.println("margin:"+topMar+"px "+rightMar+"px "+botMar+"px "+leftMar+"px;");

        out.println("border-width :"+topBor+"px "+rightBor+"px "+botBor+"px "+leftBor+"px;");

        out.println("}");
        out.println("body {line-height:0;}");
        out.println("img { vertical-align:top; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<img src='bordes.jpg'><img"); out.println("src='bordes.jpg'><img src='bordes.jpg'><br>");
        out.println("<img src='bordes.jpg'><div"); out.println("class='centro'>RUBEN</div><img"); out.println("src='bordes.jpg'><br>");
        out.println("<img src='bordes.jpg'><img"); out.println("src='bordes.jpg'><img src='bordes.jpg'>");
        out.println("</body>");
        out.println("</html>");
     }
}
