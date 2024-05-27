package co.edu.uniquindio.proyecto_final.proyecto_final.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InformacionUsuariosViewController {

    @FXML
    private TableView<?> TableListaUsuarios;

    @FXML
    private TableColumn<?, ?> TcNombreUsuario;

    @FXML
    private TableColumn<?, ?> TcCorreoUsuario;

    @FXML
    private TableColumn<?, ?> TcClaveUsuario;

    @FXML
    private TableColumn<?, ?> TcIdUsuario;

    @FXML
    private TableColumn<?, ?> TcReservaUsuario;

    @FXML
    private Button BtnRegresar;

    @FXML
    void RegresarAction(ActionEvent event) {

    }

}
