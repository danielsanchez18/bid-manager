package pe.edu.utp.html;

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
import java.time.LocalDate;
import java.util.List;

public class PedidosClienteHoyHTML {

    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    PedidoDAO pedidos = null;
    OfertaDAO obtenerOferta = null;

    public String getHTMLReport (String ClienteDni) throws IOException, SQLException, NamingException {

        System.out.println("ClienteDni = " + ClienteDni);

        LocalDate fecha = LocalDate.now();
        
        System.out.println("fecha = " + fecha);

        pedidos = new PedidoDAO(dao);
        obtenerOferta = new OfertaDAO(dao);

        List<Pedido> listaPedidos = pedidos.ObtenerPedidosPorDniClienteFecha(ClienteDni, fecha);


        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\templates\\pedidoCliente.html";
        String html = TextUTP.read(filename);

        // Cargar plantilla para los item
        String filename_items = "src\\main\\resources\\web\\templates\\pedidoCliente_item.html";
        String html_item = TextUTP.read(filename_items);

        // Recorrer la lista
        StringBuilder items_html = new StringBuilder();
        String titulo = "PEDIDOS DE " + ClienteDni;

        Oferta oferta;

        int nPed= listaPedidos.size();
        for (Pedido pedido : listaPedidos) {
            oferta = obtenerOferta.ObtenerOfertaPorID(pedido.getIdOferta());
            System.out.println("pedido = " + pedido);
            String item = html_item
                    .replace("${PedidoID}", Integer.toString(pedido.getIdPedido()))
                    .replace("${NombreOferta}", oferta.getNombre())
                    .replace("${FechaPedido}", pedido.getFechaPedido().toString())
                    .replace("${Cantidad}", Integer.toString(pedido.getCantidad()))
                    .replace("${MetodoPago}", pedido.getMetodoPago())
                    .replace("${FechaPedido}", pedido.getFechaPedido().toString())
                    .replace("${Estado}", pedido.getEstado());
            items_html.append(item);
        }

        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${items}", items_html.toString())
                .replace("${nPed}",Integer.toString(nPed))
                .replace("${titulo}",titulo);

        if (pedidos != null || obtenerOferta !=null) {
            pedidos.cerrarConexion();
            obtenerOferta.cerrarConexion();
        }
        return reporte_html;
    }

}
