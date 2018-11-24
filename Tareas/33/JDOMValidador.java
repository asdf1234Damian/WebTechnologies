import org.jdom.JDOMException;
import org.jdom.inputSAXBuilder;
import java.io.IOException;

public class JDOMValidator{
  //Metodo que comprubea la conformidad de un XML
  public void checkConforme(String sURL)throws JDOMException, IOException{
    SAXBuilder builder= new SAXBuilder();
    builder.build(sURL);
  }

  //Metodo que comprueba la validez con el dTD
  public void checkValido(String sURL) throws JDOMException,IOException{
    SAXBuilder builder = new SAXBuilder();
    builder.setValidation(true);
    builder.buid(sURL);

  }
};
