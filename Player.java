import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    // Private member variables for the player's name, list of characters, and the index of the active character
    private String name;
    private List<Character> characters;
    private int activeIndex;

    // Constructor to initialize a Player object with a given name
    public Player(String name) {
        this.name = name;
        this.characters = new ArrayList<>();
        this.activeIndex = 0;  // Default active character index
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for the list of characters
    public List<Character> getCharacters() {
        return characters;
    }

    // Getter method for the active character
    public Character getActiveCharacter() {
        return characters.get(activeIndex);
    }

    // Method for the player to choose characters from a list of available characters
    public void chooseCharacters(List<Character> availableCharacters, Scanner scanner) {
        for (int i = 0; i < 6; i++) {
            displayAvailableCharacters(availableCharacters);  // Display available characters

            int choice;
            while (true) {
                try {
                    System.out.print("\033[1;33m" + name + ", choose character " + (i + 1) + ": \033[0m");
                    choice = Integer.parseInt(scanner.nextLine()) - 1;
                    if (choice >= 0 && choice < availableCharacters.size()) {
                        break;  // Valid choice
                    } else {
                        System.out.println("\033[1;31mInvalid choice. Please choose again.\033[0m");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\033[1;31mInvalid input. Please enter a number.\033[0m");
                }
            }

            // Add chosen character to the player's list and remove from available characters
            characters.add(availableCharacters.remove(choice));
            System.out.println("\033[1;36mYou've chosen " + characters.get(i).getName() + "!\033[0m\n");
        }
    }

    // Method to check if the player has any active (non-defeated) characters
    public boolean hasActiveCharacters() {
        return characters.stream().anyMatch(character -> !character.isDefeated());
    }

    // Method to swap to the next active (non-defeated) character
    public void swapActiveCharacter() {
        for (int i = 0; i < characters.size(); i++) {
            if (!characters.get(i).isDefeated() && i != activeIndex) {
                activeIndex = i;
                System.out.println(name + " swaps to " + characters.get(activeIndex).getName());
                return;
            }
        }
    }

    // Private method to display the list of available characters
    private void displayAvailableCharacters(List<Character> characters) {
        System.out.println("\033[1;36mAvailable characters:\033[0m");
        System.out.println("\033[1;33m╔════════════════════════════════════════════════╗\033[0m");
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            System.out.println("\033[1;33m║ \033[1;32m" + (i + 1) + ". " + character.getName() + "\033[0m");
            System.out.println("\033[1;33m║    Attack: " + character.getAttack() + ", Defense: " + character.getDefense() +
                    ", Speed: " + character.getSpeed() + ", Health: " + character.getMaxHealth() + "\033[0m");
            System.out.println("\033[1;33m║    Power: " + character.getPower() + "\033[0m");
        }
        System.out.println("\033[1;33m╚════════════════════════════════════════════════╝\033[0m");
        System.out.println();
    }
}
