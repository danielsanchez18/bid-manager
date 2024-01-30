package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.html.PedidosClienteHTML;
import pe.edu.utp.html.PedidosHoyHTML;
import pe.edu.utp.model.Emprendedor;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "pedidosCliente", urlPatterns = {"/pedidoscliente"})
public class PedidosPorClienteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clienteDNI = req.getParameter("clienteDNI");

        PedidosClienteHTML pedidos= new PedidosClienteHTML();

        try {

            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(pedidos.getHTMLReport(clienteDNI));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
