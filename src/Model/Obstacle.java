package Model;

import Controll.GameRules;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Obstacle extends Rectangle2D.Double {

    private double xPos, yPos;
    private double height = 0;

    public Obstacle(double xPos, double yPos, double height) {
        super(xPos, yPos, GameRules.widthOfPipe, height);
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
    }


    public void show(Graphics2D graphics2D) {
        graphics2D.setColor(Color.GREEN);
        graphics2D.fill(new Rectangle2D.Double(xPos, yPos, width, height));
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
