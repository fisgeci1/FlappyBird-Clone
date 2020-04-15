import Controll.GameRules;
import Draw.GameFrame;
import Model.Bird;

public class Start {

    public static void main(String[] args) {
        GameRules gameRules = new GameRules();
        Bird bird = new Bird(100, 100, gameRules,"src/flappyBirdModel.png");
        GameFrame frame = new GameFrame(bird, gameRules);
        frame.start();
    }
}
