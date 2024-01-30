package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.html.PedidosHoyHTML;
import pe.edu.utp.model.Emprendedor;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "pedidosHoy", urlPatterns = {"/pedidosHoy"})
public class PedidosPorFechayEstadoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String EmprendedorDNI = req.getParameter("emprendedorDNI");
        System.out.println("EmprendedorDNI = " + EmprendedorDNI);
        LocalDate FechaPedido = LocalDate.now();
        System.out.println("FechaPedido = " + FechaPedido);
        String Estado = req.getParameter("estado");
        System.out.println("Estado = " + Estado);

        PedidosHoyHTML pedidosHoy = new PedidosHoyHTML();

        try {

            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(pedidosHoy.getHTMLReport(EmprendedorDNI,FechaPedido, Estado));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
}
