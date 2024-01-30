package pe.edu.utp.html;

import pe.edu.utp.model.Oferta;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.service.PedidoDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import pe.edu.utp.model.Pedido;
import pe.edu.utp.utils.TextUTP;

public class PedidosHoyHTML {

    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    PedidoDAO pedidosHoy = null;
    OfertaDAO obtenerOferta = null;

    public String getHTMLReport (String EmprendedorDni, LocalDate FechaPedido, String Estado) throws IOException, SQLException, NamingException {

        pedidosHoy = new PedidoDAO(dao);
        obtenerOferta = new OfertaDAO(dao);

        List<Pedido> listaPedidos = pedidosHoy.ListarPedidosPorEmprendedorEstadoYFecha(EmprendedorDni,Estado,FechaPedido);


        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\templates\\tablaPedidos.html";
        String html = TextUTP.read(filename);

        // Cargar plantilla para los item
        String filename_items = "src\\main\\resources\\web\\templates\\tablaPedidos_item.html";
        String html_item = TextUTP.read(filename_items);

        // Recorrer la lista
        StringBuilder items_html = new StringBuilder();
        String titulo = "PEDIDOS " + Estado.toUpperCase() +"S DE HOY";

        Oferta oferta;

        int nPed= listaPedidos.size();
        for (Pedido pedido : listaPedidos) {
            oferta = obtenerOferta.ObtenerOfertaPorID(pedido.getIdOferta());
            System.out.println("pedido = " + pedido);
            String item = html_item
                    .replace("${PedidoID}", Integer.toString(pedido.getIdPedido()))
                    .replace("${NombreOferta}", oferta.getNombre())
                    .replace("${dniCliente}", pedido.getDniCliente())
                    .replace("${FechaPedido}", pedido.getFechaPedido().toString())
                    .replace("${Cantidad}", Integer.toString(pedido.getCantidad()))
                    .replace("${MetodoPago}", pedido.getMetodoPago());
            items_html.append(item);
        }

        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${items}", items_html.toString())
                .replace("${nPed}",Integer.toString(nPed))
                .replace("${titulo}",titulo);

        if (pedidosHoy != null || obtenerOferta !=null) {
            pedidosHoy.cerrarConexion();
            obtenerOferta.cerrarConexion();
        }
        return reporte_html;
    }

}
