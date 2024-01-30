package pe.edu.utp;

import jakarta.servlet.MultipartConfigElement;
import pe.edu.utp.service.*;
import pe.edu.utp.servlets.*;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccess;
import pe.edu.utp.utils.DataAccessMariaDB;
import pe.edu.utp.utils.JettyAdvUTP;

import java.net.URL;

/**
 * Hello world!
 *
 */
public class App {
    public static Auth authService = null;

    public static void main( String[] args ) throws Exception {
        String path = "src\\main\\resources\\web\\";
        DataAccess dao = new DataAccessMariaDB(AppConfig.getConnectionStringCFN());
        authService = new Auth(dao);

        JettyAdvUTP webserver = new JettyAdvUTP(8080,path);


        webserver.addServlet(IndexOfertasServlet.class, "/indexOfertas");
        webserver.addServlet(ComprarOfertaServlet.class, "/comprarOferta");
        webserver.addServlet(InsertarPedidoServlet.class, "/insertarPedido");
        webserver.addServlet(RegistrarPedidoDashboardServlet.class, "/registrarPedidoDashboard");
        webserver.addServlet(OfertasDashboardServlet.class, "/ofertasDashboard");
        webserver.addServlet(InsertarPedidoClienteServlet.class, "/insertarPedidoCliente");
        webserver.addServlet(VerPedidoServlet.class, "/verPedido");
        webserver.addServlet(PedidosPorFechayEstadoServlet.class, "/pedidosHoy");
        webserver.addServlet(PedidosPorClienteServlet.class, "/pedidosCliente");
        webserver.addServlet(PedidosPorClienteHoyServlet.class, "/pedidosclienteHoy");
        webserver.addServlet(EstadoPedidoServlet.class, "/estadoPedido");
        webserver.addServlet(RegistrarOfertaEmprendedorServlet.class, "/registrarOfertaEmprendedor").getRegistration().setMultipartConfig(new MultipartConfigElement("src\\main\\resources\\upload"));
        webserver.addServlet(VerOfertaServlet.class, "/verOferta");
        webserver.addServlet(LoginEmprendedorServlet.class, "/loginEmprendedor");
        webserver.addServlet(LoginClienteServlet.class, "/loginCliente");
        webserver.addServlet(LoginModeradorServlet.class, "/loginModerador");
        webserver.addServlet(ValidacionRegistroClienteServlet.class, "/validarRegistroCliente");
        webserver.addServlet(ValidacionRegistroEmprendedorServlet.class, "/validarRegistroEmprendedor").getRegistration().setMultipartConfig(new MultipartConfigElement("src\\main\\resources\\upload"));
        webserver.addServlet(ValidacionRegistroOfertaServlet.class, "/validarRegistroOferta").getRegistration().setMultipartConfig(new MultipartConfigElement("src\\main\\resources\\upload"));
        webserver.addServlet(RegistrarOfertaServlet.class, "/registrarOferta").getRegistration().setMultipartConfig(new MultipartConfigElement("src\\main\\resources\\upload"));
        webserver.addServlet(FormularioOfertaServlet.class, "/formularioOferta");
        webserver.addServlet(RegistrarClienteServlet.class, "/registrarCliente");
        webserver.addServlet(RegistrarEmprendedorServlet.class, "/registrarEmprendedor").getRegistration().setMultipartConfig(new MultipartConfigElement("src\\main\\resources\\upload"));
        webserver.addServlet(IngresoClienteServlet.class, "/ingresarCliente");
        webserver.addServlet(IngresoEmprendedorServlet.class, "/ingresoEmprendedor");
        webserver.addServlet(IngresoModerador.class, "/ingresoModerador");
        webserver.addServlet(DashboardClienteServlet.class, "/dashboardCliente");
        webserver.addServlet(DashboardEmprendedorServlet.class, "/dashboardEmprendedor");
        webserver.addServlet(DashboardModeradorServlet.class, "/dashboardModerador");
        webserver.addServlet(DenunciarOfertaServlet.class, "/denunciar");
        webserver.addServlet(RegistrarDenunciaServlet.class, "/registrarDenuncia");
        webserver.addServlet(LogoutServlet.class, "/logout");

        URL myURL = new URL("http://localhost:8080");
        System.out.println("*********************************************************");
        System.out.println("CLICK AQUI PARA ABRIR LA APLICACION:" + myURL);
        System.out.println("*********************************************************");
        webserver.start();

    }
}