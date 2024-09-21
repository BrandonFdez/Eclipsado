package com.example.eclipsado.controller;

import com.example.eclipsado.alert.AlertManagerInterface;
import com.example.eclipsado.alert.AlertManagerLogic;
import com.example.eclipsado.model.GameModel;
import com.example.eclipsado.view.FirstStage;
import com.example.eclipsado.view.SecondStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * The controller for the first stage of the game. Allows the user to input the secret word and validates it.
 * If the word is valid, this window is closed and the second window is opened.
 * @author Brandon Fernandez
 */
public class FirstStageController {

    @FXML
    private Button PlayButton; // Botón para iniciar el juego
    @FXML
    private Label InstructionText; // Etiqueta que contiene las instrucciones
    @FXML
    private TextField SecretWord; // Campo de texto donde el usuario ingresa la palabra secreta

    private AlertManagerInterface AlertManager = new AlertManagerLogic(); // Instancia para gestionar alertas

    /**
     * Method that is executed when the user clicks the "Play" button.
     * Validates the entered secret word and, if correct, closes this window and opens the second one.
     * @throws IOException If an error occurs while opening the second window.
     */

    @FXML
    public void OnPlayButtonClick() throws IOException {
        // Obtiene la palabra secreta ingresada por el usuario
        String secretWord = SecretWord.getText();

        // Valida que la palabra tenga entre 6 y 12 letras
        if (secretWord.length() < 6 || secretWord.length() > 12) {
            AlertManager.showWarningAlert("Entrada inválida", "La palabra debe tener entre 6 y 12 letras.");
            SecretWord.clear();
            return;
        }

        // Valida que la palabra solo contenga letras del alfabeto español (incluye la ñ y vocales con acento)
        if (!secretWord.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]+")) {
            AlertManager.showWarningAlert("Entrada inválida", "La palabra solo debe contener letras del alfabeto español.");
            SecretWord.clear();
            return;
        }

        // Si la palabra es válida, se guarda en el modelo del juego
        GameModel.getInstance().setSecretWord(secretWord);
        // Cierra la primera ventana
        FirstStage.getInstance().close();
        // Abre la segunda ventana
        SecondStage.getInstance().show();
    }
}


