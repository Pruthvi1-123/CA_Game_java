public class Character {
    // Private member variables for the character's attributes
    private String name;
    private int attack;
    private int defense;
    private int speed;
    private int maxHealth;
    private int currentHealth;
    private String power;

    // Constructor to initialize a Character object with given attributes
    public Character(String name, int attack, int defense, int speed, int maxHealth, String power) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;  // Set current health to max health initially
        this.power = power;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for attack
    public int getAttack() {
        return attack;
    }

    // Getter method for defense
    public int getDefense() {
        return defense;
    }

    // Getter method for speed
    public int getSpeed() {
        return speed;
    }

    // Getter method for max health
    public int getMaxHealth() {
        return maxHealth;
    }

    // Getter method for current health
    public int getCurrentHealth() {
        return currentHealth;
    }

    // Getter method for power
    public String getPower() {
        return power;
    }

    // Method to check if the character is defeated
    public boolean isDefeated() {
        return currentHealth <= 0;
    }

    // Method to reduce the character's health by a specified damage amount
    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;  // Ensure health doesn't go below 0
        }
    }

    // Method to restore health to the character
    public void restoreHealth(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;  // Ensure health doesn't exceed max health
        }
    }

    // Method for this character to attack another character
    public void attack(Character opponent) {
        int damage = calculateDamage(opponent);  // Calculate the damage to deal
        opponent.takeDamage(damage);  // Deal the damage to the opponent
        System.out.println(name + " attacks " + opponent.getName() + " for " + damage + " damage!");
    }

    // Private method to calculate the damage dealt to an opponent
    private int calculateDamage(Character opponent) {
        return Math.max(0, attack - opponent.defense / 2);  // Calculate damage based on attack and opponent's defense
    }

    // Method for the character to defend (currently only prints a message)
    public void defend() {
        System.out.println(name + " is defending!");
    }

    // Method to inhibit another character by reducing their speed
    public void inhibit(Character opponent) {
        opponent.reduceSpeed();  // Reduce the opponent's speed
        System.out.println(name + " inhibits " + opponent.getName() + ", reducing speed!");
    }

    // Method to reduce the character's speed by 10%
    public void reduceSpeed() {
        speed = (int) (speed * 0.9);  // Reduce speed by 10%
    }

    // Method to use the character's power on an opponent (implementation of power effect needed)
    public void usePower(Character opponent) {
        System.out.println(name + " uses " + power + " on " + opponent.getName() + "!");
        // Implement power-specific effects here
    }
}
