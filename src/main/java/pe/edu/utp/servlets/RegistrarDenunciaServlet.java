package pe.edu.utp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.service.DenunciaDAO;
import pe.edu.utp.model.Denuncia;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/registrarDenuncia")
public class RegistrarDenunciaServlet extends HttpServlet {
    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    DenunciaDAO regDenuncia = null;

    Denuncia denuncia;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Obtener los parámetros de la VerOferta del formulario
        String dniModerador = "M1111111";
        int idOferta = Integer.parseInt(request.getParameter("OfertaID"));
        System.out.println("idOferta = " + idOferta);
        String dniCliente = request.getParameter("clienteDni");
        System.out.println("dniCliente = " + dniCliente);
        LocalDate fecha_denuncia = LocalDate.now();
        LocalTime hora_denuncia = LocalTime.now();
        String estado = "Registrada";
        String motivo = request.getParameter("motivo");
        System.out.println("motivo = " + motivo);


        denuncia = new Denuncia(0, dniModerador, idOferta, dniCliente, fecha_denuncia,
                hora_denuncia, estado, motivo);

        try {
            regDenuncia = new DenunciaDAO(dao);

            //Llamar al método RegistrarDenuncia
            regDenuncia.crear(denuncia);

            response.sendRedirect("/indexOfertas");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // Cierra la conexión
            if (regDenuncia != null) {
                regDenuncia.cerrarConexion();
            }
        }
    }

}
