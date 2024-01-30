package pe.edu.utp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "comprarOferta", urlPatterns = {"/comprarOferta"})
public class ComprarOfertaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ofertaID = request.getParameter("ofertaID");
        try {
            response.getWriter().println(getHTMLReport(ofertaID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getHTMLReport (String OfertaID) throws IOException, SQLException, NamingException {

        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\registroDobleClientePedido.html";
        String html = TextUTP.read(filename);

        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${OfertaID}",OfertaID);


        return reporte_html;
    }
}