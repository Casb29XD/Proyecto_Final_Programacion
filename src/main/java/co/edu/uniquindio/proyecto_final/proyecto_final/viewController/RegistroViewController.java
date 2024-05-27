package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.EmpleadoController;
import co.edu.uniquindio.proyecto_final.proyecto_final.controller.UsuarioController;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EmpleadoDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.UsuarioDto;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroViewController {
    Login login = new Login();
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/login/Usuarios.txt";
    UsuarioController usuarioControllerService;
    ObservableList<UsuarioDto> listaUsuariosDto = FXCollections.observableArrayList();
    private String idUsuario;
    @FXML
    private TextField TxtRegistroNombre;

    @FXML
    private TextField TxtRegistroCorreo;

    @FXML
    private TextField TxtRegistroId;

    @FXML
    private Button BtnRegistroRegresar;

    @FXML
    private Button BtnRegistroGuardar;

    @FXML
    private PasswordField Password;

    @FXML
    void RegistrarseAction(ActionEvent event) {
        crearUsuario();
    }

    @FXML
    void initialize() {
        usuarioControllerService = new UsuarioController();
    }

    private void crearUsuario() {
        //1. Capturar los datos
        if(TxtRegistroId.getText().isEmpty() || TxtRegistroNombre.getText().isEmpty() || TxtRegistroCorreo.getText().isEmpty() || Password.getText().isEmpty()){
            mostrarMensaje("Faltan Datos","Alerta","Tienes que llenar todos los Campos.",Alert.AlertType.ERROR);
        }else {
            UsuarioDto usuarioDto = construirUsuarioDto();
            //2. Validar la información
            if(datosValidos(usuarioDto)){
                if(usuarioControllerService.agregarUsuario(usuarioDto)){
                    listaUsuariosDto.add(usuarioDto);
                    mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                    login.register(usuarioDto.id(),Password.getText().toString(),RUTA_ARCHIVO_USUARIOS);
                    limpiarCamposUsuario();
                }else{
                    mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
                }
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }
        }

    }

    private void limpiarCamposUsuario() {
        TxtRegistroNombre.setText("");
        TxtRegistroCorreo.setText("");
        TxtRegistroId.setText("");
        Password.setText("");
    }

    private UsuarioDto construirUsuarioDto() {
        return new UsuarioDto(
            TxtRegistroId.getText(),
            TxtRegistroNombre.getText(),
            TxtRegistroCorreo.getText(),
            null
        );
    }

    @FXML
    void RegresarAction(ActionEvent event) {
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
    private boolean datosValidos(UsuarioDto usuarioDto) {
        String mensaje = "";
        if(usuarioDto.nombre() == null || usuarioDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(usuarioDto.id() == null || usuarioDto.id().equals(""))
            mensaje += "El documento es invalido \n" ;
        if(mensaje.equals("")){
            return true;
        } else{
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

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
