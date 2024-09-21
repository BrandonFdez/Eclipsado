package com.example.eclipsado.controller;

import com.example.eclipsado.alert.AlertManagerInterface;
import com.example.eclipsado.alert.AlertManagerLogic;
import com.example.eclipsado.model.GameModel;
import com.example.eclipsado.view.SecondStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.*;

/**
 * The controller for the second stage of the game. Manages user interactions,
 * including letter validation, word discovery updates, and eclipse state updates.
 * @author Brandon Fernandez
 */
public class SecondStageController {

    @FXML
    private Label InstructionTextTwo; // Instrucciones para el usuario en la segunda ventana
    @FXML
    private TextField DiscoverSecretWord; // Campo de texto donde el usuario ingresa una letra
    @FXML
    private Label DiscoveredWord; // Label que muestra el progreso de la palabra descubierta
    @FXML
    private Button TryButton; // Botón para intentar adivinar la letra
    @FXML
    private Button HelpButton; // Botón para pedir ayuda (revelar una letra aleatoria)
    @FXML
    private ImageView EclipseImage; // ImageView que muestra el estado del eclipse

    private AlertManagerInterface AlertManager = new AlertManagerLogic(); // Interfaz para manejar las alertas

    private String secretWord; // La palabra secreta a ser adivinada
    private char[] displayedWord; // Array que muestra las letras descubiertas
    private int remainingAttempts = 5; // Intentos restantes para adivinar la palabra
    private Set<Character> guessedLetters; // Conjunto de letras ya adivinadas
    private int HelpUsed = 0; // Contador para las ayudas utilizadas
    private final int MaxHelpUses = 3; // Límite máximo de veces que se puede usar la ayuda

    /**
     * Controller initialization method. Prepares the game by setting the secret word and displaying the initial eclipse.
     */
    @FXML
    public void initialize() {
        secretWord = GameModel.getInstance().getSecretWord().toLowerCase(); // Obtiene la palabra secreta desde el modelo del juego
        displayedWord = new char[secretWord.length()]; // Inicializa el array de letras descubiertas
        guessedLetters = new HashSet<>(); // Inicializa el conjunto de letras adivinadas

        // Rellena el array de la palabra mostrada con guiones bajos
        for (int i = 0; i < secretWord.length(); i++) {
            displayedWord[i] = '_';
        }
        updateDisplayedWord(); // Actualiza el label para mostrar la palabra con guiones bajos
        updateEclipseImage(); // Muestra el eclipse inicial basado en los intentos restantes
    }

    /**
     * Method executed when the user clicks the "Try" button.
     * Validates the entered letter and updates the UI based on whether the guess is correct or not.
     * @param actionEvent Event triggered when the button is clicked
     * @throws IOException if there is an error closing the window
     */
    public void OnTryButtonClick(ActionEvent actionEvent) throws IOException {
        String input = DiscoverSecretWord.getText().trim().toLowerCase(); // Obtiene la letra ingresada por el usuario

        // Valida que la entrada sea solo una letra del alfabeto español
        if (input.matches("[a-záéíóúñ]") && input.length() == 1) {
            char guessedLetter = normalizeLetter(input.charAt(0)); // Normaliza las vocales con acento

            // Verifica si la letra ya fue adivinada
            if (guessedLetters.contains(guessedLetter)) {
                AlertManager.showInfoAlert("Letra repetida", "Ya has adivinado esa letra. Intenta con otra.");
            } else {
                guessedLetters.add(guessedLetter); // Añade la letra a las adivinadas

                // Verifica si la letra está en la palabra secreta
                if (isCorrectLetterInWord(guessedLetter)) {
                    // Actualiza la palabra mostrada
                    for (int i = 0; i < secretWord.length(); i++) {
                        if (normalizeLetter(secretWord.charAt(i)) == guessedLetter) {
                            displayedWord[i] = secretWord.charAt(i); // Descubre la letra correcta
                        }
                    }
                    updateDisplayedWord();

                    // Verifica si el jugador ha descubierto toda la palabra
                    if (new String(displayedWord).equals(secretWord)) {
                        AlertManager.showInfoAlert("¡Felicidades!", "Has descubierto la palabra secreta.");
                        SecondStage.getInstance().close(); // Cierra la ventana si se adivina la palabra
                    }
                } else {
                    remainingAttempts--; // Reduce el número de intentos si la letra es incorrecta
                    updateEclipseImage(); // Actualiza la imagen del eclipse según los intentos restantes
                    if (remainingAttempts > 0) {
                        AlertManager.showErrorAlert("Incorrecto", "Letra incorrecta. Intentos restantes: " + remainingAttempts);
                    } else {
                        AlertManager.showInfoAlert("Has perdido", "Se te han acabado los intentos. La palabra era: " + secretWord);
                        SecondStage.getInstance().close(); // Cierra la ventana si no se adivina la palabra
                    }
                }
            }
        } else {
            AlertManager.showWarningAlert("Entrada inválida", "Debes ingresar solo una letra del alfabeto español.");
        }

        DiscoverSecretWord.clear(); // Limpia el campo de texto después de cada intento
    }

    /**
     * Method to check if the guessed letter is correct and present in the secret word.
     * Normalizes both the guessed letter and the secret word's letters to compare them.
     * @param guessedLetter The guessed letter entered by the user
     * @return true if the letter is present in the word, false otherwise
     */
    private boolean isCorrectLetterInWord(char guessedLetter) {
        return secretWord.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(letter -> normalizeLetter(letter) == guessedLetter);
    }

    /**
     * Method that normalizes vowels with accents, converting them to their unaccented equivalents.
     * This allows the game to treat accented and non-accented vowels as equivalent.
     * @param letter The letter entered by the user
     * @return The normalized letter (without accents)
     */
    private char normalizeLetter(char letter) {
        return switch (letter) {
            case 'á', 'a' -> 'a';
            case 'é', 'e' -> 'e';
            case 'í', 'i' -> 'i';
            case 'ó', 'o' -> 'o';
            case 'ú', 'u' -> 'u';
            default -> letter; // Para consonantes u otros caracteres
        };
    }

    /**
     * Updates the Label that displays the progress of the discovered word.
     */
    private void updateDisplayedWord() {
        DiscoveredWord.setText(new String(displayedWord).replace("", " ").trim());
    }

    /**
     * Method executed when the help button is clicked. Reveals a random letter
     * from the secret word, up to a maximum of 3 hints. If the letter revealed
     * is part of the secret word, all occurrences of that letter will be uncovered.
     * @param actionEvent Event triggered when the help button is pressed.
     */
    public void OnHelpButtonClick(ActionEvent actionEvent) throws IOException {
        if (HelpUsed < MaxHelpUses) {
            revealRandomLetter(); // Revela una letra aleatoria de la palabra secreta
            HelpUsed++; // Aumenta el contador de ayudas utilizadas
        } else {
            AlertManager.showInfoAlert("Límite alcanzado", "Ya has usado el máximo número de ayudas.");
        }
    }

    /**
     * Reveals a random letter from the secret word that has not been discovered yet.
     * This method also updates the displayed word and checks if the player has
     * uncovered the entire secret word after the hint is revealed. If the letter revealed
     * is part of the secret word, all occurrences of that letter will be uncovered.
     */
    private void revealRandomLetter() throws IOException {
        List<Integer> unrevealedIndices = new ArrayList<>();

        // Encuentra las letras que aún no se han revelado
        for (int i = 0; i < secretWord.length(); i++) {
            if (displayedWord[i] == '_') {
                unrevealedIndices.add(i);
            }
        }

        if (!unrevealedIndices.isEmpty()) {
            // Selecciona un índice aleatorio de las letras no reveladas
            Random random = new Random();
            int randomIndex = unrevealedIndices.get(random.nextInt(unrevealedIndices.size()));

            // Letra que se va a revelar
            char revealedLetter = secretWord.charAt(randomIndex);

            // Revela todas las ocurrencias de esa letra en la palabra secreta
            for (int i = 0; i < secretWord.length(); i++) {
                if (normalizeLetter(secretWord.charAt(i)) == normalizeLetter(revealedLetter)) {
                    displayedWord[i] = secretWord.charAt(i);
                }
            }

            // Actualiza el progreso de la palabra en pantalla
            updateDisplayedWord();

            // Verifica si el jugador ha descubierto toda la palabra tras la ayuda
            if (new String(displayedWord).equals(secretWord)) {
                AlertManager.showInfoAlert("¡Felicidades!", "Has descubierto la palabra secreta.");
                SecondStage.getInstance().close(); // Cierra la ventana si se adivina la palabra
            }
        }
    }

    /**
     * Updates the eclipse image based on the number of remaining attempts.
     */
    private void updateEclipseImage() {
        String imagePath;
        switch (remainingAttempts) {
            case 5:
                imagePath = "/com/example/eclipsado/Sun/Sun.png"; // Eclipse 0% (sol completo)
                break;
            case 4:
                imagePath = "/com/example/eclipsado/Sun/Eclipse20%.png"; // Eclipse 20%
                break;
            case 3:
                imagePath = "/com/example/eclipsado/Sun/Eclipse40%.png"; // Eclipse 40%
                break;
            case 2:
                imagePath = "/com/example/eclipsado/Sun/Eclipse60%.png"; // Eclipse 60%
                break;
            case 1:
                imagePath = "/com/example/eclipsado/Sun/Eclipse80%.png"; // Eclipse 80%
                break;
            case 0:
                imagePath = "/com/example/eclipsado/Sun/Eclipse100%.png"; // Eclipse completo
                break;
            default:
                imagePath = "/com/example/eclipsado/Sun/Sun.png"; // Imagen predeterminada
                break;
        }

        // Carga la imagen y la muestra en el ImageView
        Image eclipseImage = new Image(getClass().getResourceAsStream(imagePath));
        EclipseImage.setImage(eclipseImage);
    }
}

