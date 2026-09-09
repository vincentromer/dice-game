package se.iths.vincent.dicegame;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Main {
    static void main() {
        // Be användaren mata in två namn och skapa sedan två spelare
        String playerOneFirstName = IO.readln("Spelare ett, vad är ditt förnamn? ").trim();
        String playerOneLastName = IO.readln("Spelare ett, vad är ditt efternamn? ").trim();
        Player playerOne = new Player(playerOneFirstName, playerOneLastName);

        String playerTwoFirstName = IO.readln("Spelare två, vad är ditt förnamn? ").trim();
        String playerTwoLastName = IO.readln("Spelare två, vad är ditt efternamn? ").trim();
        Player playerTwo = new Player(playerTwoFirstName, playerTwoLastName);

        IO.println("Spelare: " + playerOne.getFullName() + " och " + playerTwo.getFullName());

        int numberOfThrows = 2;

        while(true) {
            // Spelare ett kastar tärningen
            IO.println(playerOne.getFullName() + " kastar tärningen.");
            for (int i = 0; i < numberOfThrows; i++) {
                IO.readln("Tryck retur för att slå tärningen"); // Pausa programmet för att låta spelaren slå.
                int number = playerOne.throwDice();
                IO.println("Kast " + (i+1) + ": " + number); // Adderar index med 1 för att inte börja på 'kast 0'
                playerOne.addToScore(number);
            }
            IO.println(playerOne.getFullName() + "'s poäng: " + playerOne.getScore());

            // Spelare två kastar tärningen
            IO.println(playerTwo.getFullName() + " kastar tärningen.");
            for (int i = 0; i < numberOfThrows; i++) {
                IO.readln("Tryck retur för att slå tärningen");
                int number = playerTwo.throwDice();
                IO.println("Kast " + (i+1) + ": " + number);
                playerTwo.addToScore(number);
            }
            IO.println(playerTwo.getFullName() + "'s poäng: " + playerTwo.getScore());

            // Kolla poängen och utse en vinnare
            if (playerOne.getScore() > playerTwo.getScore()) {
                IO.println(playerOne.getFullName() + " vinner!");
            }
            else if(playerOne.getScore() == playerTwo.getScore()) {
                IO.println("Oavgjort!");
            }
            else { // Om poängen inte är lika eller spelare ett inte har vunnit så har spelare två vunnit
                IO.println(playerTwo.getFullName() + " vinner!");
            }


            // Fråga spelaren om dom vill fortsätta
            String playerContinue = IO.readln("Vill ni fortsätta? Y/N").trim();
            if (playerContinue.equalsIgnoreCase("y")) {
                // Nollställer poängen
                playerOne.resetScore();
                playerTwo.resetScore();
                continue; // Onödigt men för tydlighets skull
            }
            else if (playerContinue.equalsIgnoreCase("n")) {
                IO.println("Avslutar spel...");
                break;
            }
            else  {
                IO.println("Ogiltigt svar, fortsätter...");
                playerOne.resetScore();
                playerTwo.resetScore();
                continue;
            }


        }




    }

}
