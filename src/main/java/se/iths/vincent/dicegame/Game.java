package se.iths.vincent.dicegame;


public class Game {
    public void start() {
        IO.println("Tärningsspel, vänligen mata in namn för två spelare.");
        // Deklarera player-objekten
        Player playerOne;
        Player playerTwo;
        int numberOfThrows = 2; // Mängden kast spelarna har

        // Be användaren mata in två namn och initialisera player-objekten med createPlayer-metoden
        playerOne = createPlayer("ett");
        playerTwo = createPlayer("två");
        IO.println("Spelare: " + playerOne.getFullName() + " och " + playerTwo.getFullName());

        // Börja spelet
        while(true) {
            // Spelare ett kastar tärningen
            playDice(playerOne, numberOfThrows);
            // Spelare två kastar tärningen
            playDice(playerTwo, numberOfThrows);

            // Kolla poängen och utse en vinnare
            IO.println(playerOne.getFullName() + ": " + playerOne.getScore());
            IO.println(playerTwo.getFullName() + ": " + playerTwo.getScore());
            if (playerOne.getScore() > playerTwo.getScore()) {
                IO.println(playerOne.getFullName() + " vinner!");
            }
            else if (playerOne.getScore() == playerTwo.getScore()) {
                IO.println("Oavgjort!");
            }
            else { // Om poängen inte är lika eller spelare ett inte har vunnit så har spelare två vunnit
                IO.println(playerTwo.getFullName() + " vinner!");
            }

            // Fråga användaren om dom vill fortsätta
            String playerContinue = IO.readln("\nVill ni fortsätta? [Y/N] ").trim();
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

    // En metod som ber användaren mata in förnamn och efternamn.
    // Gör så att koden kan återanvändas för spelare ett och två
    private Player createPlayer(String playerNumber) {
        IO.println("Mata in ett namn för spelare " + playerNumber);
        while(true) {
            try {
                String playerFirstName = IO.readln("Förnamn: ").trim();
                String playerLastName = IO.readln("Efternamn: ").trim();
                Player player = new Player(playerFirstName, playerLastName);
                IO.println("Spelare " + playerNumber + ": " + player.getFullName()  + "\n");
                return player;
            }
            catch (IllegalArgumentException e) {
                IO.println(e.getMessage());
                continue;
            }
        }
    }

    // En metod som ber spelarna trycka på retur för att slumpa ett nummer och adderar det sedan till deras poäng.
    // Kan återanvändas för båda spelarna som createPlayer.
    private void playDice(Player player, int numberOfThrows) {
        IO.println(player.getFullName() + " kastar tärningen.\n");
        for (int i = 0; i < numberOfThrows; i++) {
            IO.readln("Tryck retur för att slå tärningen"); // Pausa programmet för att låta spelaren slå.
            int number = Dice.throwDice();
            IO.println("\nKast " + (i+1) + ": " + number + "\n"); // Adderar index med 1 för att inte börja på 'kast 0'
            player.addToScore(number);
        }
       IO.println(player.getFullName() + "'s poäng: " + player.getScore() + "\n");
    }
}
