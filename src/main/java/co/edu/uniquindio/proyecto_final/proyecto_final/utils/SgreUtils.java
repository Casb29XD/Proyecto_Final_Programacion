package co.edu.uniquindio.proyecto_final.proyecto_final.utils;

import co.edu.uniquindio.proyecto_final.proyecto_final.model.*;

import java.util.ArrayList;
import java.util.Date;

public class SgreUtils {

    public static Sgre InicializarDatos(){
        Sgre sgre = new Sgre();
        Evento evento = new Evento();
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan Pérez");
        empleado.setId("34892");
        empleado.setCorreo("juan.perez@Gmail.com");
        empleado.setEvento(new ArrayList<>());
        sgre.getEmpleados().add(empleado);
        //evento
        evento.setId("EVT001");
        evento.setNombre("Conferencia de Tecnología");
        evento.setDescripcion("Una conferencia sobre las últimas tendencias en tecnología.");
        evento.setFecha("2024/10/8"); // 10 de julio de 2024
        evento.setCapacidadMaxima("200");
        evento.setEmpleado(empleado.getNombre());
        evento.setReserva(new ArrayList<>());
        sgre.getEventos().add(evento);
        evento = new Evento();

        empleado = new Empleado();
        empleado.setNombre("Ana García");
        empleado.setId("10273");
        empleado.setCorreo("ana.garcia85@gmail.com");
        empleado.setEvento(new ArrayList<>());
        sgre.getEmpleados().add(empleado);
        //evento
        evento.setId("EVT002");
        evento.setNombre("Taller de Marketing Digital");
        evento.setDescripcion("Un taller intensivo sobre estrategias de marketing digital.");
        evento.setFecha("2024/7/15"); // 15 de agosto de 2024
        evento.setCapacidadMaxima("50");
        evento.setEmpleado(empleado.getNombre());
        evento.setReserva(new ArrayList<>());
        sgre.getEventos().add(evento);
        evento = new Evento();

        empleado = new Empleado();
        empleado.setNombre("Javier Martínez");
        empleado.setId("45781");
        empleado.setCorreo(" javier.martinez92@gmail.com");
        empleado.setEvento(new ArrayList<>());
        sgre.getEmpleados().add(empleado);
        //evento
        evento.setId("EVT005");
        evento.setNombre("Festival de Música");
        evento.setDescripcion("Festival con la participación de bandas locales e internacionales.");
        evento.setFecha("2024/10/30"); // 30 de noviembre de 2024
        evento.setCapacidadMaxima("500");
        evento.setEmpleado(empleado.getNombre());
        evento.setReserva(new ArrayList<>());
        sgre.getEventos().add(evento);

        empleado = new Empleado();
        empleado.setNombre("María Rodríguez");
        empleado.setId("30894");
        empleado.setCorreo("m.rodriguez78@gmail.com");
        empleado.setEvento(new ArrayList<>());
        sgre.getEmpleados().add(empleado);

        empleado = new Empleado();
        empleado.setNombre("Pablo López");
        empleado.setId("81537");
        empleado.setCorreo("pablo.lopez89@gmail.com");
        empleado.setEvento(new ArrayList<>());
        sgre.getEmpleados().add(empleado);

        // usuario
        Usuario usuario = new Usuario();
        usuario.setNombre("María González");
        usuario.setId("12345");
        usuario.setCorreo("maria.gonzalez@example.com");
        usuario.setReserva(new ArrayList<>());
        sgre.getUsuarios().add(usuario);

        usuario = new Usuario();
        usuario.setNombre("Carlos López");
        usuario.setId("67890");
        usuario.setCorreo("carlos.lopez@example.com");
        usuario.setReserva(new ArrayList<>());
        sgre.getUsuarios().add(usuario);

        usuario= new Usuario();
        usuario.setNombre("Ana Rodríguez");
        usuario.setId("54321");
        usuario.setCorreo("ana.rodriguez@example.com");
        usuario.setReserva(new ArrayList<>());
        sgre.getUsuarios().add(usuario);

        usuario= new Usuario();
        usuario.setNombre("Juan Martínez");
        usuario.setId("98765");
        usuario.setCorreo("juan.martinez@example.com");
        usuario.setReserva(new ArrayList<>());
        sgre.getUsuarios().add(usuario);

        usuario= new Usuario();
        usuario.setNombre("Laura Pérez");
        usuario.setId("11223");
        usuario.setCorreo("laura.perez@example.com");
        usuario.setReserva(new ArrayList<>());
        sgre.getUsuarios().add(usuario);

        Reserva reserva = new Reserva();
        reserva.setId("312470");
        reserva.setUsuario(usuario.getNombre());
        reserva.setEvento(evento.getNombre());
        reserva.setFechaSolicitud("2024-1900/8/10");



        return sgre;
    }
}
