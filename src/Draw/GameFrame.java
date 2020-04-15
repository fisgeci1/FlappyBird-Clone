package Draw;

import Controll.GameRules;
import Draw.GamePanel;
import Model.Bird;
import Model.KeyboardListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {


    public GameFrame(Bird bird, GameRules gameRules) {
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel(bird);
        getContentPane().add(panel);
        addKeyListener(new KeyboardListener(gameRules));
    }

    public void start(){
        this.setVisible(true);
    }

}
