package pe.edu.utp.utils;

import java.util.ResourceBundle;

public class AppConfig {
    static ResourceBundle rb = ResourceBundle.getBundle("config");
    public static String getConnectionStringCFN(){
        return rb.getString("conectionString");
    }
    public static String getTemplateDir(){
        return rb.getString("template_dir");
    }
    public static String getRegistrarOferta() {
        return getTemplateDir() + "\\registrarOferta.html";
    }
    public static String getRegistroEmprendedor() {
        return getTemplateDir() + "\\registrarEmprendedor.html";
    }

    public static String getRegistroCliente() {
        return getTemplateDir() + "\\registrarCliente.html";
    }

    public static String getIngresuCliente() {
        return getTemplateDir() + "\\loginCliente.html";
    }
    public static String getIngresoEmprendedor() {
        return getTemplateDir() + "\\loginEmprendedor.html";
    }

    public static String getIngresoModerador() {
        return getTemplateDir() + "\\loginModerador.html";
    }
    public static String getDashboardCliente() { return getTemplateDir() + "\\dashboardCliente.html";}
    public static String getDashboardEmprendedor() { return getTemplateDir() + "\\dashboardEmprendedor.html";}
    public static String getDashboardModerador() { return getTemplateDir() + "\\dashboardModerador.html";}
    public static String getGenericErrorTemplate(){
        return rb.getString("errorLog");
    }
    public static String getInvalidUserTemplate() {
        return getTemplateDir() + "\\invalid_user.html";
    }


}
