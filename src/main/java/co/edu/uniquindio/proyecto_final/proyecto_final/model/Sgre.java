package co.edu.uniquindio.proyecto_final.proyecto_final.model;

import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.EmpleadoException;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.EventoExceprion;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.ReservaException;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.UsuarioException;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.services.IEmpleadoService;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.services.IEventoservice;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.services.IReservaService;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.services.IUsuarioService;

import java.util.ArrayList;
import java.util.List;

public class Sgre implements IEmpleadoService, IUsuarioService, IReservaService, IEventoservice {

    private static final long serialVersionUID = 1L;
    List<Usuario> usuarios = new ArrayList<>();
    List<Empleado> empleados = new ArrayList<>();
    List<Evento> eventos = new ArrayList<>();
    List<Reserva> reservas = new ArrayList<>();

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }


    //empleados
    @Override
    public Empleado crearEmpleado(String id, String nombre, String correo, ArrayList<Reserva> reserva) throws EmpleadoException {
        Empleado nuevoEmpleado= null;
        boolean empleadoExistente= verificarEmpleadoExistente(id);
        if (empleadoExistente){
            throw new EmpleadoException("El Empleado con Id: "+id+" ya se encuentra registrado");
        }else {
            nuevoEmpleado = new Empleado();
            nuevoEmpleado.setId(id);
            nuevoEmpleado.setNombre(nombre);
            nuevoEmpleado.setCorreo(correo);
            nuevoEmpleado.setEvento(eventos);
            getEmpleados().add(nuevoEmpleado);
        }
        return nuevoEmpleado;
    }

    @Override
    public Boolean eliminarEmpleado(String id) throws EmpleadoException {
        Empleado empleado= null;
        boolean existe=false;
        empleado = obtenerEmpleado(id);
        if (empleado == null){
            throw new EmpleadoException("El Empleado no se encuentra registrado");
        }else {
            getEmpleados().remove(empleado);
            existe=true;
        }
        return existe;
    }

    @Override
    public boolean actualizarEmpleado(String id, Empleado empleado) throws EmpleadoException {
        Empleado empleadoactual = obtenerEmpleado(id);
        if (empleadoactual == null){
            throw new EmpleadoException("Ese Empleado no existe");
        }else {
            empleadoactual.setNombre(empleado.getNombre());
            empleadoactual.setId(empleado.getId());
            empleadoactual.setCorreo(empleado.getCorreo());
            return true;
        }
    }

    @Override
    public boolean verificarEmpleadoExistente(String id) throws EmpleadoException {
        if (empleadoExiste(id)){
            throw new EmpleadoException("El Empleado con id: " +id+" ya se encuentra registrado");
        }else {
            return false;
        }
    }

    @Override
    public Empleado obtenerEmpleado(String id) {
        Empleado empleadoEncontrado= null;
        for (Empleado empleado: getEmpleados()){
            if (empleado.getId().equals(id)){
                empleadoEncontrado = empleado;
                break;
            }
        }
        return empleadoEncontrado;
    }
    @Override
    public ArrayList<Empleado> obtenerEmpleados() {
        return null;
    }

    //usuarios

    @Override
    public Usuario crearUsuario(String id, String nombre, String correo, ArrayList<Reserva> reserva) throws UsuarioException {
        Usuario nuevoUsuario= null;
        boolean usuarioExistente= verificarUsuario(id);
        if (usuarioExiste(id)){
            throw new UsuarioException("El usuario con Id: "+id+" ya se encuentra registrado");
        }else {
            nuevoUsuario = new Usuario();
            nuevoUsuario.setId(id);
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setCorreo(correo);
            nuevoUsuario.setReserva(reserva);
            getUsuarios().add(nuevoUsuario);
        }
        return nuevoUsuario;
    }

    @Override
    public boolean eliminarUsuario(String id) throws UsuarioException {
        Usuario usuario= null;
        boolean existe=false;
        usuario = obtenerUsuario(id);
        if (usuario == null){
            throw new UsuarioException("El Usuario no se encuentra registrado");
        }else {
            getUsuarios().remove(usuario);
            existe=true;
        }
        return existe;
    }

    @Override
    public boolean actualizarUsuario(String id, Usuario usuario) throws UsuarioException {
        Usuario usaurioactual = obtenerUsuario(id);
        if (usaurioactual == null){
            throw new UsuarioException("Ese Usuario no existe");
        }else {
            usaurioactual.setNombre(usuario.getNombre());
            usaurioactual.setId(usuario.getId());
            usaurioactual.setCorreo(usuario.getCorreo());
            return true;
        }
    }

    @Override
    public boolean verificarUsuario(String id) throws UsuarioException {
        if (usuarioExiste(id)){
            throw new UsuarioException("El Usuario con id: " +id+" ya se encuentra registrado");
        }else {
            return false;
        }
    }

    @Override
    public Usuario obtenerUsuario(String id) throws UsuarioException {
        Usuario usuarioEncontrado= null;
        for (Usuario usuario: getUsuarios()){
            if (usuario.getId().equals(id)){
                usuarioEncontrado = usuario;
                break;
            }
        }
        return usuarioEncontrado;
    }

    @Override
    public ArrayList<Usuario> obtenerUsuarios() {
        return null;
    }

//Crud Reserva
    @Override
    public Reserva crearReserva(String id, String usuario, String evento, String fechaSolicitud, String estado) throws ReservaException {
        Reserva nuevoReserva= null;
        boolean ReservaExistente= verificarReservaExistente(id);
        if (usuarioExiste(id)){
            throw new ReservaException("La Reserva con Id: "+id+" ya se encuentra registrado");
        }else {
            nuevoReserva.setId(id);
            nuevoReserva.setUsuario(usuario);
            nuevoReserva.setEvento(evento);
            nuevoReserva.setFechaSolicitud(fechaSolicitud);
            nuevoReserva.setEstado(estado);
        }
        return nuevoReserva;
    }

    @Override
    public Boolean eliminarReserva(String id) throws ReservaException {
        Reserva reserva= null;
        boolean existe=false;
        reserva = obtenerReserva(id);
        if (reserva == null){
            throw new ReservaException("El Reserva no se encuentra registrado");
        }else {
            getUsuarios().remove(reserva);
            existe=true;
        }
        return existe;
    }

    @Override
    public boolean actualizarReserva(String id, Reserva reserva) throws ReservaException {
        Reserva reservaactual = obtenerReserva(id);
        if (reservaactual == null){
            throw new ReservaException("Ese Reserva no existe");
        }else {
            reservaactual.setId(reserva.getId());
            reservaactual.setUsuario(reserva.getUsuario());
            reservaactual.setEvento(reserva.getEvento());
            reservaactual.setFechaSolicitud(reserva.getFechaSolicitud());
            reservaactual.setEstado(reserva.getEstado());
            return true;
        }
    }

    @Override
    public boolean verificarReservaExistente(String id) throws ReservaException {
        if (ReservaExiste(id)){
            throw new ReservaException("La Reserva con id: " +id+" ya se encuentra registrado");
        }else {
            return false;
        }
    }

    @Override
    public Reserva obtenerReserva(String id) {
        Reserva reservaEncontrado= null;
        for (Reserva reserva: getReservas()){
            if (reserva.getId().equals(id)){
                reservaEncontrado = reserva;
                break;
            }
        }
        return reservaEncontrado;
    }
    @Override
    public ArrayList<Reserva> obtenerReservas() {
        return null;
    }

//Evento

    @Override
    public Evento crearEvento(String id,String nombre, String descripcion, String fecha, String capacidadMaxima, String empleado, List<Reserva> reservaList) throws EventoExceprion {
        Evento nuevoEvento= null;
        boolean EventoExistente= verificarEventoExistente(id);
        if (usuarioExiste(id)){
            throw new EventoExceprion("El Evento con Id: "+id+" ya se encuentra registrado");
        }else {
            nuevoEvento.setId(id);
            nuevoEvento.setNombre(nombre);
            nuevoEvento.setDescripcion(descripcion);
            nuevoEvento.setFecha(fecha);
            nuevoEvento.setCapacidadMaxima(capacidadMaxima);
            nuevoEvento.setReserva((ArrayList<Reserva>) reservaList);
        }
        return nuevoEvento;
    }

    @Override
    public Boolean eliminarEvento(String id) throws EventoExceprion {
        Evento evento= null;
        boolean existe=false;
        evento = obtenerEvento(id);
        if (evento == null){
            throw new EventoExceprion("El Evento no se encuentra registrado");
        }else {
            getEventos().remove(evento);
            existe=true;
        }
        return existe;
    }

    @Override
    public boolean actualizarEvento(String id, Evento evento) throws EventoExceprion {
        Evento eventoactual = obtenerEvento(id);
        if (eventoactual == null){
            throw new EventoExceprion("Ese Evento no existe");
        }else {
            eventoactual.setId(evento.getId());
            eventoactual.setNombre(evento.getNombre());
            eventoactual.setDescripcion(evento.getDescripcion());
            eventoactual.setFecha(evento.getFecha());
            eventoactual.setCapacidadMaxima(evento.getCapacidadMaxima());
            eventoactual.setReserva(evento.getReserva());
            return true;
        }
    }

    @Override
    public boolean verificarEventoExistente(String id) throws EventoExceprion {
        if (EventoExiste(id)){
            throw new EventoExceprion("El Evento con id: " +id+" ya se encuentra registrado");
        }else {
            return false;
        }
    }

    @Override
    public Evento obtenerEvento(String id) {
        Evento EventoEncontrado= null;
        for (Evento evento: getEventos()){
            if (evento.getId().equals(id)){
                EventoEncontrado = evento;
                break;
            }
        }
        return EventoEncontrado;
    }

    @Override
    public ArrayList<Evento> obtenerEvento() {
        return null;
    }


    public void agregarUsuario(Usuario usuario){
        getUsuarios().add(usuario);
    }
    public void agregarEmpleado(Empleado empleado){
        getEmpleados().add(empleado);
    }
    public void agregarReserva(Reserva reserva){
        getReservas().add(reserva);
    }
    public void agregarEvento(Evento evento){
        getEventos().add(evento);
    }

    private boolean empleadoExiste(String id) {
        boolean empleadoEncontrado=false;
        for (Empleado empleado: getEmpleados()){
            if (empleado.getId().equals(id)){
                empleadoEncontrado = true;
                break;
            }
        }
        return empleadoEncontrado;
    }
    private boolean usuarioExiste(String id) {
        boolean usuarioEncontrado=false;
        for (Usuario usuario: getUsuarios()){
            if (usuario.getId().equals(id)){
                usuarioEncontrado = true;
                break;
            }
        }
        return usuarioEncontrado;
    }
    private boolean EventoExiste(String id) {
        boolean eventoEncontrado=false;
        for (Evento evento: getEventos()){
            if (evento.getId().equals(id)){
                eventoEncontrado = true;
                break;
            }
        }
        return eventoEncontrado;
    }
    private boolean ReservaExiste(String id) {
        boolean reservaEncontrado = false;
        for (Reserva reserva : getReservas()) {
            if (reserva.getId().equals(id)) {
                reservaEncontrado = true;
                break;
            }
        }
        return reservaEncontrado;
    }
}
