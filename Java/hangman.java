import java.util.Scanner;

public class Hangman {
    static String[] words = {"java", "programming", "developer", "computer", "algorithm"};
    static String wordToGuess;
    static char[] guessedWord;
    static int attemptsLeft = 6; // Player has 6 attempts to guess the word

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        wordToGuess = words[(int) (Math.random() * words.length)]; // Pick a random word
        guessedWord = new char[wordToGuess.length()];

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_'; // Initialize with underscores
        }

        while (attemptsLeft > 0 && new String(guessedWord).contains("_")) {
            printState();
            System.out.print("Enter a letter: ");
            char guessedLetter = scanner.next().charAt(0);
            boolean correctGuess = false;

            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guessedLetter) {
                    guessedWord[i] = guessedLetter;
                    correctGuess = true;
                }
            }

            if (!correctGuess) {
                attemptsLeft--;
                System.out.println("Incorrect! Attempts left: " + attemptsLeft);
            }
        }

        if (attemptsLeft > 0) {
            System.out.println("Congratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("Game Over! The word was: " + wordToGuess);
        }
    }

    // Prints the current state of the game (word and attempts left)
    public static void printState() {
        System.out.println("Word: " + new String(guessedWord));
    }
}
