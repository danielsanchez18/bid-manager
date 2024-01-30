package pe.edu.utp.service;

import pe.edu.utp.interfaces.interfacesObligatorias;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.model.Oferta;
import pe.edu.utp.utils.DataAccess;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClienteDAO implements interfacesObligatorias<Cliente> {
    private final Connection cnn;

    public ClienteDAO(DataAccess dao) throws SQLException, NamingException {
        this.cnn = dao.getConnection();
    }
    @Override
    public Cliente filtrarPorID(String dniEmprendedor) {
        Cliente cliente = null;

        String statement = "CALL ObtenerClientePorDNI(?)";

        try {
            PreparedStatement preparedStatement = cnn.prepareStatement(statement);

            preparedStatement.setString(1, dniEmprendedor);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String dniCliente = resultSet.getString("dniCliente"); String dniEmprendedorv2 = resultSet.getString("dniEmprendedor");
                String nombres = resultSet.getString("nombres");
                String apellidos = resultSet.getString("apellidos");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                String referencia = resultSet.getString("referencia");
                String password = resultSet.getString("password");
                cliente = new Cliente(dniCliente, nombres, apellidos, telefono, direccion, referencia, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }

    @Override
    public List<Cliente> lector() {
        return null;
    }

    @Override
    public boolean crear(Cliente entity) throws SQLException {
        String statement = "CALL RegistrarCliente(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = cnn.prepareStatement(statement);

        preparedStatement.setString(1,entity.getDniCliente());
        preparedStatement.setString(2,entity.getNombres());
        preparedStatement.setString(3,entity.getApellidos());
        preparedStatement.setString(4, entity.getTelefono());
        preparedStatement.setString(5, entity.getDireccion());
        preparedStatement.setString(6, entity.getReferencia());
        preparedStatement.setString(7, entity.getPassword());

        int update = preparedStatement.executeUpdate();

        return (update > 0);
    }

    @Override
    public void actualizar(Cliente entity) {

    }

    @Override
    public void eliminar(Cliente entity) {

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
