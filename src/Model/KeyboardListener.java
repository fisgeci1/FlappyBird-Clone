package Model;

import Controll.GameRules;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private GameRules gameRules;
    private boolean spaceRelesed = true;

    public KeyboardListener(GameRules gameRules) {
        this.gameRules = gameRules;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (spaceRelesed && e.getKeyCode() == KeyEvent.VK_SPACE) {
            spaceRelesed = false;
            gameRules.setThrust(-2.5);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        spaceRelesed = true;
    }
}
