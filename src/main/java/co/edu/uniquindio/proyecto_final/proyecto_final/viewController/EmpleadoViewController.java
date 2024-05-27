package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import co.edu.uniquindio.proyecto_final.proyecto_final.controller.EmpleadoController;
import co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto.EmpleadoDto;

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
import java.util.ArrayList;
import java.util.Optional;

public class EmpleadoViewController {

    EmpleadoController empleadoControllerService;
    ObservableList<EmpleadoDto> listaEmpleadosDto = FXCollections.observableArrayList();
    EmpleadoDto empleadoSeleccionado;
    Persistencia persistencia;
    Login login = new Login();
    public static final String RUTA_ARCHIVO_EMPLEADOS = "src/main/resources/persistencia/login/Empleados.txt";

    private String idUsuario;

    @FXML
    private TextField txtId;

    @FXML
    private TableColumn<EmpleadoDto, String> tcNombre;

    @FXML
    private TableColumn<EmpleadoDto, String> tcCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TableColumn<EmpleadoDto, String> tcId;

    @FXML
    private TableView<EmpleadoDto> tableEmpleados;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevo;

    @FXML
    private TableColumn<EmpleadoDto, String> tcEvento;

    @FXML
    private Button btnAgregar;

    @FXML
    private TextField txtCorreo;
    @FXML
    public PasswordField Password;

    @FXML
    private Button btnActualizar;

    @FXML
    void initialize() {
        empleadoControllerService = new EmpleadoController();
        intiView();
    }

    private void intiView() {
        initDataBinding();
        obtenerEmpleados();
        tableEmpleados.getItems().clear();
        tableEmpleados.setItems(listaEmpleadosDto);
        listenerSelection();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        tcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().correo()));
    }

    private void obtenerEmpleados() {
        listaEmpleadosDto.addAll(empleadoControllerService.obtenerEmpleados());
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

    @FXML
    void nuevoEmpleadoAction(ActionEvent event) {
        txtNombre.setText("");
        txtId.setText("");
        txtCorreo.setText("");
        Password.setText("");
        txtNombre.setPromptText("Ingrese el nombre");
        txtId.setPromptText("Ingrese la cedula");
        txtCorreo.setPromptText("Ingrese el correo");
        Password.setPromptText("Ingrese la Contraseña");
    }

    @FXML
    void agregarEmpleadoAction(ActionEvent event) {
        crearEmpleado();
    }
    @FXML
    void eliminarEmpleadoAction(ActionEvent event) {
        eliminarEmpleado();
    }
    @FXML
    void actualizarEmpleadoAction(ActionEvent event) {
        actualizarEmpleado();
    }

    private void crearEmpleado() {
        //1. Capturar los datos
        EmpleadoDto empleadoDto = construirEmpleadoDto();
        String contraseña = Password.getText().toString();
        //2. Validar la información
        if(datosValidos(empleadoDto)){
            if(empleadoControllerService.agregarEmpleado(empleadoDto)){
                listaEmpleadosDto.add(empleadoDto);
                mostrarMensaje("Notificación empleado", "Empleado creado", "El empleado se ha creado con éxito", Alert.AlertType.INFORMATION);
                persistencia.guardaRegistroLog("Creacion Empleado",2, "Se creo un empleado con la cedula de " + empleadoDto.id()+ " Por el admin de id: " +idUsuario);
                login.register(empleadoDto.id(),contraseña,RUTA_ARCHIVO_EMPLEADOS);
                limpiarCamposEmpleado();
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "El empleado no se ha creado con éxito", Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
        }

    }

    private void eliminarEmpleado() {
        boolean empleadoEliminado = false;
        if(empleadoSeleccionado != null){
            if(mostrarMensajeConfirmacion("¿Estas seguro de elmininar al empleado?")){
                empleadoEliminado = empleadoControllerService.eliminarEmpleado(empleadoSeleccionado.id());
                if(empleadoEliminado == true){
                    listaEmpleadosDto.remove(empleadoSeleccionado);
                    login.deleteUser(empleadoSeleccionado.id(), RUTA_ARCHIVO_EMPLEADOS);
                    persistencia.guardaRegistroLog("Eliminacio de Empleado",2, "Se Elimino el empleado con la cedula de " + empleadoSeleccionado.id()+ " Por el admin de id: " +idUsuario);
                    mostrarMensaje("Notificación empleado", "Empleado eliminado", "El empleado se ha eliminado con éxito", Alert.AlertType.INFORMATION);
                    tableEmpleados.getSelectionModel().clearSelection();
                    limpiarCamposEmpleado();
                    empleadoSeleccionado = null;
                }else{
                    mostrarMensaje("Notificación empleado", "Empleado no eliminado", "El empleado no se puede eliminar", Alert.AlertType.ERROR);
                }
            }
        }else{
            mostrarMensaje("Notificación empleado", "Empleado no seleccionado", "Seleccionado un empleado de la lista", Alert.AlertType.WARNING);
        }
    }

    private void actualizarEmpleado() {
        String contraseña = Password.getText().toString();
        boolean clienteActualizado = false;
        //1. Capturar los datos
        String cedulaActual = empleadoSeleccionado.id();
        EmpleadoDto empleadoDto = construirEmpleadoDto();
        //2. verificar el empleado seleccionado
        if(empleadoSeleccionado != null){
            //3. Validar la información
            if(datosValidos(empleadoSeleccionado)){
                clienteActualizado = empleadoControllerService.actualizarEmpleado(cedulaActual,empleadoDto);
                if(clienteActualizado){
                    listaEmpleadosDto.remove(empleadoSeleccionado);
                    listaEmpleadosDto.add(empleadoDto);
                    tableEmpleados.refresh();
                    mostrarMensaje("Notificación empleado", "Empleado actualizado", "El empleado se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                    persistencia.guardaRegistroLog("Actualizacion de Empleado",2, "Se Actualizo la informacion del empleado con la cedula de " + empleadoDto.id() + " Por el admin de id: " +idUsuario);
                    limpiarCamposEmpleado();
                    if (!contraseña.equals("")){
                        login.updatePassword(empleadoSeleccionado.id(),contraseña,RUTA_ARCHIVO_EMPLEADOS);
                    }
                }else{
                    mostrarMensaje("Notificación empleado", "Empleado no actualizado", "El empleado no se ha actualizado con éxito", Alert.AlertType.INFORMATION);
                }
            }else{
                mostrarMensaje("Notificación empleado", "Empleado no creado", "Los datos ingresados son invalidos", Alert.AlertType.ERROR);
            }

        }
    }

    private EmpleadoDto construirEmpleadoDto() {
        return new EmpleadoDto(
                txtId.getText(),
                txtNombre.getText(),
                txtCorreo.getText()
        );
    }

    private void limpiarCamposEmpleado() {
        txtNombre.setText("");
        txtId.setText("");
        txtCorreo.setText("");
        Password.setText("");
    }

    private boolean datosValidos(EmpleadoDto empleadoDto) {
        String mensaje = "";
        if(empleadoDto.nombre() == null || empleadoDto.nombre().equals(""))
            mensaje += "El nombre es invalido \n" ;
        if(empleadoDto.id() == null || empleadoDto.id().equals(""))
            mensaje += "El documento es invalido \n" ;
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

    @FXML
    void VerEventosAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_final/proyecto_final/EventosView.fxml"));
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
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
