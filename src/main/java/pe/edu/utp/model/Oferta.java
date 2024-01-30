package pe.edu.utp.model;

import java.time.LocalDate;
import java.util.Comparator;

public class Oferta implements Comparable<Oferta>{

    public static final Comparator<Oferta> OFERTA_COMPARATOR_NATURALORDER = Comparator.comparing
            (Oferta::getFechaInicio).thenComparing(Oferta::getFechaFin).thenComparing(Oferta::getNombre);
    private int idOferta;
    private String dniEmprendedor;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int cantidad;
    private float precio;
    private String imagenOferta;
    private String qrCode;
    private String restricciones;
    private String estado;

    //Constructor vacío
    public Oferta() {
    }

    //Constructor completo


    public Oferta(int idOferta, String dniEmprendedor, String nombre, String descripcion,
                  LocalDate fechaInicio, LocalDate fechaFin, int cantidad, float precio,
                  String imagenOferta, String qrCode, String restricciones, String estado) {
        this.idOferta = idOferta;
        this.dniEmprendedor = dniEmprendedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
        this.precio = precio;
        this.imagenOferta = imagenOferta;
        this.qrCode = qrCode;
        this.restricciones = restricciones;
        this.estado = estado;
    }

    //Getters & Setters
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getDniEmprendedor() {
        return dniEmprendedor;
    }

    public void setDniEmprendedor(String dniEmprendedor) {
        this.dniEmprendedor = dniEmprendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagenOferta() {
        return imagenOferta;
    }

    public void setImagenOferta(String imagenOferta) {
        this.imagenOferta = imagenOferta;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Oferta o) {
        return OFERTA_COMPARATOR_NATURALORDER.compare(this,o);
    }
}
