package ni.edu.uam.eventosynavegacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PulperiaApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                PulperiaApp.class.getResource("pulperia-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 600, 454);

        stage.setTitle("Inventario de Pulpería");
        stage.setScene(scene);
        stage.show();
    }

}