package co.edu.uniquindio.proyecto_final.proyecto_final.controller.service;

import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;

import java.util.List;

public interface IReservaControllerService {
    List<ReservaDto> obtenerReserva();
    boolean agregarReserva(ReservaDto reserva);
    boolean eliminarReserva(String id);
    boolean actualizarReserva(String id, ReservaDto reserva);
}
