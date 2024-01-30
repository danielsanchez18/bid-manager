package pe.edu.utp.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Denuncia {
    int idDenuncia;

    String dniModerador;
    int idOferta;
    String dniCliente;

    LocalDate fecha_denuncia;
    LocalTime hora_denuncia;
    String estado;
    String Motivo;

    public Denuncia() {
    }

    public Denuncia(int idDenuncia, String dniModerador, int idOferta,
                    String dniCliente, LocalDate fecha_denuncia, LocalTime hora_denuncia,
                    String estado, String motivo) {
        this.idDenuncia = idDenuncia;
        this.dniModerador = dniModerador;
        this.idOferta = idOferta;
        this.dniCliente = dniCliente;
        this.fecha_denuncia = fecha_denuncia;
        this.hora_denuncia = hora_denuncia;
        this.estado = estado;
        Motivo = motivo;
    }

    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getDniModerador() {
        return dniModerador;
    }

    public void setDniModerador(String dniModerador) {
        this.dniModerador = dniModerador;
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

    public LocalDate getFecha_denuncia() {
        return fecha_denuncia;
    }

    public void setFecha_denuncia(LocalDate fecha_denuncia) {
        this.fecha_denuncia = fecha_denuncia;
    }

    public LocalTime getHora_denuncia() {
        return hora_denuncia;
    }

    public void setHora_denuncia(LocalTime hora_denuncia) {
        this.hora_denuncia = hora_denuncia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Denuncia denuncia = (Denuncia) o;
        return idDenuncia == denuncia.idDenuncia && dniModerador == denuncia.dniModerador && idOferta == denuncia.idOferta && Objects.equals(dniCliente, denuncia.dniCliente) && Objects.equals(fecha_denuncia, denuncia.fecha_denuncia) && Objects.equals(hora_denuncia, denuncia.hora_denuncia) && Objects.equals(estado, denuncia.estado) && Objects.equals(Motivo, denuncia.Motivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDenuncia, dniModerador, idOferta, dniCliente, fecha_denuncia, hora_denuncia, estado, Motivo);
    }

    @Override
    public String toString() {
        return "Denuncia{" +
                "idDenuncia=" + idDenuncia +
                ", dniModerador=" + dniModerador +
                ", idOferta=" + idOferta +
                ", dniCliente='" + dniCliente + '\'' +
                ", fecha_denuncia=" + fecha_denuncia +
                ", hora_denuncia=" + hora_denuncia +
                ", estado='" + estado + '\'' +
                ", Motivo='" + Motivo + '\'' +
                '}';
    }
}
