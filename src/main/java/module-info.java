module ni.edu.uam.eventosynavegacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens ni.edu.uam.eventosynavegacion to javafx.fxml;
    opens ni.edu.uam.eventosynavegacion.controllers to javafx.fxml;
    opens ni.edu.uam.eventosynavegacion.modelos to javafx.base;
    exports ni.edu.uam.eventosynavegacion;
}