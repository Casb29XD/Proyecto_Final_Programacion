package co.edu.uniquindio.proyecto_final.proyecto_final.model;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
    String id;
    String nombre;
    String descripcion;
    String fecha;
    String capacidadMaxima;
    String empleado;
    ArrayList<Reserva> reserva;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(String capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public ArrayList<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(ArrayList<Reserva> reserva) {
        this.reserva = reserva;
    }
}
