package com.example.eclipsado.alert;

/**
 * Interface that defines the methods for displaying different types of alerts in the application.
 * @author Brandon Fernandez
 */
public interface AlertManagerInterface {

    /**
     * Displays an informational alert.
     * @param title   The title of the alert.
     * @param message The message of the alert.
     */
    void showInfoAlert(String title, String message);

    /**
     * Displays a warning alert.
     * @param title   The title of the alert.
     * @param message The message of the alert.
     */
    void showWarningAlert(String title, String message);

    /**
     * Displays an error alert.
     * @param title   The title of the alert.
     * @param message The message of the alert.
     */
    void showErrorAlert(String title, String message);
}

