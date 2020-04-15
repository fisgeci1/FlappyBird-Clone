package Controll;

import Model.Bird;

public class GameRules {

    private double gravity = 1;
    private double thrust = 0;
    private Bird bird;
    public static int widthOfPipe = 60;
    public static int gapBetweenPipes = 100;
    public static double difficulty = -.33;


    public double getGravity() {
        return gravity;
    }

    public double getThrust() {
        return thrust;
    }

    public void setThrust(double thrust) {
        this.thrust = thrust;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getWidthOfPipe() {
        return widthOfPipe;
    }

    public static void setDifficulty(double difficulty) {
        GameRules.difficulty = difficulty;
    }
}
