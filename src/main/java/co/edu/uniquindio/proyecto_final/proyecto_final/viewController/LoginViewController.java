package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;


import co.edu.uniquindio.proyecto_final.proyecto_final.model.Usuario;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Login;
import co.edu.uniquindio.proyecto_final.proyecto_final.utils.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {
    public static final String RUTA_ARCHIVO_EMPLEADOS = "src/main/resources/persistencia/login/Empleados.txt";
    public static final String RUTA_ARCHIVO_USUARIOS = "src/main/resources/persistencia/login/Usuarios.txt";
    public static final String RUTA_ARCHIVO_ADMIN = "src/main/resources/persistencia/login/Administradores.txt";
    Login login = new Login();
    Persistencia persistencia;
    @FXML
    private TextField TxtLoginID;

    @FXML
    private Button BtnLoginRegistro;

    @FXML
    private Button BtnLogin;

    @FXML
    private ComboBox<String> CmbBoxRolLogin;

    @FXML
    private Label LabelLogin;

    @FXML
    private PasswordField password;

    @FXML
    void initialize() {
        CmbBoxRolLogin.getItems().addAll("Empleado", "Usuario", "Administrador");
    }

    @FXML
    void IniciarSesionAction(ActionEvent event) {
        String selectedRole = CmbBoxRolLogin.getValue();
        String usuario= TxtLoginID.getText();
        String contraseña = password.getText().toString();
        if (selectedRole != null) {
            try {

                String viewPath = getViewPath(selectedRole);

                if(viewPath.equals("Empleado")){
                    if (login.login(usuario,contraseña,RUTA_ARCHIVO_EMPLEADOS)) {
                        persistencia.guardaRegistroLog("Inicio de seccion por Empleado",1, "Inicio seccion el empleado " + usuario);
                        viewPath= "/co/edu/uniquindio/proyecto_final/proyecto_final/ReservaEmpleadosView.fxml";
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
                        Parent root = loader.load();
                        EventosViewController controller = loader.getController();
                        controller.setIdUsuario(usuario);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    }
                } else if (viewPath.equals("Usuario")) {
                    if (login.login(usuario,contraseña,RUTA_ARCHIVO_USUARIOS)) {
                        persistencia.guardaRegistroLog("Inicio de seccion por Usuario", 2, "Inicio seccion el usuario " + usuario);
                        viewPath= "/co/edu/uniquindio/proyecto_final/proyecto_final/UsuarioView.fxml";
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
                        Parent root = loader.load();
                        UsuarioViewController controller = loader.getController();
                        controller.setIdUsuario(usuario);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    }
                } else if (viewPath.equals("Administrador")) {
                    if (login.login(usuario,contraseña,RUTA_ARCHIVO_ADMIN)) {
                        persistencia.guardaRegistroLog("Inicio de seccion por Empleado", 2, "Inicio seccion el empleado " + usuario);
                        viewPath ="/co/edu/uniquindio/proyecto_final/proyecto_final/EmpleadoView.fxml";
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
                        Parent root = loader.load();
                        EmpleadoViewController controller = loader.getController();
                        controller.setIdUsuario(usuario);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    }
                }

                CmbBoxRolLogin.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getViewPath(String role) {
            switch (role) {
                case "Empleado":
                    return "Empleado";

                case "Usuario":
                    return "Usuario";
                case "Administrador":
                    return "Administrador";
                default:
                    return null;
            }
    }

    @FXML
    void RegistrarClienteAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/proyecto_final/proyecto_final/RegistroView.fxml"));
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