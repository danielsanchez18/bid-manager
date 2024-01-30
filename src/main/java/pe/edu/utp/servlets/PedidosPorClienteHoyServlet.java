package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.html.PedidosClienteHoyHTML;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "pedidosClienteHoy", urlPatterns = {"/pedidosclienteHoy"})
public class PedidosPorClienteHoyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clienteDNI = req.getParameter("clienteDNI");

        PedidosClienteHoyHTML pedidosHoy = new PedidosClienteHoyHTML();

        try {

            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(pedidosHoy.getHTMLReport(clienteDNI));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
