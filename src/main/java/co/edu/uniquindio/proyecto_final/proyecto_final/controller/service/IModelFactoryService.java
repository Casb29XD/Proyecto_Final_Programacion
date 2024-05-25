package co.edu.uniquindio.proyecto_final.proyecto_final.controller.service;

import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EmpleadoDto;

import java.util.List;

public interface IModelFactoryService {
    List<EmpleadoDto> obtenerEmpleados();
    boolean agregarEmpleado(EmpleadoDto empleadoDto);
    boolean eliminarEmpleado(String id);
    boolean actualizarEmpleado(String id, EmpleadoDto empleadoDto);
}
