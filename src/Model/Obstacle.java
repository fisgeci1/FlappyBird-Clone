package Model;

import Controll.GameRules;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Obstacle extends Rectangle2D.Double {

    private double xPos, yPos;
    private double height = 0;
    //0 top
    // 1 bottom
    // 2 checkpoint
    private int type = 0;
    private ImageIcon texture;

    public Obstacle(double xPos, double yPos, double height, int type) {
        super(xPos, yPos, GameRules.WIDTH_OF_PIPE, height);
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.type = type;

        switch (type) {
            case 0:
                texture = new ImageIcon("src/top.png");
                break;
            case 1:
                texture = new ImageIcon("src/bottom.png");
                break;
            case 2:
                texture = null;
                break;
        }
    }


    public void show(Graphics2D graphics2D) {

        graphics2D.drawImage(texture.getImage(), (int) xPos, (int) yPos, GameRules.WIDTH_OF_PIPE, (int) height, null);
    }

    public void updateObstacle() {
        xPos += GameRules.difficulty;
    }

    public double getHeightOfObs() {
        return height;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }
}
