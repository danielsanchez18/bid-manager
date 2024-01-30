package pe.edu.utp.service;

import pe.edu.utp.exceptions.InvalidUserException;
import pe.edu.utp.utils.AppConfig;
import pe.edu.utp.utils.DataAccess;
import pe.edu.utp.utils.ErrorLog;

import javax.naming.NamingException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    private final Connection cnn;

    public Auth(final DataAccess dao) throws SQLException, NamingException {
        cnn = dao.getConnection();
    }

    public boolean isValidUserEmprendedor(String dni, String password) throws SQLException, NamingException, IOException {
        String cnx = AppConfig.getConnectionStringCFN();
        String strSQL = String.format("CALL ValidarEmprendedor('%s','%s')",dni, password);
        ErrorLog.log(strSQL, ErrorLog.Level.INFO);
        ResultSet rst = cnn.createStatement().executeQuery(strSQL);
        if (rst.next()) {
            return true;
        } else {
            throw new InvalidUserException(String.format("Credenciales para usuario %s son invalidas", dni));
        }
    }

    public boolean isValidUserCliente(String dni, String password) throws SQLException, NamingException, IOException {
        String cnx = AppConfig.getConnectionStringCFN();
        String strSQL = String.format("CALL ValidarCliente('%s','%s')",dni, password);
        ErrorLog.log(strSQL, ErrorLog.Level.INFO);
        ResultSet rst = cnn.createStatement().executeQuery(strSQL);
        if (rst.next()) {
            return true;
        } else {
            throw new InvalidUserException(String.format("Credenciales para usuario %s son invalidas", dni));
        }
    }

    public boolean isValidUserModerador(String dni, String password) throws SQLException, NamingException, IOException {
        String cnx = AppConfig.getConnectionStringCFN();
        String strSQL = String.format("CALL ValidarModerador('%s','%s')",dni, password);
        ErrorLog.log(strSQL, ErrorLog.Level.INFO);
        ResultSet rst = cnn.createStatement().executeQuery(strSQL);
        if (rst.next()) {
            return true;
        } else {
            throw new InvalidUserException(String.format("Credenciales para usuario %s son invalidas", dni));
        }
    }

    public static String md5(String data) throws IOException {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            MessageDigest msg = (MessageDigest) md.clone();
            msg.update(data.getBytes());
            return byteArrayToHex(msg.digest());
        } catch (CloneNotSupportedException | NoSuchAlgorithmException e) {
            ErrorLog.log(e.getMessage(), ErrorLog.Level.ERROR);
            return data;
        }
    }

    /*
     * Link: https://stackoverflow.com/questions/9655181/java-convert-a-byte-array-to-a-hex-string
     * Nota: Metodo altetnativo para JDK17, pero se debe tener cuidado con tener este entorno activado
     * HexFormat hex = HexFormat.of();
     * hex.formatHex(someByteArray)
     * */
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
