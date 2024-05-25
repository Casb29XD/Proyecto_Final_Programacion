package co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto;

import co.edu.uniquindio.proyecto_final.proyecto_final.model.Empleado;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Reserva;

import java.util.Date;
import java.util.List;

public record EventoDto(String id,
    String nombre,
    String descripcion,
    String fecha,
    String capacidadMaxima,
    String empleado,
    List<Reserva> reservaList
) {}
