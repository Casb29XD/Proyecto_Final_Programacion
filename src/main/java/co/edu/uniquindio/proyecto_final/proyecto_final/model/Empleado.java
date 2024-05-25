package co.edu.uniquindio.proyecto_final.proyecto_final.model;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona{
    List<Evento> evento = new ArrayList<>();

    public Empleado() {

    }

    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }
}
