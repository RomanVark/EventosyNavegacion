package ni.edu.uam.eventosynavegacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                CafeApp.class.getResource("cafeteria-view.fxml")
        );

        Scene scene = new Scene(
                fxmlLoader.load(),
                501,
                598
        );

        stage.setTitle("Recepción de Café");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}