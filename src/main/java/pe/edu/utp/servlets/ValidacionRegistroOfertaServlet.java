package pe.edu.utp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccess;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.UTPBinary;


import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*5,
        maxRequestSize = 1024*1024*5*5
)
public class ValidacionRegistroOfertaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String dniEmprendedor = session.getAttribute("id").toString();

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        LocalDate fechaInicio = LocalDate.parse(req.getParameter("fechaInicio"));
        LocalDate fechaFin = LocalDate.parse(req.getParameter("fechaFin"));
        String cantidad = req.getParameter("cantidad");
        float precio = Float.parseFloat(req.getParameter("precio"));
        String qrCode = req.getParameter("qrCode");
        String restricciones = req.getParameter("restricciones");
        String estado = "Activa";

        String destino = "src\\main\\resources\\upload\\";

        String imagenOferta = null;

        try {
            // Obtener la imagen y guardarla en la carpeta upload
            Part filePart = req.getPart("imagenOferta");
            imagenOferta = getFileName(filePart);
            String fileFoto = destino + imagenOferta;
            byte[] data = filePart.getInputStream().readAllBytes();
            UTPBinary.echobin(data, fileFoto);

        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        Oferta oferta = new Oferta(0, dniEmprendedor, nombre, descripcion, fechaInicio, fechaFin, Integer.parseInt(cantidad), precio, imagenOferta, qrCode, restricciones, estado);
        DataAccess dao = new DataAccessMariaDB(AppConfig.getConnectionStringCFN());
        boolean valid;
        try {
            OfertaDAO service = new OfertaDAO(dao);
            valid = service.crear(oferta);
            dao.closeConnection();
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }
        if (valid) {
            resp.sendRedirect("/seguimientoOfertasEmprendedor");
        } else {
            resp.sendRedirect("/registrarOferta");
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
