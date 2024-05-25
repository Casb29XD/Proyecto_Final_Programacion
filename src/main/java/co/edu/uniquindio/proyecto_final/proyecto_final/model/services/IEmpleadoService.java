package co.edu.uniquindio.proyecto_final.proyecto_final.model.services;

import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.EmpleadoException;
import co.edu.uniquindio.proyecto_final.proyecto_final.exceptions.UsuarioException;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Empleado;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Reserva;

import java.util.ArrayList;

public interface IEmpleadoService {
    public Empleado crearEmpleado(String id, String nombre, String correo, ArrayList<Reserva> reserva) throws UsuarioException, EmpleadoException;
    public Boolean eliminarEmpleado(String id)throws EmpleadoException;
    boolean actualizarEmpleado(String id, Empleado empleado) throws EmpleadoException;
    public boolean  verificarEmpleadoExistente(String id) throws EmpleadoException;
    public Empleado obtenerEmpleado(String id);
    public ArrayList<Empleado> obtenerEmpleados();
}
