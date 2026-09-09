package se.iths.vincent.dicegame;


public class Game {
    public void start() {
        // Deklarera player-objekten
        Player playerOne;
        Player playerTwo;
        int numberOfThrows = 2; // Mängden kast spelarna har

        // Be användaren mata in två namn och initialisera sedan player-objekten
        while(true) {
            try {
                String playerOneFirstName = IO.readln("Spelare ett, vad är ditt förnamn?\n").trim();
                String playerOneLastName = IO.readln("Spelare ett, vad är ditt efternamn?\n").trim();
                playerOne = new Player(playerOneFirstName, playerOneLastName);

                String playerTwoFirstName = IO.readln("Spelare två, vad är ditt förnamn?\n").trim();
                String playerTwoLastName = IO.readln("Spelare två, vad är ditt efternamn?\n").trim();
                playerTwo = new Player(playerTwoFirstName, playerTwoLastName);
                break;
            }
            catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
                continue;
            }
        }

        IO.println("Spelare: " + playerOne.getFullName() + " och " + playerTwo.getFullName() + "\n");

        // Börja spelet
        while(true) {
            // Spelare ett kastar tärningen
            IO.println(playerOne.getFullName() + " kastar tärningen.\n");
            for (int i = 0; i < numberOfThrows; i++) {
                IO.readln("Tryck retur för att slå tärningen\n"); // Pausa programmet för att låta spelaren slå.
                int number = Dice.throwDice();
                IO.println("Kast " + (i+1) + ": " + number + "\n"); // Adderar index med 1 för att inte börja på 'kast 0'
                playerOne.addToScore(number);
            }
            IO.println(playerOne.getFullName() + "'s poäng: " + playerOne.getScore() + "\n");

            // Spelare två kastar tärningen
            IO.println(playerTwo.getFullName() + " kastar tärningen.\n");
            for (int i = 0; i < numberOfThrows; i++) {
                IO.readln("Tryck retur för att slå tärningen\n");
                int number = Dice.throwDice();
                IO.println("Kast " + (i+1) + ": " + number + "\n");
                playerTwo.addToScore(number);
            }
            IO.println(playerTwo.getFullName() + "'s poäng: " + playerTwo.getScore() + "\n");

            // Kolla poängen och utse en vinnare
            if (playerOne.getScore() > playerTwo.getScore()) {
                IO.println(playerOne.getFullName() + " vinner!" + "\n");
            }
            else if(playerOne.getScore() == playerTwo.getScore()) {
                IO.println("Oavgjort!");
            }
            else { // Om poängen inte är lika eller spelare ett inte har vunnit så har spelare två vunnit
                IO.println(playerTwo.getFullName() + " vinner!");
            }


            // Fråga spelaren om dom vill fortsätta
            String playerContinue = IO.readln("Vill ni fortsätta? Y/N\n").trim();
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
