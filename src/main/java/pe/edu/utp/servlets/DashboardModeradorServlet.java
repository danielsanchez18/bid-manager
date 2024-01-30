package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.html.DashboardClienteHTML;
import pe.edu.utp.html.DashboardModeradorHTML;
import pe.edu.utp.html.ErrorHTML;

import java.io.IOException;

@WebServlet(name = "dashboardModerador", urlPatterns = {"/dashboardModerador"})
public class DashboardModeradorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();

        // Comprobar si el usuario ha iniciado sesion
        if (ses.getAttribute("is_valid_user") != null) {
            boolean is_valid_user = (boolean) ses.getAttribute("is_valid_user");
            if (is_valid_user) {
                String username = (String) ses.getAttribute("username");
                // Mostrar dashboard.html
                String htmlDashboard = DashboardModeradorHTML.showDashboard(username);
                resp.getWriter().println(htmlDashboard);
            } else {
                // Mostrar pagina de error
                String msg = "Usuario es invalido, no se le permite el ingreso";
                String htmlError = ErrorHTML.genericError(msg);
                resp.getWriter().println(htmlError);
            }
        }else{
            // Mostrar pagina de error
            String msg = "No se permite el ingreso a este sitio sin haber iniciado sesión";
            String htmlError = ErrorHTML.invalidUserError(msg);
            resp.getWriter().println(htmlError);
        }
    }
}
