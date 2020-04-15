package Model;

import Controll.GameRules;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bird extends Ellipse2D.Double {

    private double xPos, yPos;
    private int score;
    private GameRules gameRules;
    private boolean gameOver = false;
    private BufferedImage birdTexture;
    private AffineTransform rotationOfBird;
    private double rotationRequired = 0;
    private double angle = 0;


    public Bird(int xPos, int yPos, GameRules gameRules, String imagePath) {
        super(xPos, yPos, 50, 30);
        this.xPos = xPos;
        this.yPos = yPos;
        this.gameRules = gameRules;

        try {
            birdTexture = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graphics g = birdTexture.getGraphics();

        g.drawImage(birdTexture, xPos, yPos, 50, 30, null);


        birdTexture = birdTexture;
        gameRules.setBird(this);
        score = 0;
    }


    public void show(Graphics2D graphics2D) {

        setRotationOfBird();
        graphics2D.setTransform(rotationOfBird);

        graphics2D.drawImage(birdTexture, (int) xPos, (int) yPos, 50, 30, null);
    }

    private void setRotationOfBird() {
        Graphics2D g1 = (Graphics2D) birdTexture.getGraphics();
        rotationOfBird = g1.getTransform();
        rotationOfBird.rotate(rotationRequired, xPos + 25, yPos + 15);
    }

    public void updateBird() {

        if (GameRules.timeOfJump > 0) {
            angle = -45;
            rotationRequired = Math.toRadians(angle);
            if (!checkCellinOrFloor(yPos + gameRules.getThrust())) {
                this.yPos += gameRules.getThrust();
                gameRules.setGravity(-0.1);
                GameRules.timeOfJump -= 0.1;
            } else {
                GameRules.timeOfJump = 0;
            }
        } else {
            if (!checkCellinOrFloor(yPos + gameRules.getGravity()) || gameOver) {
                if (angle < 85) {
                    angle++;
                    rotationRequired = Math.toRadians(angle);
                }
                double grav = (gameRules.getGravity() < 1) ? gameRules.getGravity() + 0.05 : 1;
                gameRules.setGravity(grav);
                if (grav > 0)
                    this.yPos += gameRules.getGravity();
            }
        }

    }


    private boolean checkCellinOrFloor(double potentialY) {
        boolean isCellingOrFloor = false;

        if (potentialY > 550) {
            isCellingOrFloor = true;
        } else if (potentialY < -20) {
            GameRules.timeOfJump = 0;
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

}
