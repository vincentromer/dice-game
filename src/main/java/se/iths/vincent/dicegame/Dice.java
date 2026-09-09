package se.iths.vincent.dicegame;

import java.util.random.RandomGenerator;

public class Dice {
    public static int throwDice() {
        return RandomGenerator.getDefault().nextInt(1, 7);
    }
}
