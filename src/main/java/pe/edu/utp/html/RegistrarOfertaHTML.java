package pe.edu.utp.html;

import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.TextUTP;
import java.io.IOException;

public class RegistrarOfertaHTML {
    public static String showRegistrarOferta(String id) throws IOException {
        String templatePath = AppConfig.getRegistrarOferta();
        String html = TextUTP.read(templatePath);
        return html.replace("${id}", id);
    }
}
