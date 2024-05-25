package co.edu.uniquindio.proyecto_final.proyecto_final.controller;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.service.IUsuarioControllerService;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.UsuarioDto;

import java.util.List;

public class UsuarioController implements IUsuarioControllerService {
    ModelFactoryController modelFactoryController;

    public UsuarioController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public List<UsuarioDto> obtenerUsuarios() {
        return modelFactoryController.obtenerUsuarios();
    }

    @Override
    public boolean agregarUsuario(UsuarioDto usuario) {
        return modelFactoryController.agregarUsuario(usuario);
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return modelFactoryController.eliminarUsuario(id);
    }

    @Override
    public boolean actualizarUsuario(String id, UsuarioDto usuario) {
        return modelFactoryController.actualizarUsuario(id, usuario);
    }
}
