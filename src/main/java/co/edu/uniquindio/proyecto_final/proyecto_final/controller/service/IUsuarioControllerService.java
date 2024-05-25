package co.edu.uniquindio.proyecto_final.proyecto_final.controller.service;

import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.UsuarioDto;

import java.util.List;

public interface IUsuarioControllerService {
    List<UsuarioDto> obtenerUsuarios();
    boolean agregarUsuario(UsuarioDto usuario);
    boolean eliminarUsuario(String id);
    boolean actualizarUsuario(String id, UsuarioDto usuario);
}
