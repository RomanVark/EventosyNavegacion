module ni.edu.uam.eventosynavegacion {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.eventosynavegacion to javafx.fxml;
    exports ni.edu.uam.eventosynavegacion;
}