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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ReservaEmpleadosViewController {
    ReservaController reservaControllerServices;
    ObservableList<ReservaDto> listaReservaDto = FXCollections.observableArrayList();
    ReservaDto reservaSelecionada;
    Persistencia persistencia;
    ArrayList<String> estados=new ArrayList<>();

    private String idUsuario;
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
        tablaReserva.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSelecionada = newSelection;
            mostrarInformacionEmpleado(reservaSelecionada);
        });
    }

    private void mostrarInformacionEmpleado(ReservaDto reservaDto) {
        if(reservaSelecionada != null){
            TxtID.setText(reservaDto.id());
            TxtUsuario.setText(reservaDto.usuario());
            TxtCodigoEve.setText(reservaDto.evento());
            TxtFecha.setText(reservaDto.fechaSolicitud());
        }
    }
    @FXML
    void ActualizarReservaAction(ActionEvent event) {
        actualizarEvento();
    }

    private void actualizarEvento() {

    }
    @FXML
    void regresar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_final/proyecto_final/LoginView.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            ((Stage)((Button)event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ReservaDto construirReservaDto() {
        return new ReservaDto(
                TxtID.getText(),
            TxtUsuario.getText(),
            TxtCodigoEve.getText(),
            TxtFecha.getText(),
                estado.getSelectionModel().getSelectedItem().toString()
        );
    }

    private void limpiarCamposReserva() {
        TxtID.setText("");
        TxtUsuario.setText("");
        TxtCodigoEve.setText("");
        TxtFecha.setText("");
    }
    private boolean datosValidos(ReservaDto reservaDto) {
        String mensaje = "";
        if (reservaDto.id().equals("") || reservaDto.usuario().equals(" ")){
            mensaje += "El nombre del usuario es obligatorio\n";
        }
        if (reservaDto.evento().equals("") || reservaDto.usuario().equals(" ")){
            mensaje += "El nombre del evento es obligatorio\n";
        }
        if (reservaDto.usuario().equals("") || reservaDto.usuario().equals(" ")){
            mensaje += "El usuario es obligatorio\n";
        }
        if(reservaDto.fechaSolicitud().equals("") || reservaDto.fechaSolicitud().equals(" ")){
            mensaje += "El fecha de solicitud es obligatorio\n";
        }
        if(reservaDto.estado().equals("") || reservaDto.estado().equals(" ")){
            mensaje += "El estado es obligatorio\n";
        }
        if(mensaje.equals("")){
            return true;
        }else{
            mostrarMensaje("Notificación cliente","Datos invalidos",mensaje, Alert.AlertType.WARNING);
            return false;
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

}
