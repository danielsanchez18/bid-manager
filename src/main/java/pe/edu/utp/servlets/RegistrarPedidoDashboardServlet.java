package pe.edu.utp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registrarPedidoDashboard", urlPatterns = {"/registrarPedidoDashboard"})
public class RegistrarPedidoDashboardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ofertaID = request.getParameter("ofertaID");
        String clienteDNI = request.getParameter("clienteDni");
        try {
            response.getWriter().println(getHTMLReport(ofertaID, clienteDNI));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHTMLReport (String OfertaID, String ClienteDNI) throws IOException, SQLException, NamingException {

        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\registroPedido.html";
        String html = TextUTP.read(filename);

        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${OfertaID}",OfertaID).replace("${ClienteDNI}", ClienteDNI);


        return reporte_html;
    }
}