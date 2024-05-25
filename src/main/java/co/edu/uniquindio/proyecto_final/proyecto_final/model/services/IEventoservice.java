package co.edu.uniquindio.proyecto_final.proyecto_final.model.services;


import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.EventoExceprion;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Evento;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Reserva;

import java.util.ArrayList;
import java.util.List;

public interface IEventoservice {
    public Evento crearEvento(String id,String nombre, String descripcion, String fecha, String capacidadMaxima, String empleado, List<Reserva> reservaList) throws EventoExceprion;
    public Boolean eliminarEvento(String id)throws EventoExceprion;
    boolean actualizarEvento(String id, Evento evento) throws EventoExceprion;
    public boolean  verificarEventoExistente(String id) throws EventoExceprion;
    public Evento obtenerEvento(String id);
    public ArrayList<Evento> obtenerEvento();
}
