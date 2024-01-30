package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.html.ListarOfertasHTML;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ofertasDashboard", urlPatterns = {"/ofertasDashboard"})

public class OfertasDashboardServlet extends HttpServlet {
    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    OfertaDAO ofertaDAO = null;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dni = req.getParameter("dni");

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

        ofertaDAO = new OfertaDAO(dao);
        List<Oferta> Ofertas = ofertaDAO.lector();

        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\templates\\ofertasDashboard.html";
        String html = TextUTP.read(filename);

        // Cargar plantilla para los item
        String filename_items = "src\\main\\resources\\web\\templates\\ofertasDashboard_item.html";
        String html_item = TextUTP.read(filename_items);

        // Recorrer la lista
        StringBuilder items_html = new StringBuilder();

        //Número de Ofertas en la lista
        int nOf = Ofertas.size();

        String titulo ="OFERTAS DISPONIBLES";

        for (Oferta oferta : Ofertas) {
            String item = html_item
                    .replace("${Imagen}", oferta.getImagenOferta())
                    .replace("${NombreOferta}", oferta.getNombre())
                    .replace("${FechaInicio}", oferta.getFechaInicio().toString())
                    .replace("${FechaFin}", oferta.getFechaFin().toString())
                    .replace("${CantidadLimite}", Integer.toString(oferta.getCantidad()))
                    .replace("${Monto}", Float.toString(oferta.getPrecio()))
                    .replace("${Restricciones}", oferta.getRestricciones())
                    .replace("${ofertaID}", Integer.toString(oferta.getIdOferta()))
                    .replace("${estadoOferta}", oferta.getEstado())
                    .replace("${clienteDni}", dni);
            items_html.append(item);
        }

        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${items}", items_html.toString()).replace("${titulo}",titulo);
        reporte_html = reporte_html.replace("${nOf}", Integer.toString(nOf));

        if (ofertaDAO != null) {
            ofertaDAO.cerrarConexion();
        }
        return reporte_html;
    }
}
