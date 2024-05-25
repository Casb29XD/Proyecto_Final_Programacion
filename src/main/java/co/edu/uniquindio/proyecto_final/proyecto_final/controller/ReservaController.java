package co.edu.uniquindio.proyecto_final.proyecto_final.controller;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.service.IReservaControllerService;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;

import java.util.List;

public class ReservaController implements IReservaControllerService {
    ModelFactoryController modelFactoryController;

    public ReservaController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public List<ReservaDto> obtenerReserva() {
        return modelFactoryController.obtenerReserva();
    }

    @Override
    public boolean agregarReserva(ReservaDto reserva) {
        return modelFactoryController.agregarReserva(reserva);
    }

    @Override
    public boolean eliminarReserva(String id) {
        return modelFactoryController.eliminarReserva(id);
    }

    @Override
    public boolean actualizarReserva(String id, ReservaDto reserva) {
        return modelFactoryController.actualizarReserva(id, reserva);
    }

}
