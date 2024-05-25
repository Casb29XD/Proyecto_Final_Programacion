package co.edu.uniquindio.proyecto_final.proyecto_final.model.services;

import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.ReservaException;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Reserva;

import java.util.ArrayList;

public interface IReservaService {
    public Reserva crearReserva(String id, String usuario, String evento, String fechaSolicitud, String estado) throws ReservaException;
    public Boolean eliminarReserva(String id)throws ReservaException;
    boolean actualizarReserva(String id, Reserva reserva) throws ReservaException;
    public boolean  verificarReservaExistente(String id) throws ReservaException;
    public Reserva obtenerReserva(String id);
    public ArrayList<Reserva> obtenerReservas();
}
