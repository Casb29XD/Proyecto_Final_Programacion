package co.edu.uniquindio.proyecto_final.proyecto_final.controller.service;

import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EventoDto;

import java.util.List;

public interface IEventoControllerService {
    List<EventoDto> obtenerEvento();
    boolean agregarEvento(EventoDto evento);
    boolean eliminarEvento(String id);
    boolean actualizarEvento(String id, EventoDto evento);
}
