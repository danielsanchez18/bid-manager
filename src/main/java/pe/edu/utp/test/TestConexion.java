package pe.edu.utp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexion {

    private static final String URL = "jdbc:mariadb://127.0.0.1:3307/ofertas?user=ofertas&password=123456&connectTimeout=1000&maxPoolSize=5&pool";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void cerrarConexion(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection conexion = obtenerConexion();
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos");
                cerrarConexion(conexion);
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar conectar a la base de datos");
            e.printStackTrace();
        }
    }
}
