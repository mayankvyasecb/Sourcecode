import java.util.Random;
import java.util.Scanner;

public class SpaceInvaders {

    static char[][] board = new char[10][20]; // 10 rows, 20 columns game board
    static int playerPos = 9; // Player starts at the bottom row
    static int playerCol = 9; // Player starts in the middle
    static boolean gameRunning = true;
    static int lives = 3;
    static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        setupBoard();

        while (gameRunning) {
            printBoard();
            System.out.println("Lives: " + lives + " | Score: " + score);
            System.out.println("Move: (a=left, d=right, space=shoot): ");
            char move = scanner.next().charAt(0);

            if (move == 'a' || move == 'd') {
                movePlayer(move);
            } else if (move == ' ') {
                shoot();
            }

            updateEnemies();
            checkCollisions();
            if (lives <= 0) {
                gameRunning = false;
                System.out.println("Game Over! Final Score: " + score);
            }
        }
    }

    // Set up the initial empty board
    public static void setupBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
        board[playerPos][playerCol] = '^'; // Player spaceship
    }

    // Print the game board
    public static void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    // Move the player left or right
    public static void movePlayer(char direction) {
        board[playerPos][playerCol] = ' '; // Clear previous player position
        if (direction == 'a' && playerCol > 0) {
            playerCol--;
        } else if (direction == 'd' && playerCol < board[0].length - 1) {
            playerCol++;
        }
        board[playerPos][playerCol] = '^'; // Update player position
    }

    // Player shoots upwards
    public static void shoot() {
        int bulletRow = playerPos - 1;
        while (bulletRow >= 0) {
            if (board[bulletRow][playerCol] == 'E') {
                System.out.println("Hit! Enemy destroyed.");
                board[bulletRow][playerCol] = ' ';
                score += 10;
                break;
            }
            bulletRow--;
        }
    }

    // Update enemy positions and spawn new ones randomly
    public static void updateEnemies() {
        Random rand = new Random();
        // Move all enemies down one row
        for (int i = board.length - 2; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'E') {
                    board[i][j] = ' ';
                    if (i + 1 < board.length) {
                        board[i + 1][j] = 'E'; // Move enemy down
                    }
                }
            }
        }
        // Randomly spawn new enemies at the top
        for (int j = 0; j < board[0].length; j++) {
            if (rand.nextInt(10) < 2) { // 20% chance to spawn an enemy
                board[0][j] = 'E';
            }
        }
    }

    // Check for collisions between player and enemies
    public static void checkCollisions() {
        for (int j = 0; j < board[0].length; j++) {
            if (board[playerPos][j] == 'E' && playerCol == j) {
                System.out.println("Collision! You lost a life.");
                lives--;
                board[playerPos][j] = '^'; // Keep player position intact
            }
        }
    }
}
