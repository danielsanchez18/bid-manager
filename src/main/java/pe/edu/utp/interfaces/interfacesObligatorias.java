package pe.edu.utp.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface interfacesObligatorias<inter> {
    inter filtrarPorID(String id);
    List<inter> lector();
    boolean crear(inter entity) throws SQLException;
    void actualizar(inter entity);
    void eliminar(inter entity);
}
