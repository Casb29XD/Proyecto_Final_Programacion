package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.ReservaController;
import co.edu.uniquindio.proyecto_final.proyecto_final.controller.UsuarioController;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.ReservaDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.model.Usuario;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Login;
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
import java.util.Optional;

public class UsuarioViewController {

    UsuarioController usuarioControllerServices;
    ReservaController reservaControllerServices;
    ObservableList<ReservaDto> listaReservasDto = FXCollections.observableArrayList();
    ReservaDto reservaSelecionada;
    Persistencia persistencia= new Persistencia();
    Login login= new Login();
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/login/Usuarios.txt";

    private String idUsuario;

    @FXML
    private Button btnEliminarUser;

    @FXML
    private Button BtnUserReserva;

    @FXML
    private TableView<ReservaDto> tablaReserva;

    @FXML
    private TextField txtCorreoUser;

    @FXML
    private TableColumn<ReservaDto, String> tcID;


    @FXML
    private TableColumn<ReservaDto, String> tcNombreReserva;

    @FXML
    private TextField txtNombreUser;

    @FXML
    private TableColumn<ReservaDto, String> tcFechaReserva;

    @FXML
    private TextField txtClaveUser;

    @FXML
    private Button btnActualizarUser;

    @FXML
    private Button BtnUserSalir;

    @FXML
    private TableColumn<ReservaDto, String> tcEstadoReserva;

    @FXML
    void initialize() {
        usuarioControllerServices = new UsuarioController();
        reservaControllerServices = new ReservaController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerReservasUsuario();
        tablaReserva.getItems().clear();
        tablaReserva.setItems(listaReservasDto);
        listenerSelection();
    }

    private void initDataBinding() {
        tcID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        tcNombreReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario()));
        tcFechaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().fechaSolicitud()));
        tcEstadoReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().estado()));
    }

    private void obtenerReservasUsuario() {
        listaReservasDto.addAll(reservaControllerServices.obtenerReserva());
    }

    private void listenerSelection() {
        tablaReserva.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSelecionada = newSelection;
        });
    }



    @FXML
    void btnEliminarUser(ActionEvent event) {
        if(eliminarUsaurio()){
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
    }

    private boolean eliminarUsaurio() {
        boolean usuarioEliminado=false;
        if (idUsuario.equals("")) {
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al empleado?")){
                usuarioEliminado= usuarioControllerServices.eliminarUsuario(idUsuario);
                if(usuarioEliminado){
                    listaReservasDto.clear();
                    login.deleteUser(idUsuario,RUTA_ARCHIVO_USUARIOS);
                    persistencia.guardaRegistroLog("Elliminacio de Usuario",2,"regresar al inicio");
                    mostrarMensaje("Notificación Usuario", "Usuario eliminado", "El Usuario se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                }
            }else {
                mostrarMensaje("Notificación Usuario", "Usuario no eliminado", "El Usuario no se puede eliminar", Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje("Notificación Usuario", "Usuario no Registrado", "Reiniciar sistema", Alert.AlertType.WARNING);
        }
        return usuarioEliminado;
    }


    @FXML
    void btnActualizarUser(ActionEvent event) {
        actualizarUsuario();
    }

    private void actualizarUsuario() {

    }
    @FXML
    void btnUserSalir(ActionEvent event) {
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
    private UsuarioDto {}

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
}
