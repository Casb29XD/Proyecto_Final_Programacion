package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsuarioViewController {

    private String idUsuario;

    @FXML
    private Button btnEliminarUser;

    @FXML
    private Button BtnUserReserva;

    @FXML
    private TableView<?> tableUser;

    @FXML
    private TextField txtCorreoUser;

    @FXML
    private TableColumn<?, ?> tcReservaCode;

    @FXML
    private Button btnNuevoUser;

    @FXML
    private TableColumn<?, ?> tcNombreReserva;

    @FXML
    private TextField txtNombreUser;

    @FXML
    private TableColumn<?, ?> tcFechaReserva;

    @FXML
    private TextField txtClaveUser;

    @FXML
    private Button btnActualizarUser;

    @FXML
    private TableColumn<?, ?> tcClaveReserva;

    @FXML
    private Button BtnUserSalir;

    @FXML
    private TableColumn<?, ?> tcEstadoReserva;

    @FXML
    void eliminarUserAction(ActionEvent event) {

    }

    @FXML
    void nuevoEmpleadoAction(ActionEvent event) {

    }

    @FXML
    void actualizarEmpleadoAction(ActionEvent event) {

    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }


}
