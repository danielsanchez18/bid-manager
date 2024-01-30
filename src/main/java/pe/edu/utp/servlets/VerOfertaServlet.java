package pe.edu.utp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.service.PedidoDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/verOferta")
public class VerOfertaServlet extends HttpServlet {
    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    PedidoDAO buscarPedido = null;
    OfertaDAO buscarOferta = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int OfertaID = Integer.valueOf(req.getParameter("id"));
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(getHTMLReport(OfertaID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getHTMLReport (int OfertaID) throws IOException, SQLException, NamingException {

        buscarOferta = new OfertaDAO(dao);
        Oferta oferta = buscarOferta.ObtenerOfertaPorID(OfertaID);


        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\templates\\oferta.html";
        String html = TextUTP.read(filename);

        // Cargar plantilla para los item
        String filename_items = "src\\main\\resources\\web\\templates\\oferta_item.html";
        String html_item = TextUTP.read(filename_items);

        String qr = "oferta"+OfertaID+".jpg";

        // Recorrer la lista
        StringBuilder items_html = new StringBuilder();



        String item = html_item
                .replace("${Imagen}", oferta.getImagenOferta())
                .replace("${NombreOferta}", oferta.getNombre())
                .replace("${FechaInicio}", oferta.getFechaInicio().toString())
                .replace("${FechaFin}", oferta.getFechaFin().toString())
                .replace("${CantidadLimite}", Integer.toString(oferta.getCantidad()))
                .replace("${Monto}", Float.toString(oferta.getPrecio()))
                .replace("${Restricciones}", oferta.getRestricciones())
                .replace("${Descripcion}", oferta.getDescripcion())
                .replace("${ofertaID}", Integer.toString(oferta.getIdOferta()));
        items_html.append(item);


        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${items}", items_html.toString()).replace("${titulo}",oferta.getNombre())
                .replace("${qr}",qr);

        if (buscarOferta != null) {
            buscarOferta.cerrarConexion();
        }
        return reporte_html;
    }
}
