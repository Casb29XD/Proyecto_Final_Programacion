package co.edu.uniquindio.proyecto_final.proyecto_final.controller;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.service.*;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.EmpleadoException;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.EventoExceprion;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.ReservaException;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.UsuarioException;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EventoDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.mappers.SgreMapper;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.*;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.ArchivoUtil;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Persistencia;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.SgreUtils;

import java.io.IOException;
import java.util.List;

public class ModelFactoryController implements IModelFactoryService,IUsuarioControllerService, IEventoControllerService,IReservaControllerService {
    Sgre sgre;
    SgreMapper mapper = SgreMapper.INSTANCE;


    //------------------------------  Singleton ------------------------------------------------
    // Clase estatica oculta. Tan solo se instanciara el singleton una vez
    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

    // Método para obtener la instancia de nuestra clase
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactoryController() {
        System.out.println("invocación clase singleton");

        //cargarDatosBase();
        //salvarDatosPrueba();

        //2. Cargar los datos de los archivos
		cargarDatosDesdeArchivos();

        //3. Guardar y Cargar el recurso serializable binario

		//cargarResourceBinario();
		//guardarResourceBinario();

        //4. Guardar y Cargar el recurso serializable XML
		guardarResourceXML();
        cargarResourceXML();

        //Siempre se debe verificar si la raiz del recurso es null

        if(sgre == null){

            cargarDatosBase();
            guardarResourceXML();
            cargarDatosDesdeArchivos();
            cargarResourceXML();
        }
        registrarAccionesSistema("Inicio de sesión", 1, "inicioSesión");


    }

    private void cargarDatosDesdeArchivos() {
        sgre = new Sgre();
        try {
            Persistencia.cargarDatosArchivos(sgre);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void salvarDatosPrueba() {
        try {
            Persistencia.guardarEmpleados(getSgre().getEmpleados());
            //Persistencia.guardarClientes(getSgre().getUsuarios());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cargarDatosBase() {
        sgre = SgreUtils.InicializarDatos();
    }

    public Sgre getSgre() {
        return sgre;
    }

    public void setSgre(Sgre sgre) {
        this.sgre = sgre;
    }

//empleado
    @Override
    public List<EmpleadoDto> obtenerEmpleados() {
        return  mapper.getEmpleadosDto(sgre.getEmpleados());
    }

    @Override
    public boolean agregarEmpleado(EmpleadoDto empleadoDto) {
        try {
            Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
            getSgre().agregarEmpleado(empleado);
            Persistencia.guardarEmpleados(getSgre().getEmpleados());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEmpleado(String id) {
        try {
            boolean flagExiste = getSgre().eliminarEmpleado(id);
            if (flagExiste) {
                Persistencia.guardarEmpleados(getSgre().getEmpleados());
                guardarResourceXML();
            }
            return flagExiste;
        } catch (IOException | EmpleadoException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEmpleado(String id, EmpleadoDto empleadoDto) {
        try {
            Empleado empleado = mapper.empleadoDtoToEmpleado(empleadoDto);
            boolean resultado = getSgre().actualizarEmpleado(id, empleado);
            if (resultado) {
                Persistencia.guardarEmpleados(getSgre().getEmpleados());
                guardarResourceXML();
            }
            return resultado;
        } catch (IOException | EmpleadoException e) {
            e.printStackTrace();
            return false;
        }
    }

//usuario
    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return mapper.getUsuariosDto(sgre.getUsuarios());
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuarioDto) {
        try {
            Usuario usuario = mapper.usuarioDtoToUsuario(usuarioDto);
            getSgre().agregarUsuario(usuario);
            Persistencia.guardarUsuarios(getSgre().getUsuarios());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(String id) {
        try {
            boolean flagExiste = getSgre().eliminarUsuario(id);
            if (flagExiste) {
                Persistencia.guardarUsuarios(getSgre().getUsuarios());
                guardarResourceXML();
            }
            return flagExiste;
        } catch (IOException | UsuarioException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarUsuario(String id, UsuarioDto usuario) {
        try {
            Usuario usuario1 = mapper.usuarioDtoToUsuario(usuario);
            boolean resultado = getSgre().actualizarUsuario(id, usuario1);
            if (resultado) {
                Persistencia.guardarUsuarios(getSgre().getUsuarios());
                guardarResourceXML();
            }
            return resultado;
        } catch (IOException | UsuarioException e) {
            e.printStackTrace();
            return false;
        }
    }

//Evento
    @Override
    public List<EventoDto> obtenerEvento() {
        return mapper.getEventoDto(sgre.getEventos());
    }

    @Override
    public boolean agregarEvento(EventoDto evento) {
        try {
            Evento evento1 = mapper.eventoDtoToEvento(evento);
            getSgre().agregarEvento(evento1);
            Persistencia.guardarEventos(getSgre().getEventos());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEvento(String id) {
        try {
            boolean flagExiste = getSgre().eliminarEvento(id);
            if (flagExiste) {
                Persistencia.guardarEventos(getSgre().getEventos());
                guardarResourceXML();
            }
            return flagExiste;
        } catch (IOException | EventoExceprion e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEvento(String id, EventoDto evento) {
        try {
            Evento evento1 = mapper.eventoDtoToEvento(evento);
            boolean resultado = getSgre().actualizarEvento(id, evento1);
            if (resultado) {
                Persistencia.guardarEventos(getSgre().getEventos());
                guardarResourceXML();
            }
            return resultado;
        } catch (IOException | EventoExceprion e) {
            e.printStackTrace();
            return false;
        }
    }
//Reserva
    @Override
    public List<ReservaDto> obtenerReserva() {
        return mapper.getReservaDto(sgre.getReservas());
    }

    @Override
    public boolean agregarReserva(ReservaDto reservaDto) {
        try {
            Reserva reserva = mapper.reservaDtoToReserva(reservaDto);
            getSgre().agregarReserva(reserva);
            Persistencia.guardarEventos(getSgre().getEventos());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarReserva(String id) {
        try {
            boolean flagExiste = getSgre().eliminarReserva(id);
            if (flagExiste) {
                Persistencia.guardarReservas(getSgre().getReservas());
                guardarResourceXML();
            }
            return flagExiste;
        } catch (IOException | ReservaException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarReserva(String id, ReservaDto reserva) {
        try {
            Reserva reserva1 = mapper.reservaDtoToReserva(reserva);
            boolean resultado = getSgre().actualizarReserva(id, reserva1);
            if (resultado) {
                Persistencia.guardarReservas(getSgre().getReservas());
                guardarResourceXML();
            }
            return resultado;
        } catch (IOException | ReservaException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void cargarResourceXML() {
        sgre = Persistencia.cargarRecursosSgreXML();
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoSgreXML(sgre);
    }

    private void cargarResourceBinario() {
        sgre = Persistencia.cargarRecursoSgreBinario();
    }

    private void guardarResourceBinario() {
        Persistencia.guardarRecursoSgreBinario(sgre);
    }

    public void registrarAccionesSistema(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
}
