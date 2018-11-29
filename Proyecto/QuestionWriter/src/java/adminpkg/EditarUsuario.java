package adminpkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class EditarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        request.getSession().setAttribute("original",correo);
        SAXBuilder SAXbuilder = new SAXBuilder();
        String DBPath=request.getSession().getServletContext().getRealPath("/resources/XML/userDB.xml");
        File DBfile = new File(DBPath);
        Element root;
        try {
            root = SAXbuilder.build(DBfile).getRootElement();
            Element e;
            for(int i=0;i<root.getChildren().size(); i++){
                e = (Element)root.getChildren().get(i);
                if (e.getChild("email").getTextTrim().equals(correo)){
                    response.getWriter().print("<html>\n" +
                    "    <head>\n" +
                    "        <title>Nuevo usuario</title>\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "        <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n" +
                    "        <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n" +
                    "        <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">\n" +
                    "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                    "        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                    "        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
                    "        <script src=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js\"></script>\n" +
                    "        <script src=\"//code.jquery.com/jquery-1.11.1.min.js\"></script>\n" +
                    "        <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n" +
                    "        <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n" +
                    "        <style>\n" +
                    "            body, html {\n" +
                    "            height: 100%;\n" +
                    "            background-image: linear-gradient(to left, rgb(255, 255, 255), rgb(0, 0, 0));\n" +
                    "        }\n" +
                    "\n" +
                    "        h3{\n" +
                    "          text-align: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        .card-container.card {\n" +
                    "            max-width: 600px;\n" +
                    "            padding: 40px 40px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .btn {\n" +
                    "            font-weight: 700;\n" +
                    "            height: 36px;\n" +
                    "            -moz-user-select: none;\n" +
                    "            -webkit-user-select: none;\n" +
                    "            user-select: none;\n" +
                    "            cursor: default;\n" +
                    "        }\n" +
                    "\n" +
                    "        .card {\n" +
                    "            text-align: center;\n" +
                    "            background-color: #F7F7F7;\n" +
                    "            padding: 20px 25px 30px;\n" +
                    "            margin: 0 auto 25px;\n" +
                    "            margin-top: 20%;\n" +
                    "            -moz-border-radius: 2px;\n" +
                    "            -webkit-border-radius: 2px;\n" +
                    "            border-radius: 2px;\n" +
                    "            -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);\n" +
                    "            -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);\n" +
                    "            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);\n" +
                    "        }\n" +
                    "\n" +
                    "        #inputNombre,\n" +
                    "        #inPassword,\n" +
                    "        #inGrupo,\n" +
                    "        #confirmPassword,\n" +
                    "        #inCorreo{\n" +
                    "            height: 44px;\n" +
                    "            font-size: 16px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        .form-signin input[type=text],\n" +
                    "        .form-signin input[type=password],\n" +
                    "        .form-signin button {\n" +
                    "            width: 100%;\n" +
                    "            display: block;\n" +
                    "            margin-bottom: 10px;\n" +
                    "            z-index: 1;\n" +
                    "            position: relative;\n" +
                    "            -moz-box-sizing: border-box;\n" +
                    "            -webkit-box-sizing: border-box;\n" +
                    "            box-sizing: border-box;\n" +
                    "        }\n" +
                    "\n" +
                    "        .form-signin .form-control:focus {\n" +
                    "            border-color: rgb(104, 145, 162);\n" +
                    "            outline: 0;\n" +
                    "            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);\n" +
                    "            box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);\n" +
                    "        }\n" +
                    "\n" +
                    "        .btn.btn-signin {\n" +
                    "            background-color: rgb(104, 145, 162);\n" +
                    "            padding: 0px;\n" +
                    "            font-weight: 700;\n" +
                    "            font-size: 14px;\n" +
                    "            height: 36px;\n" +
                    "            -moz-border-radius: 3px;\n" +
                    "            -webkit-border-radius: 3px;\n" +
                    "            border-radius: 3px;\n" +
                    "            border: none;\n" +
                    "            -o-transition: all 0.218s;\n" +
                    "            -moz-transition: all 0.218s;\n" +
                    "            -webkit-transition: all 0.218s;\n" +
                    "            transition: all 0.218s;\n" +
                    "        }\n" +
                    "\n" +
                    "        .btn.btn-signin:hover,\n" +
                    "        .btn.btn-signin:active,\n" +
                    "        .btn.btn-signin:focus {\n" +
                    "            background-color: rgb(12, 97, 33);\n" +
                    "        }\n" +
                    "        fieldset {\n" +
                    "            border: 0;\n" +
                    "            margin-bottom: 10px;\n" +
                    "        } \n" +
                    "        .overflow {\n" +
                    "          height: 600px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "    <script>\n" +
                    "        $( function() {//Si el usuario es student, muestra el campo grupo\n" +
                    "            var grupo = $( \"#inGrupo\" );\n" +
                    "            $( \"#inTipo\" ).selectmenu();\n" +
                    "            $( \"#inTipo\" ).selectmenu({\n" +
                    "                change: function( event, data ) {\n" +
                    "                    if(data.item.value !==\"Student\"){\n" +
                    "                        grupo.css( \"visibility\", \"hidden\");\n" +
                    "                        grupo.css( \"height\", \"0\");\n" +
                    "                        grupo.css( \"margin-bottom\", \"0px\");\n" +
                    "                    }else{\n" +
                    "                        grupo.css( \"visibility\", \"visible\");\n" +
                    "                        grupo.css( \"height\", \"44px\");\n" +
                    "                        grupo.css( \"margin-bottom\", \"10px\");\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            });\n" + 
                    "            $( \"#inTipo\" ).selectmenu({\n" +
                    "                create: function( event, data ) {\n" +
                    "                    if(data.item.value !==\"Student\"){\n" +
                    "                        grupo.css( \"visibility\", \"hidden\");\n" +
                    "                        grupo.css( \"height\", \"0\");\n" +
                    "                        grupo.css( \"margin-bottom\", \"0px\");\n" +
                    "                    }else{\n" +
                    "                        grupo.css( \"visibility\", \"visible\");\n" +
                    "                        grupo.css( \"height\", \"44px\");\n" +
                    "                        grupo.css( \"margin-bottom\", \"10px\");\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            });\n" +
                    "        } );\n" +
                    "        </script>\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <div class=\"w3-sidebar w3-bar-block w3-black w3-xxlarge center\" style=\"width:70px\">\n" +
                    "            <a href=\"/QuestionWriter/AdminUsuarios\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-address-book\"></i></a> \n" +
                    "            <a href=\"NuevoUsuario.html\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-address-card\"></i></a> \n" +
                    "            <a href=\"/QuestionWriter/LogOut\" class=\"w3-bar-item w3-button\"><i class=\"fas fa-door-open\"></i></a> \n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"container\">\n" +
                    "            <div class=\"card card-container\">\n" +
                    "                <form name=\"formNombre\" class=\"form-signin\" onsubmit=\"return validateForm()\" method=\"get\" action=\"/QuestionWriter/GuardarCambiosUsuario\">\n" +
                    "                    <h3>Introduzca los datos del usuario</h3>\n" +
                    "                    <input type=\"text\" id=\"inputNombre\" name=\"inNombre\" placeholder=\""+e.getChild("usrName").getTextTrim()+"\" autofocus >\n" +
                    "                    <input type=\"password\" id=\"inPassword\" name=\"inPassword\" placeholder=\""+e.getChild("pass").getTextTrim()+"\" autofocus>\n" +
                    "                    <input type=\"password\" id=\"confirmPassword\" name=\"confirmPassword\" placeholder=\""+e.getChild("pass").getTextTrim()+"\" autofocus>\n");
                    if(e.getChild("grupo")!=null){
                        response.getWriter().print("<input type=\"text\" id=\"inGrupo\" name=\"inGrupo\" placeholder=\""+e.getChild("grupo").getTextTrim()+"\" autofocus>\n" );
                    }else{
                        response.getWriter().print("<input type=\"text\" id=\"inGrupo\" name=\"inGrupo\" placeholder=\"NA\" autofocus>\n" );
                    }
                    response.getWriter().print("<input type=\"text\" id=\"inCorreo\" name=\"inCorreo\" placeholder=\""+e.getChild("email").getTextTrim()+"\" autofocus>\n" +
                    "                    <fieldset >\n" +
                    "                    <select name=\"inTipo\" id=\"inTipo\">\n" +
                    "                      <option selected=\"selected\">"+e.getChild("uType").getTextTrim()+"</option>\n" +
                    "                      <option >Student</option>\n" +
                    "                      <option >Professor</option>\n" +
                    "                      <option >Admin</option> \n" +
                    "                    </select>\n" +
                    "                    </fieldset>\n" +
                    "                    <button class=\"btn btn-lg btn-primary btn-block btn-signin\" type=\"submit\">Modificar usuario</button>\n" +
                    "                </form>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        \n" +
                    "        \n" +
                    "</body>"
                            + "</html>");                    
                }
            }
                //TODO cambiar por no encontrado response.sendRedirect("ProfCuestionarios");
            }catch (JDOMException ex) {
                
            response.getWriter().print("Error con la base de datos"+ ex.getMessage());
        }
    }
}
