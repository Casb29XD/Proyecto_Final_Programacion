package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservaViewController {
    private String idUsuario;
    private String idReserva;
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

    }

    @FXML
    void ActualizarReservaAction(ActionEvent event) {

    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
}
