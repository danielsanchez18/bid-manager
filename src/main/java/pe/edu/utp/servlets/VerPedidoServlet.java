package pe.edu.utp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.model.Pedido;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.service.PedidoDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/verPedido")
public class VerPedidoServlet extends HttpServlet {
    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    PedidoDAO buscarPedido = null;
    OfertaDAO buscarOferta = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int PedidoID = Integer.valueOf(req.getParameter("id"));
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(getHTMLReport(PedidoID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public String getHTMLReport (int PedidoID) throws IOException, SQLException, NamingException {



        buscarPedido = new PedidoDAO(dao);
        buscarOferta = new OfertaDAO(dao);

        Pedido pedido = buscarPedido.ObtenerPedidoPorID(PedidoID);


        int ofertaID = pedido.getIdOferta();

        Oferta oferta = buscarOferta.ObtenerOfertaPorID(ofertaID);


        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\templates\\pedido.html";
        String html = TextUTP.read(filename);

        // Cargar plantilla para los item
        String filename_items = "src\\main\\resources\\web\\templates\\pedido_item.html";
        String html_item = TextUTP.read(filename_items);

        String qr = "pedido"+pedido.getIdPedido()+".jpg";

        // Recorrer la lista
        StringBuilder items_html = new StringBuilder();

        float preciototal= (float) (pedido.getCantidad()*oferta.getPrecio());

        String item = html_item
                .replace("${Imagen}", oferta.getImagenOferta())
                .replace("${NombreOferta}", oferta.getNombre())
                .replace("${OfertaID}", Integer.toString(pedido.getIdOferta()))
                .replace("${Monto}", Double.toString(oferta.getPrecio()))
                .replace("${Cantidad}", Integer.toString(pedido.getCantidad()))
                .replace("${PrecioTotal}", Float.toString(preciototal))
                .replace("${FechaPedido}", pedido.getFechaPedido().toString())
                .replace("${HoraPedido}", pedido.getHoraPedido().toString())
                .replace("${MetodoPago}", pedido.getMetodoPago())
                .replace("${EstadoPedido}", pedido.getEstado());
        items_html.append(item);


        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${items}", items_html.toString())
                .replace("${titulo}",Integer.toString(pedido.getIdPedido())).
                replace("${qr}",qr);


        if (buscarPedido != null) {
            buscarPedido.cerrarConexion();
        }
        return reporte_html;
    }
}
