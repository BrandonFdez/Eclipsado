package com.example.eclipsado.view;

import com.example.eclipsado.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that represents the first window (FirstStage) of the game.
 * Extends {@link javafx.stage.Stage} and follows the singleton pattern to ensure only one instance of the window exists.
 * @author Brandon Fernandez
 */
public class FirstStage extends Stage {

    /**
     * Constructor of the FirstStage window.
     * Loads the FXML file, sets the window title and icon, and displays it.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public FirstStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("FirstStage-view.fxml"));
        Parent root = loader.load();
        Scene first_scene = new Scene(root);
        setResizable(false);
        getIcons().add(new Image(Main.class.getResource("/com/example/eclipsado/Icon/logo.png").toString())); // Cargar el icono
        setTitle("El Sol Eclipsado"); // Establecer el título de la ventana
        setScene(first_scene);
        show(); // Mostrar la ventana
    }

    /**
     * Returns the only instance of FirstStage, following the singleton pattern.
     * @return The instance of FirstStage.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public static FirstStage getInstance() throws IOException {
        if (FirstStageHolder.INSTANCE == null) {
            return FirstStageHolder.INSTANCE = new FirstStage(); // Crea la instancia si no existe
        } else {
            return FirstStageHolder.INSTANCE;
        }
    }

    /**
     * Static inner class that maintains the only instance of FirstStage.
     */
    private static class FirstStageHolder {
        private static FirstStage INSTANCE; // Instancia única de FirstStage
    }
}

