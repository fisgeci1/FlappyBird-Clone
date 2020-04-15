package Controll;

import Model.Bird;

public class GameRules {

    public static final int WIDTH_OF_PIPE = 60;
    public static final int GAP_BETWEEN_PIPES = 120;
    public static double difficulty = -.33;
    public static double timeOfJump = 0;
    private double gravity = 1;
    private double thrust = -1;
    private Bird bird;


    public double getGravity() {
        return gravity;
    }

    public double getThrust() {
        return thrust;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public static void setDifficulty(double difficulty) {
        GameRules.difficulty = difficulty;
    }
}
