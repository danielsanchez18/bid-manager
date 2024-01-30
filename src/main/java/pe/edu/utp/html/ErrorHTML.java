package pe.edu.utp.html;

import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.TextUTP;
import java.io.IOException;

public class ErrorHTML {
    public static String genericError(String msg) throws IOException {
        // Se usara la plantilla error.html
        // Tags a reemplazar en esta plantilla:
        //     ${error_detail} -> Aqui va el mensaje de error

        String templateFile = AppConfig.getGenericErrorTemplate();
        String html = TextUTP.read(templateFile);

        return html.replace("${msg}", msg);

    }

    public static String invalidUserError(String msg) throws IOException {
        // Se usara la plantilla invalid_user.html
        // Tags a reemplazar en esta plantilla:
        //     ${error_detail} -> Aqui va el mensaje de error

        String templateFile = AppConfig.getInvalidUserTemplate();
        String html = TextUTP.read(templateFile);

        return html.replace("${error_detail}", msg);

    }
}