package pe.edu.utp.service;

import pe.edu.utp.interfaces.interfacesObligatorias;
import pe.edu.utp.model.Pedido;
import pe.edu.utp.utils.DataAccess;
import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class PedidoDAO implements interfacesObligatorias<Pedido> {

    private final Connection cnn;

    public PedidoDAO(DataAccess dao) throws SQLException, NamingException {
        this.cnn = dao.getConnection();
    }

    public List<Pedido> ObtenerPedidosPorDniCliente(String dniCliente) {
        List<Pedido> pedidosPorCliente = new LinkedList<>();
        String q = "CALL ObtenerPedidosPorDniCliente(?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(q);

            preparedStatement.setString(1, dniCliente);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPedido = Integer.parseInt(resultSet.getString("idPedido"));
                int idOferta = Integer.parseInt(resultSet.getString("idOferta"));
                String dniClientev2 = resultSet.getString("dniCliente");
                int cantidad = Integer.parseInt(resultSet.getString("cantidad"));
                DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaPedido = LocalDate.parse(resultSet.getString("fechaPedido"), fecha);
                DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime horaPedido = LocalTime.parse(resultSet.getString("horaPedido"), hora);
                String metodoPago = resultSet.getString("metodoPago");
                String estado = resultSet.getString("estado");
                Pedido pedido = new Pedido(idPedido, idOferta, dniClientev2, cantidad, fechaPedido, horaPedido, metodoPago, estado);
                pedidosPorCliente.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosPorCliente;
    }

    public List<Pedido> ObtenerPedidosPorDniClienteFecha(String dniCliente, LocalDate fecha) {
        List<Pedido> pedidosPorClienteHoy = new LinkedList<>();

        String q = "CALL ObtenerPedidosPorDniClienteFecha(?, ?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(q);

            preparedStatement.setString(1, dniCliente);
            preparedStatement.setString(2, fecha.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPedido = Integer.parseInt(resultSet.getString("idPedido"));
                int idOferta = Integer.parseInt(resultSet.getString("idOferta"));
                String dniClientev2 = resultSet.getString("dniCliente");
                int cantidad = Integer.parseInt(resultSet.getString("cantidad"));
                DateTimeFormatter fechaDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaPedido = LocalDate.parse(resultSet.getString("fechaPedido"), fechaDTF);
                DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime horaPedido = LocalTime.parse(resultSet.getString("horaPedido"), hora);
                String metodoPago = resultSet.getString("metodoPago");
                String estado = resultSet.getString("estado");
                Pedido pedido = new Pedido(idPedido, idOferta, dniClientev2, cantidad, fechaPedido, horaPedido, metodoPago, estado);
                pedidosPorClienteHoy.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosPorClienteHoy;
    }

    public List<Pedido> ObtenerPedidosDisponiblesPorDniCliente(String dniCliente) {
        List<Pedido> pedidosPorCliente = new LinkedList<>();
        String statement = "CALL ObtenerPedidosActivosPorDniCliente(?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(statement);

            preparedStatement.setString(1, dniCliente);

            ResultSet resulSet = preparedStatement.executeQuery();

            while (resulSet.next()) {
                int idPedido = Integer.parseInt(resulSet.getString("idPedido"));
                int idOferta = Integer.parseInt(resulSet.getString("idOferta"));
                String dniClientev2 = resulSet.getString("dniCliente");
                int cantidad = Integer.parseInt(resulSet.getString("cantidad"));
                DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaPedido = LocalDate.parse(resulSet.getString("fechaPedido"), fecha);
                DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime horaPedido = LocalTime.parse(resulSet.getString("horaPedido"), hora);
                String metodoPago = resulSet.getString("metodoPago");
                String estado = resulSet.getString("estado");
                Pedido pedido = new Pedido(idPedido, idOferta, dniClientev2, cantidad, fechaPedido, horaPedido, metodoPago, estado);
                pedidosPorCliente.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosPorCliente;
    }

    public List<Pedido> ObtenerPedidosPorOfertasDisponibles(String dniCliente) {
        List<Pedido> pedidosPorCliente = new LinkedList<>();
        String statement = "CALL ObtenerPedidosPorOfertasDisponibles(?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(statement);

            preparedStatement.setString(1, dniCliente);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPedido = Integer.parseInt(resultSet.getString("idPedido"));
                int idOferta = Integer.parseInt(resultSet.getString("idOferta"));
                String dniClientev2 = resultSet.getString("dniCliente");
                int cantidad = Integer.parseInt(resultSet.getString("cantidad"));
                DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaPedido = LocalDate.parse(resultSet.getString("fechaPedido"), fecha);
                DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime horaPedido = LocalTime.parse(resultSet.getString("horaPedido"), hora);
                String metodoPago = resultSet.getString("metodoPago");
                String estado = resultSet.getString("estado");
                Pedido pedido = new Pedido(idPedido, idOferta, dniClientev2, cantidad, fechaPedido, horaPedido, metodoPago, estado);
                pedidosPorCliente.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosPorCliente;
    }

    public List<Pedido> ObtenerPedidosPorDniEmprendedor(String dniEmprendedor) {
        List<Pedido> pedidosPorEmprendedor = new LinkedList<>();
        String q = "CALL ObtenerPedidosPorDniEmprendedor(?)";
        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(q);

            preparedStatement.setString(1, dniEmprendedor);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPedido = Integer.parseInt(resultSet.getString("idPedido"));
                int idOferta = Integer.parseInt(resultSet.getString("idOferta"));
                String dniCliente = resultSet.getString("dniCliente");
                int cantidad = Integer.parseInt(resultSet.getString("cantidad"));
                DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaPedido = LocalDate.parse(resultSet.getString("fechaPedido"), fecha);
                DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime horaPedido = LocalTime.parse(resultSet.getString("horaPedido"), hora);
                String metodoPago = resultSet.getString("metodoPago");
                String estado = resultSet.getString("estado");
                Pedido pedido = new Pedido(idPedido, idOferta, dniCliente, cantidad, fechaPedido, horaPedido, metodoPago, estado);
                pedidosPorEmprendedor.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosPorEmprendedor;
    }
    @Override
    public Pedido filtrarPorID(String id) {
        return null;
    }

    @Override
    public List<Pedido> lector() {
        return null;
    }

    @Override
    public boolean crear(Pedido entity) throws SQLException {
        return false;
    }


    public int InsertarPedido(Pedido pedido) throws SQLException {
        String statement = "CALL InsertarPedido(?, ?, ?, ?, ?, ?, ?)";
        int pedidoID = 0;

        PreparedStatement preparedStatement = cnn.prepareStatement(statement);

        preparedStatement.setInt(1,pedido.getIdOferta());
        preparedStatement.setString(2,pedido.getDniCliente());
        preparedStatement.setInt(3,pedido.getCantidad());
        preparedStatement.setDate(4, Date.valueOf(pedido.getFechaPedido()));
        preparedStatement.setTime(5, Time.valueOf(pedido.getHoraPedido()));
        preparedStatement.setString(6, pedido.getMetodoPago());
        preparedStatement.setString(7, pedido.getEstado());

        preparedStatement.execute();

        ResultSet rs = preparedStatement.getResultSet();
        if (rs.next()) {
            pedidoID = rs.getInt("idPedido");
        }
        System.out.println("pedidoID = " + pedidoID);
        return pedidoID;
    }

    public Pedido ObtenerPedidoPorID(int PedidoID) throws SQLException {
        Pedido pedido = null;
        String sql = String.format("CALL ObtenerPedidoPorID('%s')", PedidoID);

        try{
            ResultSet rst = cnn.createStatement().executeQuery(sql);
            while (rst.next()) {
                int idPedido = rst.getInt("idPedido");
                int idOferta = rst.getInt("idOferta");
                String dniCliente = rst.getString("dniCliente");
                int cantidad = rst.getInt("cantidad");
                LocalDate fechaPedido = rst.getDate("fechaPedido").toLocalDate();
                LocalTime horaPedido = rst.getTime("horaPedido").toLocalTime();
                String metodoPago = rst.getString("metodoPago");
                String Estado = rst.getString("Estado");

                pedido = new Pedido(idPedido, idOferta, dniCliente, cantidad, fechaPedido, horaPedido, metodoPago, Estado);

            }
        } catch (SQLException e) {
            String msg = String.format("Ocurrió una excepción SQL: %s", e.getMessage());
            throw new SQLException(msg);
        }
        return pedido;
    }

    @Override
    public void actualizar(Pedido entity) {

    }

    public void actualizarEstado(int PedidoID, String Estado) throws SQLException {

        String statement = "CALL ActualizarEstadoPedido(?, ?)";

        PreparedStatement preparedStatement = cnn.prepareStatement(statement);

        preparedStatement.setInt(1,PedidoID);
        preparedStatement.setString(2,Estado);

        preparedStatement.execute();

    }

    @Override
    public void eliminar(Pedido entity) {

    }

    public List<Pedido> ListarPedidosPorEmprendedorEstadoYFecha(String dniCliente, String Estado, LocalDate fechaPedido) {
        List<Pedido> pedidosPorClienteEstadoYFecha = new LinkedList<>();
        String q = "CALL ListarPedidosPorEmprendedorEstadoYFecha(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(q);

            preparedStatement.setString(1, dniCliente);
            preparedStatement.setString(2, Estado);
            preparedStatement.setString(3, fechaPedido.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idPedido = Integer.parseInt(resultSet.getString("idPedido"));
                int idOferta = Integer.parseInt(resultSet.getString("idOferta"));
                String dniClientev2 = resultSet.getString("dniCliente");
                int cantidad = Integer.parseInt(resultSet.getString("cantidad"));
                DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate FechaPedido = LocalDate.parse(resultSet.getString("fechaPedido"), fecha);
                DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime horaPedido = LocalTime.parse(resultSet.getString("horaPedido"), hora);
                String metodoPago = resultSet.getString("metodoPago");
                String estado = resultSet.getString("estado");
                Pedido pedido = new Pedido(idPedido, idOferta, dniClientev2, cantidad, FechaPedido, horaPedido, metodoPago, estado);
                pedidosPorClienteEstadoYFecha.add(pedido);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedidosPorClienteEstadoYFecha;
    }

    public void cerrarConexion() {
        try {
            if (cnn != null && !cnn.isClosed()) {
                cnn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
