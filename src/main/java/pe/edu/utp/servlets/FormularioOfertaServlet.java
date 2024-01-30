package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "formularioOferta", urlPatterns = {"/formularioOferta"})
public class FormularioOfertaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dni =req.getParameter("dni");
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(getHTMLReport(dni));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getHTMLReport (String dni) throws IOException, SQLException, NamingException {

        // Cargar plantilla
        String filename = "src\\main\\resources\\web\\templates\\registrarOferta.html";
        String html = TextUTP.read(filename);


        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${dni}", dni);

        return reporte_html;
    }
}
