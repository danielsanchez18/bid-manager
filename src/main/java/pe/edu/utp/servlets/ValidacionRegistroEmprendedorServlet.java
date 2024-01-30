package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pe.edu.utp.model.Emprendedor;
import pe.edu.utp.service.EmprendedorDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccess;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.UTPBinary;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*5,
        maxRequestSize = 1024*1024*5*5
)
public class ValidacionRegistroEmprendedorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dniEmprendedor = req.getParameter("dniEmprendedor");
        String nombres = req.getParameter("nombres");
        String apellidos = req.getParameter("apellidos");
        String nombreNegocio = req.getParameter("nombreNegocio");
        String ruc = req.getParameter("ruc");
        String telefono = req.getParameter("telefono");
        String password = req.getParameter("password");

        String destino = "src\\main\\resources\\upload\\";
        String logotipoNegocio = null;
        try {
            // Obtener la imagen y guardarla en la carpeta upload
            Part filePart = req.getPart("logotipoNegocio");
            logotipoNegocio = getFileName(filePart);
            String fileNombre = destino + logotipoNegocio;
            byte[] data = filePart.getInputStream().readAllBytes();
            UTPBinary.echobin(data, fileNombre);

        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        Emprendedor e = new Emprendedor(dniEmprendedor, nombres, apellidos, nombreNegocio, ruc, telefono, logotipoNegocio, password);
        DataAccess dao = new DataAccessMariaDB(AppConfig.getConnectionStringCFN());
        boolean valid = false;
        try {
            EmprendedorDAO service = new EmprendedorDAO(dao);
            valid = service.crear(e);
            dao.closeConnection();
        } catch (SQLException | NamingException ex) {
        ex.printStackTrace(); // o utiliza algún sistema de logs
        resp.sendRedirect("/errorPage"); // redirige a una página de error
    }
        if (valid) {
            resp.sendRedirect("/dashboardEmprendedor");
        } else {
            resp.sendRedirect("/registrarEmprendedor");
        }
    }

    private String getFileName(Part part) {
        if (part != null) {
            String contentDisposition = part.getHeader("content-disposition");
            if (contentDisposition.trim().startsWith("filename")) {
                return contentDisposition.substring(
                        contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
