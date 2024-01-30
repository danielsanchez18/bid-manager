package pe.edu.utp.service;

import pe.edu.utp.interfaces.interfacesObligatorias;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.model.Emprendedor;
import pe.edu.utp.utils.DataAccess;
import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

public class EmprendedorDAO implements interfacesObligatorias<Emprendedor> {
    private final Connection c;

    public EmprendedorDAO(DataAccess dao) throws SQLException, NamingException {
        this.c = dao.getConnection();
    }
    @Override
    public Emprendedor filtrarPorID(String dniEmprendedor) {
        Emprendedor emprendedor = null;

        String statement = "CALL ObtenerEmprendedorPorDNI(?)";

        try {
            PreparedStatement preparedStatement = c.prepareStatement(statement);

            preparedStatement.setString(1, dniEmprendedor);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombres = resultSet.getString("nombres");
                String apellidos = resultSet.getString("apellidos");
                String nombreNegocio = resultSet.getString("nombreNegocio");
                String ruc = resultSet.getString("ruc");
                String logotipoNegocio = resultSet.getString("logotipoNegocio");
                String telefono = resultSet.getString("telefono");
                String password = resultSet.getString("password");
                emprendedor = new Emprendedor(dniEmprendedor, nombres, apellidos, nombreNegocio, ruc, logotipoNegocio, telefono, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return emprendedor;
    }

    @Override
    public List<Emprendedor> lector() {
        return null;
    }

    @Override
    public boolean crear(Emprendedor entity) throws SQLException {
        String statement = "CALL RegistrarEmprendedor(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = c.prepareStatement(statement);

        preparedStatement.setString(1,entity.getDniEmprendedor());
        preparedStatement.setString(2, entity.getNombres());
        preparedStatement.setString(3,entity.getApellidos());
        preparedStatement.setString(4,entity.getNombreNegocio());
        preparedStatement.setString(5, entity.getRuc());
        preparedStatement.setString(6, entity.getLogotipoNegocio());
        preparedStatement.setString(7, entity.getTelefono());
        preparedStatement.setString(8, entity.getPassword());

        int update = preparedStatement.executeUpdate();

        return (update > 0);
    }

    public boolean validarEmprendedor(String dniEmprendedor, String password) throws SQLException {
        String statement = "CALL ValidarEmprendedor(?, ?)";
        boolean userValid = false;

        try (CallableStatement callableStatement = c.prepareCall(statement)) {
            callableStatement.setString(1, dniEmprendedor);
            callableStatement.setString(2, password);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                // Verificar si el conjunto de resultados tiene alguna fila
                if (resultSet.next()) {
                    // Si hay resultados, el usuario es válido
                    userValid = true;
                }
            }
        }

        return userValid;
    }

    @Override
    public void actualizar(Emprendedor entity) {

    }

    @Override
    public void eliminar(Emprendedor entity) {

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
