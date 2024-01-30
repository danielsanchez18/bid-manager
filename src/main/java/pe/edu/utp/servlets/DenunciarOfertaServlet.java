package pe.edu.utp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/denunciar")
public class DenunciarOfertaServlet extends HttpServlet {
    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    OfertaDAO obteneroferta = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ofertaID = request.getParameter("ofertaID");

        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(getHTMLReport(ofertaID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHTMLReport (String OfertaID) throws IOException, SQLException, NamingException {
        obteneroferta = new OfertaDAO(dao);
        Oferta oferta = obteneroferta.ObtenerOfertaPorID(Integer.parseInt(OfertaID));


        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\templates\\denuncia.html";
        String html = TextUTP.read(filename);

        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${OfertaID}",OfertaID);

        if (obteneroferta != null) {
            obteneroferta.cerrarConexion();
        }
        return reporte_html;
    }
}
