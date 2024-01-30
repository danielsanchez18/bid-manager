package pe.edu.utp.service;

import pe.edu.utp.interfaces.interfacesObligatorias;
import pe.edu.utp.model.Cliente;
import pe.edu.utp.model.Denuncia;
import pe.edu.utp.utils.DataAccess;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DenunciaDAO implements interfacesObligatorias<Denuncia> {
    private final Connection c;

    public DenunciaDAO(DataAccess dao) throws SQLException, NamingException {
        this.c = dao.getConnection();
    }

    @Override
    public Denuncia filtrarPorID(String id) {
        return null;
    }

    @Override
    public List<Denuncia> lector() {
        return null;
    }

    @Override
    public boolean crear(Denuncia entity) throws SQLException {
        String statement = "CALL InsertarDenuncia(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = c.prepareStatement(statement);

        preparedStatement.setString(1, entity.getDniModerador());
        preparedStatement.setInt(2,entity.getIdOferta());
        preparedStatement.setString(3,entity.getDniCliente());
        preparedStatement.setString(4, entity.getFecha_denuncia().toString());
        preparedStatement.setString(5, entity.getHora_denuncia().toString());
        preparedStatement.setString(6, entity.getEstado());
        preparedStatement.setString(7, entity.getMotivo());

        int update = preparedStatement.executeUpdate();

        return (update > 0);
    }

    @Override
    public void actualizar(Denuncia entity) {

    }

    @Override
    public void eliminar(Denuncia entity) {

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
