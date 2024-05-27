package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.EventoController;
import co.edu.uniquindio.proyecto_final.proyecto_final.controller.ReservaController;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Reserva;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ReservaViewController {
    Persistencia persistencia = new Persistencia();
    private String idUsuario;
    private String idReserva;
    ReservaController reservaControllerService;
    ObservableList<ReservaDto> listareservaDto = FXCollections.observableArrayList();
    @FXML
    private TextField txtCodeReserva;

    @FXML
    private TextField TxtUserReserva;

    @FXML
    private DatePicker DateReserva;

    @FXML
    private TextField TxtIdReserva;

    @FXML
    private Button BtnRegresarReserva;

    @FXML
    private Button BtnActualizarReserva;

    @FXML
    private Button BtnReservar;

    @FXML
    private TextField TxtNombreReserva;

    @FXML
    void initialize() {
        reservaControllerService = new ReservaController();
        intiView();
    }
    private void intiView() {
        txtCodeReserva.setText(idReserva);
        TxtUserReserva.setText(idUsuario);
    }

    @FXML
    void RegresarAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_final/proyecto_final/UsuarioView.fxml"));
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

    @FXML
    void ReservarAction(ActionEvent event) {
        agregarReserva();
    }

    private void agregarReserva() {
        if (txtCodeReserva.getText().equals("")|| TxtUserReserva.getText().equals("") || TxtIdReserva.getText().equals("") || TxtNombreReserva.getText().equals("")
                || DateReserva.getValue() == null) {
            mostrarMensaje("Faltan Datos","Alerta","Tienes que llenar todos los Campos.",Alert.AlertType.ERROR);
        }else{
            ReservaDto reservaDto = construirResrvaDto();
            if (datosValidos(reservaDto)) {
                if(reservaControllerService.agregarReserva(reservaDto)){
                    listareservaDto.add(reservaDto);
                    mostrarMensaje("Notificación Reserva", "Reserva creado", "El Reserva se ha creado con éxito", Alert.AlertType.INFORMATION);
                    limpiarCamposReserva();
                }else{
                    mostrarMensaje("Notificación Reserva", "Reserva no creado", "El Reserva no se ha creado con éxito", Alert.AlertType.ERROR);
                }
            }else{
                mostrarMensaje("Notificación Reserva", "Reserva no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }
        }
    }

    private void limpiarCamposReserva() {
        txtCodeReserva.setId("");
        TxtUserReserva.setText("");
        TxtNombreReserva.setText("");
    }


    @FXML
    void ActualizarReservaAction(ActionEvent event) {
        actualizarReserva();
    }
    private void actualizarReserva() {
        boolean reservaActualizado = false;
        //1. Capturar los datos
        String idReserva = txtCodeReserva.getText().toString();
        ReservaDto reservaDto = construirResrvaDto();
        //2. verificar el Reserva seleccionado
        if(idReserva != null){
            //3. Validar la información
            if(datosValidos(reservaDto)){
                reservaActualizado = reservaControllerService.actualizarReserva(idReserva, reservaDto);
                if(reservaActualizado){
                    listareservaDto.remove(idReserva);
                    listareservaDto.add(reservaDto);
                    mostrarMensaje("Notificación Reserva", "Reserva actualizado", "El Reserva se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    persistencia.guardaRegistroLog("Actualizacion de Reserva",2, "Se Actualizo la informacion del Reserva con la cedula de " + idReserva + " Por el usuario de id: " +idUsuario);
                    limpiarCamposReserva();
                }else{
                    mostrarMensaje("Notificación Reserva", "Reserva no actualizado", "El Reserva no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación Reserva", "Reserva no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }
    private ReservaDto construirResrvaDto() {
        LocalDate date = DateReserva.getValue();
        String fechaReserva =""+date.getDayOfMonth();
        String estado="pendiente";
        return new ReservaDto(
                txtCodeReserva.getId().toString(),
                TxtUserReserva.getText().toString(),
                TxtNombreReserva.getText().toString(),
                fechaReserva,
                estado
        );
    }
    private boolean datosValidos(ReservaDto reservaDto) {
        String mensaje = "";
        if (reservaDto.id().equals("") || reservaDto.id().equals(" ")){
            mensaje += "El id del reserva es obligatorio\n";
        }
        if (reservaDto.usuario().equals("") || reservaDto.usuario().equals(" ")){
            mensaje += "El usuario es obligatorio\n";
        }
        if (reservaDto.evento().equals("") || reservaDto.evento().equals(" ")){
            mensaje += "El evento es obligatorio\n";
        }
        if (reservaDto.fechaSolicitud().equals("") || reservaDto.fechaSolicitud().equals(" ")){
            mensaje += "El fecha de solicitud es obligatorio\n";
        }

        if(mensaje.equals("")){
            return true;
        } else{
            mostrarMensaje("Notificación Evento","Datos invalidos",mensaje, Alert.AlertType.WARNING);
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


    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
}
