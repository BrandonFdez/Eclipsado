package com.example.eclipsado;

import com.example.eclipsado.view.FirstStage;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class that starts the JavaFX application.
 * This class extends {@link javafx.application.Application} and launches the first window of the game.
 * author Brandon Fernandez
 */
public class Main extends Application {

    /**
     * Main method that runs the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args); // Lanza la aplicación JavaFX
    }

    /**
     * Start method that loads and displays the first window (FirstStage) when the application starts.
     * @param primaryStage The primary stage provided by JavaFX.
     * @throws IOException If an error occurs while loading the FXML file for the first window.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FirstStage.getInstance(); // Carga y muestra la primera ventana (FirstStage)
    }
}
