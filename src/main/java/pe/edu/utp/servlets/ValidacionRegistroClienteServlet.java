package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.service.Auth;
import pe.edu.utp.service.ClienteDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccess;
import pe.edu.utp.utils.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "validarRegistroCliente", urlPatterns = {"/validarRegistroCliente"})
public class ValidacionRegistroClienteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dniCliente = req.getParameter("dniCliente");
        String nombres = req.getParameter("nombres");
        String apellidos = req.getParameter("apellidos");
        String telefono = req.getParameter("telefono");
        String direccion = req.getParameter("direccion");
        String referencia = req.getParameter("referencia");
        String password = Auth.md5(req.getParameter("password"));
        Cliente cliente = new Cliente(dniCliente, nombres, apellidos, telefono, direccion, referencia, password);
        DataAccess dao = new DataAccessMariaDB(AppConfig.getConnectionStringCFN());
        boolean valid;
        try {
            ClienteDAO service = new ClienteDAO(dao);
            valid = service.crear(cliente);
            dao.closeConnection();
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
        if (valid) {
            HttpSession s = req.getSession(true);
            s.setAttribute("type", "cliente");
            s.setAttribute("id", dniCliente);
            resp.sendRedirect("/dashboardCliente");
        } else {
            resp.sendRedirect("/registrarCliente");
        }
    }
}
