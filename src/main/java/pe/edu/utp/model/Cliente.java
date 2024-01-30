package pe.edu.utp.model;

public class Cliente {

    private String dniCliente;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String referencia;
    private String password;

    //Constructor vacío
    public Cliente() {
    }

    //Constructor completo
    public Cliente(String dniCliente, String nombres, String apellidos, String telefono, String direccion, String referencia, String password) {
        this.dniCliente = dniCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.referencia = referencia;
        this.password = password;
    }

    //Getters & Setters
    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
