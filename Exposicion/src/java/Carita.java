import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Carita extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        String R = request.getParameter("red");
        String G=request.getParameter("green");
        String B=request.getParameter("blue");
        response.getWriter().print("<!doctype html>\n" +
"<html lang=\"en\">\n" +
"    <head>\n" +
"        <meta charset=\"utf-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"        <title>☺️</title>\n" +
"        <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css\">\n" +
"        <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css'>\n" +
"        <style>\n" +
"            #contenedor{\n" +
"                text-align: center;\n" +
"                align-content: center;\n" +
"                position: relative;\n" +
"            }\n" +
"            \n" +
"            .custom-colorize {\n" +
"                margin-left: auto;\n" +
"                margin-right: auto;\n" +
"                font-size: 20px;\n" +
"                width: 500px;\n" +
"                height: 530px;\n" +
"                align-content: center;\n" +
"                text-align: center;\n" +
"            }\n" +
"            .custom-colorize-changer {\n" +
"                font-size: 15px;\n" +
"            }\n" +
"        </style>\n" +
"        <!--librerias de jqueryUS-->\n" +
"        <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script>\n" +
"        <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script>\n" +
"        <script>\n" +
"            $(function () {\n" +
"                //Se declara el widget\n" +
"                $.widget(\"custom.colorize\", {\n" +
"                    options: {\n" +
"                        red: "+ R +",\n" +
"                        green: "+G+",\n" +
"                        blue: "+B+"\n" +
"                    },\n" +
"\n" +
"                    //Constructor \n" +
"                    _create: function () {\n" +
"                        //Se le da una clase para que se aplique el tema\n" +
"                        this.element.addClass(\"custom-colorize\");\n" +
"                        //El widget tendra un boton\n" +
"                        this.changer = $(\"<button>\", {\n" +
"                            text: \"Cambiar\",\n" +
"                            \"class\": \"custom-colorize-changer\"\n" +
"                        }).appendTo(this.element).button();\n" +
"                        //_on relaciona al widget con un evento\n" +
"                        // el evento en este caso es click en changer, que activa la fucion random\n" +
"                        this._on(this.changer, {click: \"random\"});\n" +
"                        this._refresh();\n" +
"                    },\n" +
"\n" +
"                    // Cada que se cambia alguna opcion\n" +
"                    _refresh: function () {\n" +
"                        this.element.css(\"background-color\", \"rgb(\" +\n" +
"                                this.options.red + \",\" +\n" +
"                                this.options.green + \",\" +\n" +
"                                this.options.blue + \")\"\n" +
"                                );\n" +
"                        //Cambio ( terminar el click o enter)\n" +
"                        this._trigger(\"change\");\n" +
"                    },\n" +
"\n" +
"                    // Genera valores aleatorios para el widget\n" +
"                    random: function (event) {\n" +
"                        //Crea un objeto\n" +
"                        var colors = {\n" +
"                            red: Math.floor(Math.random() * 256),\n" +
"                            green: Math.floor(Math.random() * 256),\n" +
"                            blue: Math.floor(Math.random() * 256)\n" +
"                        };\n" +
"\n" +
"                        // En caso de que se cancele\n" +
"                        if (this._trigger(\"random\", event, colors) !== false) {\n" +
"                            this.option(colors);\n" +
"                        }\n" +
"                    },\n" +
"\n" +
"                    //Destructor \n" +
"                    _destroy: function () {\n" +
"                        // remove generated elements\n" +
"                        this.changer.remove();\n" +
"                        this.element\n" +
"                                .removeClass(\"custom-colorize\")\n" +
"                                .enableSelection()\n" +
"                                .css(\"background-color\", \"transparent\");\n" +
"                    },\n" +
"\n" +
"                    //funciones estandar , más en la documentacion\n" +
"                    _setOptions: function () {\n" +
"                        this._superApply(arguments);\n" +
"                        this._refresh();\n" +
"                    }\n" +
"                });\n" +
"\n" +
"                // Senicializa el elemento del html como un widget\n" +
"                $(\"#my-widget1\").colorize();\n" +
"            });\n" +
"        </script>\n" +
"    </head>\n" +
"    <body>\n" +
"        <div id='contenedor'>\n" +
"            <div id=\"my-widget1\"><i style='font-size:500px; color: #0099ff;' class='far fa-grin-alt'></i></div>\n" +
"        </div>\n" +
"    </body>\n" +
"</html>\n" +
"");
    }
}
