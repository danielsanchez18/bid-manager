package pe.edu.utp.servlets;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.model.Pedido;
import pe.edu.utp.service.ClienteDAO;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.service.PedidoDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;

import javax.imageio.ImageIO;
import javax.naming.NamingException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet(name = "insertarPedidoCliente", urlPatterns = {"/insertarPedidoCliente"})
public class InsertarPedidoClienteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Apellidos = req.getParameter("apellidosCliente");
        String Nombres = req.getParameter("nombreCliente");
        String ClienteDNI = req.getParameter("dni");
        String Telefono = req.getParameter("telefono");
        String Direccion = req.getParameter("direccion");
        String Referencia = req.getParameter("referencia");
        String Password = req.getParameter("contrasenaCliente");

        String OfertaID = req.getParameter("OfertaID");
        String Cantidad = req.getParameter("cantidad");
        String MetodoPago = req.getParameter("metodoPago");

        int ofertaID = Integer.parseInt(OfertaID);
        int cantidad = Integer.parseInt(Cantidad);

        String cnx = AppConfig.getConnectionStringCFN();
        DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
        ClienteDAO regCliente = null;
        PedidoDAO regPedido = null;

        try {
            regCliente = new ClienteDAO(dao);
            regPedido = new PedidoDAO(dao);


            // Llama al método crear de ClienteDAO
            Cliente cliente = new Cliente(ClienteDNI, Nombres, Apellidos, Telefono, Direccion, Referencia, Password);
            regCliente.crear(cliente);

            //Llamar al método InsertarPedido
            LocalDate fecha = LocalDate.now();
            LocalTime time = LocalTime.now();
            String Estado = "Realizado";
            Pedido pedido = new Pedido(0, ofertaID,ClienteDNI, cantidad, fecha, time, MetodoPago, Estado);
            int pedidoID = regPedido.InsertarPedido(pedido);



            BufferedImage bufferedImage2 = genQR(new URL("http://localhost:8080/verPedido?id="+ pedidoID).toString(),300,300);
            System.out.println(bufferedImage2);
            String strQrFile2 = System.getProperty("user.dir")+"\\src\\main\\resources\\web\\upload\\pedido"+pedidoID+".jpg";
            ImageIO.write(bufferedImage2,"jpg",new File(strQrFile2));

            resp.sendRedirect("/verPedido?id=" + pedidoID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Cierra la conexión
            if (regPedido != null || regCliente != null) {
                regPedido.cerrarConexion();
                regCliente.cerrarConexion();
            }
        }
    }
    public static BufferedImage genQR(String text, int width, int height) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bits =
                barcodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        return MatrixToImageWriter.toBufferedImage(bits);
    }
}
