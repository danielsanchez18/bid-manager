package pe.edu.utp.html;

import pe.edu.utp.model.Oferta;
import pe.edu.utp.service.OfertaDAO;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.TextUTP;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ListarOfertasHTML {

    String cnx = AppConfig.getConnectionStringCFN();
    DataAccessMariaDB dao = new DataAccessMariaDB(cnx);
    OfertaDAO ofertaDAO = null;
    public String getHTMLReport () throws IOException, SQLException, NamingException {

        ofertaDAO = new OfertaDAO(dao);
        List<Oferta> Ofertas = ofertaDAO.lector();
        List<Oferta> OfertasFechaVigencia = new ArrayList<>();

        for (Oferta oferta : Ofertas) {
            if(oferta.getEstado().equals("Disponible")){
                OfertasFechaVigencia.add(oferta);
                OfertasFechaVigencia.sort(oferta.OFERTA_COMPARATOR_NATURALORDER);
            }
        }



        // Cargar plantilla principal
        String filename = "src\\main\\resources\\web\\templates\\indexOfertas.html";
        String html = TextUTP.read(filename);

        // Cargar plantilla para los item
        String filename_items = "src\\main\\resources\\web\\templates\\indexOfertas_item.html";
        String html_item = TextUTP.read(filename_items);

        // Recorrer la lista
        StringBuilder items_html = new StringBuilder();

        //Número de Ofertas en la lista
        int nOf = Ofertas.size();

        String titulo ="OFERTAS DISPONIBLES";

        for (Oferta oferta : OfertasFechaVigencia) {
            String item = html_item
                    .replace("${Imagen}", oferta.getImagenOferta())
                    .replace("${NombreOferta}", oferta.getNombre())
                    .replace("${FechaInicio}", oferta.getFechaInicio().toString())
                    .replace("${FechaFin}", oferta.getFechaFin().toString())
                    .replace("${CantidadLimite}", Integer.toString(oferta.getCantidad()))
                    .replace("${Monto}", Float.toString(oferta.getPrecio()))
                    .replace("${Restricciones}", oferta.getRestricciones())
                    .replace("${ofertaID}", Integer.toString(oferta.getIdOferta()))
                    .replace("${estadoOferta}", oferta.getEstado());
            items_html.append(item);
        }

        // Reemplazar en la plantilla principal
        String reporte_html = html.replace("${items}", items_html.toString()).replace("${titulo}",titulo);
        reporte_html = reporte_html.replace("${nOf}", Integer.toString(nOf));

        if (ofertaDAO != null) {
            ofertaDAO.cerrarConexion();
        }
        return reporte_html;
    }
}
