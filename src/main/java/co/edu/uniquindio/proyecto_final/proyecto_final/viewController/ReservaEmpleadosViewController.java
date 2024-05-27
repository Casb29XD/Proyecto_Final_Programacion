package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ReservaEmpleadosViewController {

    @FXML
    private TableColumn<?, ?> tcCorreo;

    @FXML
    private TableView<?> tableUser;

    @FXML
    private TextField TxtNombre;

    @FXML
    private Button BtnRegresarReserva;

    @FXML
    private TableColumn<?, ?> tcReservaCode;

    @FXML
    private Button BtnVerEventos;

    @FXML
    private TableColumn<?, ?> tcNombreReserva;

    @FXML
    private TableColumn<?, ?> tcEstadoReserva;

    @FXML
    private Button BtnActualizar;

    @FXML
    void ActualizarReservaAction(ActionEvent event) {

    }

}
