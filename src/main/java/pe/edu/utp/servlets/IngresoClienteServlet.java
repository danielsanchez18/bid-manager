package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;

@WebServlet(name = "ingresoCliente", urlPatterns = {"/ingresoCliente"})
public class IngresoClienteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = AppConfig.getIngresuCliente();
        String html = TextUTP.read(path);
        resp.getWriter().write(html);
    }
}