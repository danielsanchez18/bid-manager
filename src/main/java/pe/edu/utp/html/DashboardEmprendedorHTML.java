package pe.edu.utp.html;

import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;

public class DashboardEmprendedorHTML {
    public static String showDashboard(String username) throws IOException {
        // Se usara la plantilla dashboard.html
        // Tags a reemplazar en esta plantilla:
        //     ${username} -> Aqui va el nombre del usuario

        String templateFile = AppConfig.getDashboardEmprendedor();
        String html = TextUTP.read(templateFile);

        return html.replace("${username}", username);
    }

}
