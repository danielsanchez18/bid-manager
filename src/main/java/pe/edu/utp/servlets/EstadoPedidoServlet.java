package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Pedido;
import pe.edu.utp.service.PedidoDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "estadoPedido", urlPatterns = {"/estadoPedido"})
public class EstadoPedidoServlet extends HttpServlet {
    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    PedidoDAO actualizarPedido = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int PedidoID = Integer.parseInt(req.getParameter("PedidoID"));
        String NuevoEstado = req.getParameter("NuevoEstado");

        try {
            actualizarPedido = new PedidoDAO(dao);
            Pedido pedido= actualizarPedido.ObtenerPedidoPorID(PedidoID);
            actualizarPedido.actualizarEstado(PedidoID, NuevoEstado);
            resp.sendRedirect("templates/registrado.html");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        if (actualizarPedido !=null) {
            actualizarPedido.cerrarConexion();
        }
    }
}
