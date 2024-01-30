package pe.edu.utp.servlets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.utp.html.ErrorHTML;
import pe.edu.utp.html.RegistrarOfertaHTML;
import pe.edu.utp.model.Emprendedor;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.service.EmprendedorDAO;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.UTPBinary;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

@MultipartConfig(
        fileSizeThreshold = 1024*1024,
        maxFileSize = 1024*1024*5,
        maxRequestSize = 1024*1024*5*5
)
@WebServlet(name = "registrarOferta", urlPatterns = {"/registrarOferta"})
public class RegistrarOfertaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String NombreOferta = req.getParameter("nombreOferta");
        System.out.println("NombreOferta = " + NombreOferta);
        String Descripcion = req.getParameter("descripcion");
        System.out.println("Descripcion = " + Descripcion);
        LocalDate FechaInicio = LocalDate.parse(req.getParameter("fechaInicio"));
        System.out.println("FechaInicio = " + FechaInicio);
        LocalDate FechaFin = LocalDate.parse(req.getParameter("fechaFin"));
        System.out.println("FechaFin = " + FechaFin);
        String ImagenOferta = req.getParameter("imagenOferta");
        String qrCode= "....";
        String Restricciones = req.getParameter("restricciones");
        System.out.println("Restricciones = " + Restricciones);
        String Estado = "Disponible";
        int CantidadLimite = Integer.parseInt(req.getParameter("cantidad"));
        System.out.println("CantidadLimite = " + CantidadLimite);
        Float Monto = Float.valueOf(req.getParameter("precio"));
        System.out.println("Precio = " + Monto);
        String DNI = req.getParameter("dni");
        System.out.println("DNI = " + DNI);
        String Password = req.getParameter("pass");
        System.out.println("Password = " + Password);

        String cnx = AppConfig.getConnectionStringCFN();
        DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
        EmprendedorDAO validarEmprendedor = null;
        OfertaDAO regOferta = null;


        String destino = System.getProperty("user.dir") + "\\src\\main\\resources\\web\\img\\";

        try {

            Part filePart1 = req.getPart("imagenOferta");
            String foto1 = getFileName(filePart1);
            String fileFoto1 = destino + foto1;
            byte[] data1 = filePart1.getInputStream().readAllBytes();
            UTPBinary.echobin(data1,fileFoto1);

            validarEmprendedor = new EmprendedorDAO(dao);
            regOferta = new OfertaDAO(dao);


            // Llama al método validarEmprendedor
            boolean valEmp= validarEmprendedor.validarEmprendedor(DNI, Password);

            if(valEmp) {
                //Llamar al método InsertarOferta
                Oferta oferta = new Oferta(0, DNI, NombreOferta, Descripcion, FechaInicio, FechaFin, CantidadLimite, Monto, foto1, qrCode, Restricciones, Estado);
                int ofertaID = regOferta.InsertarOferta(oferta);


                BufferedImage bufferedImage2 = genQR(new URL("http://localhost:8080/verOferta?id=" + ofertaID).toString(), 300, 300);
                System.out.println(bufferedImage2);
                String strQrFile2 = System.getProperty("user.dir") + "\\src\\main\\resources\\web\\upload\\oferta" + ofertaID + ".jpg";
                ImageIO.write(bufferedImage2, "jpg", new File(strQrFile2));

                resp.sendRedirect("/verOferta?id=" + ofertaID);
            }else resp.sendRedirect(ErrorHTML.genericError("Contraseña incorrecta"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Cierra la conexión
            if (regOferta != null || validarEmprendedor != null) {
                regOferta.cerrarConexion();
                validarEmprendedor.cerrarConexion();
            }
        }
    }
    public static BufferedImage genQR(String text, int width, int height) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bits =
                barcodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        return MatrixToImageWriter.toBufferedImage(bits);
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
