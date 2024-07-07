import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Battle {
    // Instance variables for the players, scanner, and round counter
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private int round;

    // Constructor to initialize the battle with two players
    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.scanner = new Scanner(System.in);
        this.round = 0;
    }

    // Method to start the battle
    public void start() {
        // Print start of battle message
        System.out.println("\033[1;35m===============================");
        System.out.println(" Battle starts between ");
        System.out.println(player1.getName() + " and " + player2.getName());
        System.out.println("===============================\033[0m\n");

        // Loop through 12 rounds
        for (round = 1; round <= 12; round++) {
            System.out.println("\033[1;34mPress Enter to start Round " + round + "\033[0m");
            scanner.nextLine();  // Wait for user to press Enter
            playRound();  // Play a round
            printRoundStats();  // Print stats for the round
        }

        printFinalStats();  // Print final stats after all rounds are complete
        scanner.close();  // Close the scanner
    }

    // Method to play a single round
    private void playRound() {
        List<Character> roundCharacters = new ArrayList<>();
        roundCharacters.add(player1.getActiveCharacter());
        roundCharacters.add(player2.getActiveCharacter());
        roundCharacters.sort(Comparator.comparingInt(Character::getSpeed).reversed());  // Sort characters by speed in descending order

        // Loop through characters in order of speed
        for (Character character : roundCharacters) {
            if (character.getCurrentHealth() <= 0) {
                continue;  // Skip turn if character is defeated
            }

            // Determine active player and opponent
            Player activePlayer = (character == player1.getActiveCharacter()) ? player1 : player2;
            Player opponentPlayer = (activePlayer == player1) ? player2 : player1;

            takeTurn(activePlayer, opponentPlayer);  // Take a turn for the active player
        }
    }

    // Method to handle a single turn for a player
    private void takeTurn(Player activePlayer, Player opponentPlayer) {
        Character activeCharacter = activePlayer.getActiveCharacter();
        Character opponentCharacter = opponentPlayer.getActiveCharacter();

        // Print turn prompt for the active player
        System.out.println("\033[1;33m" + activePlayer.getName() + ", it's your turn with " + activeCharacter.getName() + "!\033[0m");
        System.out.println("What action will you take?");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Inhibit");
        System.out.println("4. Swap");

        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());  // Get user input for action choice
                if (choice >= 1 && choice <= 4) {
                    break;  // Valid choice entered
                } else {
                    System.out.println("\033[1;31mInvalid choice. Please choose again.\033[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\033[1;31mInvalid input. Please enter a number.\033[0m");
            }
        }

        // Perform action based on choice
        switch (choice) {
            case 1:
                activeCharacter.attack(opponentCharacter);
                break;
            case 2:
                activeCharacter.defend();
                break;
            case 3:
                activeCharacter.inhibit(opponentCharacter);
                break;
            case 4:
                activePlayer.swapActiveCharacter();
                break;
        }

        // Check if opponent character is defeated
        if (opponentCharacter.isDefeated()) {
            System.out.println("\033[1;31m" + opponentCharacter.getName() + " is defeated!\033[0m");
            opponentPlayer.swapActiveCharacter();  // Swap opponent's character
        }

        System.out.println();
    }

    // Method to print statistics for the current round
    private void printRoundStats() {
        System.out.println("\033[1;34m╔══════════════════════════════╗");
        System.out.println("║         Round " + round + " Stats         ║");
        System.out.println("╚══════════════════════════════╝\033[0m");
        System.out.println("\033[1;33m╔════════════════════════════════════════════════════════════╗\033[0m");
        System.out.printf("\033[1;33m║ %-15s │ %-10s │ %-15s │ %-10s ║\033[0m\n", "Character", "Health", "Attack", "Defense");
        System.out.println("\033[1;33m╠════════════════════════╦═══════════╦═══════════════════╦══════════╣\033[0m");

        // Print stats for player 1's characters
        player1.getCharacters().forEach(character -> {
            System.out.printf("\033[1;33m║ %-20s │ %-9d │ %-17d │ %-8d ║\033[0m\n", character.getName(), character.getCurrentHealth(), character.getAttack(), character.getDefense());
        });

        // Print stats for player 2's characters
        player2.getCharacters().forEach(character -> {
            System.out.printf("\033[1;33m║ %-20s │ %-9d │ %-17d │ %-8d ║\033[0m\n", character.getName(), character.getCurrentHealth(), character.getAttack(), character.getDefense());
        });

        System.out.println("\033[1;33m╚════════════════════════╩═══════════╩═══════════════════╩══════════╝\033[0m");
        System.out.println();
    }

    // Method to print final statistics after all rounds
    private void printFinalStats() {
        System.out.println("\033[1;35m===============================");
        System.out.println(" Final Battle Stats");
        System.out.println("===============================\033[0m");

        // Print final stats for player 1
        System.out.println(player1.getName() + ":");
        player1.getCharacters().forEach(character -> System.out.println(character.getName() + ": Health - " + character.getCurrentHealth() + "/" + character.getMaxHealth()));

        // Print final stats for player 2
        System.out.println(player2.getName() + ":");
        player2.getCharacters().forEach(character -> System.out.println(character.getName() + ": Health - " + character.getCurrentHealth() + "/" + character.getMaxHealth()));

        // Determine and print the winner
        if (player1.hasActiveCharacters()) {
            System.out.println(player1.getName() + " wins!");
        } else {
            System.out.println(player2.getName() + " wins!");
        }

        System.out.println("\033[1;35m===============================\033[0m");
    }
}
