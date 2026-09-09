package se.iths.vincent.dicegame;

public class Player {
    private final String firstName;
    private final String lastName;
    private int score;

    public Player(String firstName, String lastName) {
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("Namnen får inte vara tomma.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public void resetScore() {
        score = 0;
    }
}
