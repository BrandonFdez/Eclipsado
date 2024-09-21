package com.example.eclipsado.alert;

import javafx.scene.control.Alert;

/**
 * Implementation of the {@link AlertManagerInterface}, which handles the logic
 * to display different types of alerts (information, warning, error) using JavaFX.
 * @author Brandon Fernandez
 */
public class AlertManagerLogic implements AlertManagerInterface {

    /**
     * Displays an informational alert with a custom title and message.
     * @param title   The title of the informational alert.
     * @param message The content of the alert message.
     */
    @Override
    public void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Alerta de tipo información
        alert.setTitle(title); // Establecer el título de la alerta
        alert.setHeaderText(null); // Elimina el encabezado
        alert.setContentText(message); // Establecer el mensaje de la alerta
        alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
    }

    /**
     * Displays a warning alert with a custom title and message.
     * @param title   The title of the warning alert.
     * @param message The content of the alert message.
     */
    @Override
    public void showWarningAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING); // Alerta de tipo advertencia
        alert.setTitle(title); // Establecer el título de la alerta
        alert.setHeaderText(null); // Elimina el encabezado
        alert.setContentText(message); // Establecer el mensaje de la alerta
        alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
    }

    /**
     * Displays an error alert with a custom title and message.
     * @param title   The title of the error alert.
     * @param message The content of the alert message.
     */
    @Override
    public void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR); // Alerta de tipo error
        alert.setTitle(title); // Establecer el título de la alerta
        alert.setHeaderText(null); // Elimina el encabezado
        alert.setContentText(message); // Establecer el mensaje de la alerta
        alert.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
    }
}
