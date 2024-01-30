package pe.edu.utp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pedido {

    private int idPedido;
    private int idOferta;
    private String dniCliente;
    private int cantidad;
    private LocalDate fechaPedido;
    private LocalTime horaPedido;
    private String metodoPago;
    private String estado;

    //Constructor vacío
    public Pedido() {
    }

    //Constructor completo
    public Pedido(int idPedido, int idOferta, String dniCliente, int cantidad, LocalDate fechaPedido, LocalTime horaPedido, String metodoPago, String estado) {
        this.idPedido = idPedido;
        this.idOferta = idOferta;
        this.dniCliente = dniCliente;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;
        this.horaPedido = horaPedido;
        this.metodoPago = metodoPago;
        this.estado = estado;
    }

    //Getters & Setters

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(LocalTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idOferta=" + idOferta +
                ", dniCliente='" + dniCliente + '\'' +
                ", cantidad=" + cantidad +
                ", fechaPedido=" + fechaPedido +
                ", horaPedido=" + horaPedido +
                ", metodoPago='" + metodoPago + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
