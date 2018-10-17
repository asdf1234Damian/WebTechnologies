package jdomvalidator;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.IOException;

public class JDOMValidator {

    //Metodo que comprubea la conformidad de un XML
    public void checkConforme(String sURL)throws JDOMException, IOException{
        SAXBuilder builder= new SAXBuilder();
        builder.build(sURL);
    }

    //Metodo que comprueba la validez con el dTD
    public void checkValido(String sURL) throws JDOMException,IOException{
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        builder.build(sURL);
    }
  
    public static void main(String[] args) throws JDOMException,IOException {
        if (args.length==0) {
            System.out.println("Utilizacion: java JDOMValidator URL");
        }else{
            try{
            JDOMValidator validator= new JDOMValidator();
            try{
                validator.checkConforme(args[0]);
                try{
                    validator.checkConforme(args[0]);
                }catch(JDOMException e ){
                    System.out.println(args[0]+"NO esta bien formado");
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
                System.out.println(args[0]+ "esta bien formado");
            }catch(JDOMException e){
                System.out.println(args[0]+"NO es valido");
                System.out.println(e.getMessage());
                System.exit(0);
            }
            System.out.println(args[0]+ "es valido");
        }catch(IOException e){
                System.out.println("No se puede chequear"+args[0]);
                System.out.println(" porque: "+ e.getMessage());
                }
        }
    }
    
}
