import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print ASCII art
        printAsciiArt();

        System.out.println("\n\033[1;35mWelcome to the Ultimate Battle Game!\033[0m\n");
        System.out.println("\033[1;33m╔═════════════════════════════╗");
        System.out.println("║ Player 1, choose your characters ║");
        System.out.println("╚═════════════════════════════╝\033[0m");

        // Character selection for Player 1
        Player player1 = new Player("Player 1");
        player1.chooseCharacters(getAvailableCharacters(), scanner);

        // Character selection for Player 2
        System.out.println("\033[1;33m╔═════════════════════════════╗");
        System.out.println("║ Player 2, choose your characters ║");
        System.out.println("╚═════════════════════════════╝\033[0m");
        Player player2 = new Player("Player 2");
        player2.chooseCharacters(getAvailableCharacters(), scanner);
        System.out.println();

        // Start the battle
        Battle battle = new Battle(player1, player2);
        battle.start();

        scanner.close();
    }

    private static void printAsciiArt() {
        System.out.println("\033[0;36m" +
                " ██████╗ ██╗   ██╗███████╗██████╗ ██╗   ██╗    ██╗    ██╗ ██████╗ ██████╗ ██╗  ██╗\n" +
                "██╔═══██╗██║   ██║██╔════╝██╔══██╗██║   ██║    ██║    ██║██╔═══██╗██╔══██╗╚██╗██╔╝\n" +
                "██║   ██║██║   ██║█████╗  ██████╔╝██║   ██║    ██║ █╗ ██║██║   ██║██████╔╝ ╚███╔╝ \n" +
                "██║▄▄ ██║██║   ██║██╔══╝  ██╔══██╗██║   ██║    ██║███╗██║██║   ██║██╔══██╗ ██╔██╗ \n" +
                "╚██████╔╝╚██████╔╝███████╗██║  ██║╚██████╔╝    ╚███╔███╔╝╚██████╔╝██║  ██║██╔╝ ██╗\n" +
                " ╚══▀▀═╝  ╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝      ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝\n" +
                "                                                                                   \n" +
                "\033[0m");
    }

    // Method to create a list of available characters
    private static List<Character> getAvailableCharacters() {
        return new ArrayList<>(Arrays.asList(
                new Character("Hulk", 10, 5, 7, 100, "Smash"),
                new Character("Batman", 8, 4, 6, 100, "Gadgets"),
                new Character("Superman", 9, 6, 8, 100, "Heat Vision"),
                new Character("Ironman", 7, 5, 7, 100, "Repulsors"),
                new Character("Starlight", 6, 4, 6, 100, "Light Blast"),
                new Character("Thor", 10, 5, 7, 100, "Mjolnir"),
                new Character("Shazam", 10, 5, 7, 100, "Magic"),
                new Character("Naruto", 10, 5, 7, 100, "Ninjutsu"),
                new Character("Goku", 10, 5, 7, 100, "Kamehameha"),
                new Character("Doremon", 10, 5, 7, 100, "Gadgets"),
                new Character("Antman", 10, 5, 7, 100, "Size Manipulation"),
                new Character("Spiderman", 9, 6, 8, 100, "Web-Slinging"),
                new Character("Black Panther", 9, 6, 8, 100, "Vibranium Suit")
        ));
    }
}
