package co.edu.uniquindio.proyecto_final.proyecto_final.controller;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.service.IEventoControllerService;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EventoDto;

import java.util.List;

public class EventoController implements IEventoControllerService {
    ModelFactoryController modelFactoryController;

    public EventoController(){
        modelFactoryController = ModelFactoryController.getInstance();
    }

    @Override
    public List<EventoDto> obtenerEvento() {
        return modelFactoryController.obtenerEvento();
    }

    @Override
    public boolean agregarEvento(EventoDto evento) {
        return modelFactoryController.agregarEvento(evento);
    }

    @Override
    public boolean eliminarEvento(String id) {
        return modelFactoryController.eliminarEvento(id);
    }

    @Override
    public boolean actualizarEvento(String id, EventoDto evento) {
        return modelFactoryController.actualizarEvento(id, evento);
    }


}
