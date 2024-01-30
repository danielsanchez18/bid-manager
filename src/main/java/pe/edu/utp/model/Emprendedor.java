package pe.edu.utp.model;

public class Emprendedor {
    private String dniEmprendedor;
    private String nombres;
    private String apellidos;
    private String nombreNegocio;
    private String ruc;
    private String logotipoNegocio;
    private String telefono;
    private String password;

    //Constructor vacío
    public Emprendedor() {
    }

    //Constructor completo
    public Emprendedor(String dniEmprendedor, String nombres, String apellidos, String nombreNegocio, String ruc, String logotipoNegocio, String telefono, String password) {
        this.dniEmprendedor = dniEmprendedor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombreNegocio = nombreNegocio;
        this.ruc = ruc;
        this.logotipoNegocio = logotipoNegocio;
        this.telefono = telefono;
        this.password = password;
    }

    //Getters & SetterS
    public String getDniEmprendedor() {
        return dniEmprendedor;
    }

    public void setDniEmprendedor(String dniEmprendedor) {
        this.dniEmprendedor = dniEmprendedor;
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

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getLogotipoNegocio() {
        return logotipoNegocio;
    }

    public void setLogotipoNegocio(String logotipoNegocio) {
        this.logotipoNegocio = logotipoNegocio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}