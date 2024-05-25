module co.edu.uniquindio.proyecto_final.proyecto_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mapstruct;
    requires java.logging;
    requires java.desktop;

    opens co.edu.uniquindio.proyecto_final.proyecto_final.viewController to javafx.fxml;

    opens co.edu.uniquindio.proyecto_final.proyecto_final to javafx.fxml;

    exports co.edu.uniquindio.proyecto_final.proyecto_final;
    exports co.edu.uniquindio.proyecto_final.proyecto_final.controller;
    exports co.edu.uniquindio.proyecto_final.proyecto_final.mapping.dto;
    exports co.edu.uniquindio.proyecto_final.proyecto_final.mapping.mappers;
    exports co.edu.uniquindio.proyecto_final.proyecto_final.model;
    opens co.edu.uniquindio.proyecto_final.proyecto_final.controller to javafx.fxml;
}
