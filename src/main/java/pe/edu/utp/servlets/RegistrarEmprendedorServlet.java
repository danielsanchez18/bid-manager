package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pe.edu.utp.model.Emprendedor;
import pe.edu.utp.service.EmprendedorDAO;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.TextUTP;
import pe.edu.utp.utils.UTPBinary;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*5,
        maxRequestSize = 1024*1024*5*5
)

@WebServlet(name = "registrarEmprendedor", urlPatterns = {"/registrarEmprendedor"})
public class RegistrarEmprendedorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Nombres = req.getParameter("nombreEmprendedor");
        System.out.println("Nombres = " + Nombres);
        String Apellidos = req.getParameter("apellidos");
        System.out.println("Apellidos = " + Apellidos);
        String DNI = req.getParameter("dni");
        System.out.println("DNI = " + DNI);
        String RUC = req.getParameter("ruc");
        System.out.println("RUC = " + RUC);
        String NombreNegocio = req.getParameter("nombreEstablecimiento");
        System.out.println("NombreNegocio = " + NombreNegocio);
        String LogotipoNegocio = req.getParameter("logoEstablecimiento");
        String Telefono = req.getParameter("telefono");
        System.out.println("Telefono = " + Telefono);
        String Password = req.getParameter("contrasena");

        String cnx = AppConfig.getConnectionStringCFN();
        DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
        EmprendedorDAO regEmprendedor = null;

        String destino = System.getProperty("user.dir") + "\\src\\main\\resources\\web\\img\\";

        try {
            Part filePart = req.getPart("logoEstablecimiento");
            String foto = getFileName(filePart);
            String fileFoto = destino + foto;
            byte[] data = filePart.getInputStream().readAllBytes();
            UTPBinary.echobin(data, fileFoto);

            regEmprendedor = new EmprendedorDAO(dao);


            // Llama al método crear de EmprendorDAO
            Emprendedor emprendedor = new Emprendedor(DNI, Nombres, Apellidos, NombreNegocio, RUC, foto, Telefono, Password);
            regEmprendedor.crear(emprendedor);

            resp.sendRedirect("templates/registrarEmprendedor.html");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
