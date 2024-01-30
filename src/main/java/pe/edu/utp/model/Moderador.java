package pe.edu.utp.model;

public class Moderador {

    private String dniModerador;
    private String nombres;
    private String apellidos;
    private String password;

    //Constructor vacío
    public Moderador() {
    }

    //Constructor completo
    public Moderador(String dniModerador, String nombres, String apellidos, String password) {
        this.dniModerador = dniModerador;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.password = password;
    }

    //Getters & Setters
    public String getDniModerador() {
        return dniModerador;
    }

    public void setDniModerador(String dniModerador) {
        this.dniModerador = dniModerador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
