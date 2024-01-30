package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.App;
import pe.edu.utp.exceptions.InvalidUserException;
import pe.edu.utp.html.ErrorHTML;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.ErrorLog;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginModerador", urlPatterns = "/loginModerador")
public class LoginModeradorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recuperar datos del formulario
        String dni = req.getParameter("dni");
        String pwd = req.getParameter("contraseña");

        PrintWriter out = resp.getWriter();

        try {
            App.authService.isValidUserModerador(dni, pwd);// si falla se genera una excepcion InvalidUser
            // Crear sesion si es valid user
            HttpSession ses = req.getSession();
            ses.setAttribute("is_valid_user", true);
            ses.setAttribute("username", dni);
            resp.sendRedirect("dashboardModerador"); // Se reenvia al servlet /dashboardModerador
        } catch (SQLException | NamingException e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            String htmlError = ErrorHTML.genericError(e.getMessage());
            out.println(htmlError);
        } catch (InvalidUserException e){
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            String htmlError = ErrorHTML.invalidUserError(e.getMessage());
            out.println(htmlError);
        }

    }
}