package Draw;

import Controll.GameRules;
import Model.Bird;
import Model.Obstacle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class GamePanel extends JPanel {

    private int width = 600;
    private int height = 600;
    private Bird bird;
    private ArrayList<ArrayList<Obstacle>> pipes = new ArrayList<>();
    private LinkedList<Obstacle> checkPoints = new LinkedList<>();
    private ImageIcon background = new ImageIcon("src/background.png");


    public GamePanel(Bird bird) {
        this.bird = bird;

        Obstacle top = new Obstacle(600, 0, 200, 0);
        Obstacle bot = new Obstacle(600, 200 + GameRules.GAP_BETWEEN_PIPES, 600 - 200 + GameRules.GAP_BETWEEN_PIPES, 1);
        ArrayList<Obstacle> initial = new ArrayList<>();
        initial.add(top);
        initial.add(bot);
        Obstacle checkPoint = new Obstacle(600, 200, GameRules.GAP_BETWEEN_PIPES, 2);
        checkPoints.add(checkPoint);
        pipes.add(initial);
        generateObstacle(3);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) (g);
        graphics2D.clearRect(0, 0, width, height);

        graphics2D.drawImage(background.getImage(), 0, 0, width, height, this);

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
                            GameRules.WIDTH_OF_PIPE, checkPoints.get(i).getHeightOfObs()))) {
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
        graphics2D.drawString(bird.getScore() + "", 100, 100);

        bird.updateBird();
        bird.show(graphics2D);
        graphics2D.setColor(Color.BLACK);

        repaint();

        freshRate(1000 / 240);

    }

    private void generateObstacle(int numberOfPipes) {

        for (int i = 0; i < numberOfPipes; i++) {
            ArrayList<Obstacle> obstacles = new ArrayList<>();
            double randomCord = Math.random() * height;


            if(randomCord >500){
                randomCord = 490;
            }
            Obstacle topObs = genereatePipe(pipes.get(pipes.size() - 1).get(0).getxPos() + 300, 0, randomCord, 0);
            Obstacle bottomObs = genereatePipe(pipes.get(pipes.size() - 1).get(0).getxPos() + 300, randomCord + GameRules.GAP_BETWEEN_PIPES, 600 - randomCord + GameRules.GAP_BETWEEN_PIPES, 1);


            Obstacle checkPoint = new Obstacle(pipes.get(pipes.size() - 1).get(0).getxPos() + 300, randomCord, GameRules.GAP_BETWEEN_PIPES, 2);
            checkPoints.add(checkPoint);
            obstacles.add(topObs);
            obstacles.add(bottomObs);
            pipes.add(obstacles);

        }
    }

    private void checkIfLost(ArrayList<Obstacle> obstacles) {
        Rectangle2D.Double topObst = new Rectangle2D.Double(obstacles.get(0).getxPos(), obstacles.get(0).getyPos(), GameRules.WIDTH_OF_PIPE, obstacles.get(0).getHeightOfObs());
        Rectangle2D.Double botObst = new Rectangle2D.Double(obstacles.get(1).getxPos(), obstacles.get(1).getyPos(), GameRules.WIDTH_OF_PIPE, obstacles.get(1).getHeightOfObs());

        Rectangle2D.Double birdRect = new Rectangle2D.Double(bird.getxPos(), bird.getyPos(), 50, 30);
        if (birdRect.intersects(topObst) || birdRect.intersects(botObst)) {
            bird.setGameOver(true);
            GameRules.setDifficulty(0);
        }

    }

    private Obstacle genereatePipe(double xPos, double yPos, double height, int type) {
        return new Obstacle(xPos, yPos, height, type);
    }

    private void freshRate(double delay) {
        try {
            Thread.sleep((long) delay);
        } catch (Exception e) {

        }
    }
}
