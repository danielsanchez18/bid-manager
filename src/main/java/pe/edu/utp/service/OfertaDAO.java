package pe.edu.utp.service;


import pe.edu.utp.interfaces.interfacesObligatorias;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.model.Pedido;
import pe.edu.utp.utils.DataAccess;
import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class OfertaDAO implements interfacesObligatorias<Oferta> {
    private final Connection c;

    public OfertaDAO(DataAccess dao) throws SQLException, NamingException {
        this.c = dao.getConnection();
    }


    @Override
    public Oferta filtrarPorID(String id) {
        return null;
    }

    @Override
    public List<Oferta> lector() {

        List<Oferta> ofertas = new LinkedList<>();

        String statement = "CALL ObtenerOfertas()";

        try {
            ResultSet resultSet = c.createStatement().executeQuery(statement);
            int count = 0;
            while (resultSet.next()) {
                int idOferta = resultSet.getInt("idOferta");
                String dniEmprendedor = resultSet.getString("dniEmprendedor");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicio = LocalDate.parse(resultSet.getString("fechaInicio"), formatter);
                LocalDate fechaFin = LocalDate.parse(resultSet.getString("fechaFin"), formatter);
                int cantidad = resultSet.getInt("cantidad");
                float precio = resultSet.getFloat("precio");
                String imagenOferta = resultSet.getString("imagenOferta");
                String qrCode = resultSet.getString("qrCode");
                String restricciones = resultSet.getString("restricciones");
                String estado = resultSet.getString("estado");
                Oferta oferta = new Oferta(idOferta, dniEmprendedor, nombre, descripcion, fechaInicio, fechaFin, cantidad, precio, imagenOferta, qrCode, restricciones, estado);
                ofertas.add(oferta);
                count++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ofertas;
    }

    public int InsertarOferta(Oferta entity) throws SQLException {
        String q = "CALL RegistrarOferta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int ofertaid = 0;

        PreparedStatement ps = c.prepareStatement(q);

        ps.setString(1,entity.getDniEmprendedor());
        ps.setString(2,entity.getNombre());
        ps.setString(3,entity.getDescripcion());
        ps.setDate(4, Date.valueOf(entity.getFechaInicio()));
        ps.setDate(5, Date.valueOf(entity.getFechaFin()));
        ps.setInt(6, entity.getCantidad());
        ps.setDouble(7, entity.getPrecio());
        ps.setString(8, entity.getImagenOferta());
        ps.setString(9, entity.getQrCode());
        ps.setString(10, entity.getRestricciones());
        ps.setString(11, entity.getEstado());

        ps.execute();

        ResultSet rs = ps.getResultSet();
        if (rs.next()) {
            ofertaid = rs.getInt("idOferta");
        }
        System.out.println("ofertaID = " + ofertaid);
        return ofertaid;
    }


    public List<Oferta> ObtenerOfertasPorDniEmprendedor(String dniEmprendedor) {
        List<Oferta> ofertasPorDniEmprendedor = new LinkedList<>();
        String statement = "CALL ObtenerOfertasPorDniEmprendedor(?)";
        try {
            PreparedStatement preparedStatement = c.prepareStatement(statement);

            preparedStatement.setString(1, dniEmprendedor);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idOferta = resultSet.getInt("idOferta");
                String dniEmprendedorv2 = resultSet.getString("dniEmprendedor");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaInicio = LocalDate.parse(resultSet.getString("fechaInicio"), formatter);
                LocalDate fechaFin = LocalDate.parse(resultSet.getString("fechaFin"), formatter);
                int cantidad = resultSet.getInt("cantidad");
                float precio = resultSet.getFloat("precio");
                String imagenOferta = resultSet.getString("imagenOferta");
                String qrCode = resultSet.getString("qrCode");
                String restricciones = resultSet.getString("restricciones");
                String estado = resultSet.getString("estado");
                Oferta o = new Oferta(idOferta, dniEmprendedorv2, nombre, descripcion, fechaInicio, fechaFin, cantidad, precio, imagenOferta, qrCode, restricciones, estado);
                ofertasPorDniEmprendedor.add(o);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ofertasPorDniEmprendedor;
    }



    @Override
    public void actualizar(Oferta entity) {

    }

    @Override
    public void eliminar(Oferta entity) {

    }

    public Oferta ObtenerOfertaPorID(int OfertaID) throws SQLException {
        Oferta oferta = null;
        String sql = String.format("CALL ObtenerOfertaPorID('%s')", OfertaID);

        try{
            ResultSet rst = c.createStatement().executeQuery(sql);
            while (rst.next()) {
                int ofertaID = rst.getInt("idOferta");
                String emprendedorDNI = rst.getString("dniEmprendedor");
                String nombreOferta = rst.getString("nombre");
                String descripcion = rst.getString("descripcion");
                LocalDate fechaInicio = rst.getDate("fechaInicio").toLocalDate();
                LocalDate fechaFin = rst.getDate("fechaFin").toLocalDate();
                int cantidad = rst.getInt("cantidad");
                float precio = rst.getFloat("precio");
                String imagen = rst.getString("imagenOferta");
                String qrCode = "pedido"+ofertaID+"qr.jpg";
                String restricciones = rst.getString("restricciones");
                String estadoOferta = rst.getString("estado");

                oferta = new Oferta(ofertaID, emprendedorDNI, nombreOferta, descripcion, fechaInicio, fechaFin, cantidad,
                        precio, imagen, qrCode, restricciones, estadoOferta);

            }
        } catch (SQLException e) {
            String msg = String.format("Ocurrió una excepción SQL: %s", e.getMessage());
            throw new SQLException(msg);
        }
        return oferta;
    }

    @Override
    public boolean crear(Oferta entity) throws SQLException {
        return false;
    }

    public void cerrarConexion() {
        try {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}


