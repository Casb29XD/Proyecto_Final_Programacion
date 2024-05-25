package co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto;

import co.edu.uniquindio.proyecto_final.proyecto_final.model.Evento;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Usuario;

import java.util.Date;

public record ReservaDto(
        String id,
    String usuario,
    String evento,
    String fechaSolicitud,
    String estado
) {}
