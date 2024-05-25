package co.edu.uniquindio.proyecto_final.proyecto_final.controller.service;

import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EmpleadoDto;

import java.util.List;

public interface IEmpleadoControllerService {
    List<EmpleadoDto> obtenerEmpleados();
    boolean agregarEmpleado(EmpleadoDto empleado);
    boolean eliminarEmpleado(String id);
    boolean actualizarEmpleado(String id,EmpleadoDto empleado);
}
