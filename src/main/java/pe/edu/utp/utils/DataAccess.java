package pe.edu.utp.utils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataAccess {
    Connection getConnection() throws SQLException, NamingException;
    ResultSet querySQL(String sql) throws SQLException;
    void closeConnection() throws SQLException;
}
