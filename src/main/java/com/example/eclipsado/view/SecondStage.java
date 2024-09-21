package com.example.eclipsado.view;

import com.example.eclipsado.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class that represents the second window (SecondStage) of the game.
 * Extends {@link javafx.stage.Stage} and follows the singleton pattern to ensure only one instance of the window exists.
 * @author Brandon Fernandez
 */
public class SecondStage extends Stage {

    /**
     * Constructor of the SecondStage window.
     * Loads the FXML file, sets the window title and icon, and displays it.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public SecondStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("SecondStage-view.fxml"));
        Parent root = loader.load();
        Scene second_scene = new Scene(root);
        setResizable(false);
        getIcons().add(new Image(Main.class.getResource("/com/example/eclipsado/Icon/logo.png").toString())); // Cargar el icono
        setTitle("El Sol Eclipsado"); // Establecer el título de la ventana
        setScene(second_scene);
        show(); // Mostrar la ventana
    }

    /**
     * Returns the only instance of SecondStage, following the singleton pattern.
     * @return The instance of SecondStage.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public static SecondStage getInstance() throws IOException {
        if (SecondStageHolder.INSTANCE == null) {
            return SecondStageHolder.INSTANCE = new SecondStage(); // Crea la instancia si no existe
        } else {
            return SecondStageHolder.INSTANCE;
        }
    }

    /**
     * Static inner class that maintains the only instance of SecondStage.
     */
    private static class SecondStageHolder {
        private static SecondStage INSTANCE; // Instancia única de SecondStage
    }
}
