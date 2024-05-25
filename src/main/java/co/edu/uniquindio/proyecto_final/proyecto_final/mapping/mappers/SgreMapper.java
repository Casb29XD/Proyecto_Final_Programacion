package co.edu.uniquindio.proyecto_final.proyecto_final.mapping.mappers;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EventoDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Empleado;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Evento;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Reserva;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Usuario;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SgreMapper {
    SgreMapper INSTANCE = Mappers.getMapper(SgreMapper.class);
    @Named("empleadoToEmpleadoDto")
    EmpleadoDto empleadoToEmpleadoDto(Empleado empleado);

    Empleado empleadoDtoToEmpleado(EmpleadoDto empleadoDto);

    @IterableMapping(qualifiedByName = "empleadoToEmpleadoDto")
    List<EmpleadoDto> getEmpleadosDto(List<Empleado> listaEmpleados);

    //usuario
    @Named("usuarioToUsuarioDto")
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);

    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

    @IterableMapping(qualifiedByName = "usuarioToUsuarioDto")
    List<UsuarioDto> getUsuariosDto(List<Usuario> listaUsuarios);

    //Evento
    @Named("eventoToEventoDto")
    EventoDto eventoToEventoDto(Evento evento);

    Evento eventoDtoToEvento(EventoDto eventoDto);

    @IterableMapping(qualifiedByName = "eventoToEventoDto")
    List<EventoDto> getEventoDto(List<Evento> listaEventos);

    //Reserva
    @Named("reservaToReservaDto")
    ReservaDto reservaToReservaDto(Reserva reserva);

    Reserva reservaDtoToReserva(ReservaDto reservaDto);

    @IterableMapping(qualifiedByName = "reservaToReservaDto")
    List<ReservaDto> getReservaDto(List<Reserva> listaReservas);
}
