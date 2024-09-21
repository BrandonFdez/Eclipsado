package com.example.eclipsado.model;

/**
 * This class represents the game's model.
 * Follows the singleton pattern to ensure only one instance of the secret word exists during the game.
 * @author Brandon Fernandez
 */
public class GameModel {
    private static GameModel instance; // Instancia única de la clase
    private String secretWord; // Palabra secreta del juego

    /**
     * Private constructor to prevent the creation of multiple instances of GameModel.
     */
    private GameModel() {}

    /**
     * Returns the single instance of GameModel.
     * @return The single instance of GameModel.
     */
    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel(); // Crea una nueva instancia si no existe
        }
        return instance;
    }

    /**
     * Gets the secret word.
     * @return The current secret word.
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * Sets the secret word.
     * @param secretWord The new secret word.
     */
    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }
}


