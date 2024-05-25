package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

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
    private TextField BtnLoginClave;

    @FXML
    void initialize() {

        CmbBoxRolLogin.getItems().addAll("Empleado", "Usuario", "Administrador");
    }

    @FXML
    void IniciarSesionAction(ActionEvent event) {
        String selectedRole = CmbBoxRolLogin.getValue();

        if (selectedRole != null) {
            try {

                String viewPath = getViewPath(selectedRole);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                CmbBoxRolLogin.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getViewPath(String role) {

        switch (role) {
            case "Empleado":
                return "/co/edu/uniquindio/proyecto_final/proyecto_final/EmpleadoView.fxml";
            case "Usuario":
                return "/co/edu/uniquindio/proyecto_final/proyecto_final/UsuarioView.fxml";
            case "Administrador":
                return "/co/edu/uniquindio/proyecto_final/proyecto_final/AdministradorView.fxml";
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
