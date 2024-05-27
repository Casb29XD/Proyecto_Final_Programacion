package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.EmpleadoController;
import co.edu.uniquindio.proyecto_final.proyecto_final.controller.ReservaController;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class ReservaEmpleadosViewController {
    ReservaController reservaControllerServices;
    ObservableList<ReservaDto> listaReservaDto = FXCollections.observableArrayList();
    Persistencia persistencia;
    ArrayList<String> estados=new ArrayList<>();

    @FXML
    private TableView<ReservaDto> tablaReserva;

    @FXML
    private TableColumn<ReservaDto, String> ID;
    @FXML
    private TableColumn<ReservaDto, String> Usuario;
    @FXML
    private TableColumn<ReservaDto, String> Evento;
    @FXML
    private TableColumn<ReservaDto, String> Fecha;
    @FXML
    private TableColumn<ReservaDto, String> Estado;

    @FXML
    private TextField TxtID;

    @FXML
    private TextField TxtUsuario;

    @FXML
    private TextField TxtCodigoEve;

    @FXML
    private TextField TxtFecha;

    @FXML
    private ComboBox<ReservaDto> estado;

    @FXML
    private Button BtnRegresarReserva;

    @FXML
    private Button BtnVerEventos;


    @FXML
    private Button BtnActualizar;

    @FXML
    void ActualizarReservaAction(ActionEvent event) {

    }
    @FXML
    void initialize() {
        reservaControllerServices = new ReservaController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerEmpleados();
        tablaReserva.getItems().clear();
        tablaReserva.setItems(listaReservaDto);
        listenerSelection();
    }

    private void initDataBinding() {
        ID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        Usuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario()));
        Evento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().evento()));
        Fecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaSolicitud()));
        Estado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
    }

    private void obtenerEmpleados() {
        listaReservaDto.addAll(reservaControllerServices.obtenerReserva());
    }

    private void listenerSelection() {
        tableEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            empleadoSeleccionado = newSelection;
            mostrarInformacionEmpleado(empleadoSeleccionado);
        });
    }

    private void mostrarInformacionEmpleado(EmpleadoDto empleadoSeleccionado) {
        if(empleadoSeleccionado != null){
            txtNombre.setText(empleadoSeleccionado.nombre());
            txtId.setText(empleadoSeleccionado.id());
            txtCorreo.setText(empleadoSeleccionado.correo());
        }
    }

}
