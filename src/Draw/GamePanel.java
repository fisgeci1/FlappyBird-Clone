package Draw;

import Controll.GameRules;
import Model.Bird;
import Model.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.LinkedList;

public class GamePanel extends JPanel {

    private int width = 600;
    private int height = 600;
    private Bird bird;
    private ArrayList<ArrayList<Obstacle>> pipes = new ArrayList<>();
    private LinkedList<Obstacle> checkPoints = new LinkedList<>();


    public GamePanel(Bird bird) {
        this.bird = bird;

        Obstacle top = new Obstacle(600, 0, 200);
        Obstacle bot = new Obstacle(600, 200 + GameRules.gapBetweenPipes, 600 - 200 + GameRules.gapBetweenPipes);
        ArrayList<Obstacle> initial = new ArrayList<>();
        initial.add(top);
        initial.add(bot);
        Obstacle checkPoint = new Obstacle(600, 200, GameRules.gapBetweenPipes);
        checkPoints.add(checkPoint);
        pipes.add(initial);
        generateObstacle(3);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) (g);
        graphics2D.clearRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        graphics2D.fillRect(0, 0, width, height);


        for (ArrayList<Obstacle> obstacles : pipes) {
            checkIfLost(obstacles);
            for (Obstacle pipe : obstacles) {
                pipe.show(graphics2D);
                pipe.updateObstacle();
            }
        }

        for (int i = 0; i < checkPoints.size(); i++) {
            if (new Rectangle2D.Double(bird.getxPos(), bird.getyPos(), 50, 30).
                    intersects(new Rectangle2D.Double(checkPoints.get(i).getxPos(), checkPoints.get(i).getyPos(),
                            GameRules.widthOfPipe, checkPoints.get(i).getHeightOfObs()))) {
                checkPoints.remove(i);
                bird.setScore(bird.getScore() + 1);
            }
        }

        for (Obstacle checkPoint : checkPoints) {
            checkPoint.updateObstacle();
        }


        if (pipes.get(0).get(0).getxPos() < -20) {
            pipes.remove(0);
            generateObstacle(1);
        }

        bird.updateBird();
        bird.show(graphics2D);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString(bird.getScore() + "", 100, 100);

        repaint();

        freshRate(1000 / 240);

    }

    private void generateObstacle(int numberOfPipes) {

        for (int i = 0; i < numberOfPipes; i++) {
            ArrayList<Obstacle> obstacles = new ArrayList<>();
            double randomCord = Math.random() * height;
            Obstacle topObs = genereatePipe(pipes.get(pipes.size() - 1).get(0).getxPos() + 300, 0, randomCord);
            Obstacle bottomObs = genereatePipe(pipes.get(pipes.size() - 1).get(0).getxPos() + 300, randomCord + GameRules.gapBetweenPipes, 600 - randomCord + GameRules.gapBetweenPipes);


            Obstacle checkPoint = new Obstacle(pipes.get(pipes.size() - 1).get(0).getxPos() + 300, randomCord, GameRules.gapBetweenPipes);
            checkPoints.add(checkPoint);
            obstacles.add(topObs);
            obstacles.add(bottomObs);
            pipes.add(obstacles);

        }
    }

    private void checkIfLost(ArrayList<Obstacle> obstacles) {
        Rectangle2D.Double topObst = new Rectangle2D.Double(obstacles.get(0).getxPos(), obstacles.get(0).getyPos(), GameRules.widthOfPipe, obstacles.get(0).getHeightOfObs());
        Rectangle2D.Double botObst = new Rectangle2D.Double(obstacles.get(1).getxPos(), obstacles.get(1).getyPos(), GameRules.widthOfPipe, obstacles.get(1).getHeightOfObs());

        Rectangle2D.Double birdRect = new Rectangle2D.Double(bird.getxPos(), bird.getyPos(), 50, 30);
        if (birdRect.intersects(topObst) || birdRect.intersects(botObst)) {
            bird.setGameOver(true);
            GameRules.setDifficulty(0);
        }

    }

    private Obstacle genereatePipe(double xPos, double yPos, double height) {
        return new Obstacle(xPos, yPos, height);
    }

    private void freshRate(double delay) {
        try {
            Thread.sleep((long) delay);
        } catch (Exception e) {

        }
    }
}
