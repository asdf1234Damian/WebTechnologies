/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package professorpkg;

import org.jdom.Element;

/**
 *
 * @author damian
 */
public class Utils {

    public static String fUpper(String in) {
        String s;
        s = in.substring(0, 1).toUpperCase() + in.substring(1);
        return s;
    }

    public static void edit(Element e, String name, String value) {
        if (value.equals("")) {
            return;
        }
        Element child;
        if (e.getChild(name) == null) {
            child = new Element(name);
            e.addContent(child);
        } else {
            child = e.getChild(name);
        }
        child.setText(value);
    }

    public static void edit(Element e, int index, Boolean Sequential, String value, Boolean contenido) {
        //Si el valor no cambia
        if (value.equals("")) {
            return;
        }
        //Si la respueta ya existe
        Element respuesta;
        
        if (index < e.getChild("respuestas").getChildren().size()) {
            respuesta = (Element) e.getChild("respuestas").getChildren().get(index);
        } else {
            respuesta = new Element("respuesta");
            e.getChild("respuestas").addContent(respuesta);
        }
        
        //Si se va a cambiar el contenido
        if (contenido) {
            if(respuesta.getChild("contenido")!=null){
                respuesta.getChild("contenido").setText(value);
            }else{
                respuesta.addContent(new Element("contenido").setText(value));
            }
        }else if (Sequential) {
            if(respuesta.getChild("correspondencia")!=null){
                respuesta.getChild("correspondencia").setText(value);    
            }else{
                respuesta.addContent(new Element("correspondencia").setText(value));
            }
        } else {
            if(respuesta.getChild("puntaje")!=null){
                respuesta.getChild("puntaje").setText(value);    
            }else{
                respuesta.addContent(new Element("puntaje").setText(value));
            }
        }
    }

    public static Boolean found(Element tbf, String val, String child) {
        if (child == null) {
            for (int i = 0; i < tbf.getChildren().size(); i++){
                Element e = (Element) tbf.getChildren().get(i);        
                if (e.getValue().equals(val)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < tbf.getContentSize(); i++) {
                Element e = (Element) tbf.getChildren().get(i);
                e.getChild(child);
                if (e.getValue().equals(val)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static String exists(Element e) {
        if (e != null) {
            return e.getTextTrim();
        }
        return "";
    }

    public static String exists(Element e, String s) {
        if (e != null) {
            return e.getTextTrim();
        }
        return s;
    }
}
