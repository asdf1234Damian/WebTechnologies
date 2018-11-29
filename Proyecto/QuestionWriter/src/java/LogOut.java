import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        //https://stackoverflow.com/questions/24677949/why-session-is-not-null-after-session-invalidate-in-java//
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        request.getSession().invalidate();//Elimina el objeto session
        response.sendRedirect("/QuestionWriter/index.html");
    }
}
