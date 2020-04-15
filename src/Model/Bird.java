package Model;

import Controll.GameRules;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Bird extends Ellipse2D.Double {

    private double xPos, yPos;
    private int score;
    private GameRules gameRules;
    private boolean gameOver = false;


    public Bird(int xPos, int yPos, GameRules gameRules) {
        super(xPos, yPos, 50, 30);
        this.xPos = xPos;
        this.yPos = yPos;
        this.gameRules = gameRules;
        gameRules.setBird(this);
        score = 0;
    }


    public void show(Graphics2D graphics2D) {
        graphics2D.setColor(Color.YELLOW);
        graphics2D.fill(new Ellipse2D.Double(xPos, yPos, 50, 30));
    }

    public void updateBird() {

        if (gameRules.getThrust() < 0 && !gameOver) {
            if (!checkCellinOrFloor(yPos + gameRules.getThrust() + 0.07)) {
                gameRules.setThrust(gameRules.getThrust() + 0.07);
                this.yPos += gameRules.getThrust();
            } else {

                gameRules.setThrust(0);
            }
        } else {
            if (!checkCellinOrFloor(yPos + gameRules.getGravity()) || gameOver) {
                this.yPos += gameRules.getGravity();
            }
        }
    }


    private boolean checkCellinOrFloor(double potentialY) {
        boolean isCellingOrFloor = false;

        if (potentialY > 550) {
            isCellingOrFloor = true;
        } else if (potentialY < -20) {
            isCellingOrFloor = true;
        }
        return isCellingOrFloor;
    }

    public int getScore() {
        return score;
    }

    public double getyPos() {
        return yPos;
    }

    public double getxPos() {
        return xPos;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
