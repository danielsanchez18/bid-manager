package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.html.ListarOfertasHTML;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "indexOfertas", urlPatterns = {"/indexOfertas"})

public class IndexOfertasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListarOfertasHTML Ofertas = new ListarOfertasHTML();
        try {
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(Ofertas.getHTMLReport());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
