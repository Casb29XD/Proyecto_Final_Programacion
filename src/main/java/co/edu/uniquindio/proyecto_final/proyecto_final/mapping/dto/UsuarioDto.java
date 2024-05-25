package co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto;

import co.edu.uniquindio.proyecto_final.proyecto_final.model.Reserva;

import java.util.List;

public record UsuarioDto(
    String id,
    String nombre,
    String correo,
    List<Reserva> reservaList){
}
